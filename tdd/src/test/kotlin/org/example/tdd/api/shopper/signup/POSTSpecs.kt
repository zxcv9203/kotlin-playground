package org.example.tdd.api.shopper.signup

import org.assertj.core.api.Assertions.assertThat
import org.example.tdd.ShopperRepository
import org.example.tdd.api.CommerceApiTest
import org.example.tdd.api.seller.signup.EmailGenerator
import org.example.tdd.api.seller.signup.PasswordGenerator
import org.example.tdd.api.seller.signup.UsernameGenerator
import org.example.tdd.command.CreateShopperCommand
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import org.junit.jupiter.params.provider.ValueSource
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.client.postForEntity
import org.springframework.http.HttpStatus
import org.springframework.security.crypto.password.PasswordEncoder
import kotlin.test.Test

@CommerceApiTest
@DisplayName("/shopper/signup")
class POSTSpecs {
    @Autowired
    private lateinit var shopperRepository: ShopperRepository

    @Autowired
    private lateinit var passwordEncoder: PasswordEncoder

    @Test
    fun `올바르게 요청하면 204 No Content 상태코드를 반환한다`(
        @Autowired client: TestRestTemplate,
    ) {
        // Arrange
        val command =
            CreateShopperCommand(
                email = EmailGenerator.generateEmail(),
                password = PasswordGenerator.generate(),
                username = UsernameGenerator.generate(),
            )

        // Act
        val response = client.postForEntity<Unit>("/shopper/signup", command, Unit::class)

        // Assert
        assertThat(response.statusCode).isEqualTo(HttpStatus.NO_CONTENT)
    }

    @Test
    fun `email 속성이 지정되지 않으면 400 Bad Request 상태코드를 반환한다`(
        @Autowired client: TestRestTemplate,
    ) {
        // Arrange
        val command =
            CreateShopperCommand(
                email = null,
                password = PasswordGenerator.generate(),
                username = UsernameGenerator.generate(),
            )

        // Act
        val response = client.postForEntity<Unit>("/shopper/signup", command, Unit::class)

        // Assert
        assertThat(response.statusCode).isEqualTo(HttpStatus.BAD_REQUEST)
    }

    @ParameterizedTest
    @ValueSource(
        strings = [
            "invalid-email",
            "invalid-email@",
            "invalid-email@test",
            "invalid@test.",
            "invalid@.com",
        ],
    )
    fun `email 속성이 올바른 형식을 따르지 않으면 400 Bad Request 상태코드를 반환한다`(
        email: String,
        @Autowired client: TestRestTemplate,
    ) {
        // Arrange
        val command =
            CreateShopperCommand(
                email = email,
                password = PasswordGenerator.generate(),
                username = UsernameGenerator.generate(),
            )

        // Act
        val response = client.postForEntity<Unit>("/shopper/signup", command, Unit::class)

        // Assert
        assertThat(response.statusCode).isEqualTo(HttpStatus.BAD_REQUEST)
    }

    @Test
    fun `username 속성이 지정되지 않으면 400 Bad Request 상태코드를 반환한다`(
        @Autowired client: TestRestTemplate,
    ) {
        // Arrange
        val command =
            CreateShopperCommand(
                email = EmailGenerator.generateEmail(),
                password = PasswordGenerator.generate(),
                username = null,
            )

        // Act
        val response = client.postForEntity<Unit>("/shopper/signup", command, Unit::class)

        // Assert
        assertThat(response.statusCode).isEqualTo(HttpStatus.BAD_REQUEST)
    }

    @ParameterizedTest
    @ValueSource(
        strings = [
            "",
            "sh",
            "shopper.",
            "shopper!",
            "shopper@",
        ],
    )
    fun `username 속성이 올바른 형식을 따르지 않으면 400 Bad Request 상태코드를 반환한다`(
        username: String,
        @Autowired client: TestRestTemplate,
    ) {
        // Arrange
        val command =
            CreateShopperCommand(
                email = EmailGenerator.generateEmail(),
                password = PasswordGenerator.generate(),
                username = username,
            )

        // Act
        val response = client.postForEntity<Unit>("/shopper/signup", command, Unit::class)

        // Assert
        assertThat(response.statusCode).isEqualTo(HttpStatus.BAD_REQUEST)
    }

