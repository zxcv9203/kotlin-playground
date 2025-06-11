package org.example.practicetesting.spring.domain.api.service.product

import org.example.practicetesting.spring.domain.api.service.product.response.ProductResponse
import org.example.practicetesting.spring.domain.product.ProductRepository
import org.example.practicetesting.spring.domain.product.ProductSellingStatus
import org.springframework.stereotype.Service

@Service
class ProductService(
    private val productRepository: ProductRepository,
) {
    fun getSellingProducts(): List<ProductResponse> =
        productRepository
            .findAllBySellingStatusIn(ProductSellingStatus.forDisplay())
            .map { ProductResponse.of(it) }
}
