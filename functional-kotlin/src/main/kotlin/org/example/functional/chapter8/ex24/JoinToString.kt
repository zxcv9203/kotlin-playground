package org.example.functional.chapter8.ex24

fun main() {
    val names = listOf("Maja", "Norbert", "Ola")
    println(names.joinToString())
    println(names.joinToString { it.uppercase() })
    println(names.joinToString(separator = "; "))
    println(names.joinToString(limit = 2))
    println(names.joinToString(limit = 2, truncated = "etc."))
    println(names.joinToString(prefix = "{names=[", postfix = "]}"))
}