package org.example.tdd.api

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder
import org.springframework.security.oauth2.core.authorization.OAuth2AuthorizationManagers.hasScope
import org.springframework.security.oauth2.jwt.JwtDecoder
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder
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
    fun jwtDecoder(jwtKeyHolder: JwtKeyHolder): JwtDecoder = NimbusJwtDecoder.withSecretKey(jwtKeyHolder.key).build()

    @Bean
    fun securityFilterChain(
        http: HttpSecurity,
        jwtDecoder: JwtDecoder,
    ): DefaultSecurityFilterChain =
        http
            .csrf { it.disable() }
            .oauth2ResourceServer { it.jwt { jwt -> jwt.decoder(jwtDecoder) } }
            .authorizeHttpRequests {
                it
                    .requestMatchers(
                        "/seller/signup",
                        "/seller/issueToken",
                        "/shopper/signup",
                        "/shopper/issueToken",
                    ).permitAll()
                    .requestMatchers("/seller/**")
                    .access(hasScope("seller"))
                    .requestMatchers("/shopper/**")
                    .access(hasScope("shopper"))
                    .anyRequest()
                    .authenticated()
            }.build()
}
