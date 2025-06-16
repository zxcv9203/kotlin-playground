package org.example.practicetesting.spring.api.controller.product

import org.example.practicetesting.spring.api.controller.product.request.ProductCreateRequest
import org.example.practicetesting.spring.api.service.product.ProductService
import org.example.practicetesting.spring.api.service.product.response.ProductResponse
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class ProductController(
    private val productService: ProductService,
) {
    @PostMapping("/api/v1/products/new")
    fun createProduct(request: ProductCreateRequest) {
        productService.createProduct(request)
    }

    @GetMapping("/api/v1/products/selling")
    fun getSellingProducts(): List<ProductResponse> = productService.getSellingProducts()
}
