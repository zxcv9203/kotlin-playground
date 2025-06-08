package org.example.practicetesting.practice.unit.beverage

import org.assertj.core.api.Assertions.assertThat
import kotlin.test.Test

class AmericanoTest {
    @Test
    fun getName() {
        val americano = Americano()

//        assertEquals(americano.name, "아메리카노")
        assertThat(americano.name).isEqualTo("아메리카노")
    }

    @Test
    fun getPrice() {
        val americano = Americano()

        assertThat(americano.price).isEqualTo(4000)
    }
}
