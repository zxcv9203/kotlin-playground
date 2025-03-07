package org.example.functional.chapter6.ex01

fun main() {
    val adapter = ListAdapter()

    val listener = OnSwipeListener { println("onSwipe") }
    adapter.setOnSwipeListener(listener)
    adapter.setOnSwipeListener(fun() { println("onSwipe") })
    adapter.setOnSwipeListener(::someFunction)
}

fun someFunction() {
    println("onSwipe")
}