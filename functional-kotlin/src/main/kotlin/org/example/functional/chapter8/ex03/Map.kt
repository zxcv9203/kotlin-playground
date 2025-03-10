package org.example.functional.chapter8.ex03

fun main() {
    val old = listOf(1, 2, 3, 4)
    val new = old.map { it * it }
    println(new)
}