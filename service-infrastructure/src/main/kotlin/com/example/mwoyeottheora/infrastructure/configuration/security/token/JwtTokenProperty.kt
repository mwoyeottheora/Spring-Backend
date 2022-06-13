package com.example.mwoyeottheora.infrastructure.configuration.security.token

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding
import java.util.*

@ConstructorBinding
@ConfigurationProperties(prefix = "jwt")
class JwtTokenProperty(
    val accessTokenExpirationForMinute: Long,
    val refreshTokenExpirationForMinute: Long,
    val jwtHeaderName: String,
    val jwtTokenType: String,
    secretKey: String
) {
    val secretKey: String = Base64.getEncoder().encodeToString(secretKey.toByteArray())
}
