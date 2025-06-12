package org.example.practicetesting.spring.api.service.order.response

import org.example.practicetesting.spring.api.service.product.response.ProductResponse
import org.example.practicetesting.spring.domain.order.Order
import java.time.LocalDateTime

data class OrderResponse(
    val id: Long,
    val totalPrice: Int,
    val registeredDateTime: LocalDateTime,
    val products: List<ProductResponse>,
) {
    companion object {
        fun of(order: Order): OrderResponse =
            OrderResponse(
                id = order.id,
                totalPrice = order.totalPrice,
                registeredDateTime = order.registeredDateTime,
                products = order.orderProducts.map { ProductResponse.of(it.product) },
            )
    }
}
