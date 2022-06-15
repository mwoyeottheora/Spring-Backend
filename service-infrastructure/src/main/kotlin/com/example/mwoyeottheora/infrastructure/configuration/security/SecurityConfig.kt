package com.example.mwoyeottheora.infrastructure.configuration.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.web.server.SecurityWebFiltersOrder
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.server.SecurityWebFilterChain
import org.springframework.security.web.server.authentication.AuthenticationWebFilter
import reactor.core.publisher.Mono

@Configuration
class SecurityConfig(
    private val authenticationWebFilter: AuthenticationWebFilter
) {
    @Bean
    fun securityWebFilterChain(http: ServerHttpSecurity): SecurityWebFilterChain {
        return http
            .addFilterAt(authenticationWebFilter, SecurityWebFiltersOrder.AUTHENTICATION)
            .authorizeExchange()
            .pathMatchers(HttpMethod.POST, "/users").permitAll()
            .pathMatchers(HttpMethod.POST, "/users/tokens").permitAll()
            .pathMatchers(HttpMethod.GET, "/words/random").permitAll()
            .pathMatchers(HttpMethod.GET, "/words").permitAll()
            .anyExchange()
            .permitAll()
            .and()
            .csrf().disable()
            .cors().and()
            .build()
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }
}