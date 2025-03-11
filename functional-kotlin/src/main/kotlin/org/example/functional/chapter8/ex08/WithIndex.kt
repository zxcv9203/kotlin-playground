package org.example.functional.chapter8.ex08

fun main() {
    listOf("A", "B", "C", "D")
        .withIndex()
        .filter {(index, value) -> index % 2 == 0 }
        .map { (index, value) -> "[$index] $value" }
        .forEach(::println)
}