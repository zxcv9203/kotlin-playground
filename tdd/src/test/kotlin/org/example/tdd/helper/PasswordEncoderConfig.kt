package org.example.tdd.helper

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Primary
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder

class PasswordEncoderConfig {
    @Bean
    @Primary
    fun testPasswordEncoder(): Pbkdf2PasswordEncoder =
        Pbkdf2PasswordEncoder(
            "",
            16,
            10,
            Pbkdf2PasswordEncoder.SecretKeyFactoryAlgorithm.PBKDF2WithHmacSHA256,
        )
}
