package org.example.functional.chapter8.ex22

fun main() {
    // zip은 중위 표기법으로 사용 가능
    val zipped = (1..4) zip ('a'..'z')
    println(zipped)

    val (numbers, letters) = zipped.unzip()
    println(numbers)
    println(letters)
}