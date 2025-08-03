package org.example.tdd.api.seller.products

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.within
import org.example.tdd.RegisterProductCommandGenerator
import org.example.tdd.api.CommerceApiTest
import org.example.tdd.api.ProductAssertions
import org.example.tdd.api.TestFixture
import org.example.tdd.view.ArrayCarrier
import org.example.tdd.view.SellerProductView
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.HttpStatus
import org.springframework.http.RequestEntity
import java.time.LocalDateTime
import java.time.ZoneOffset.UTC
import java.time.temporal.ChronoUnit

@CommerceApiTest
@DisplayName("GET /seller/products")
class GetSpecs {
    @Test
    fun `올바르게 요청하면 200 OK 상태코드를 반환한다`(
        @Autowired fixture: TestFixture,
    ) {
        // Arrange
        fixture.createSellerThenSetAsDefaultUser()

        // Act
        val response =
            fixture.client.exchange(
                RequestEntity.get("/seller/products").build(),
                object : ParameterizedTypeReference<ArrayCarrier<SellerProductView>>() {},
            )

        // Assert
        assertThat(response.statusCode).isEqualTo(HttpStatus.OK)
    }

    @Test
    fun `판매자가 등록한 모든 상품을 반환한다`(
        @Autowired fixture: TestFixture,
    ) {
        // Arrange
        fixture.createSellerThenSetAsDefaultUser()
        val productIds = fixture.registerProducts()

        // Act
        val response =
            fixture.client.exchange(
                RequestEntity.get("/seller/products").build(),
                object : ParameterizedTypeReference<ArrayCarrier<SellerProductView>>() {},
            )

        // Assert
        val actual = response.body
        assertThat(actual).isNotNull
        val extractProductIds = actual!!.items.map(SellerProductView::id)
        assertThat(extractProductIds)
            .containsAll(productIds)
    }

    @Test
    fun `다른 판매자가 등록한 상품이 포함되지 않는다`(
        @Autowired fixture: TestFixture,
    ) {
        // Arrange
        fixture.createSellerThenSetAsDefaultUser()
        val unexpected = fixture.registerProduct()

        fixture.createSellerThenSetAsDefaultUser()
        fixture.registerProduct()

        // Act
        val response =
            fixture.client.exchange(
                RequestEntity.get("/seller/products").build(),
                object : ParameterizedTypeReference<ArrayCarrier<SellerProductView>>() {},
            )

        // Assert
        val actual = response.body
        assertThat(actual).isNotNull
        assertThat(actual!!.items.map(SellerProductView::id))
            .doesNotContain(unexpected)
    }

    @Test
    fun `상품 정보를 올바르게 반환한다`(
        @Autowired fixture: TestFixture,
    ) {
        // Arrange
        fixture.createSellerThenSetAsDefaultUser()
        val command = RegisterProductCommandGenerator.generate()
        val productId = fixture.registerProduct(command)

        // Act
        val response =
            fixture.client.exchange(
                RequestEntity.get("/seller/products").build(),
                object : ParameterizedTypeReference<ArrayCarrier<SellerProductView>>() {},
            )

        // Assert
        val body =
            response.body
                ?: error("Response body is null")
        val actual = body.items[0]
        assertThat(actual).satisfies(ProductAssertions.isDerivedFrom(command))
    }

    @Test
    fun `상품 등록 시각을 올바르게 반환한다`(
        @Autowired fixture: TestFixture,
    ) {
        // Arrange
        fixture.createSellerThenSetAsDefaultUser()
        val now = LocalDateTime.now(UTC)
        val command = RegisterProductCommandGenerator.generate()
        fixture.registerProduct(command)

        // Act
        val response =
            fixture.client.exchange(
                RequestEntity.get("/seller/products").build(),
                object : ParameterizedTypeReference<ArrayCarrier<SellerProductView>>() {},
            )

        // Assert
        val body =
            response.body
                ?: error("Response body is null")
        val actual = body.items[0]
        assertThat(actual.registeredTimeUtc).isCloseTo(now, within(1, ChronoUnit.SECONDS))
    }
}
