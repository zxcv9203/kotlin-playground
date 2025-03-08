package org.example.functional.chapter7

fun <T, R> Iterable<T>.fold(
    initial: R,
    operation: (acc: R, T) -> R
): R {
    var accumlator = initial
    for (element in this) {
        accumlator = operation(accumlator, element)
    }
    return accumlator
}

class Student(val name: String, val points: Int)

fun main() {
    val students = listOf(Student("AAA", 10), Student("BBB", 20), Student("CCC", 30))
    val points = students.fold(0) { acc, s -> acc + s.points }
    println(points)
}