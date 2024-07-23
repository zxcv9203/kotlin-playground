package com.group.libraryapp.domain.user

import com.group.libraryapp.domain.user.QUser.user
import com.querydsl.jpa.impl.JPAQueryFactory

class UserRepositoryCustomImpl(
    private val query: JPAQueryFactory
) : UserRepositoryCustom {

    override fun findAllWithHistories(): List<User> {
        return query
            .select(user).distinct()
            .from(user)
            .leftJoin(user.userLoanHistories).fetchJoin()
            .fetch()
    }
}