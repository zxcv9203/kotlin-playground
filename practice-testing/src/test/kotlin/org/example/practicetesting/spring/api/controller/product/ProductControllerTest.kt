package org.example.practicetesting.spring.api.controller.product

import com.fasterxml.jackson.databind.ObjectMapper
import org.example.practicetesting.spring.api.controller.product.request.ProductCreateRequest
import org.example.practicetesting.spring.api.service.product.ProductService
import org.example.practicetesting.spring.domain.product.ProductSellingStatus
import org.example.practicetesting.spring.domain.product.ProductType
import org.junit.jupiter.api.DisplayName
import org.mockito.Mockito.`when`
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.context.bean.override.mockito.MockitoBean
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import kotlin.test.Test

@WebMvcTest(controllers = [ProductController::class])
class ProductControllerTest {
    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockitoBean
    private lateinit var productService: ProductService

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @Test
    @DisplayName("신규 상품을 등록한다.")
    fun createProduct() {
        val request =
            ProductCreateRequest(
                type = ProductType.HANDMADE,
                price = 4000,
                name = "아메리카노",
                sellingStatus = ProductSellingStatus.SELLING,
            )
        mockMvc
            .perform(
                post("/api/v1/products/new")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(request)),
            ).andExpect(status().isOk)
    }

    @Test
    @DisplayName("상품 등록 시 필수 값이 누락된 경우 예외가 발생한다.")
    fun createProductWithMissingRequiredFields() {
        mockMvc
            .perform(
                post("/api/v1/products/new")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(
                        """
                        {
                        }
                        """.trimIndent(),
                    ),
            ).andDo(MockMvcResultHandlers.print())
            .andExpect(status().isBadRequest)
            .andExpect(jsonPath("$.code").value("400"))
            .andExpect(jsonPath("$.message").value("BAD_REQUEST"))
            .andExpect(jsonPath("$.data").isNotEmpty)
    }

    @Test
    @DisplayName("상품 등록 시 이름이 비어있는 경우 예외가 발생한다.")
    fun createProductWithEmptyName() {
        val request =
            ProductCreateRequest(
                type = ProductType.HANDMADE,
                price = 4000,
                name = "",
                sellingStatus = ProductSellingStatus.SELLING,
            )
        mockMvc
            .perform(
                post("/api/v1/products/new")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(request)),
            ).andDo(MockMvcResultHandlers.print())
            .andExpect(status().isBadRequest)
            .andExpect(jsonPath("$.code").value("400"))
            .andExpect(jsonPath("$.message").value("BAD_REQUEST"))
            .andExpect(jsonPath("$.data").value("상품 이름은 필수입니다."))
    }

    @Test
    @DisplayName("판매 상품을 조회한다.")
    fun getSellingProducts() {
        `when`(productService.getSellingProducts())
            .thenReturn(listOf())

        mockMvc
            .perform(
                get("/api/v1/products/selling"),
            ).andExpect(status().isOk)
            .andDo(MockMvcResultHandlers.print())
            .andExpect(jsonPath("$.code").value("200"))
            .andExpect(jsonPath("$.message").value("OK"))
            .andExpect(jsonPath("$.data").isArray)
    }
}
