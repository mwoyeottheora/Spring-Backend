package com.example.mwoyeottheora.infrastructure.word.service

data class WordListResponse(
    val words: List<WordResponse>
)

data class WordResponse(
    val word: String?
)
