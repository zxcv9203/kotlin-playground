package org.example.tdd.api.shopper.me

import org.assertj.core.api.Assertions.assertThat
import org.example.tdd.api.CommerceApiTest
import org.example.tdd.api.TestFixture
import org.example.tdd.api.seller.signup.EmailGenerator
import org.example.tdd.api.seller.signup.PasswordGenerator
import org.example.tdd.api.seller.signup.UsernameGenerator
import org.example.tdd.view.ShopperMeView
import org.junit.jupiter.api.DisplayName
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.web.client.exchange
import org.springframework.http.HttpStatus
import org.springframework.http.RequestEntity
import kotlin.test.Test

@CommerceApiTest
@DisplayName("GET /shopper/me")
class GETSpecs {
    @Test
    fun `올바르게 요청하면 200 OK 상태코드를 반환한다`(
        @Autowired fixture: TestFixture,
    ) {
        // Arrange
        val email = EmailGenerator.generateEmail()
        val password = PasswordGenerator.generate()

        fixture.createShopper(email, UsernameGenerator.generate(), password)
        val token = fixture.issueShopperToken(email, password)

        // Act
        val response =
            fixture
                .client
                .exchange<ShopperMeView>(
                    RequestEntity
                        .get("/shopper/me")
                        .header("Authorization", "Bearer $token")
                        .build(),
                )

        // Assert
        assertThat(response.statusCode).isEqualTo(HttpStatus.OK)
    }
}
