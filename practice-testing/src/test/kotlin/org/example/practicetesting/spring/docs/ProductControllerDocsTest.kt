package org.example.practicetesting.spring.docs

import org.example.practicetesting.spring.api.controller.product.ProductController
import org.example.practicetesting.spring.api.controller.product.request.ProductCreateRequest
import org.example.practicetesting.spring.api.service.product.ProductService
import org.example.practicetesting.spring.api.service.product.response.ProductResponse
import org.example.practicetesting.spring.domain.product.ProductSellingStatus
import org.example.practicetesting.spring.domain.product.ProductType
import org.junit.jupiter.api.DisplayName
import org.mockito.BDDMockito.given
import org.mockito.Mockito.mock
import org.springframework.http.MediaType
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document
import org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath
import org.springframework.restdocs.payload.PayloadDocumentation.requestFields
import org.springframework.restdocs.payload.PayloadDocumentation.responseFields
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import kotlin.test.Test

class ProductControllerDocsTest : RestdocsSupport() {
    private val productService: ProductService = mock(ProductService::class.java)

    override fun initController(): Any = ProductController(productService)

    @Test
    @DisplayName("신규 상품을 등록하는 API")
    fun createProduct() {
        val request =
            ProductCreateRequest(
                type = ProductType.HANDMADE,
                price = 4000,
                name = "아메리카노",
                sellingStatus = ProductSellingStatus.SELLING,
            )

        given(productService.createProduct(request)).willReturn(
            ProductResponse(
                id = 1L,
                productNumber = "001",
                type = ProductType.HANDMADE,
                price = 4000,
                name = "아메리카노",
                sellingStatus = ProductSellingStatus.SELLING,
            ),
        )
        mockMvc
            .perform(
                post("/api/v1/products/new")
                    .content(objectMapper.writeValueAsString(request))
                    .contentType(MediaType.APPLICATION_JSON),
            ).andDo(
                document(
                    "product-create",
                    requestFields(
                        fieldWithPath("type").description("상품 타입"),
                        fieldWithPath("price").description("상품 가격"),
                        fieldWithPath("name").description("상품 이름"),
                        fieldWithPath("sellingStatus")
                            .description("상품 판매 상태")
                            .optional(),
                    ),
                    responseFields(
                        fieldWithPath("code").description("응답 코드"),
                        fieldWithPath("status").description("응답 상태"),
                        fieldWithPath("message").description("응답 메시지"),
                        fieldWithPath("data").description("응답 데이터"),
                        fieldWithPath("data.id").description("등록된 상품의 ID"),
                        fieldWithPath("data.productNumber").description("등록된 상품의 상품 번호"),
                        fieldWithPath("data.type").description("등록된 상품의 타입"),
                        fieldWithPath("data.price").description("등록된 상품의 가격"),
                        fieldWithPath("data.name").description("등록된 상품의 이름"),
                        fieldWithPath("data.sellingStatus").description("등록된 상품의 판매 상태"),
                    ),
                ),
            )
    }
}
