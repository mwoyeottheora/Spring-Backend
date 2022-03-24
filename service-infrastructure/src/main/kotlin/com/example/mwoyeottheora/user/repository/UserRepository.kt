package com.example.mwoyeottheora.user.repository

import com.example.mwoyeottheora.user.User
import java.util.*

interface UserRepository {
    suspend fun save(user: User): User
    suspend fun findByIdOrNull(id: UUID): User?
}