package org.example.functional.chapter8.ex20

fun main() {
    val numbers = listOf(1, 6, 2, 4, 7, 1)
    println(numbers.maxOrNull()) // 7
    println(numbers.minOrNull()) // 1
}