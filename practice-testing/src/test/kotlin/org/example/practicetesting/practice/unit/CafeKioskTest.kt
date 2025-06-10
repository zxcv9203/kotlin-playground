package org.example.practicetesting.practice.unit

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.example.practicetesting.practice.unit.beverage.Americano
import org.example.practicetesting.practice.unit.beverage.Latte
import org.junit.jupiter.api.DisplayName
import java.time.LocalDateTime
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
    @DisplayName("아메리카노를 한잔 추가하면 주문 목록에 아메리카노가 추가 된다.")
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

    @Test
    fun calculateTotalPrice() {
        // given
        val cafeKiosk = CafeKiosk()
        cafeKiosk.add(Americano())
        cafeKiosk.add(Latte())

        // when
        val totalPrice = cafeKiosk.calculateTotalPrice()

        // then
        assertThat(totalPrice).isEqualTo(8500)
    }

    @Test
    fun createOrder() {
        val cafeKiosk = CafeKiosk()
        cafeKiosk.add(Americano())

        val order = cafeKiosk.createOrder()

        assertThat(order.beverages).hasSize(1)
        assertThat(order.beverages[0].name).isEqualTo("아메리카노")
    }

    @Test
    fun createOrderWithTime() {
        val cafeKiosk = CafeKiosk()
        cafeKiosk.add(Americano())

        val order = cafeKiosk.createOrder(LocalDateTime.of(2025, 1, 1, 12, 0))

        assertThat(order.beverages).hasSize(1)
        assertThat(order.beverages[0].name).isEqualTo("아메리카노")
    }

    @Test
    fun createOrderWithOutSideOpenTime() {
        val cafeKiosk = CafeKiosk()
        cafeKiosk.add(Americano())

        assertThatThrownBy { cafeKiosk.createOrder(LocalDateTime.of(2025, 1, 1, 9, 59)) }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("주문 시간이 아닙니다. 관리자에게 문의하세요.")
    }
}
