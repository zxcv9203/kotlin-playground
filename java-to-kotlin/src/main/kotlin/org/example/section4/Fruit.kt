package org.example.section4

class Fruit(
    val name: String,
    val price: Int
)

fun main() {
    val fruits = listOf(
        Fruit("apple", 1000),
        Fruit("banana", 2000),
        Fruit("kiwi", 3000)
    )
}

// 람다를 만드는 방법 1
val isApple = fun(fruit: Fruit): Boolean = fruit.name == "apple"

// 람다를 만드는 방법 2
val isAppleLambda = { fruit: Fruit -> fruit.name == "apple" }