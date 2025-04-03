package org.example.functional.chapter11.ex01

fun main() {
    println(listOf("a", "b", "c").map { it.uppercase() })
    println("a".let { it.uppercase() })
}