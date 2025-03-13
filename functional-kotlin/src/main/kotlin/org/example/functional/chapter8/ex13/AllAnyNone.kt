package org.example.functional.chapter8.ex13

data class Person(val name: String, val age: Int, val male: Boolean)

fun main() {
    val people = listOf(
        Person("Alice", 31, false),
        Person("Bob", 29, true),
        Person("Carol", 31, true),
    )

    fun isAdult(p: Person) = p.age > 18
    fun isChild(p: Person) = p.age < 18
    fun isMale(p: Person) = p.male
    fun isFemale(p: Person) = !p.male

    // 어른이 있는가?
    println(people.any(::isAdult))
    // 모두 어른인가?
    println(people.all(::isAdult))
    // 어른이 아무도 없는가?
    println(people.none(::isAdult))

    // 아이가 있는가?
    println(people.any(::isChild))
    // 모두 아이인가?
    println(people.all(::isChild))
    // 아이가 아무도 없는가?
    println(people.none(::isChild))

    // 남자가 있는가?
    println(people.any(::isMale))
    // 모두 남자인가?
    println(people.all(::isMale))
    // 남자가 아무도 없는가?
    println(people.none(::isMale))

    // 여자가 있는가?
    println(people.any(::isFemale))
    // 모두 여자인가?
    println(people.all(::isFemale))
    // 여자가 아무도 없는가?
    println(people.none(::isFemale))
}