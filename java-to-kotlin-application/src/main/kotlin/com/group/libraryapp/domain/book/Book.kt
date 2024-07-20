package com.group.libraryapp.domain.book

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class Book(
    val name: String,
    val type: String,
    // default 파라미터는 마지막에 두는 것이 관례
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
) {

    init {
        require(name.isNotBlank()) { "이름은 비어 있을 수 없습니다" }
    }

    companion object {
        fun fixture(
            name: String = "책 이름",
            type: String = "COMPUTER",
            id: Long? = null
        ): Book {
            return Book(
                name = name,
                type = type,
                id = id
            )
        }
    }
}