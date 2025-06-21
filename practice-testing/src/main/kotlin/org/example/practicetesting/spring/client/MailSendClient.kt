package org.example.practicetesting.spring.client

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
class MailSendClient(
    private val log: Logger = LoggerFactory.getLogger(MailSendClient::class.java),
) {
    fun sendEmail(
        fromEmail: String,
        toEmail: String,
        subject: String,
        content: String,
    ): Boolean {
        log.info("메일 전송")
        TODO()
    }
}
