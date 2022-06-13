package com.example.mwoyeottheora.infrastructure.user.repository

import com.example.mwoyeottheora.infrastructure.user.User
import java.util.*

interface UserRepository {
    suspend fun save(user: User): User
    suspend fun findByIdOrNull(id: UUID): User?
    suspend fun findByAccountIdOrNull(accountId: String): User?
}