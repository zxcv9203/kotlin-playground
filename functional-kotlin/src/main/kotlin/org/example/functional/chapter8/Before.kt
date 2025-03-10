package org.example.functional.chapter8

import java.time.LocalDateTime
import java.util.*

class News(val title: String, val content: String, val publishedAt: LocalDateTime, var visible: Boolean = false)
class NewsItemAdapter(val news: News) {
}

fun main() {
    val news = listOf(
        News("title1", "content1", LocalDateTime.of(2021, 1, 1, 0, 0), true),
        News("title2", "content2", LocalDateTime.of(2021, 1, 2, 0, 0)),
        News("title3", "content3", LocalDateTime.of(2020, 1, 3, 0, 0), true),
    )
    val visibleNews = mutableListOf<News>()
    for (n in news) {
        if (n.visible) {
            visibleNews.add(n)
        }
    }

    Collections.sort(visibleNews) { n1, n2 -> n1.publishedAt.compareTo(n2.publishedAt) }

    val newsItemAdapters = mutableListOf<NewsItemAdapter>()
    for (n in visibleNews) {
        newsItemAdapters.add(NewsItemAdapter(n))
    }
}