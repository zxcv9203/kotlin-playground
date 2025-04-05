package org.example.functional.chapter11.ex02

class Node2(val name: String) {
    fun makeChild(childName: String) = create(childName)
        .also { print("Created ${it?.name}") }

    fun create(name: String): Node2? = Node2(name)
}

fun main() {
    val node = Node2("parent")
    // created child 출력
    node.makeChild("child")
}
