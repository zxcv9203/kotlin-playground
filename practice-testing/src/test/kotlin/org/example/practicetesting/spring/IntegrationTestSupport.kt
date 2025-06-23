package org.example.practicetesting.spring

import org.example.practicetesting.spring.client.MailSendClient
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.bean.override.mockito.MockitoBean

@SpringBootTest
@ActiveProfiles("test")
abstract class IntegrationTestSupport {
    @MockitoBean
    protected lateinit var mailSendClient: MailSendClient
}
