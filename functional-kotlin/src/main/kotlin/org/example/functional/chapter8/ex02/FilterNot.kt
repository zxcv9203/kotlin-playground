package org.example.functional.chapter8.ex02

fun main() {
    val old = listOf(1, 2, 3, 4, 5)
    val new = old.filterNot { it % 2 == 0 }
    println(new)
}