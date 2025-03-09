package org.example.functional.chapter7.ex01


fun main() {
    val students = listOf(Student("Alice", 70), Student("Bob", 80))
    var points = 0
    for (student in students) {
        points += student.points
    }
    println(points)
}