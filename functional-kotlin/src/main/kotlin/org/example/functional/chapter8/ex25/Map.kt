package org.example.functional.chapter8.ex25

data class User(val id: Int, val name: String)

fun main() {
    val names: Map<Int, String> = mapOf(0 to "Alex", 1 to "Ben")
    println(names)

    val users: List<User> = names.map { User(it.key, it.value) }
    println(users)

    val usersById: Map<Int, User> = users.associateBy { it.id }
    println(usersById)

    val namesById: Map<Int, String> = usersById.mapValues { it.value.name }
    println(namesById)

    val usersByName: Map<String, User> = usersById.mapKeys { it.value.name }
    println(usersByName)
}