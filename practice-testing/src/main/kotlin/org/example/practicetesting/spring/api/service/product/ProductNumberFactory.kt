package org.example.practicetesting.spring.api.service.product

import org.example.practicetesting.spring.domain.product.ProductRepository
import org.springframework.stereotype.Component

@Component
class ProductNumberFactory(
    private val productRepository: ProductRepository,
) {
    fun createNextProductNumber(): String {
        val latestProductNumber =
            productRepository.findLatestProductNumber()
                ?: "000"
        val nextNumber = latestProductNumber.toInt() + 1
        return nextNumber.toString().padStart(3, '0')
    }
}
