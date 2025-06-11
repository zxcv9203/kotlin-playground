package org.example.practicetesting.spring.domain.api.controller.product

import org.example.practicetesting.spring.domain.api.service.product.ProductService
import org.example.practicetesting.spring.domain.api.service.product.response.ProductResponse
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class ProductController(
    private val productService: ProductService,
) {
    @GetMapping("/api/v1/products/selling")
    fun getSellingProducts(): List<ProductResponse> = productService.getSellingProducts()
}
