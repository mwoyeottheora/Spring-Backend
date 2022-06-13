package com.example.mwoyeottheora.infrastructure.user.controller

import com.example.mwoyeottheora.infrastructure.user.router.UserHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.coRouter

@Configuration
class UserController(
    private val userHandler: UserHandler
) {
    @Bean
    fun userRouter() = coRouter {
        "/users".nest {
            POST("", userHandler::handleSaveUser)
            POST("/tokens", userHandler::handleUserSignIn)
        }
    }
}
