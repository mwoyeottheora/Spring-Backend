package com.example.mwoyeottheora.infrastructure.word.service

data class WordApiListResponse(
    val item: List<WordApiResponse>
)

data class WordApiResponse(
    val word: String
)
