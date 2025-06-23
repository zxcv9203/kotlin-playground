package org.example.practicetesting.spring.domain.product

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import kotlin.test.Test

class ProductTypeTest {
    @Test
    @DisplayName("상품 타입이 재고 관련 타입인지를 체크합니다.")
    fun containsStockType() {
        val type = ProductType.BAKERY

        val result = ProductType.containsStockType(type)

        assertThat(result).isTrue
    }

    @ParameterizedTest
    @CsvSource(value = ["HANDMADE,false", "BOTTLE,true", "BAKERY,true"], delimiter = ',')
    @DisplayName("상품 타입이 재고 관련 타입인지 여부를 확인합니다.")
    fun containsStockType2(
        productType: ProductType,
        expected: Boolean,
    ) {
        val result = ProductType.containsStockType(productType)

        assertThat(result).isEqualTo(expected)
    }
}
