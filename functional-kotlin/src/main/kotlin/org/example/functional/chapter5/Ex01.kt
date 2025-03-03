package org.example.functional.chapter5

data class Number(val num: Int) {
    fun toFloat(): Float = num.toFloat()
    fun times(n: Int): Number = Number(num * n)
}

fun main() {
    val numberObject = Number(10)

    // 메서드(멤버 함수) 참조
    val float: (Number) -> Float = Number::toFloat

    // toFloat에는 매개변수가 없지만,
    // 함수 타입에 Number 타입의 리시버가 필요
    println(float(numberObject))

    val multiply: (Number, Int) -> Number = Number::times
    // times에는 매개변수가 Int 타입 하나뿐이지만, 그 함수 타입에는 Number 타입의 리시버가 필요합니다.
    println(multiply(numberObject, 4))
}