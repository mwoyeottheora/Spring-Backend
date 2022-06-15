package com.example.mwoyeottheora.infrastructure.word.service

import com.example.mwoyeottheora.infrastructure.word.FoundWord
import com.example.mwoyeottheora.infrastructure.word.repository.WordRepository
import java.util.UUID
import kotlinx.coroutines.reactor.awaitSingle
import org.springframework.security.core.context.ReactiveSecurityContextHolder
import org.springframework.stereotype.Service

@Service
class WordService(
    private val wordRepository: WordRepository
) {
    suspend fun findWord(name: String): WordListResponse {
        val words = wordRepository.findAllByNameLike(name)
        return WordListResponse(words.map { WordResponse(it) })
    }

    suspend fun saveFoundWord(word: String) {
        val userId = ReactiveSecurityContextHolder.getContext().awaitSingle().authentication.name
        wordRepository.save(
            FoundWord(word, UUID.fromString(userId))
        )
    }

    suspend fun getAllUserFoundWord(): WordListResponse {
        val userId = ReactiveSecurityContextHolder.getContext().awaitSingle().authentication.name
        val words = wordRepository.findAllByUserId(UUID.fromString(userId))
        return WordListResponse(words.map { WordResponse(it.name) })
    }

    suspend fun getRandomFoundWord(): WordResponse {
        val word = wordRepository.findRandom()
        return WordResponse(word?.name)
    }
}