    @ParameterizedTest
    @ValueSource(
        strings = [
            "abcdefghijklmnopqrstuvwxyz",
            "ABCDEFGHIJKLMNOPQRSTUVWXYZ",
            "0123456789",
            "shopper_123",
            "shopper-123",
        ],
    )
    fun `username 속성이 올바른 형식을 따르면 204 No Content 상태코드를 반환한다`(
        username: String,
        @Autowired client: TestRestTemplate,
    ) {
        // Arrange
        val command =
            CreateShopperCommand(
                email = EmailGenerator.generateEmail(),
                password = PasswordGenerator.generate(),
                username = username,
            )

        // Act
        val response = client.postForEntity<Unit>("/shopper/signup", command, Unit::class)

        // Assert
        assertThat(response.statusCode).isEqualTo(HttpStatus.NO_CONTENT)
    }

    @Test
    fun `password 속성이 지정되지 않으면 400 Bad Request 상태코드를 반환한다`(
        @Autowired client: TestRestTemplate,
    ) {
        // Arrange
        val command =
            CreateShopperCommand(
                email = EmailGenerator.generateEmail(),
                password = null,
                username = UsernameGenerator.generate(),
            )

        // Act
        val response = client.postForEntity<Unit>("/shopper/signup", command, Unit::class)

        // Assert
        assertThat(response.statusCode).isEqualTo(HttpStatus.BAD_REQUEST)
    }

    @ParameterizedTest
    @MethodSource("org.example.tdd.TestDataSource#invalidPasswords")
    fun `password 속성이 올바른 형식을 따르지 않으면 400 Bad Request 상태코드를 반환한다`(
        password: String,
        @Autowired client: TestRestTemplate,
    ) {
        // Arrange
        val command =
            CreateShopperCommand(
                email = EmailGenerator.generateEmail(),
                password = password,
                username = UsernameGenerator.generate(),
            )

        // Act
        val response = client.postForEntity<Unit>("/shopper/signup", command, Unit::class)

        // Assert
        assertThat(response.statusCode).isEqualTo(HttpStatus.BAD_REQUEST)
    }

    @Test
    fun `email 속성에 이미 존재하는 이메일 주소가 지정되면 400 Bad Request 상태코드를 반환한다`(
        @Autowired client: TestRestTemplate,
    ) {
        // Arrange
        val email = EmailGenerator.generateEmail()
        val password = PasswordGenerator.generate()
        val username = UsernameGenerator.generate()

        // First signup
        client.postForEntity<Unit>(
            "/shopper/signup",
            CreateShopperCommand(
                email = email,
                password = password,
                username = username,
            ),
            Unit::class,
        )

        // Act
        val response =
            client.postForEntity<Unit>(
                "/shopper/signup",
                CreateShopperCommand(
                    email = email,
                    password = PasswordGenerator.generate(),
                    username = UsernameGenerator.generate(),
                ),
                Unit::class,
            )

        // Assert
        assertThat(response.statusCode).isEqualTo(HttpStatus.BAD_REQUEST)
    }

    @Test
    fun `username 속성에 이미 존재하는 사용자 이름이 지정되면 400 Bad Request 상태코드를 반환한다`(
        @Autowired client: TestRestTemplate,
    ) {
        // Arrange
        val email = EmailGenerator.generateEmail()
        val password = PasswordGenerator.generate()
        val username = UsernameGenerator.generate()

        // First signup
        client.postForEntity<Unit>(
            "/shopper/signup",
            CreateShopperCommand(
                email = email,
                password = password,
                username = username,
            ),
            Unit::class,
        )

        // Act
        val response =
            client.postForEntity<Unit>(
                "/shopper/signup",
                CreateShopperCommand(
                    email = EmailGenerator.generateEmail(),
                    password = PasswordGenerator.generate(),
                    username = username,
                ),
                Unit::class,
            )

        // Assert
        assertThat(response.statusCode).isEqualTo(HttpStatus.BAD_REQUEST)
    }

    @Test
    fun `비밀번호를 올바르게 암호화한다`(
        @Autowired client: TestRestTemplate,
    ) {
        // Arrange
        val email = EmailGenerator.generateEmail()
        val password = PasswordGenerator.generate()
        val username = UsernameGenerator.generate()

        // Act
        val response =
            client.postForEntity<Unit>(
                "/shopper/signup",
                CreateShopperCommand(
                    email = email,
                    password = password,
                    username = username,
                ),
                Unit::class,
            )

        // Assert
        val shopper = (
            shopperRepository
                .findAll()
                .find { x -> x.email == email }
                ?: error("Shopper with email $email not found")
        )
        val actual = shopper.hashedPassword
        assertThat(passwordEncoder.matches(password, actual)).isTrue
    }
}
