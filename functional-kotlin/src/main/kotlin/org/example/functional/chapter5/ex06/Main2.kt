package org.example.functional.chapter5.ex06

class Drone {
    fun setOff() {
        // ...
    }

    fun land() {
        // ...
    }

    companion object {
        fun makeDrone(): Drone = Drone()
    }
}

fun main() {
    val maker: () -> Drone = Drone.Companion::makeDrone
}