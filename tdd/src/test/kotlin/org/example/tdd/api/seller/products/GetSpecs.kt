package org.example.tdd.api.seller.products

import org.assertj.core.api.Assertions.assertThat
import org.example.tdd.api.CommerceApiTest
import org.example.tdd.api.TestFixture
import org.example.tdd.view.ArrayCarrier
import org.example.tdd.view.SellerProductView
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.HttpStatus
import org.springframework.http.RequestEntity

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
}
