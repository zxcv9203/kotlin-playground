package org.example.functional.chapter8.ex07

import java.math.BigDecimal

data class Player(
    val name: String,
    val points: Int,
    val money: BigDecimal
)

fun main() {
    val players = listOf(
        Player("Jake", 234, BigDecimal("2.30")),
        Player("Megan", 567, BigDecimal("1.50")),
        Player("Beth", 123, BigDecimal("0.00")),
    )

    println(players.map { it.points }.sum())
    println(players.sumOf { it.points })

}