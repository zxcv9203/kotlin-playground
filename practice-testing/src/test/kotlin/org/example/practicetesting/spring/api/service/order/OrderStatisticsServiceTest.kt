package org.example.practicetesting.spring.api.service.order

import org.assertj.core.api.Assertions.assertThat
import org.example.practicetesting.spring.client.MailSendClient
import org.example.practicetesting.spring.domain.history.mail.MailSendHistoryRepository
import org.example.practicetesting.spring.domain.order.Order
import org.example.practicetesting.spring.domain.order.OrderRepository
import org.example.practicetesting.spring.domain.order.OrderStatus
import org.example.practicetesting.spring.domain.orderproduct.OrderProductRepository
import org.example.practicetesting.spring.domain.product.Product
import org.example.practicetesting.spring.domain.product.ProductRepository
import org.example.practicetesting.spring.domain.product.ProductSellingStatus
import org.example.practicetesting.spring.domain.product.ProductType
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.DisplayName
import org.mockito.ArgumentMatchers.anyString
import org.mockito.BDDMockito.given
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.bean.override.mockito.MockitoBean
import java.time.LocalDateTime
import kotlin.test.Test

@SpringBootTest
class OrderStatisticsServiceTest {
    @Autowired
    private lateinit var orderStatisticsService: OrderStatisticsService

    @Autowired
    private lateinit var orderRepository: OrderRepository

    @Autowired
    private lateinit var productRepository: ProductRepository

    @Autowired
    private lateinit var mailSendHistoryRepository: MailSendHistoryRepository

    @Autowired
    private lateinit var orderProductRepository: OrderProductRepository

    @MockitoBean
    private lateinit var mailSendClient: MailSendClient

    @AfterEach
    fun tearDown() {
        mailSendHistoryRepository.deleteAllInBatch()
        orderProductRepository.deleteAllInBatch()
        orderRepository.deleteAllInBatch()
        productRepository.deleteAllInBatch()
    }

    @Test
    @DisplayName("결제 완료 주문들을 조회하여 매출 통계 메일을 전송합니다.")
    fun sendOrderStatisticsMail() {
        val orderDate = LocalDateTime.of(2023, 3, 5, 0, 0, 0)
        val product1 = createProduct(ProductType.HANDMADE, "001", 1000)
        val product2 = createProduct(ProductType.HANDMADE, "002", 2000)
        val product3 = createProduct(ProductType.HANDMADE, "003", 3000)
        val products = productRepository.saveAll(listOf(product1, product2, product3))

        val order1 = Order.create(products, LocalDateTime.of(2023, 3, 4, 23, 59), OrderStatus.PAYMENT_COMPLETED)
        val order2 = Order.create(products, orderDate, OrderStatus.PAYMENT_COMPLETED)
        val order3 = Order.create(products, LocalDateTime.of(2023, 3, 5, 23, 59, 59), OrderStatus.PAYMENT_COMPLETED)
        val order4 = Order.create(products, LocalDateTime.of(2023, 3, 6, 0, 0), OrderStatus.PAYMENT_COMPLETED)
        orderRepository.saveAll(listOf(order1, order2, order3, order4))

        given(
            mailSendClient.sendEmail(
                anyString(),
                toEmail = anyString(),
                subject = anyString(),
                content = anyString(),
            ),
        ).willReturn(true)

        val result =
            orderStatisticsService.sendOrderStatisticsMail(
                orderDate = orderDate.toLocalDate(),
                email = "test@test.com",
            )

        assertThat(result).isTrue

        val histories = mailSendHistoryRepository.findAll()
        assertThat(histories)
            .hasSize(1)
            .extracting("content")
            .contains("총 매출 합계는 12000 원입니다.")
    }

    private fun createProduct(
        type: ProductType,
        productNumber: String,
        price: Int,
    ) = Product(
        type = type,
        productNumber = productNumber,
        name = "메뉴이름",
        price = price,
        sellingStatus = ProductSellingStatus.SELLING,
    )
}
