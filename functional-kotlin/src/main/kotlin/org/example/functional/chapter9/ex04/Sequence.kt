package org.example.functional.chapter9.ex04

import java.math.BigInteger

val fibonacci: Sequence<BigInteger> = sequence {
    var current = 1.toBigInteger()
    var prev = 0.toBigInteger()
    yield(prev)
    while (true) {
        yield(current)
        val temp = current
        current += prev
        prev = temp
    }
}

fun main() {
    print(fibonacci.take(10).toList())
}