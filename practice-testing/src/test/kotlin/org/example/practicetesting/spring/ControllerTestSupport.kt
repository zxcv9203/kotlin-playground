package org.example.practicetesting.spring

import com.fasterxml.jackson.databind.ObjectMapper
import org.example.practicetesting.spring.api.controller.order.OrderController
import org.example.practicetesting.spring.api.controller.product.ProductController
import org.example.practicetesting.spring.api.service.order.OrderService
import org.example.practicetesting.spring.api.service.product.ProductService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.context.bean.override.mockito.MockitoBean
import org.springframework.test.web.servlet.MockMvc

@WebMvcTest(controllers = [OrderController::class, ProductController::class])
abstract class ControllerTestSupport {
    @Autowired
    protected lateinit var mockMvc: MockMvc

    @MockitoBean
    protected lateinit var orderService: OrderService

    @MockitoBean
    protected lateinit var productService: ProductService

    @Autowired
    protected lateinit var objectMapper: ObjectMapper
}
