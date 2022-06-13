package com.example.mwoyeottheora.infrastructure.configuration.security.auth

import com.example.mwoyeottheora.infrastructure.user.repository.UserRepository
import java.util.UUID
import kotlinx.coroutines.reactor.mono
import org.springframework.security.core.userdetails.ReactiveUserDetailsService
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class AuthDetailsService(
    private val userRepository: UserRepository
) : ReactiveUserDetailsService {
    override fun findByUsername(username: String): Mono<UserDetails> = mono {
        val user = userRepository.findByIdOrNull(UUID.fromString(username)) ?: throw TODO()
        User(
            user.id.toString(),
            user.password,
            listOf()
        )
    }
}
