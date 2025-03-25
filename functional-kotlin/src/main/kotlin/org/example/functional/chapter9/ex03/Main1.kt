package org.example.functional.chapter9.ex03

fun main() {
    val resI = (1..10).asIterable()
        .map { print("M$it, "); it * it }
        .find { print("F$it "); it > 3 }
    println(resI)

    val resS = (1..10).asSequence()
        .map { print("M$it, "); it * it }
        .find { print("F$it "); it > 3 }
    println(resS)
}