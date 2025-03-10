package org.example.functional.chapter8.ex04

fun main() {
    val old = listOf("1", "A", "2", "3", "B", "4")
    val new = old.mapNotNull { it.toIntOrNull() }
    println(new)

    val numbers = listOf(-1, 2, -3, 4)
    println(numbers.mapNotNull { prod(it) })
}

fun prod(num: Int): Int? {
    if (num <= 0) return null
    var res = 1
    for (i in 1..num) {
        res *= i
    }
    return res
}