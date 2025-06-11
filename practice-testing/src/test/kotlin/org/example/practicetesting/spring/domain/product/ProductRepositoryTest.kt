package org.example.practicetesting.spring.domain.product

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.tuple
import org.junit.jupiter.api.DisplayName
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.ActiveProfiles
import kotlin.test.Test

@DataJpaTest
@ActiveProfiles("test")
class ProductRepositoryTest {
    @Autowired
    private lateinit var productRepository: ProductRepository

    @Test
    @DisplayName("원하는 판매상태를 가진 상품들을 조회한다.")
    fun findAllBySellingStatusIn() {
        val product =
            Product(
                productNumber = "001",
                type = ProductType.HANDMADE,
                sellingStatus = ProductSellingStatus.SELLING,
                name = "아메리카노",
                price = 4000,
            )
        val product2 =
            Product(
                productNumber = "002",
                type = ProductType.HANDMADE,
                sellingStatus = ProductSellingStatus.HOLD,
                name = "카페라떼",
                price = 4500,
            )
        val product3 =
            Product(
                productNumber = "003",
                type = ProductType.HANDMADE,
                sellingStatus = ProductSellingStatus.STOP_SELLING,
                name = "팥빙수",
                price = 7000,
            )
        productRepository.saveAll(listOf(product, product2, product3))

        val products =
            productRepository.findAllBySellingStatusIn(
                ProductSellingStatus.forDisplay(),
            )

        assertThat(products)
            .hasSize(2)
            .extracting("productNumber", "name", "sellingStatus")
            .containsExactlyInAnyOrder(
                tuple("001", "아메리카노", ProductSellingStatus.SELLING),
                tuple("002", "카페라떼", ProductSellingStatus.HOLD),
            )
    }
}
