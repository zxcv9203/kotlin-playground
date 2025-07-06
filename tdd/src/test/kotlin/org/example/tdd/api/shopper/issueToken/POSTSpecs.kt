package org.example.tdd.api.shopper.issueToken

import org.assertj.core.api.Assertions.assertThat
import org.example.tdd.api.CommerceApiTest
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
}
