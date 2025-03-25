package org.example.functional.chapter9.ex02

fun main() {
    listOf(1, 2, 3)
        .filter { print("F$it, "); it % 2 == 1 }
        .map { it * 2 }
        .forEach { print("M$it, ") }
}