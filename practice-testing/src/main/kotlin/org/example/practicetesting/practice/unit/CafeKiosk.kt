package org.example.practicetesting.practice.unit

import org.example.practicetesting.practice.unit.beverage.Beverage
import org.example.practicetesting.practice.unit.order.Order
import java.time.LocalDateTime
import java.time.LocalTime

class CafeKiosk(
    val beverages: MutableList<Beverage> = ArrayList(),
) {
    fun add(beverage: Beverage) {
        beverages.add(beverage)
    }

    fun add(
        beverage: Beverage,
        quantity: Int,
    ) {
        if (quantity <= 0) {
            throw IllegalArgumentException("음료는 1잔 이상 주문할 수 없습니다.")
        }
        repeat(quantity) { beverages.add(beverage) }
    }

    fun remove(beverage: Beverage) {
        beverages.remove(beverage)
    }

    fun clear() {
        beverages.clear()
    }

    fun calculateTotalPrice(): Int = beverages.sumOf { it.price }

    fun createOrder(): Order {
        val now = LocalDateTime.now()
        val currentTime = now.toLocalTime()
        if (currentTime.isBefore(SHOP_OPEN_TIME) || currentTime.isAfter(SHOP_CLOSE_TIME)) {
            throw IllegalArgumentException("주문 시간이 아닙니다. 관리자에게 문의하세요.")
        }
        return Order(LocalDateTime.now(), beverages)
    }

    fun createOrder(currentDateTime: LocalDateTime): Order {
        val currentTime = currentDateTime.toLocalTime()
        if (currentTime.isBefore(SHOP_OPEN_TIME) || currentTime.isAfter(SHOP_CLOSE_TIME)) {
            throw IllegalArgumentException("주문 시간이 아닙니다. 관리자에게 문의하세요.")
        }
        return Order(LocalDateTime.now(), beverages)
    }

    companion object {
        private val SHOP_OPEN_TIME = LocalTime.of(10, 0)
        private val SHOP_CLOSE_TIME = LocalTime.of(22, 0)
    }
}
