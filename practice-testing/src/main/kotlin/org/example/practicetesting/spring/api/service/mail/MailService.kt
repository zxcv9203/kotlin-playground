package org.example.practicetesting.spring.api.service.mail

import org.example.practicetesting.spring.client.MailSendClient
import org.example.practicetesting.spring.domain.history.mail.MailSendHistory
import org.example.practicetesting.spring.domain.history.mail.MailSendHistoryRepository
import org.springframework.stereotype.Service

@Service
class MailService(
    private val mailSendClient: MailSendClient,
    private val mailSendHistoryRepository: MailSendHistoryRepository,
) {
    fun sendMail(
        fromEmail: String,
        toEmail: String,
        subject: String,
        content: String,
    ): Boolean {
        val result = mailSendClient.sendEmail(fromEmail, toEmail, subject, content)

        if (result) {
            mailSendHistoryRepository.save(
                MailSendHistory(
                    fromEmail = fromEmail,
                    toEmail = toEmail,
                    subject = subject,
                    content = content,
                ),
            )
            return true
        }
        return false
    }
}
