package org.example.practicetesting.practice.unit

import org.example.practicetesting.practice.unit.beverage.Americano
import org.example.practicetesting.practice.unit.beverage.Latte
import java.time.LocalDateTime

fun main() {
    val cafeKiosk = CafeKiosk()

    cafeKiosk.add(Americano())
    println("아메리카노가 추가되었습니다.")
    cafeKiosk.add(Latte())
    println("라떼가 추가되었습니다.")

    val totalPrice = cafeKiosk.calculateTotalPrice()
    println("총 가격은 $totalPrice 원입니다.")

    val order = cafeKiosk.createOrder(LocalDateTime.now())
    println("주문이 생성되었습니다. 주문 시간: ${order.orderTime}, 주문한 음료: ${order.beverages.joinToString { it.name }}")
}
