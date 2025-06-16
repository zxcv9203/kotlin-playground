package org.example.practicetesting.spring.api.service.product

import org.example.practicetesting.spring.api.controller.product.request.ProductCreateRequest
import org.example.practicetesting.spring.api.service.product.response.ProductResponse
import org.example.practicetesting.spring.domain.product.Product
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

    fun createProduct(request: ProductCreateRequest): ProductResponse {
        val nextProductNumber = createNextProductNumber()
        val savedProduct =
            productRepository.save(
                Product(
                    productNumber = nextProductNumber,
                    name = request.name,
                    price = request.price,
                    sellingStatus = request.sellingStatus,
                    type = request.type,
                ),
            )
        return ProductResponse.of(savedProduct)
    }

    private fun createNextProductNumber(): String {
        val latestProductNumber =
            productRepository.findLatestProductNumber()
                ?: "000"
        val nextNumber = latestProductNumber.toInt() + 1
        return nextNumber.toString().padStart(3, '0')
    }
}
