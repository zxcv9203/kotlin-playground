package org.example.practicetesting.spring.api.controller.product.request

import org.example.practicetesting.spring.domain.product.ProductSellingStatus
import org.example.practicetesting.spring.domain.product.ProductType

data class ProductCreateRequest(
    val type: ProductType,
    val sellingStatus: ProductSellingStatus,
    val name: String,
    val price: Int,
)
