package org.example.tdd.api.shopper.issueToken

import org.assertj.core.api.Assertions.assertThat
import org.example.tdd.api.CommerceApiTest
import org.example.tdd.api.JwtAssertions
import org.example.tdd.api.seller.signup.EmailGenerator
import org.example.tdd.api.seller.signup.PasswordGenerator
import org.example.tdd.api.seller.signup.UsernameGenerator
import org.example.tdd.command.CreateShopperCommand
import org.example.tdd.query.IssueShopperToken
import org.example.tdd.result.AccessTokenCarrier
import org.junit.jupiter.api.DisplayName
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.client.postForEntity
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import kotlin.test.Test

@CommerceApiTest
@DisplayName("POST /shopper/issueToken")
class POSTSpecs {
    @Test
    fun `올바르게 요청하면 200 OK 상태코드와 접근 토큰을 반환한다`(
        @Autowired client: TestRestTemplate,
    ) {
        // Arrange
        val email = EmailGenerator.generateEmail()
        val password = PasswordGenerator.generate()

        client.postForEntity<Unit>(
            "/shopper/signup",
            CreateShopperCommand(
                email = email,
                password = password,
                username = UsernameGenerator.generate(),
            ),
        )
        // Act
        val response =
            client.postForEntity<AccessTokenCarrier>(
                "/shopper/issueToken",
                IssueShopperToken(
                    email = email,
                    password = password,
                ),
            )

        // Assert
        assertThat(response.statusCode).isEqualTo(HttpStatus.OK)
        assertThat(response.body).isNotNull
        assertThat(response.body!!.accessToken).isNotBlank
    }

    @Test
    fun `접근 토큰은 JWT 형식을 따른다`(
        @Autowired client: TestRestTemplate,
    ) {
        // Arrange
        val email = EmailGenerator.generateEmail()
        val password = PasswordGenerator.generate()

        client.postForEntity<Unit>(
            "/shopper/signup",
            CreateShopperCommand(
                email = email,
                password = password,
                username = UsernameGenerator.generate(),
            ),
        )
        // Act
        val response =
            client.postForEntity<AccessTokenCarrier>(
                "/shopper/issueToken",
                IssueShopperToken(
                    email = email,
                    password = password,
                ),
            )

        // Assert
        val accessToken = response.body?.accessToken
        assertThat(accessToken).satisfies(JwtAssertions.conformsToJwtFormat())
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
            client.postForEntity<ResponseEntity<AccessTokenCarrier>>(
                "/shopper/issueToken",
                IssueShopperToken(
                    email = email,
                    password = password,
                ),
            )

        // Assert
        assertThat(response.statusCode).isEqualTo(HttpStatus.BAD_REQUEST)
    }
}
