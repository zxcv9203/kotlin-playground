package org.example.functional.chapter8.ex22

fun main() {
    val nums = 1..4
    val chars = 'A'..'F'
    println(nums.zip(chars))

    val winner = listOf(
        "Ashley",
        "Barbara",
        "Cyprian",
        "David",
    )

    val prices = listOf(5000, 3000, 1000)
    val zipped = winner.zip(prices)
    println(zipped)
    zipped.forEach { (name, price) ->
        println("$name: $price")
    }
}