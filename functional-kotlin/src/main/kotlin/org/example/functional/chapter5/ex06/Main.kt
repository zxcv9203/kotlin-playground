package org.example.functional.chapter5.ex06

object Robot {
    fun moveForward() {
        // ...
    }
    fun moveBackword() {
        // ...
    }
}

fun main() {
    Robot.moveForward()
    Robot.moveBackword()

    val moveFunc: () -> Unit = Robot::moveForward
    val moveFunc2: () -> Unit = Robot::moveBackword
}