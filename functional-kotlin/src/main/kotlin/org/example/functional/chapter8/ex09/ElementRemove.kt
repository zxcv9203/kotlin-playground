package org.example.functional.chapter8.ex09

fun main() {
    val chars = ('a'..'z').toList()

    println(chars.take(10))
    println(chars.takeLast(10))
    println(chars.drop(10))
    println(chars.dropLast(10))
}