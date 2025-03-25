package org.example.functional.chapter9.ex02

fun main() {
    for (e in listOf(1, 2, 3)) {
        print("F$e, ")
        if (e % 2 == 1) {
            val r = e * 2
            print("M$r, ")
        }
    }
}