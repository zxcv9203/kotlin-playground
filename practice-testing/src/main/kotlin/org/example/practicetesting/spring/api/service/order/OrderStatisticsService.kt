package org.example.practicetesting.spring.api.service.order

import org.example.practicetesting.spring.api.service.mail.MailService
import org.example.practicetesting.spring.domain.order.OrderRepository
import org.example.practicetesting.spring.domain.order.OrderStatus
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class OrderStatisticsService(
    private val mailService: MailService,
    private val orderRepository: OrderRepository,
) {
    fun sendOrderStatisticsMail(
        orderDate: LocalDate,
        email: String,
    ): Boolean {
        val orders =
            orderRepository.findOrdersBy(
                startDateTime = orderDate.atStartOfDay(),
                endDateTime = orderDate.plusDays(1).atStartOfDay(),
                orderStatus = OrderStatus.PAYMENT_COMPLETED,
            )

        val totalPrice = orders.sumOf { it.totalPrice }

        val result =
            mailService.sendMail(
                "no-reply@cafekiosk.com",
                email,
                "[매출 통계] $orderDate",
                "총 매출 합계는 $totalPrice 원입니다.",
            )
        if (!result) {
            throw IllegalArgumentException("매출 통계 메일 전송에 실패했습니다.")
        }
        return true
    }
}
