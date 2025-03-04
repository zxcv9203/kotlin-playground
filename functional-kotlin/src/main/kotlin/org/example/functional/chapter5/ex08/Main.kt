package org.example.functional.chapter5.ex08

data class Complex(val real: Double, val imaginary: Double)

fun main() {
    val c1 = Complex(1.0, 2.0)
    val c2 = Complex(3.0, 4.0)

    // 프로퍼티 참조
    val getter: (Complex) -> Double = Complex::real

    println(getter(c1))
    println(getter(c2))

    // 한정된 프로퍼티 참조
    val c1ImgGetter: () -> Double = c1::imaginary
    println(c1ImgGetter())
}