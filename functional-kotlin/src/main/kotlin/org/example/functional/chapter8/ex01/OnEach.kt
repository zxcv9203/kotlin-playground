package org.example.functional.chapter8.ex01

fun main() {
    val users = listOf(
        User(true, listOf(Message(true), Message(false))),
        User(false, listOf(Message(true), Message(true)))
    )

    // onEach
    users.filter { it.isActive }
        .onEach { println("Sending message for user $it") }
        .flatMap { it.remainingMessage }
        .filter { it.isToBeSent }
        .forEach(::sendMessage)
}