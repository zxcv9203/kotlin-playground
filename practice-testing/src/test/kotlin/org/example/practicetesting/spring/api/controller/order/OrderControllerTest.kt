package org.example.practicetesting.spring.api.controller.order

import com.fasterxml.jackson.databind.ObjectMapper
import org.example.practicetesting.spring.api.controller.order.request.OrderCreateRequest
import org.example.practicetesting.spring.api.service.order.OrderService
import org.junit.jupiter.api.DisplayName
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.context.bean.override.mockito.MockitoBean
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import kotlin.test.Test

@WebMvcTest(controllers = [OrderController::class])
class OrderControllerTest {
    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockitoBean
    private lateinit var orderService: OrderService

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @Test
    @DisplayName("신규 주문을 등록한다.")
    fun createOrder() {
        val request =
            OrderCreateRequest(
                productNumbers = listOf("001", "002"),
            )

        mockMvc
            .perform(
                post("/api/v1/orders/new")
                    .contentType("application/json")
                    .content(objectMapper.writeValueAsString(request)),
            ).andExpect(status().isOk)
            .andExpect(jsonPath("$.code").value("200"))
            .andExpect(jsonPath("$.message").value("OK"))
            .andDo(MockMvcResultHandlers.print())
    }

    @Test
    @DisplayName("신규 주문을 등록할 때 상품번호는 1개 이상이어야 한다.")
    fun createOrderWithEmptyProductNumbers() {
        val request = OrderCreateRequest(productNumbers = emptyList())

        mockMvc
            .perform(
                post("/api/v1/orders/new")
                    .contentType("application/json")
                    .content(objectMapper.writeValueAsString(request)),
            ).andExpect(status().isBadRequest)
            .andExpect(jsonPath("$.code").value("400"))
            .andExpect(jsonPath("$.status").value("BAD_REQUEST"))
            .andExpect(jsonPath("$.message").value("BAD_REQUEST"))
            .andExpect(jsonPath("$.data").value("상품번호는 1개 이상이어야 합니다."))
    }
}
