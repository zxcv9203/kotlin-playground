package org.example.practicetesting.spring.api.controller.order.request

import jakarta.validation.constraints.NotEmpty

data class OrderCreateRequest(
    @field:NotEmpty(message = "상품번호는 1개 이상이어야 합니다.")
    val productNumbers: List<String>,
)
