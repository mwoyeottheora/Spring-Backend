package com.example.mwoyeottheora.infrastructure.configuration.security

import com.example.mwoyeottheora.infrastructure.configuration.security.auth.AuthenticationManager
import com.example.mwoyeottheora.infrastructure.configuration.security.token.JwtTokenExtractor
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.web.server.authentication.AuthenticationWebFilter
import org.springframework.security.web.server.util.matcher.ServerWebExchangeMatchers

@Configuration
class CustomAuthenticationWebFilter(
    private val authenticationManager: AuthenticationManager,
    private val jwtTokenExtractor: JwtTokenExtractor,
) {
    @Bean
    fun authenticationWebFilter(): AuthenticationWebFilter {
        return AuthenticationWebFilter(authenticationManager).apply {
            setServerAuthenticationConverter(jwtTokenExtractor)
            setRequiresAuthenticationMatcher(ServerWebExchangeMatchers.anyExchange())
        }
    }
}
