package org.example.practicetesting.spring.api.controller.product.request

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Positive
import org.example.practicetesting.spring.domain.product.ProductSellingStatus
import org.example.practicetesting.spring.domain.product.ProductType

data class ProductCreateRequest(
    @field:NotNull(message = "상품 타입은 필수입니다.")
    val type: ProductType,
    @field:NotNull(message = "상품 판매 상태는 필수입니다.")
    val sellingStatus: ProductSellingStatus,
    @field:NotBlank(message = "상품 이름은 필수입니다.")
    val name: String,
    @field:Positive(message = "상품 가격은 양수여야 합니다.")
    val price: Int,
)
