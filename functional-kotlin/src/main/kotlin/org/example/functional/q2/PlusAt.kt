package org.example.functional.q2

inline fun <reified T> List<T>.plusAt(index: Int, element: T): List<T> {
    if (index < 0 || index > size) {
        throw IndexOutOfBoundsException("index: $index, size: $size")
    }
    return take(index) + element + drop(index)
}

fun main() {
    val list = listOf(1, 2, 3)

    println(list.plusAt(1, 4))
    println(list.plusAt(0, 5))
    println(list.plusAt(3, 6))
    val list2 = listOf("a", "b", "c")
    println(list2.plusAt(1, "d"))

    println(list.plusAt(3, 4))
}