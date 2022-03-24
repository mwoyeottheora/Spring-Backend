package com.example.mwoyeottheora

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

internal const val BASE_PACKAGE = "com.example.mwoyeottheora"

@SpringBootApplication
class MwoyeottheoraApplication

fun main(args: Array<String>) {
    runApplication<MwoyeottheoraApplication>(*args)
}
