package org.example.functional.chapter5.ex07

fun foo(i: Int) = 1
fun foo(str: String) = "AAA"

fun main() {
    val f: (Int) -> Int = ::foo
    println(f(3))
    val f2: (String) -> String = ::foo
    println(f2("azaa"))
}