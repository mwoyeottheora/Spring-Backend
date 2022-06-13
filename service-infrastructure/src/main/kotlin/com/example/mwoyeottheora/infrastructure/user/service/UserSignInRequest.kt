package com.example.mwoyeottheora.infrastructure.user.service

data class UserSignInRequest(
    val accountId: String,
    val password: String
)
