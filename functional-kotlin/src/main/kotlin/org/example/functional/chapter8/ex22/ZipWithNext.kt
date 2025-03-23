package org.example.functional.chapter8.ex22

fun main() {
    println((1..4).zipWithNext())

    val person = listOf(
        "Ashley",
        "Barbara",
        "Cyprian",
    )

    println(person.zipWithNext())

    val person2 = listOf("A", "B", "C", "D", "E")
    println(person2.zipWithNext { prev, next -> "$prev$next" })
}