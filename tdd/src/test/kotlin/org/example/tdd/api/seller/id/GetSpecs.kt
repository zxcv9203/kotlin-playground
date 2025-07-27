package org.example.tdd.api.seller.id

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.within
import org.example.tdd.RegisterProductCommandGenerator
import org.example.tdd.api.CommerceApiTest
import org.example.tdd.api.ProductAssertions
import org.example.tdd.api.TestFixture
import org.example.tdd.view.SellerProductView
import org.junit.jupiter.api.DisplayName
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.web.client.getForEntity
import org.springframework.boot.test.web.client.getForObject
import org.springframework.http.HttpStatus
import java.time.LocalDateTime
import java.time.ZoneOffset.UTC
import java.time.temporal.ChronoUnit
import java.util.UUID
import kotlin.test.Test

@CommerceApiTest
@DisplayName("GET /seller/products/{id}")
class GetSpecs {
    @Test
    fun `올바르게 요청하면 200 OK 상태코드를 반환한다`(
        @Autowired fixture: TestFixture,
    ) {
        // Arrange
        fixture.createSellerThenSetAsDefaultUser()
        val id = fixture.registerProduct()

        // Act
        val response =
            fixture.client.getForEntity<SellerProductView>("/seller/products/$id")

        // Assert
        assertThat(response.statusCode).isEqualTo(HttpStatus.OK)
    }

    @Test
    fun `판매자가 아닌 사용자의 접근 토큰을 사용하면 403 Forbidden 상태코드를 반환한다`(
        @Autowired fixture: TestFixture,
    ) {
        // Arrange
        fixture.createSellerThenSetAsDefaultUser()
        val id = fixture.registerProduct()

        fixture.createShopperThenSetAsDefaultUser()

        // Act
        val response =
            fixture.client.getForEntity<SellerProductView>("/seller/products/$id")

        // Assert
        assertThat(response.statusCode).isEqualTo(HttpStatus.FORBIDDEN)
    }

    @Test
    fun `존재하지 않는 상품 식별자를 사용하면 404 Not Found 상태코드를 반환한다`(
        @Autowired fixture: TestFixture,
    ) {
        // Arrange
        fixture.createSellerThenSetAsDefaultUser()
        val id = UUID.randomUUID()

        // Act
        val response =
            fixture.client.getForEntity<SellerProductView>("/seller/products/$id")

        // Assert
        assertThat(response.statusCode).isEqualTo(HttpStatus.NOT_FOUND)
    }

    @Test
    fun `다른 판매자가 등록한 상품 식별자를 사용하면 404 Not Found 상태코드를 반환한다`(
        @Autowired fixture: TestFixture,
    ) {
        // Arrange
        fixture.createSellerThenSetAsDefaultUser()
        val id = fixture.registerProduct()

        fixture.createSellerThenSetAsDefaultUser()

        // Act
        val response =
            fixture.client.getForEntity<SellerProductView>("/seller/products/$id")

        // Assert
        assertThat(response.statusCode).isEqualTo(HttpStatus.NOT_FOUND)
    }

    @Test
    fun `상품 식별자를 올바르게 반환한다`(
        @Autowired fixture: TestFixture,
    ) {
        // Arrange
        fixture.createSellerThenSetAsDefaultUser()
        val id = fixture.registerProduct()

        // Act
        val response =
            fixture.client.getForObject<SellerProductView>("/seller/products/$id")

        // Assert
        assertThat(response).isNotNull
        assertThat(response?.id).isEqualTo(id)
    }

    @Test
    fun `상품 정보를 올바르게 반환한다`(
        @Autowired fixture: TestFixture,
    ) {
        // Arrange
        fixture.createSellerThenSetAsDefaultUser()
        val command = RegisterProductCommandGenerator.generate()
        val id = fixture.registerProduct(command)

        // Act
        val response =
            fixture.client.getForObject<SellerProductView>("/seller/products/$id")!!

        // Assert
        assertThat(response).satisfies(ProductAssertions.isDerivedFrom(command))
    }

    @Test
    fun `상품 등록 시각을 올바르게 반환한다`(
        @Autowired fixture: TestFixture,
    ) {
        // Arrange
        fixture.createSellerThenSetAsDefaultUser()
        val now = LocalDateTime.now(UTC)
        val command = RegisterProductCommandGenerator.generate()
        val id = fixture.registerProduct(command)

        // Act
        val response =
            fixture.client.getForObject<SellerProductView>("/seller/products/$id")!!

        // Assert
        assertThat(response.registeredTimeUtc)
            .isCloseTo(now, within(1, ChronoUnit.SECONDS))
    }
}
