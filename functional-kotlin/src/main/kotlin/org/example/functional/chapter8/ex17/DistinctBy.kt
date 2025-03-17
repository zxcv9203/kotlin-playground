package org.example.functional.chapter8.ex17

fun main() {
    val names = listOf("Marta", "Maciek", "Marta", "Daniel")
    println(names)
    println(names.distinctBy { it[0] })
    println(names.distinctBy { it.length })
}