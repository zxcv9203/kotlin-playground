package org.example.functional.chapter8.ex15

fun main() {
    val names = listOf("Marcin", "Maja", "Cookie")

    val byCapital = names.groupBy { it.first() }
    println(byCapital)

    val byLength = names.groupBy { it.length }
    println(byLength)
}