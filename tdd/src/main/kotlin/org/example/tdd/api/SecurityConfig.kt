package org.example.tdd.api

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder
import org.springframework.security.web.DefaultSecurityFilterChain
import javax.crypto.spec.SecretKeySpec

@Configuration
class SecurityConfig {
    @Bean
    fun passwordEncoder(): PasswordEncoder = Pbkdf2PasswordEncoder.defaultsForSpringSecurity_v5_8()

    @Bean
    fun jwtKeyHolder(
        @Value("\${security.jwt.secret}") secret: String,
    ): JwtKeyHolder {
        val key = SecretKeySpec(secret.toByteArray(), "HmacSHA256")
        return JwtKeyHolder(key)
    }

    @Bean
    fun securityFilterChain(http: HttpSecurity): DefaultSecurityFilterChain =
        http
            .csrf { it.disable() }
            .authorizeHttpRequests {
                it
                    .requestMatchers(
                        "/seller/signup",
                        "/seller/issueToken",
                        "/shopper/signup",
                        "/shopper/issueToken",
                    ).permitAll()
            }.build()
}
