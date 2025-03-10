package org.example.functional.chapter8.ex02

data class User(
    val isActive: Boolean
)

data class Article(
    val visibility: Visibility
)

enum class Visibility {
    PUBLIC, PRIVATE
}

fun main() {
    val users = listOf(
        User(true),
        User(false),
        User(true)
    )
    val articles = listOf(
        Article(Visibility.PUBLIC),
        Article(Visibility.PRIVATE),
        Article(Visibility.PUBLIC)
    )
    val activeUsers = users.filter { it.isActive }
    val publicArticles = articles.filter { it.visibility == Visibility.PUBLIC }
}