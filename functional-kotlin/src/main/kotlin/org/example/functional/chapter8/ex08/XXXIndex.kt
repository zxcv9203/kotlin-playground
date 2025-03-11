package org.example.functional.chapter8.ex08

fun main() {
    val chars = listOf("A", "B", "C", "D")
    val filtered = chars.filterIndexed { index, _ -> index % 2 == 0 }
    println(filtered)
    val mapped = chars.mapIndexed { index, value -> "[$index] $value" }
    println(mapped)
}