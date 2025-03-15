package org.example.functional.chapter8.ex15

data class Player(val name: String, val team: String)

fun main() {
    val players = listOf(
        Player("Alex", "A"),
        Player("Ben", "B"),
        Player("Cal", "A")
    )

    val grouped = players.groupBy { it.team }
    println(grouped)
    println(grouped.flatMap { it.value })
}