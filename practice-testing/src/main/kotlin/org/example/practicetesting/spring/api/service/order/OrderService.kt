package org.example.practicetesting.spring.api.service.order

import org.example.practicetesting.spring.api.controller.order.request.OrderCreateRequest
import org.example.practicetesting.spring.api.service.order.response.OrderResponse
import org.example.practicetesting.spring.domain.order.Order
import org.example.practicetesting.spring.domain.order.OrderRepository
import org.example.practicetesting.spring.domain.product.Product
import org.example.practicetesting.spring.domain.product.ProductRepository
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class OrderService(
    private val productRepository: ProductRepository,
    private val orderRepository: OrderRepository,
) {
    fun createOrder(
        request: OrderCreateRequest,
        registeredDateTime: LocalDateTime,
    ): OrderResponse {
        val productNumbers = request.productNumbers
        val duplicateProducts = findProductsBy(productNumbers)
        val order = Order.create(duplicateProducts, registeredDateTime)
        val savedOrder = orderRepository.save(order)

        return OrderResponse.of(savedOrder)
    }

    private fun findProductsBy(productNumbers: List<String>): List<Product> {
        val products = productRepository.findAllByProductNumberIn(productNumbers)
        val productMap = products.associateBy { it.productNumber }

        val duplicateProducts =
            productNumbers
                .map { it -> productMap[it] ?: throw IllegalArgumentException("상품번호 ${it}에 해당하는 상품이 존재하지 않습니다.") }
                .toList()
        return duplicateProducts
    }
}
