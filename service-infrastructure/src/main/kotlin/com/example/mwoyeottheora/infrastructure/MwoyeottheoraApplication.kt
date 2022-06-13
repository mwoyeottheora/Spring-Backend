package com.example.mwoyeottheora.infrastructure

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

internal const val BASE_PACKAGE = "com.example.mwoyeottheora.infrastructure"

@SpringBootApplication
class MwoyeottheoraApplication

fun main(args: Array<String>) {
    runApplication<MwoyeottheoraApplication>(*args)
}
