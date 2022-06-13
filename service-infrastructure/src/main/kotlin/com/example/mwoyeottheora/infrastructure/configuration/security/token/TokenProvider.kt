package com.example.mwoyeottheora.infrastructure.configuration.security.token

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.stereotype.Component
import java.util.*

@Component
class TokenProvider(
    private val tokenProperty: JwtTokenProperty
) {
    fun generateToken(subject: String): String = Jwts.builder()
        .signWith(SignatureAlgorithm.HS512, tokenProperty.secretKey)
        .setSubject(subject)
        .setIssuedAt(Date())
        .setExpiration(Date(System.currentTimeMillis() + (tokenProperty.accessTokenExpirationForMinute.times(60000))))
        .compact()
}