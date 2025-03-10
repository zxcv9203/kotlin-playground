package org.example.functional.chapter8

import java.time.LocalDateTime

fun main() {
    val news = listOf(
        News("title1", "content1", LocalDateTime.of(2021, 1, 1, 0, 0), true),
        News("title2", "content2", LocalDateTime.of(2021, 1, 2, 0, 0)),
        News("title3", "content3", LocalDateTime.of(2020, 1, 3, 0, 0), true),
    )

    val newsItemAdapters = news
        .filter { it.visible }
        .sortedByDescending { it.publishedAt }
        .map(::NewsItemAdapter)

    println(newsItemAdapters)
}