package org.example.functional.chapter5.ex04

object SuperUser {
    fun getId() = 0
}

fun main() {
    val myId = SuperUser::getId
    println(myId())

    val obj = object {
        fun cheer() {
            println("Hello")
        }
    }
    val f = obj::cheer
    f()
}