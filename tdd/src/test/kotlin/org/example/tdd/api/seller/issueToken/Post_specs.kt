package org.example.tdd.api.seller.issueToken

import org.assertj.core.api.Assertions.assertThat
import org.example.tdd.TddApplication
import org.example.tdd.api.seller.signup.EmailGenerator
import org.example.tdd.api.seller.signup.PasswordGenerator
import org.example.tdd.api.seller.signup.UsernameGenerator
import org.example.tdd.command.CreateSellerCommand
import org.example.tdd.query.IssueSellerToken
import org.example.tdd.result.AccessTokenCarrier
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
}
