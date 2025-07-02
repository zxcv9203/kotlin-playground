package org.example.tdd.api

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder
import org.springframework.security.web.DefaultSecurityFilterChain

@Configuration
class SecurityConfig {
    @Bean
    fun passwordEncoder(): PasswordEncoder = Pbkdf2PasswordEncoder.defaultsForSpringSecurity_v5_8()

    @Bean
    fun securityFilterChain(http: HttpSecurity): DefaultSecurityFilterChain =
        http
            .csrf { it.disable() }
            .authorizeHttpRequests { it.requestMatchers("/seller/signup").permitAll() }
            .build()
}
