package org.example.functional.chapter5.ex03

class TeamPoints(val points: List<Int>) {
    fun <T> calculatePoints(operations: (List<Int>) -> T): T =
        operations(points)
}

fun main() {
    val teamPoints = TeamPoints(listOf(1, 3, 5))

    val sum = teamPoints.calculatePoints(List<Int>::sum)

    println(sum)

    val avg = teamPoints.calculatePoints(List<Int>::average)
    println(avg)

    val invalid = String?::isNullOrBlank
    println(invalid(""))
    println(invalid(null))
    println(invalid("A"))
}