package org.example.functional.chapter4

class FunctionsClassic {
    fun add(a: Int, b: Int): Int {
        return a + b
    }

    fun printNum(num: Int) {
        println(num)
    }

    fun triple(num: Int): Int {
        return num * 3
    }

    fun produceName(name: String): Name {
        return Name(name)
    }

    fun longestOf(str1: String, str2: String, str3: String): String = maxOf(str1, str2, str3, compareBy { it.length })
}

data class Name(val name: String)

fun main() {
    val functionsClassic = FunctionsClassic()
    println(functionsClassic.add(1, 2))
    functionsClassic.printNum(3)
    println(functionsClassic.triple(4))
    println(functionsClassic.produceName("John"))
    println(functionsClassic.longestOf("John", "Jane", "Doe"))
}