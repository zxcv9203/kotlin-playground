package org.example.practicetesting.practice.unit

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
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
    fun addSeveralBeverages() {
        val cafeKiosk = CafeKiosk()
        val americano = Americano()
        cafeKiosk.add(americano, 2)

        assertThat(cafeKiosk.beverages[0]).isEqualTo(americano)
        assertThat(cafeKiosk.beverages[1]).isEqualTo(americano)
    }

    @Test
    fun addZeroBeverages() {
        val cafeKiosk = CafeKiosk()
        val americano = Americano()

        assertThatThrownBy { cafeKiosk.add(americano, 0) }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("음료는 1잔 이상 주문할 수 없습니다.")
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
