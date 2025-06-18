package org.example.practicetesting.spring.api.controller.product

import jakarta.validation.Valid
import org.example.practicetesting.spring.api.ApiResponse
import org.example.practicetesting.spring.api.controller.product.request.ProductCreateRequest
import org.example.practicetesting.spring.api.service.product.ProductService
import org.example.practicetesting.spring.api.service.product.response.ProductResponse
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class ProductController(
    private val productService: ProductService,
) {
    @PostMapping("/api/v1/products/new")
    fun createProduct(
        @Valid @RequestBody request: ProductCreateRequest,
    ): ApiResponse<ProductResponse> = ApiResponse.ok(productService.createProduct(request))

    @GetMapping("/api/v1/products/selling")
    fun getSellingProducts(): ApiResponse<List<ProductResponse>> = ApiResponse.ok(productService.getSellingProducts())
}
