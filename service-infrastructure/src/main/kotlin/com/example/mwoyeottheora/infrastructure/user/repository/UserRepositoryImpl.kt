package com.example.mwoyeottheora.infrastructure.user.repository

import com.linecorp.kotlinjdsl.query.HibernateMutinyReactiveQueryFactory
import com.linecorp.kotlinjdsl.querydsl.expression.col
import com.linecorp.kotlinjdsl.selectQuery
import com.example.mwoyeottheora.infrastructure.user.User
import java.util.*
import org.springframework.stereotype.Repository

@Repository
class UserRepositoryImpl(
    private val reactiveQueryFactory: HibernateMutinyReactiveQueryFactory
) : UserRepository {
    override suspend fun save(user: User): User {
        reactiveQueryFactory.withFactory { session, _ ->
            session.persist(user)
                .await()
        }
        return user
    }

    override suspend fun findByIdOrNull(id: UUID): User? {
        return reactiveQueryFactory.withFactory { _, factory ->
            factory.selectQuery<User> {
                select(entity(User::class))
                from(entity(User::class))
                where(col(User::id).equal(id))
            }
        }.singleResultOrNull()
    }

    override suspend fun findByAccountIdOrNull(accountId: String): User? {
        return reactiveQueryFactory.withFactory { _, factory ->
            factory.selectQuery<User> {
                select(entity(User::class))
                from(entity(User::class))
                where(col(User::accountId).equal(accountId))
            }
        }.singleResultOrNull()
    }
}