package org.example.functional.chapter8.ex19

fun main() {
    val list = listOf(4, 2, 3, 1)
    val sortedRes = list.sorted()
    // list.sort() 는 사용불가능합니다.

    val mutableList = mutableListOf(4, 2, 3, 1)
    mutableList.sort()

    println(sortedRes)
    println(mutableList)
}