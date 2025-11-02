package org.example.tdd.api

import org.example.tdd.ProductRepository
import org.example.tdd.RegisterProductCommandGenerator
import org.example.tdd.api.seller.signup.EmailGenerator
import org.example.tdd.api.seller.signup.PasswordGenerator
import org.example.tdd.api.seller.signup.UsernameGenerator
import org.example.tdd.command.CreateSellerCommand
import org.example.tdd.command.CreateShopperCommand
import org.example.tdd.command.RegisterProductCommand
import org.example.tdd.query.IssueSellerToken
import org.example.tdd.query.IssueShopperToken
import org.example.tdd.result.AccessTokenCarrier
import org.example.tdd.result.PageCarrier
import org.example.tdd.view.ProductView
import org.example.tdd.view.SellerMeView
import org.springframework.boot.test.web.client.LocalHostUriTemplateHandler
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.client.exchange
import org.springframework.boot.test.web.client.getForObject
import org.springframework.boot.test.web.client.postForEntity
import org.springframework.boot.test.web.client.postForObject
import org.springframework.core.env.Environment
import org.springframework.http.RequestEntity.get
import org.springframework.http.ResponseEntity
import java.util.UUID

class TestFixture(
    val client: TestRestTemplate,
    val productRepository: ProductRepository,
) {
    companion object {
        fun create(
            environment: Environment,
            productRepository: ProductRepository,
        ): TestFixture {
            val client = TestRestTemplate()
            val handler = LocalHostUriTemplateHandler(environment)
            client.setUriTemplateHandler(handler)

            return TestFixture(client, productRepository)
        }
    }

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
        ensureSuccessful(
            client.postForEntity<Unit>("/shopper/signup", command),
            command,
        )
    }

    private fun ensureSuccessful(
        response: ResponseEntity<Unit>,
        request: Any,
    ) {
        if (!response.statusCode.is2xxSuccessful) {
            val message =
                """
                Request with $request failed with status code ${response.statusCode}
                """.trimIndent()
            throw RuntimeException(message)
        }
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
        val authorization = "Bearer $token"
        setDefaultAuthorization(authorization)
    }

    private fun setDefaultAuthorization(authorization: String) {
        client.restTemplate
            .interceptors
            .addFirst { request, body, execution ->
                if (!request.headers.containsKey("Authorization")) {
                    request.headers.add("Authorization", authorization)
                }
                return@addFirst execution.execute(request, body)
            }
    }

    fun createSellerThenSetAsDefaultUser() {
        val email = EmailGenerator.generateEmail()
        val password = PasswordGenerator.generate()

        createSeller(email, UsernameGenerator.generate(), password)
        setSellerAsDefaultUser(email, password)
    }

    private fun createSeller(
        email: String,
        username: String,
        password: String,
    ) {
        val command =
            CreateSellerCommand(
                email = email,
                username = username,
                password = password,
                contactEmail = EmailGenerator.generateEmail(),
            )
        ensureSuccessful(
            client.postForEntity<Unit>("/seller/signup", command),
            command,
        )
    }

    private fun setSellerAsDefaultUser(
        email: String,
        password: String,
    ) {
        val token = issueSellerToken(email, password)
        setDefaultAuthorization("Bearer $token")
    }

    private fun issueSellerToken(
        email: String,
        password: String,
    ): String {
        val carrier =
            client.postForObject<AccessTokenCarrier>(
                "/seller/issueToken",
                IssueSellerToken(email, password),
            )
        return carrier
            ?.accessToken
            ?: throw IllegalStateException("Access token could not be issued")
    }

    fun createShopperThenSetAsDefaultUser() {
        val email = EmailGenerator.generateEmail()
        val password = PasswordGenerator.generate()
        createShopper(email, UsernameGenerator.generate(), password)
        setShopperAsDefaultUser(email, password)
    }

    fun registerProduct(command: RegisterProductCommand = RegisterProductCommandGenerator.generate()): UUID {
        val response =
            client.postForEntity<Unit>(
                "/seller/products",
                command,
            )
        val location =
            response.headers.location
                ?: throw IllegalStateException("Location header is missing in the response")
        val id = location.path.substring("/seller/products/".length)
        return UUID.fromString(id)
    }

    fun registerProducts(count: Int = 3): List<UUID> =
        (1..count)
            .map { registerProduct() }

    fun deleteAllProducts() {
        productRepository.deleteAllInBatch()
    }

    fun getSeller(): SellerMeView = client.getForObject("/seller/me") ?: throw IllegalStateException("Seller view is null")

    fun consumeProductPage(): String? {
        val response =
            client.exchange<PageCarrier<ProductView>>(
                get("/shopper/products?page=2").build(),
            )
        return response.body!!.continuationToken
    }

    fun consumeTwoProductPages(): String? {
        val token = consumeProductPage()

        val response =
            client.exchange<PageCarrier<ProductView>>(
                get("/shopper/products?continuationToken=$token").build(),
            )

        return response.body!!.continuationToken
    }
}
