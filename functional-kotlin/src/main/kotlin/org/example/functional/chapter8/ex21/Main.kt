package org.example.functional.chapter8.ex21

import kotlin.random.Random

fun main() {
    val range = (1..100)
    val list = range.toList()

    println(list.random())
    println(list.randomOrNull())

    println(list.random(Random(123)))
    println(list.randomOrNull(Random(123)))
    println(range.shuffled())
}