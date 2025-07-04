package org.example.tdd.api.shopper.signup

import org.assertj.core.api.Assertions.assertThat
import org.example.tdd.api.CommerceApiTest
import org.example.tdd.api.seller.signup.EmailGenerator
import org.example.tdd.api.seller.signup.PasswordGenerator
import org.example.tdd.api.seller.signup.UsernameGenerator
import org.example.tdd.command.CreateShopperCommand
import org.junit.jupiter.api.DisplayName
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.client.postForEntity
import org.springframework.http.HttpStatus
import kotlin.test.Test

@CommerceApiTest
@DisplayName("/shopper/signup")
class POSTSpecs {
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
}
