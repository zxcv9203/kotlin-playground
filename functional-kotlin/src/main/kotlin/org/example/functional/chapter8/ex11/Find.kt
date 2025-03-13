package org.example.functional.chapter8.ex11

fun main() {
    val names = listOf("Cookie", "Figa")
    println(names.find { it.first() == 'A' })
    println(names.firstOrNull { it.first() == 'A' })
    println(names.find { it.first() == 'C' })
    println(names.firstOrNull { it.first() == 'C' })
}