package org.example.functional.chapter8.ex07

fun main() {
    val numbers = listOf(1, 6, 2, 4, 7, 1)
    println(numbers.sum())

    val doubles = listOf(0.1, 0.6, 0.2, 0.4, 0.7)
    println(doubles.sum())
    // JVM에서 부동소수점 수를 표현하는 데 한계가 있기 때문에
    // 정확히 2가 될 수 없습니다.

    val bytes = listOf<Byte>(1, 4, 2, 4, 5)
    println(bytes.sum()) // 16
}