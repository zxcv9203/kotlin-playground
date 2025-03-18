package org.example.functional.chapter8.ex18

data class FullName(val name: String, val surname: String) {
    override fun toString(): String {
        return "$name $surname"
    }
}

fun main() {
    val names = listOf(
        FullName("B", "B"),
        FullName("B", "A"),
        FullName("A", "A"),
        FullName("A", "B")
    )

    println(names.sortedBy { it.name })
    println(names.sortedBy { it.surname })
    println(names.sortedWith(compareBy({ it.name }, { it.surname })))
}