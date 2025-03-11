package org.example.functional.chapter8.ex06

fun main() {
    val numbers = listOf(1, 2, 3, 4, 5)
    println(numbers.fold(0) { acc, i -> acc + i })
    println(numbers.reduce { acc, i -> acc + i })

    println(numbers.fold("") { acc, i -> acc + i })
    // 여기서는 reduce 사용 불가능
}