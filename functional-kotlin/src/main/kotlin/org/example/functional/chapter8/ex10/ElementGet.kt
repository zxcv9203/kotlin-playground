package org.example.functional.chapter8.ex10

fun main() {
    val c = ('a'..'z').toList()
    println(c.first())
    println(c.last())
    println(c.get(3))
    println(c[3])

    val (c1, c2, c3) = c // 구조 분해
    println(c1)
    println(c2)
    println(c3)
}