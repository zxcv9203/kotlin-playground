package org.example.practicetesting.spring.domain.order

import org.assertj.core.api.Assertions.assertThat
import org.example.practicetesting.spring.domain.product.Product
import org.example.practicetesting.spring.domain.product.ProductSellingStatus
import org.example.practicetesting.spring.domain.product.ProductType
import org.junit.jupiter.api.DisplayName
import java.time.LocalDateTime
import kotlin.test.Test

class OrderTest {
    @Test
    @DisplayName("상품 리스트에서 주문의 총 금액을 계산합니다.")
    fun calculateTotalPrice() {
        val registeredDateTime = LocalDateTime.now()

        val products =
            listOf(
                createProduct("001", 1000),
                createProduct("002", 2000),
            )
        val order = Order.create(products, registeredDateTime)

        assertThat(order.totalPrice).isEqualTo(3000)
    }

    @Test
    @DisplayName("주문 생성 시 주문 상태는 INIT이다.")
    fun init() {
        val registeredDateTime = LocalDateTime.now()
        val products = listOf(createProduct("001", 1000), createProduct("002", 2000))

        val order = Order.create(products, registeredDateTime)

        assertThat(order.orderStatus).isEqualTo(OrderStatus.INIT)
    }

    @Test
    @DisplayName("주문 생성 시 주문 등록 시간을 기록한다.")
    fun registeredDateTime() {
        val registeredDateTime = LocalDateTime.now()
        val products = listOf(createProduct("001", 1000), createProduct("002", 2000))

        val order = Order.create(products, registeredDateTime)

        assertThat(order.registeredDateTime).isEqualTo(registeredDateTime)
    }

    private fun createProduct(
        productNumber: String,
        price: Int,
    ) = Product(
        type = ProductType.HANDMADE,
        productNumber = productNumber,
        price = price,
        sellingStatus = ProductSellingStatus.SELLING,
        name = "메뉴 이름",
    )
}
