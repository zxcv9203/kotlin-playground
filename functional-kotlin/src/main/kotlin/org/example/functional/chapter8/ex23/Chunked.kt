package org.example.functional.chapter8.ex23

fun main() {
    val person = listOf("Ashley", "Barbara", "Cyprian", "David")

    println(person.chunked(1))
    println(person.chunked(2))
    println(person.chunked(3))
    println(person.chunked(4))
}