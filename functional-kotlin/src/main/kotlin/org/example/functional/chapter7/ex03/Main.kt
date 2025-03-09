package org.example.functional.chapter7.ex03

inline fun repeat(times: Int, action: (Int) -> Unit) {
    for (index in 0 until times) {
        action(index)
    }
}

fun main() {
    repeat(3) { println(it) }
}