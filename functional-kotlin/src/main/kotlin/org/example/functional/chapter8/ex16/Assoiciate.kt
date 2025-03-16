package org.example.functional.chapter8.ex16

fun main() {
    val names = listOf("Alice", "Ben", "Cal")
    println(names.associate { it.first() to it.drop(1) })

    println(names.associateWith { it.length })
    println(names.associateBy { it.first() })

}