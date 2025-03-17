package org.example.functional.chapter8.ex18

fun main() {
    val names = listOf("Ben", "Bob", "Bass", "Alex")
    val sorted = names.sortedBy { it.first() }
    println(sorted)
}