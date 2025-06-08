package org.example.practicetesting.practice.unit

import org.assertj.core.api.Assertions.assertThat
import org.example.practicetesting.practice.unit.beverage.Americano
import org.example.practicetesting.practice.unit.beverage.Latte
import kotlin.test.Test

class CafeKioskTest {
    @Test
    fun add_manual_test() {
        val cafeKiosk = CafeKiosk()

        cafeKiosk.add(Americano())

        println("size :  ${cafeKiosk.beverages.size}")
        println("menu :  ${cafeKiosk.beverages[0].name}")
    }

    @Test
    fun add() {
        val cafeKiosk = CafeKiosk()
        cafeKiosk.add(Americano())

        assertThat(cafeKiosk.beverages).hasSize(1)
        assertThat(cafeKiosk.beverages[0].name).isEqualTo("아메리카노")
    }

    @Test
    fun remove() {
        val cafeKiosk = CafeKiosk()
        val americano = Americano()

        cafeKiosk.add(americano)
        assertThat(cafeKiosk.beverages).hasSize(1)

        cafeKiosk.remove(americano)
        assertThat(cafeKiosk.beverages).isEmpty()
    }

    @Test
    fun clear() {
        val cafeKiosk = CafeKiosk()
        cafeKiosk.add(Americano())
        cafeKiosk.add(Latte())
        assertThat(cafeKiosk.beverages).hasSize(2)

        cafeKiosk.clear()
        assertThat(cafeKiosk.beverages).isEmpty()
    }
}
