package com.example.mwoyeottheora.infrastructure.word.repository

import com.example.mwoyeottheora.infrastructure.word.FoundWord
import java.util.UUID

interface WordRepository {
    suspend fun findAllByNameLike(name: String): List<String>
    suspend fun save(word: FoundWord): FoundWord
    suspend fun findAllByUserId(userId: UUID): List<FoundWord>
    suspend fun findRandom(): FoundWord
}