package org.example.section2

fun startWithA(obj: Any) = when(obj) {
    is String -> obj.startsWith("a", ignoreCase = true)
    else -> false
}