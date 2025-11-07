package org.example.tdd.api.seller.me

import org.assertj.core.api.Assertions.assertThat
import org.example.tdd.api.CommerceApiTest
import org.example.tdd.api.TestFixture
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
import org.springframework.boot.test.web.client.postForObject
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
                contactEmail = EmailGenerator.generateEmail(),
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

    @Test
    fun `접근 토큰을 사용하지 않으면 401 Unauthorized 상태코드를 반환한다`(
        @Autowired client: TestRestTemplate,
    ) {
        // Act
        val response =
            client.exchange(
                get("/seller/me").build(),
                SellerMeView::class.java,
            )
        // Assert
        assertThat(response.statusCode).isEqualTo(HttpStatus.UNAUTHORIZED)
    }

    @Test
    fun `서로 다른 판매자의 식별자는 서로 다르다`(
        @Autowired client: TestRestTemplate,
    ) {
        // Arrange
        val email1 = EmailGenerator.generateEmail()
        val username1 = UsernameGenerator.generate()
        val password1 = PasswordGenerator.generate()

        val command1 =
            CreateSellerCommand(
                email = email1,
                username = username1,
                password = password1,
                contactEmail = EmailGenerator.generateEmail(),
            )
        client.postForEntity<Unit>("/seller/signup", command1)

        val carrier1 =
            client.postForObject(
                "/seller/issueToken",
                IssueSellerToken(email1, password1),
                AccessTokenCarrier::class.java,
            )
        val token1 = carrier1.accessToken

        val email2 = EmailGenerator.generateEmail()
        val username2 = UsernameGenerator.generate()
        val password2 = PasswordGenerator.generate()

        val command2 =
            CreateSellerCommand(
                email = email2,
                username = username2,
                password = password2,
                contactEmail = EmailGenerator.generateEmail(),
            )
        client.postForEntity<Unit>("/seller/signup", command2)

        val carrier2 =
            client.postForObject(
                "/seller/issueToken",
                IssueSellerToken(email2, password2),
                AccessTokenCarrier::class.java,
            )
        val token2 = carrier2.accessToken

        // Act
        val response1 =
            client.exchange(
                get("/seller/me")
                    .header("Authorization", "Bearer $token1")
                    .build(),
                SellerMeView::class.java,
            )

        val response2 =
            client.exchange(
                get("/seller/me")
                    .header("Authorization", "Bearer $token2")
                    .build(),
                SellerMeView::class.java,
            )

        // Assert
        println(response1)
        println(response2)
        assertThat(response1.body).isNotNull
        assertThat(response2.body).isNotNull
        assertThat(response1!!.body!!.id).isNotEqualTo(response2!!.body!!.id)
    }

    @Test
    fun `같은 판매자의 식별자는 항상 같다`(
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
                contactEmail = EmailGenerator.generateEmail(),
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
        val response1 =
            client.exchange(
                get("/seller/me")
                    .header("Authorization", "Bearer $token")
                    .build(),
                SellerMeView::class.java,
            )

        val response2 =
            client.exchange(
                get("/seller/me")
                    .header("Authorization", "Bearer $token")
                    .build(),
                SellerMeView::class.java,
            )

        // Assert
        assertThat(response1.body).isNotNull
        assertThat(response2.body).isNotNull
        assertThat(response1!!.body!!.id).isEqualTo(response2!!.body!!.id)
    }

    @Test
    fun `판매자의 기본 정보가 올바르게 설정된다`(
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
                contactEmail = EmailGenerator.generateEmail(),
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
        assertThat(response.body).isNotNull
        assertThat(response.body!!.email).isEqualTo(email)
        assertThat(response.body!!.username).isEqualTo(username)
    }

    @Test
    fun `문의 이메일 주소를 올바르게 설정한다`(
        @Autowired fixture: TestFixture,
    ) {
        // Arrange
        val email = EmailGenerator.generateEmail()
        val username = UsernameGenerator.generate()
        val password = PasswordGenerator.generate()
        val contactEmail = EmailGenerator.generateEmail()
        fixture.createSeller(email, username, password, contactEmail)

        val carrier =
            fixture.client
                .postForObject<AccessTokenCarrier>(
                    "/seller/issueToken",
                    IssueSellerToken(email, password),
                )
        val token = carrier!!.accessToken

        // Act
        val response =
            fixture.client
                .exchange(
                    get("/seller/me")
                        .header("Authorization", "Bearer $token")
                        .build(),
                    SellerMeView::class.java,
                )

        // Assert
        assertThat(response.body).isNotNull
        assertThat(response.body!!.contactEmail).isEqualTo(contactEmail)
    }
}
