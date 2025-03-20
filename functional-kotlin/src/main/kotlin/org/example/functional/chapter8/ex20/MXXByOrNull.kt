package org.example.functional.chapter8.ex20

data class Player(val name: String, val score: Int)

fun main() {
    val players = listOf(
        Player("Jake", 234),
        Player("Megan", 567),
        Player("Beth", 123),
    )

    println(players.maxByOrNull { it.score })
    println(players.minByOrNull { it.score })
}