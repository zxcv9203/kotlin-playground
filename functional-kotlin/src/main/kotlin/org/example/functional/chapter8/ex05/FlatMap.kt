package org.example.functional.chapter8.ex05

fun main() {
    val old = listOf(1, 2, 3)
    val new = old.flatMap { listOf(it, it + 10) }
    println(new)
}