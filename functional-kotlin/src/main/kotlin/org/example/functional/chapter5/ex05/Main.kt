package org.example.functional.chapter5.ex05

data class Complex(
    val real: Double,
    val imaginary: Double
)

fun main() {
    // 생성자 참조
    val produce: (Double, Double) -> Complex = ::Complex
    println(produce(3.0, 4.0))
}