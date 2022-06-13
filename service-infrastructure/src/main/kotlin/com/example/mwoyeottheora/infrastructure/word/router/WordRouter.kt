package com.example.mwoyeottheora.infrastructure.word.router

import com.example.mwoyeottheora.infrastructure.word.handler.WordHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.coRouter

@Configuration
class WordRouter(
    private val wordHandler: WordHandler
) {
    @Bean
    fun wordBaseRouter() = coRouter {
        "/words".nest {
            GET("", wordHandler::handleGetWord)
            POST("", wordHandler::handleSaveFoundWord)
            GET("/users", wordHandler::handleGetFoundWord)
        }
    }
}
