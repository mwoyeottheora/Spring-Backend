package com.example.mwoyeottheora.infrastructure.word.handler

import com.example.mwoyeottheora.infrastructure.word.service.SaveFoundWordRequest
import com.example.mwoyeottheora.infrastructure.word.service.WordService
import java.net.URI
import kotlinx.coroutines.reactor.awaitSingle
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.bodyToMono
import org.springframework.web.reactive.function.server.bodyValueAndAwait
import org.springframework.web.reactive.function.server.buildAndAwait

@Component
class WordHandler(
    private val wordService: WordService
) {
    suspend fun handleGetWord(serverRequest: ServerRequest): ServerResponse {
        val wordName = serverRequest.queryParam("name")
        val wordListResponse = wordService.findWord(wordName.get())
        return ServerResponse.ok().bodyValueAndAwait(wordListResponse)
    }

    suspend fun handleSaveFoundWord(serverRequest: ServerRequest): ServerResponse {
        val request = serverRequest.bodyToMono<SaveFoundWordRequest>().awaitSingle()
        wordService.saveFoundWord(request.word)
        return ServerResponse.created(URI("/words")).buildAndAwait()
    }

    suspend fun handleGetFoundWord(serverRequest: ServerRequest): ServerResponse {
        val wordListResponse = wordService.getAllUserFoundWord()
        return ServerResponse.ok().bodyValueAndAwait(wordListResponse)
    }

    suspend fun handleGetRandomWord(serverRequest: ServerRequest): ServerResponse {
        val wordResponse = wordService.getRandomFoundWord()
        return ServerResponse.ok().bodyValueAndAwait(wordResponse)
    }
}