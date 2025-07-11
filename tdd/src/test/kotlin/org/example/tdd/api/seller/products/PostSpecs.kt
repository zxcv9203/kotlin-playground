package org.example.tdd.api.seller.products

import org.assertj.core.api.Assertions.assertThat
import org.example.tdd.RegisterProductCommandGenerator
import org.example.tdd.api.CommerceApiTest
import org.example.tdd.api.TestFixture
import org.junit.jupiter.api.DisplayName
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.web.client.postForEntity
import org.springframework.http.HttpStatus
import kotlin.test.Test

@CommerceApiTest
@DisplayName("POST /seller/products")
class PostSpecs {
    @Test
    fun `올바르게 요청하면 201 Created 상태코드를 반환한다`(
        @Autowired fixture: TestFixture,
    ) {
        // Arrange
        fixture.createSellerThenSetAsDefaultUser()

        // Act
        val response =
            fixture.client
                .postForEntity<Unit>(
                    "/seller/products",
                    RegisterProductCommandGenerator.generate(),
                )

        // Assert
        assertThat(response.statusCode).isEqualTo(HttpStatus.CREATED)
    }

    @Test
    fun `판매자가 아닌 사용자의 접근 토큰을 사용하면 403 Forbidden 상태코드를 반환한다`(
        @Autowired fixture: TestFixture,
    ) {
        // Arrange
        fixture.createShopperThenSetAsDefaultUser()

        // Act
        val response =
            fixture.client
                .postForEntity<Unit>(
                    "/seller/products",
                    RegisterProductCommandGenerator.generate(),
                )

        // Assert
        assertThat(response.statusCode).isEqualTo(HttpStatus.FORBIDDEN)
    }
}
