package org.example.practicetesting.spring.domain.product

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import kotlin.test.Test

class ProductTypeTest {
    @Test
    @DisplayName("상품 타입이 재고 관련 타입인지를 체크합니다.")
    fun containsStockType() {
        val type = ProductType.BAKERY

        val result = ProductType.containsStockType(type)

        assertThat(result).isTrue
    }
}
