package org.example.functional.chapter8.ex16

fun main() {
    val names = listOf("Alex", "Aaron", "Ada")
    println(names.associateBy { it.first() }) // ada 하나만 남음
    println(names.groupBy { it.first() }) // a로 시작하는 이름들이 모두 남음
}