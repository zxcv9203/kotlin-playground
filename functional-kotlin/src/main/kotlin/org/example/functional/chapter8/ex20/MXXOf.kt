package org.example.functional.chapter8.ex20

fun main() {
    val players = listOf(
        Player("Marta", 100),
        Player("Maciek", 200),
        Player("Marta", 300),
        Player("Daniel", 400)
    )

    println(players.maxOf { it.score })
    println(players.minOf { it.score })
}