package org.example.practicetesting.spring.api.controller.order

import jakarta.validation.Valid
import org.example.practicetesting.spring.api.ApiResponse
import org.example.practicetesting.spring.api.controller.order.request.OrderCreateRequest
import org.example.practicetesting.spring.api.service.order.OrderService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDateTime

@RestController
class OrderController(
    private val orderService: OrderService,
) {
    @PostMapping("/api/v1/orders/new")
    fun createOrder(
        @Valid @RequestBody request: OrderCreateRequest,
    ): ApiResponse<Unit> {
        val registeredDateTime = LocalDateTime.now()
        orderService.createOrder(request, registeredDateTime)
        return ApiResponse.ok(Unit)
    }
}
