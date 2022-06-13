package com.example.mwoyeottheora.infrastructure.user.service

import com.example.mwoyeottheora.infrastructure.configuration.security.token.TokenProvider
import com.example.mwoyeottheora.infrastructure.user.User
import com.example.mwoyeottheora.infrastructure.user.repository.UserRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
    private val tokenProvider: TokenProvider
) {
    suspend fun saveUser(saveUserRequest: SaveUserRequest) {
        val unSavedUser = saveUserRequest.toUserEntity()
        userRepository.save(unSavedUser)
    }

    private fun SaveUserRequest.toUserEntity() =
        User(
            accountId = this.accountId,
            password = passwordEncoder.encode(this.password)
        )

    suspend fun signIn(userSignInRequest: UserSignInRequest): UserTokenResponse {
        val user = userRepository.findByAccountIdOrNull(userSignInRequest.accountId) ?: throw TODO()
        val isPasswordMatches = passwordEncoder.matches(userSignInRequest.password, user.password)
        if (!isPasswordMatches) {
            throw TODO()
        }

        val token = tokenProvider.generateToken(user.id.toString())
        return UserTokenResponse(token)
    }
}