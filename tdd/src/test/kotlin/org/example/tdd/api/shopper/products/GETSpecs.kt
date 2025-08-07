package org.example.tdd.api.shopper.products

import org.assertj.core.api.Assertions.assertThat
import org.example.tdd.api.CommerceApiTest
import org.example.tdd.api.TestFixture
import org.example.tdd.result.PageCarrier
import org.example.tdd.view.ProductView
import org.junit.jupiter.api.DisplayName
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.HttpStatus
import org.springframework.http.RequestEntity
import kotlin.test.Test

@CommerceApiTest
@DisplayName("GET /shopper/products")
class GETSpecs {
    @Test
    fun `올바르게 요청하면 200 OK 상태코드를 반환한다`(
        @Autowired fixture: TestFixture,
    ) {
        // Arrange
        fixture.createShopperThenSetAsDefaultUser()

        // Act
        val response =
            fixture.client
                .exchange(
                    RequestEntity.get("/shopper/products").build(),
                    object : ParameterizedTypeReference<PageCarrier<ProductView>>() {},
                )

        // Assert
        assertThat(response.statusCode).isEqualTo(HttpStatus.OK)
    }
}
