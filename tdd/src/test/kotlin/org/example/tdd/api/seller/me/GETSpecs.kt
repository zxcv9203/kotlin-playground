package org.example.tdd.api.seller.me

import org.assertj.core.api.Assertions.assertThat
import org.example.tdd.api.CommerceApiTest
import org.example.tdd.api.seller.signup.EmailGenerator
import org.example.tdd.api.seller.signup.PasswordGenerator
import org.example.tdd.api.seller.signup.UsernameGenerator
import org.example.tdd.command.CreateSellerCommand
import org.example.tdd.query.IssueSellerToken
import org.example.tdd.result.AccessTokenCarrier
import org.example.tdd.view.SellerMeView
import org.junit.jupiter.api.DisplayName
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.client.postForEntity
import org.springframework.http.HttpStatus
import org.springframework.http.RequestEntity.get
import kotlin.test.Test

@CommerceApiTest
@DisplayName("GET /seller/me")
class GETSpecs {
    @Test
    fun `올바르게 요청하면 200 OK 상태코드를 반환한다`(
        @Autowired client: TestRestTemplate,
    ) {
        // Arrange
        val email = EmailGenerator.generateEmail()
        val username = UsernameGenerator.generate()
        val password = PasswordGenerator.generate()

        val command =
            CreateSellerCommand(
                email = email,
                username = username,
                password = password,
            )
        client.postForEntity<Unit>("/seller/signup", command)

        val carrier =
            client.postForObject(
                "/seller/issueToken",
                IssueSellerToken(email, password),
                AccessTokenCarrier::class.java,
            )
        val token = carrier.accessToken
        // Act
        val response =
            client.exchange(
                get("/seller/me")
                    .header("Authorization", "Bearer $token")
                    .build(),
                SellerMeView::class.java,
            )
        // Assert
        assertThat(response.statusCode).isEqualTo(HttpStatus.OK)
    }
}
