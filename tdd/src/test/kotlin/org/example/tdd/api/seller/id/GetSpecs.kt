package org.example.tdd.api.seller.id

import org.assertj.core.api.Assertions.assertThat
import org.example.tdd.api.CommerceApiTest
import org.example.tdd.api.TestFixture
import org.example.tdd.view.SellerProductView
import org.junit.jupiter.api.DisplayName
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.web.client.getForEntity
import org.springframework.http.HttpStatus
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
}
