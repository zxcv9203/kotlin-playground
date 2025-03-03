package org.example.functional.chapter5.ex03

class Box<T>(private val value: T) {
    fun unbox(): T = value
}

fun main() {
    // Box::unbox는 허용되지 않음
    val unbox = Box<String>::unbox
    val box = Box("AAA")

    println(unbox(box))
}