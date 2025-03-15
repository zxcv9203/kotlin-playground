package org.example.functional.chapter8.ex14

fun main() {
    val nums = listOf(1, 2, 6, 11)
    val partitioned: Pair<List<Int>, List<Int>> = nums.partition { it in 2..10}
    println(partitioned)

    val (inRange, outRange) = partitioned
    println(inRange)
    println(outRange)
}