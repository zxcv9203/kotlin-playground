package org.example.practicetesting.practice.unit

import org.example.practicetesting.practice.unit.beverage.Americano
import kotlin.test.Test

class CafeKioskTest {
    @Test
    fun add() {
        val cafeKiosk = CafeKiosk()

        cafeKiosk.add(Americano())

        println("size :  ${cafeKiosk.beverages.size}")
        println("menu :  ${cafeKiosk.beverages[0].name}")
    }
}
