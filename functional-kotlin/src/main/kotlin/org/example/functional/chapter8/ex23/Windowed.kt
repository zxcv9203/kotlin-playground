package org.example.functional.chapter8.ex23

fun main() {
    val person = listOf(
        "Ashley",
        "Barbara",
        "Cyprian",
        "David"
    )
    // map { listOf(it) } 과 비슷합니다.
    println(person.windowed(size = 1, step = 1))

    // zipWithNext().map { it.toList() } 와 비슷합니다.
    println(person.windowed(size = 2, step = 1))

    println(person.windowed(size = 1, step = 2))

    println(person.windowed(size = 2, step = 2))

    println(person.windowed(size = 3, step = 1))

    println(person.windowed(size = 3, step = 2))

    println(person.windowed(size = 3, step = 1, partialWindows = true))

    println(person.windowed(size = 3, step = 2, partialWindows = true))
}