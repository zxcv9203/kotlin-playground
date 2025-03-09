package org.example.functional.chapter7.q1

inline fun <reified T> Iterable<*>.anyOf() = any { it is T }
inline fun <reified T> Iterable<*>.firstOfOrNull() = firstOrNull { it is T }
inline fun <reified T, reified R> Map<*, *>.filterValuesInstanceOf() =
    filter { it.key is T && it.value is R }

fun main() {
    val list = listOf(1, "A", 3, "B")
    println(list.anyOf<String>())
    println(list.anyOf<Int>())
    println(list.anyOf<Double>())

    println(list.firstOfOrNull<String>())
    println(list.firstOfOrNull<Int>())
    println(list.firstOfOrNull<Double>())

    val map = mapOf(1 to 2, 2 to "A", 3 to 4, "B" to "C")
    println(map.filterValuesInstanceOf<Int, String>())
    println(map.filterValuesInstanceOf<String, String>())
    println(map.filterValuesInstanceOf<Int, Int>())
    println(map.filterValuesInstanceOf<String, Int>())
}