package org.example.functional.chapter10.ex01

// 명명된 확장 함수
fun String.myPlus1(other: String) = this + other

fun main() {
    println("A".myPlus1("B"))

    // 변수에 할당된 익명 확장 함수
    val myPlus2 = fun String.(other: String) = this + other
    println(myPlus2.invoke("A", "B"))
    println(myPlus2("A", "B"))
    println("A".myPlus2("B"))
}