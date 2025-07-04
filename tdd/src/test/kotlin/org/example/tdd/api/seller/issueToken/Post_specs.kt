package org.example.tdd.api.seller.issueToken

import org.assertj.core.api.Assertions.assertThat
import org.example.tdd.api.CommerceApiTest
import org.example.tdd.api.JwtAssertions
import org.example.tdd.api.seller.signup.EmailGenerator
import org.example.tdd.api.seller.signup.PasswordGenerator
import org.example.tdd.api.seller.signup.UsernameGenerator
import org.example.tdd.command.CreateSellerCommand
import org.example.tdd.query.IssueSellerToken
import org.example.tdd.result.AccessTokenCarrier
import org.junit.jupiter.api.DisplayName
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.client.postForEntity
import org.springframework.http.HttpStatus
import kotlin.test.Test

@CommerceApiTest
@DisplayName("POST /seller/issueToken")
class PostSpecs {
    @Test
    fun `올바르게 요청하면 200 OK 상태 코드를 반환한다`(
        @Autowired client: TestRestTemplate,
    ) {
        // Arrange
        val email = EmailGenerator.generateEmail()
        val password = PasswordGenerator.generate()

        client.postForEntity<Unit>(
            "/seller/signup",
            CreateSellerCommand(
                email = email,
                username = UsernameGenerator.generate(),
                password = password,
            ),
            Unit::class,
        )
        // Act
        val response =
            client.postForEntity<Unit>(
                "/seller/issueToken",
                IssueSellerToken(
                    email = email,
                    password = password,
                ),
                AccessTokenCarrier::class,
            )

        // Assert
        assertThat(response.statusCode).isEqualTo(HttpStatus.OK)
    }

    @Test
    fun `올바르게 요청하면 접근 토큰을 반환한다`(
        @Autowired client: TestRestTemplate,
    ) {
        // Arrange
        val email = EmailGenerator.generateEmail()
        val password = PasswordGenerator.generate()

        client.postForEntity<Unit>(
            "/seller/signup",
            CreateSellerCommand(
                email = email,
                username = UsernameGenerator.generate(),
                password = password,
            ),
            Unit::class,
        )
        // Act
        val response =
            client.postForEntity<AccessTokenCarrier>(
                "/seller/issueToken",
                IssueSellerToken(
                    email = email,
                    password = password,
                ),
                AccessTokenCarrier::class,
            )
        // Assert
        assertThat(response.body).isNotNull
        assertThat(response.body?.accessToken).isNotNull
    }

    @Test
    fun `접근 토큰은 JWT 형식을 따른다`(
        @Autowired client: TestRestTemplate,
    ) {
        // Arrange
        val email = EmailGenerator.generateEmail()
        val password = PasswordGenerator.generate()

        client.postForEntity<Unit>(
            "/seller/signup",
            CreateSellerCommand(
                email = email,
                username = UsernameGenerator.generate(),
                password = password,
            ),
            Unit::class,
        )
        // Act
        val response =
            client.postForEntity<AccessTokenCarrier>(
                "/seller/issueToken",
                IssueSellerToken(
                    email = email,
                    password = password,
                ),
                AccessTokenCarrier::class,
            )
        // Assert
        val actual = response.body?.accessToken
        assertThat(actual).satisfies(JwtAssertions.conformsToJwtFormat())
    }

    @Test
    fun `존재하지 않는 이메일 주소가 사용되면 400 Bad Request 상태코드를 반환한다`(
        @Autowired client: TestRestTemplate,
    ) {
        // Arrange
        val email = EmailGenerator.generateEmail()
        val password = PasswordGenerator.generate()

        // Act
        val response =
            client.postForEntity<Unit>(
                "/seller/issueToken",
                IssueSellerToken(
                    email = email,
                    password = password,
                ),
                Unit::class,
            )

        // Assert
        assertThat(response.statusCode).isEqualTo(HttpStatus.BAD_REQUEST)
    }

    @Test
    fun `잘못된 비밀번호가 사용되면 400 Bad Request 상태코드를 반환한다`(
        @Autowired client: TestRestTemplate,
    ) {
        // Arrange
        val email = EmailGenerator.generateEmail()
        val password = PasswordGenerator.generate()

        client.postForEntity<Unit>(
            "/seller/signup",
            CreateSellerCommand(
                email = email,
                username = UsernameGenerator.generate(),
                password = password,
            ),
            Unit::class,
        )
        // Act
        val response =
            client.postForEntity<Unit>(
                "/seller/issueToken",
                IssueSellerToken(
                    email = email,
                    password = "wrongPassword",
                ),
                Unit::class,
            )

        // Assert
        assertThat(response.statusCode).isEqualTo(HttpStatus.BAD_REQUEST)
    }
}
