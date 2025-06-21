package org.example.practicetesting.spring.api.service.mail

import org.assertj.core.api.Assertions.assertThat
import org.example.practicetesting.spring.client.MailSendClient
import org.example.practicetesting.spring.domain.history.mail.MailSendHistory
import org.example.practicetesting.spring.domain.history.mail.MailSendHistoryRepository
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.ArgumentMatchers.any
import org.mockito.ArgumentMatchers.anyString
import org.mockito.BDDMockito.given
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension
import kotlin.test.Test

@ExtendWith(MockitoExtension::class)
class MailServiceTest {
    @Mock
    private lateinit var mailSendClient: MailSendClient

    @Mock
    private lateinit var mailSendHistoryRepository: MailSendHistoryRepository

    @InjectMocks
    private lateinit var mailService: MailService

    @Test
    @DisplayName("메일 전송 테스트")
    fun sendMail() {
        given(
            mailSendClient.sendEmail(
                anyString(),
                anyString(),
                anyString(),
                anyString(),
            ),
        ).willReturn(true)
        given(
            mailSendHistoryRepository.save(
                any(MailSendHistory::class.java),
            ),
        ).willReturn(Mockito.mock(MailSendHistory::class.java))

        val result = mailService.sendMail("", "", "", "")

        assertThat(result).isTrue
        Mockito.verify(mailSendHistoryRepository, Mockito.times(1)).save(any(MailSendHistory::class.java))
    }
}
