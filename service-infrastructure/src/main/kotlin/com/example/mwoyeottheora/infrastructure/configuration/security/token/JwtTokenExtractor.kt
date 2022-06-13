package com.example.mwoyeottheora.infrastructure.configuration.security.token

import kotlinx.coroutines.reactor.mono
import org.springframework.http.HttpHeaders
import org.springframework.security.core.Authentication
import org.springframework.security.web.server.authentication.ServerAuthenticationConverter
import org.springframework.stereotype.Component
import org.springframework.web.server.ServerWebExchange
import reactor.core.publisher.Mono

@Component
class JwtTokenExtractor(
    private val tokenParser: TokenParser
) : ServerAuthenticationConverter {
    override fun convert(exchange: ServerWebExchange): Mono<Authentication> = mono {
        val authorization = exchange.request.headers[HttpHeaders.AUTHORIZATION]?.get(0)
        val authenticationOrNull = authorization?.run {
            val pureToken = authorization.split(" ")[1]
            tokenParser.getUsernamePasswordAuthenticationTokenFromToken(pureToken)
        }

        authenticationOrNull!!
    }
}