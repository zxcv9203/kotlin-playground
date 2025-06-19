package org.example.practicetesting.spring.domain.history.mail

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import org.example.practicetesting.spring.domain.BaseEntity

@Entity
class MailSendHistory(
    val fromEmail: String,
    val toEmail: String,
    val subject: String,
    val content: String,
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L,
) : BaseEntity()
