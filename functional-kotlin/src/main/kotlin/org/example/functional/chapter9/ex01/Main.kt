package org.example.functional.chapter9.ex01

fun main() {
    val seq = sequenceOf(1, 2, 3)
    val filtered = seq.filter { print("f$it"); it % 2 == 1 }
    println(filtered)

    val asList = filtered.toList()

    println(asList)

    println("----")

    val list = listOf(1, 2, 3)
    val listFiltered = list.filter { print("f$it"); it % 2 == 1 }
    println(listFiltered)
}