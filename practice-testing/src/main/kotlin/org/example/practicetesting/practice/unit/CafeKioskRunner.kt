package org.example.practicetesting.practice.unit

import org.example.practicetesting.practice.unit.beverage.Americano
import org.example.practicetesting.practice.unit.beverage.Latte

fun main() {
    val cafeKiosk = CafeKiosk()

    cafeKiosk.add(Americano())
    println("아메리카노가 추가되었습니다.")
    cafeKiosk.add(Latte())
    println("라떼가 추가되었습니다.")

    val totalPrice = cafeKiosk.calculateTotalPrice()
    println("총 가격은 $totalPrice 원입니다.")
}
