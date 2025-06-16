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

    @Test
    @DisplayName("상품번호 리스트로 상품들을 조회한다.")
    fun findAllByProductNumberIn() {
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

        val products = productRepository.findAllByProductNumberIn(listOf("001", "002"))

        assertThat(products)
            .hasSize(2)
            .extracting("productNumber", "name", "sellingStatus")
            .containsExactlyInAnyOrder(
                tuple("001", "아메리카노", ProductSellingStatus.SELLING),
                tuple("002", "카페라떼", ProductSellingStatus.HOLD),
            )
    }

    @Test
    @DisplayName("가장 마지막 상품번호를 조회한다.")
    fun findLatestProductNumber() {
        val want = "003"
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
                productNumber = want,
                type = ProductType.HANDMADE,
                sellingStatus = ProductSellingStatus.STOP_SELLING,
                name = "팥빙수",
                price = 7000,
            )
        productRepository.saveAll(listOf(product, product2, product3))

        val got = productRepository.findLatestProductNumber()

        assertThat(got).isEqualTo(want)
    }

    @Test
    @DisplayName("상품이 하나도 없는 경우에는 null을 반환한다.")
    fun findLatestProductNumberWhenProductIsEmpty() {
        val got = productRepository.findLatestProductNumber()

        assertThat(got).isNull()
    }
}
