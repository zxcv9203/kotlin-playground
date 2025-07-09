package org.example.tdd.api

import org.example.tdd.api.seller.signup.EmailGenerator
import org.example.tdd.api.seller.signup.PasswordGenerator
import org.example.tdd.api.seller.signup.UsernameGenerator
import org.example.tdd.command.CreateShopperCommand
import org.example.tdd.query.IssueShopperToken
import org.example.tdd.result.AccessTokenCarrier
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.client.postForEntity
import org.springframework.boot.test.web.client.postForObject

class TestFixture(
    val client: TestRestTemplate,
) {
    fun createShopper(
        email: String,
        username: String,
        password: String,
    ) {
        val command =
            CreateShopperCommand(
                email = email,
                username = username,
                password = password,
            )
        client
            .postForEntity<Unit>("/shopper/signup", command)
    }

    fun issueShopperToken(
        email: String,
        password: String,
    ): String {
        val command =
            IssueShopperToken(
                email = email,
                password = password,
            )

        val carrier =
            client
                .postForObject<AccessTokenCarrier>("/shopper/issueToken", command)

        return carrier
            ?.accessToken
            ?: throw IllegalStateException("Access token could not be issued")
    }

    fun createShopperThenIssueToken(): String {
        val email = EmailGenerator.generateEmail()
        val password = PasswordGenerator.generate()
        val username = UsernameGenerator.generate()
        createShopper(email, username, password)
        return issueShopperToken(email, password)
    }

    fun setShopperAsDefaultUser(
        email: String,
        password: String,
    ) {
        val token = issueShopperToken(email, password)
        client.restTemplate
            .interceptors
            .add { request, body, execution ->
                if (!request.headers.containsKey("Authorization")) {
                    request.headers.add("Authorization", "Bearer $token")
                }
                return@add execution.execute(request, body)
            }
    }
}
