package org.example.functional.chapter5.ex04

data class Number(val num: Int) {
    fun toFloat(): Float = num.toFloat()
    fun times(n: Int): Number = Number(this.num * n)
}

fun main() {
    val num = Number(10)

    // 한정된 함수 참조
    val getNumAsFloat: () -> Float = num::toFloat

    // 객체에 한정하여 참조하기 때문에 함수 타입에 리시버 타입이 필요하지 않습니다.
    println(getNumAsFloat())

    val multiplyNum: (Int) -> Number = num::times
    println(multiplyNum(4))
}