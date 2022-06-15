package com.example.mwoyeottheora.infrastructure.configuration.security.token

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jws
import io.jsonwebtoken.Jwts
import kotlinx.coroutines.reactor.awaitSingle
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.ReactiveUserDetailsService
import org.springframework.stereotype.Component

@Component
class TokenParser(
    private val tokenProperty: JwtTokenProperty,
    private val reactiveUserDetailsService: ReactiveUserDetailsService
) {
    suspend fun getUsernamePasswordAuthenticationTokenFromToken(token: String): Authentication {
        val jwsClaims = parseToken(token)
        val subject = jwsClaims.body.subject
        val userDetails = reactiveUserDetailsService.findByUsername(subject).awaitSingle()

        return UsernamePasswordAuthenticationToken(userDetails, "", userDetails.authorities)
    }


    private fun parseToken(tokenWithoutPrefix: String): Jws<Claims> =
        Jwts.parser().setSigningKey(tokenProperty.secretKey).parseClaimsJws(tokenWithoutPrefix)
}