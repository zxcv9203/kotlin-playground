package org.example.practicetesting.spring.api.service.order

import org.example.practicetesting.spring.api.controller.order.request.OrderCreateRequest
import org.example.practicetesting.spring.api.service.order.response.OrderResponse
import org.example.practicetesting.spring.domain.order.Order
import org.example.practicetesting.spring.domain.order.OrderRepository
import org.example.practicetesting.spring.domain.product.Product
import org.example.practicetesting.spring.domain.product.ProductRepository
import org.example.practicetesting.spring.domain.product.ProductType
import org.example.practicetesting.spring.domain.stock.StockRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Service
class OrderService(
    private val productRepository: ProductRepository,
    private val orderRepository: OrderRepository,
    private val stockRepository: StockRepository,
) {
    @Transactional
    fun createOrder(
        request: OrderCreateRequest,
        registeredDateTime: LocalDateTime,
    ): OrderResponse {
        val productNumbers = request.productNumbers
        val duplicateProducts = findProductsBy(productNumbers)

        deductStockQuantities(duplicateProducts)

        val order = Order.create(duplicateProducts, registeredDateTime)
        val savedOrder = orderRepository.save(order)

        return OrderResponse.of(savedOrder)
    }

    private fun deductStockQuantities(products: List<Product>) {
        val stockProductNumbers = extractStockProductNumbers(products)
        val stocks = stockRepository.findAllByProductNumberIn(stockProductNumbers)
        val stockMap = stocks.associateBy { it.productNumber }
        val productCountingMap =
            stockProductNumbers
                .groupingBy { it }
                .eachCount()

        for (stockProductNumber in HashSet(stockProductNumbers)) {
            val stock = stockMap[stockProductNumber]!!
            val quantity = productCountingMap[stockProductNumber] ?: 0
            if (stock.isQuantityLessThan(quantity)) {
                throw IllegalArgumentException("재고가 부족한 상품이 있습니다.")
            }
            stock.deductQuantity(quantity)
        }
    }

    private fun extractStockProductNumbers(products: List<Product>): List<String> {
        val stockProductNumbers =
            products
                .filter { ProductType.containsStockType(it.type) }
                .map { it.productNumber }
        return stockProductNumbers
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
