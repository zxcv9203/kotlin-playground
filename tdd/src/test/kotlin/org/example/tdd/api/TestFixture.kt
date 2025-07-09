package org.example.tdd.api

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
}
