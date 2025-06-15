package org.example.practicetesting.spring.domain.stock

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.DisplayName
import kotlin.test.Test

class StockTest {
    @Test
    @DisplayName("재고의 수량이 제공된 수량보다 작은지 확인한다.")
    fun isQuantityLessThan() {
        val stock = Stock.create("001", 1)
        val quantity = 2

        val result = stock.isQuantityLessThan(quantity)

        assertThat(result).isTrue
    }

    @Test
    @DisplayName("재고를 주어진 개수만큼 차감할 수 있습니다.")
    fun deductQuantity() {
        val stock = Stock.create("001", 1)
        val quantityToDeduct = 1

        stock.deductQuantity(quantityToDeduct)

        assertThat(stock.quantity).isZero
    }

    @Test
    @DisplayName("재고보다 많은 수의 수량으로 차감 시도하는 경우 예외가 발생한다.")
    fun deductQuantity2() {
        val stock = Stock.create("001", 1)
        val quantityToDeduct = 2

        assertThatThrownBy { stock.deductQuantity(quantityToDeduct) }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("차감할 재고 수량이 없습니다.")
    }
}
