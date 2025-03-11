package org.example.functional.chapter8.ex08

fun main() {
    val chars = listOf("A", "B", "C", "D")

    chars
        .withIndex()
        .filter { (index, _ ) -> index % 2 == 0 }
        .map { (index, value) -> "[$index] $value" }
        .forEach(::print)

    chars
        .filterIndexed { index, _ -> index % 2 == 0 }
        .mapIndexed { index, value -> "[$index] $value" }
        .forEach(::print)

}