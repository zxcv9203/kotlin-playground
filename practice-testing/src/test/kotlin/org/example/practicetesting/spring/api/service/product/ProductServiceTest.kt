package org.example.practicetesting.spring.api.service.product

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.tuple
import org.example.practicetesting.spring.api.controller.product.request.ProductCreateRequest
import org.example.practicetesting.spring.domain.product.Product
import org.example.practicetesting.spring.domain.product.ProductRepository
import org.example.practicetesting.spring.domain.product.ProductSellingStatus
import org.example.practicetesting.spring.domain.product.ProductType
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.DisplayName
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import kotlin.test.Test

@SpringBootTest
@ActiveProfiles("test")
class ProductServiceTest {
    @Autowired
    private lateinit var productService: ProductService

    @Autowired
    private lateinit var productRepository: ProductRepository

    @AfterEach
    fun tearDown() {
        productRepository.deleteAllInBatch()
    }

    @Test
    @DisplayName("신규 상품을 등록한다. 상품번호는 가장 최근 상품의 증가번호에서 1 증가한 값이다.")
    fun test() {
        val product1 = createProduct(ProductType.HANDMADE, "001", 4000, ProductSellingStatus.SELLING, "아메리카노")
        productRepository.saveAll(listOf(product1))
        val request =
            ProductCreateRequest(
                type = ProductType.HANDMADE,
                price = 5000,
                name = "카푸치노",
                sellingStatus = ProductSellingStatus.SELLING,
            )

        val response = productService.createProduct(request)

        assertThat(response)
            .extracting("productNumber", "name", "price", "sellingStatus", "type")
            .contains("002", "카푸치노", 5000, ProductSellingStatus.SELLING, ProductType.HANDMADE)

        assertThat(productRepository.findAll())
            .hasSize(2)
            .extracting("productNumber", "name", "price", "sellingStatus", "type")
            .containsExactlyInAnyOrder(
                tuple("002", "카푸치노", 5000, ProductSellingStatus.SELLING, ProductType.HANDMADE),
                tuple("001", "아메리카노", 4000, ProductSellingStatus.SELLING, ProductType.HANDMADE),
            )
    }

    @Test
    @DisplayName("신규 상품을 등록한다. 상품이 하나도 없는 경우 신규 상품을 등록하면 상품번호는 001이다.")
    fun test2() {
        val request =
            ProductCreateRequest(
                type = ProductType.HANDMADE,
                price = 5000,
                name = "카푸치노",
                sellingStatus = ProductSellingStatus.SELLING,
            )

        val response = productService.createProduct(request)

        assertThat(response)
            .extracting("productNumber", "name", "price", "sellingStatus", "type")
            .contains("001", "카푸치노", 5000, ProductSellingStatus.SELLING, ProductType.HANDMADE)

        assertThat(productRepository.findAll()).hasSize(1)
        assertThat(productRepository.findAll())
            .extracting("productNumber", "name", "price", "sellingStatus", "type")
            .containsExactlyInAnyOrder(
                tuple("001", "카푸치노", 5000, ProductSellingStatus.SELLING, ProductType.HANDMADE),
            )
    }

    private fun createProduct(
        type: ProductType,
        productNumber: String,
        price: Int,
        productSellingStatus: ProductSellingStatus,
        name: String,
    ) = Product(
        type = type,
        productNumber = productNumber,
        price = price,
        sellingStatus = productSellingStatus,
        name = name,
    )
}
