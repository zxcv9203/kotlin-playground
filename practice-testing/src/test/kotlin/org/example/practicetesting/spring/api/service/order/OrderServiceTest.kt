package org.example.practicetesting.spring.api.service.order

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.assertj.core.api.Assertions.tuple
import org.example.practicetesting.spring.api.controller.order.request.OrderCreateRequest
import org.example.practicetesting.spring.domain.order.OrderRepository
import org.example.practicetesting.spring.domain.orderproduct.OrderProductRepository
import org.example.practicetesting.spring.domain.product.Product
import org.example.practicetesting.spring.domain.product.ProductRepository
import org.example.practicetesting.spring.domain.product.ProductSellingStatus
import org.example.practicetesting.spring.domain.product.ProductType
import org.example.practicetesting.spring.domain.stock.Stock
import org.example.practicetesting.spring.domain.stock.StockRepository
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.DisplayName
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import java.time.LocalDateTime
import kotlin.test.Test

@SpringBootTest
@ActiveProfiles("test")
class OrderServiceTest {
    @Autowired
    private lateinit var productRepository: ProductRepository

    @Autowired
    private lateinit var orderRepository: OrderRepository

    @Autowired
    private lateinit var orderProductRepository: OrderProductRepository

    @Autowired
    private lateinit var orderService: OrderService

    @Autowired
    private lateinit var stockRepository: StockRepository

    @AfterEach
    fun tearDown() {
        orderProductRepository.deleteAllInBatch()
        orderRepository.deleteAllInBatch()
        productRepository.deleteAllInBatch()
        stockRepository.deleteAllInBatch()
    }

    @Test
    @DisplayName("주문번호 리스트를 받아 주문을 생성한다.")
    fun createOrder() {
        val registeredDateTime = LocalDateTime.now()
        val product1 = createProduct(ProductType.HANDMADE, "001", 1000)
        val product2 = createProduct(ProductType.HANDMADE, "002", 3000)
        val product3 = createProduct(ProductType.HANDMADE, "003", 5000)
        productRepository.saveAll(listOf(product1, product2, product3))
        val request =
            OrderCreateRequest(
                productNumbers = listOf("001", "002"),
            )

        val got = orderService.createOrder(request, registeredDateTime)

        assertThat(got.id).isNotNull
        assertThat(got)
            .extracting("registeredDateTime", "totalPrice")
            .contains(registeredDateTime, 4000)
        assertThat(got.products)
            .hasSize(2)
            .extracting("productNumber", "price")
            .containsExactlyInAnyOrder(
                tuple("001", 1000),
                tuple("002", 3000),
            )
    }

    @Test
    @DisplayName("재고와 관련된 상품이 포함되어 있는 주문번호 리스트를 받아 주문을 생성한다.")
    fun createOrderWithStock() {
        val registeredDateTime = LocalDateTime.now()
        val product1 = createProduct(ProductType.BOTTLE, "001", 1000)
        val product2 = createProduct(ProductType.BAKERY, "002", 3000)
        val product3 = createProduct(ProductType.HANDMADE, "003", 5000)
        productRepository.saveAll(listOf(product1, product2, product3))

        val stock1 = Stock.create("001", 2)
        val stock2 = Stock.create("002", 2)
        stockRepository.saveAll(listOf(stock1, stock2))

        val request =
            OrderCreateRequest(
                productNumbers = listOf("001", "001", "002", "003"),
            )

        val got = orderService.createOrder(request, registeredDateTime)

        assertThat(got.id).isNotNull
        assertThat(got)
            .extracting("registeredDateTime", "totalPrice")
            .contains(registeredDateTime, 10000)
        assertThat(got.products)
            .hasSize(4)
            .extracting("productNumber", "price")
            .containsExactlyInAnyOrder(
                tuple("001", 1000),
                tuple("001", 1000),
                tuple("002", 3000),
                tuple("003", 5000),
            )

        val stocks: List<Stock> = stockRepository.findAll()
        assertThat(stocks)
            .hasSize(2)
            .extracting("productNumber", "quantity")
            .containsExactlyInAnyOrder(
                tuple("001", 0),
                tuple("002", 1),
            )
    }

    @Test
    @DisplayName("재고가 없는 상품으로 주문을 생성하려는 경우 예외가 발생합니다.")
    fun createOrderWithoutNoStock() {
        val registeredDateTime = LocalDateTime.now()
        val product1 = createProduct(ProductType.BOTTLE, "001", 1000)
        val product2 = createProduct(ProductType.BAKERY, "002", 3000)
        val product3 = createProduct(ProductType.HANDMADE, "003", 5000)
        productRepository.saveAll(listOf(product1, product2, product3))

        val stock1 = Stock.create("001", 1)
        val stock2 = Stock.create("002", 1)
        stockRepository.saveAll(listOf(stock1, stock2))

        val request =
            OrderCreateRequest(
                productNumbers = listOf("001", "001", "002", "003"),
            )

        assertThatThrownBy { orderService.createOrder(request, registeredDateTime) }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("재고가 부족한 상품이 있습니다.")
    }

    @Test
    @DisplayName("중복되는 상품번호 리스트로 주문을 생성할 수 있다.")
    fun createOrderWithDuplicateProductNumbers() {
        val registeredDateTime = LocalDateTime.now()
        val product1 = createProduct(ProductType.HANDMADE, "001", 1000)
        val product2 = createProduct(ProductType.HANDMADE, "002", 3000)
        productRepository.saveAll(listOf(product1, product2))
        val request =
            OrderCreateRequest(
                productNumbers = listOf("001", "001"),
            )

        val got = orderService.createOrder(request, registeredDateTime)

        assertThat(got.id).isNotNull
        assertThat(got)
            .extracting("registeredDateTime", "totalPrice")
            .contains(registeredDateTime, 2000)
        assertThat(got.products)
            .hasSize(2)
            .extracting("productNumber", "price")
            .containsExactlyInAnyOrder(
                tuple("001", 1000),
                tuple("001", 1000),
            )
    }

    private fun createProduct(
        type: ProductType,
        productNumber: String,
        price: Int,
    ) = Product(
        type = type,
        productNumber = productNumber,
        price = price,
        sellingStatus = ProductSellingStatus.SELLING,
        name = "메뉴 이름",
    )
}
