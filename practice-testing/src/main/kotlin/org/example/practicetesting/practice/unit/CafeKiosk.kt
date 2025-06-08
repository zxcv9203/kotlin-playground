package org.example.practicetesting.practice.unit

import org.example.practicetesting.practice.unit.beverage.Beverage
import org.example.practicetesting.practice.unit.order.Order
import java.time.LocalDateTime

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

    fun createOrder(): Order = Order(LocalDateTime.now(), beverages)
}
