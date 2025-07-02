package org.example.tdd.api.seller.signup

import org.assertj.core.api.Assertions.assertThat
import org.example.tdd.TddApplication
import org.example.tdd.command.CreateSellerCommand
import org.junit.jupiter.api.DisplayName
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.client.postForEntity
import org.springframework.http.HttpStatus
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
                email = "seller@test.com",
                password = "password",
                username = "seller",
            )

        // Act
        val response = client.postForEntity<Unit>("/seller/signup", command, Unit::class)

        // Assert
        assertThat(response.statusCode).isEqualTo(HttpStatus.NO_CONTENT)
    }
}
