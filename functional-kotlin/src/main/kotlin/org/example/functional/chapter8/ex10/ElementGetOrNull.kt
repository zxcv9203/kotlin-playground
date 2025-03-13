package org.example.functional.chapter8.ex10

fun main() {
    val c = listOf<Char>()
    println(c.firstOrNull())
    println(c.lastOrNull())
    println(c.getOrNull(3))
}