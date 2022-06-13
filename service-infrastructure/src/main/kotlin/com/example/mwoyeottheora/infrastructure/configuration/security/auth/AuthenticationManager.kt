package com.example.mwoyeottheora.infrastructure.configuration.security.auth

import com.example.mwoyeottheora.infrastructure.user.repository.UserRepository
import java.util.UUID
import kotlinx.coroutines.reactor.mono
import org.springframework.security.authentication.ReactiveAuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono

@Component
class AuthenticationManager(
    private val userRepository: UserRepository
) : ReactiveAuthenticationManager {
    override fun authenticate(authentication: Authentication): Mono<Authentication> = mono {
        val user = userRepository.findByIdOrNull(UUID.fromString(authentication.name)) ?: throw TODO()
        UsernamePasswordAuthenticationToken(user.id, user.password)
    }
}
