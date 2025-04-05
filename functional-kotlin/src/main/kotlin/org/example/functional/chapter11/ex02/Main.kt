package org.example.functional.chapter11.ex02

class Node(val name: String) {
    fun makeChild(childName: String) = create(childName)
        .apply { print("Created $name") }

    fun create(name: String): Node? = Node(name)
}

fun main() {
    val node = Node("parent")
    // created parent 출력
    node.makeChild("child")
}