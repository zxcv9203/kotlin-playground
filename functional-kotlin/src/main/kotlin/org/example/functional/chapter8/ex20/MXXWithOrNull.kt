package org.example.functional.chapter8.ex20

data class FullName(val name: String, val surname: String)

fun main() {
    val names = listOf(
        FullName("B", "B"),
        FullName("B", "A"),
        FullName("A", "A")
    )

    println(names.maxWithOrNull(compareBy({ it.name }, { it.surname })))
    println(names.minWithOrNull(compareBy({ it.name }, { it.surname })))
}