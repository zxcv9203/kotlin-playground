package org.example.functional.chapter7.ex04

class User(val name: String, val surname: String) {
    inline val fullName: String get() = "$name $surname"
}

fun main() {
    val user = User("A", "B")
    println(user.fullName)

    // 컴파일 과정에서 다음 코드로 바뀝니다.
    println("${user.name} ${user.surname}")
}