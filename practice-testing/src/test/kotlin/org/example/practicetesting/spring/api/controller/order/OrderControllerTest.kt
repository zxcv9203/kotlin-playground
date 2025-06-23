package org.example.practicetesting.spring.api.controller.order

import org.example.practicetesting.spring.ControllerTestSupport
import org.example.practicetesting.spring.api.controller.order.request.OrderCreateRequest
import org.junit.jupiter.api.DisplayName
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import kotlin.test.Test

class OrderControllerTest : ControllerTestSupport() {
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
