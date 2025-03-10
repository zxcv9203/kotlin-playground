package org.example.functional.chapter8.ex01

data class User(
    val isActive: Boolean,
    val remainingMessage: List<Message>
)

data class Message(
    val isToBeSent: Boolean
)

fun sendMessage(message: Message) {
    println("메세지 전송 $message")
}

fun main() {
    val users = listOf(
        User(true, listOf(Message(true), Message(false))),
        User(false, listOf(Message(true), Message(true)))
    )

    // 변수가 없으면 코드가 읽기 힘들어짐
    val messagesToSend = users.filter { it.isActive }
        .flatMap { it.remainingMessage }
        .filter { it.isToBeSent }
    for (message in messagesToSend) {
        sendMessage(message)
    }

    // forEach를 사용하면 코드가 간결해짐
    users.filter { it.isActive }
        .flatMap { it.remainingMessage }
        .filter { it.isToBeSent }
        .forEach(::sendMessage)

}