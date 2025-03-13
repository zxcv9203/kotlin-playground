package org.example.functional.chapter8.ex12

fun main() {
    val range = (1..100 step 3)
    println(range.count())
    println(range.count { it % 5 == 0 })
}