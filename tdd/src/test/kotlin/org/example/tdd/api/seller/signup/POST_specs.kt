package org.example.tdd.api.seller.signup

import org.assertj.core.api.Assertions.assertThat
import org.example.tdd.TddApplication
import org.example.tdd.command.CreateSellerCommand
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.client.postForEntity
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import kotlin.test.Test

@SpringBootTest(
    classes = [TddApplication::class],
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
)
@DisplayName("POST /seller/signup")
class PostSpecs {
    @Test
    fun `올바르게 요청하면 204 No Content 상태 코드를 반환한다`(
        @Autowired client: TestRestTemplate,
    ) {
        // Arrange
        val command =
            CreateSellerCommand(
                email = EmailGenerator.generateEmail(),
                password = "password",
                username = "seller",
            )

        // Act
        val response = client.postForEntity<Unit>("/seller/signup", command, Unit::class)

        // Assert
        assertThat(response.statusCode).isEqualTo(HttpStatus.NO_CONTENT)
    }

    @Test
    fun `email 속성이 지정되지 않으면 400 Bad Request 상태 코드를 반환한다`(
        @Autowired client: TestRestTemplate,
    ) {
        // Arrange
        val command =
            """
            {
                "password": "password",
                "username": "seller"
            }
            """.trimIndent()
        val headers =
            HttpHeaders()
                .apply { contentType = MediaType.APPLICATION_JSON }
        val request = HttpEntity(command, headers)

        // Act
        val response = client.postForEntity<Unit>("/seller/signup", request, Unit::class)

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
    fun `email 속성이 올바른 형식을 따르지 않으면 400 Bad Request 상태 코드르 반환한다`(
        email: String,
        @Autowired client: TestRestTemplate,
    ) {
        // Arrange
        val command =
            CreateSellerCommand(
                email = email,
                password = "password",
                username = "seller",
            )

        // Act
        val response = client.postForEntity<Unit>("/seller/signup", command, Unit::class)

        // Assert
        assertThat(response.statusCode).isEqualTo(HttpStatus.BAD_REQUEST)
    }

    @Test
    fun `username 속성이 지정되지 않으면 400 Bad Request 상태 코드를 반환한다`(
        @Autowired client: TestRestTemplate,
    ) {
        // Arrange
        val command =
            """
            {
                "email": ${EmailGenerator.generateEmail()},
                "password": "password"
            }
            """.trimIndent()

        val headers =
            HttpHeaders()
                .apply { contentType = MediaType.APPLICATION_JSON }
        val request = HttpEntity(command, headers)

        // Act
        val response = client.postForEntity<Unit>("/seller/signup", request, Unit::class)

        // Assert
        assertThat(response.statusCode).isEqualTo(HttpStatus.BAD_REQUEST)
    }

    @ParameterizedTest
    @ValueSource(
        strings = [
            "",
            "se",
            "seller ",
            "seller.",
            "seller@",
        ],
    )
    fun `username 속성이 올바른 형식을 따르지 않으면 400 Bad Request 상태 코드를 반환한다`(
        username: String,
        @Autowired client: TestRestTemplate,
    ) {
        // Arrange
        val command =
            CreateSellerCommand(
                email = EmailGenerator.generateEmail(),
                password = "password",
                username = username,
            )

        // Act
        val response = client.postForEntity<Unit>("/seller/signup", command, Unit::class)

        // Assert
        assertThat(response.statusCode).isEqualTo(HttpStatus.BAD_REQUEST)
    }

    @ParameterizedTest
    @ValueSource(
        strings = [
            "seller",
            "ABCDEFGHIJKLMNOPQRSTUVWXYZ",
            "0123456789",
            "seller_",
            "seller-",
        ],
    )
    fun `username 속성이 올바른 형식을 따르면 204 No Content 상태 코드를 반환한다`(
        username: String,
        @Autowired client: TestRestTemplate,
    ) {
        // Arrange
        val command =
            CreateSellerCommand(
                email = EmailGenerator.generateEmail(),
                password = "password",
                username = username,
            )

        // Act
        val response = client.postForEntity<Unit>("/seller/signup", command, Unit::class)

        // Assert
        assertThat(response.statusCode).isEqualTo(HttpStatus.NO_CONTENT)
    }

    @Test
    fun `password 속성이 지정되지 않으면 400 Bad Request 상태 코드를 반환한다`(
        @Autowired client: TestRestTemplate,
    ) {
        // Arrange
        val command =
            """
            {
                "email": ${EmailGenerator.generateEmail()},
                "username": "seller"
            }
            """.trimIndent()
        val headers =
            HttpHeaders()
                .apply { contentType = MediaType.APPLICATION_JSON }
        val request = HttpEntity(command, headers)
        // Act
        val response = client.postForEntity<Unit>("/seller/signup", request, Unit::class)
        // Assert
        assertThat(response.statusCode).isEqualTo(HttpStatus.BAD_REQUEST)
    }

    @ParameterizedTest
    @ValueSource(
        strings = [
            "",
            "pass",
            "1234567",
        ],
    )
    fun `password 속성이 올바른 형식을 따르지 않으면 400 Bad Request 상태 코드를 반환한다`(
        password: String,
        @Autowired client: TestRestTemplate,
    ) {
        // Arrange
        val command =
            CreateSellerCommand(
                email = EmailGenerator.generateEmail(),
                password = password,
                username = "seller",
            )
        // Act
        val response = client.postForEntity<Unit>("/seller/signup", command, Unit::class)
        // Assert
        assertThat(response.statusCode).isEqualTo(HttpStatus.BAD_REQUEST)
    }

    @Test
    fun `email 속성에 이미 중복되는 이메일 주소가 지정되면 400 Bad Request 상태 코드를 반환한다`(
        @Autowired client: TestRestTemplate,
    ) {
        // Arrange
        val email = "seller@test.com"
        client.postForEntity<Unit>(
            "/seller/signup",
            CreateSellerCommand(
                email = email,
                password = "password",
                username = "seller1",
            ),
            Unit::class,
        )

        // Act
        val response =
            client.postForEntity<Unit>(
                "/seller/signup",
                CreateSellerCommand(
                    email = email,
                    password = "password",
                    username = "seller2",
                ),
                Unit::class,
            )

        // Assert
        assertThat(response.statusCode).isEqualTo(HttpStatus.BAD_REQUEST)
    }
}
