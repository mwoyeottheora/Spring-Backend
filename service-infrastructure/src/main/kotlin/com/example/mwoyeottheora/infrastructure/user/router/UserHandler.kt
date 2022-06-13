package com.example.mwoyeottheora.infrastructure.user.router

import com.example.mwoyeottheora.infrastructure.user.service.SaveUserRequest
import com.example.mwoyeottheora.infrastructure.user.service.UserService
import com.example.mwoyeottheora.infrastructure.user.service.UserSignInRequest
import java.net.URI
import kotlinx.coroutines.reactor.awaitSingle
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.bodyToMono
import org.springframework.web.reactive.function.server.bodyValueAndAwait
import org.springframework.web.reactive.function.server.buildAndAwait

@Component
class UserHandler(
    private val userService: UserService
) {
    suspend fun handleSaveUser(serverRequest: ServerRequest): ServerResponse {
        val saveUserRequest = serverRequest.bodyToMono<SaveUserRequest>().awaitSingle()
        userService.saveUser(saveUserRequest)

        return ServerResponse.created(URI("/users")).buildAndAwait()
    }

    suspend fun handleUserSignIn(serverRequest: ServerRequest): ServerResponse {
        val userSignInRequest = serverRequest.bodyToMono<UserSignInRequest>().awaitSingle()
        val tokenResponse = userService.signIn(userSignInRequest)
        return ServerResponse.ok().bodyValueAndAwait(tokenResponse)
    }
}