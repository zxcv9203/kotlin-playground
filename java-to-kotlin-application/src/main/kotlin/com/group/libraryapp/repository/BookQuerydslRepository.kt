package com.group.libraryapp.repository

import com.group.libraryapp.config.QuerydslConfig
import com.group.libraryapp.domain.book.QBook.book
import com.group.libraryapp.dto.book.response.BookStatResponse
import com.querydsl.core.types.Projections
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.stereotype.Component

@Component
class BookQuerydslRepository(
    private val query: JPAQueryFactory
) {

    fun getStats(): List<BookStatResponse> {
        return query
            .select(
                Projections.constructor(
                    BookStatResponse::class.java,
                    book.type,
                    book.id.count()
                )
            )
            .from(book)
            .groupBy(book.type)
            .fetch()
    }
}