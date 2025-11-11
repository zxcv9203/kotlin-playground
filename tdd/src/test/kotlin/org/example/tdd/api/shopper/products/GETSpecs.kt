package org.example.tdd.api.shopper.products

import org.assertj.core.api.Assertions.assertThat
import org.example.tdd.helper.CommerceApiTest
import org.example.tdd.helper.ProductAssertions
import org.example.tdd.helper.RegisterProductCommandGenerator
import org.example.tdd.helper.TestFixture
import org.example.tdd.result.PageCarrier
import org.example.tdd.view.ProductView
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.web.client.exchange
import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.HttpStatus
import org.springframework.http.RequestEntity
import org.springframework.http.RequestEntity.get
import kotlin.test.Test

@CommerceApiTest
@DisplayName("GET /shopper/products")
class GETSpecs {
    @Test
    fun `올바르게 요청하면 200 OK 상태코드를 반환한다`(
        @Autowired fixture: TestFixture,
    ) {
        // Arrange
        fixture.createShopperThenSetAsDefaultUser()

        // Act
        val response =
            fixture.client
                .exchange(
                    RequestEntity.get("/shopper/products").build(),
                    object : ParameterizedTypeReference<PageCarrier<ProductView>>() {},
                )

        // Assert
        assertThat(response.statusCode).isEqualTo(HttpStatus.OK)
    }

    @Test
    fun `판매자 접근 토큰을 사용하면 403 Forbidden 상태코드를 반환한다`(
        @Autowired fixture: TestFixture,
    ) {
        // Arrange
        fixture.createSellerThenSetAsDefaultUser()

        // Act
        val response =
            fixture.client
                .exchange(
                    RequestEntity.get("/shopper/products").build(),
                    object : ParameterizedTypeReference<PageCarrier<ProductView>>() {},
                )

        // Assert
        assertThat(response.statusCode).isEqualTo(HttpStatus.FORBIDDEN)
    }

    @Test
    fun `첫 번째 페이지의 상품을 반환한다`(
        @Autowired fixture: TestFixture,
    ) {
        // Arrange
        fixture.deleteAllProducts()
        fixture.createSellerThenSetAsDefaultUser()
        val productIds = fixture.registerProducts(10)
        fixture.createShopperThenSetAsDefaultUser()

        // Act
        val response =
            fixture.client
                .exchange(
                    RequestEntity.get("/shopper/products").build(),
                    object : ParameterizedTypeReference<PageCarrier<ProductView>>() {},
                )

        // Assert
        val actual = response.body
        assertThat(actual).isNotNull
        val extractProductIds = actual!!.items.map(ProductView::id)
        assertThat(extractProductIds).containsAll(productIds)
    }

    @Test
    fun `상품 목록을 등록 시점 역순으로 정렬한다`(
        @Autowired fixture: TestFixture,
    ) {
        fixture.deleteAllProducts()

        fixture.createSellerThenSetAsDefaultUser()
        val id1 = fixture.registerProduct()
        val id2 = fixture.registerProduct()
        val id3 = fixture.registerProduct()

        fixture.createShopperThenSetAsDefaultUser()

        val response =
            fixture.client.exchange(
                RequestEntity.get("/shopper/products").build(),
                object : ParameterizedTypeReference<PageCarrier<ProductView>>() {},
            )

        val actual = response.body
        assertThat(actual).isNotNull
        val extractProductIds = actual!!.items.map(ProductView::id)
        assertThat(extractProductIds).containsExactly(id3, id2, id1)
    }

    @Test
    fun `상품 정보를 올바르게 반환한다`(
        @Autowired fixture: TestFixture,
    ) {
        fixture.deleteAllProducts()

        fixture.createSellerThenSetAsDefaultUser()
        val command = RegisterProductCommandGenerator.generate()
        fixture.registerProduct(command)

        fixture.createShopperThenSetAsDefaultUser()

        val response =
            fixture.client.exchange(
                RequestEntity.get("/shopper/products").build(),
                object : ParameterizedTypeReference<PageCarrier<ProductView>>() {},
            )

        val actual = response.body!!.items[0]
        assertThat(actual).satisfies(ProductAssertions.isViewDerivedFrom(command))
    }

    @Test
    fun `판매자 정보를 올바르게 반환한다`(
        @Autowired fixture: TestFixture,
    ) {
        // Arrange
        fixture.deleteAllProducts()
        fixture.createSellerThenSetAsDefaultUser()
        val seller = fixture.getSeller()
        fixture.registerProduct()

        fixture.createShopperThenSetAsDefaultUser()

        // Act
        val response =
            fixture
                .client
                .exchange<PageCarrier<ProductView>>(
                    get("/shopper/products").build(),
                )

        // Assert
        val body = response.body!!
        val actual = body.items[0].seller
        assertThat(actual).isNotNull
        assertThat(actual.id).isEqualTo(seller.id)
        assertThat(actual.username).isEqualTo(seller.username)
    }

    @Test
    fun `두 번째 페이지를 올바르게 반환한다`(
        @Autowired fixture: TestFixture,
    ) {
        // Arrange
        fixture.deleteAllProducts()
        fixture.createSellerThenSetAsDefaultUser()
        fixture.registerProducts(10)

        val ids =
            fixture
                .registerProducts(10)
                .reversed()
        fixture.registerProducts(10)

        fixture.createShopperThenSetAsDefaultUser()
        val token = fixture.consumeProductPage()

        // Act
        val response =
            fixture.client.exchange<PageCarrier<ProductView>>(
                get("/shopper/products?continuationToken=$token").build(),
            )

        // Assert
        assertThat(response.body!!.items.map(ProductView::id))
            .containsExactlyElementsOf(ids)
    }

    @ParameterizedTest
    @ValueSource(ints = [1, 10])
    fun `마지막 페이지를 올바르게 반환한다`(
        page: Int,
        @Autowired fixture: TestFixture,
    ) {
        // Arrange
        fixture.deleteAllProducts()

        fixture.createSellerThenSetAsDefaultUser()
        val ids =
            fixture
                .registerProducts(page)
                .reversed()
        fixture.registerProducts(10 * 2)

        fixture.createShopperThenSetAsDefaultUser()
        val token = fixture.consumeTwoProductPages()

        // Act
        val response =
            fixture.client.exchange<PageCarrier<ProductView>>(
                get("/shopper/products?continuationToken=$token").build(),
            )

        // Assert
        assertThat(response.body!!.items.map(ProductView::id))
            .containsExactlyElementsOf(ids)
        assertThat(response.body!!.continuationToken).isNull()
    }

    @Test
    fun `continuationToken 매개변수에 빈 문자열이 지정되면 첫 번째 페이지를 반환한다`(
        @Autowired fixture: TestFixture,
    ) {
        // Arrange
        fixture.deleteAllProducts()

        fixture.createSellerThenSetAsDefaultUser()
        val ids = fixture.registerProducts(10)

        fixture.createShopperThenSetAsDefaultUser()
        val token = ""

        // Act
        val response =
            fixture.client.exchange<PageCarrier<ProductView>>(
                get("/shopper/products?continuationToken=$token").build(),
            )

        // Assert
        assertThat(response.body!!.items.map(ProductView::id))
            .containsExactlyElementsOf(ids.reversed())
    }

    @Test
    fun `문의 이메일 주소를 올바르게 설정한다`(
        @Autowired fixture: TestFixture,
    ) {
        // Arrange
        fixture.deleteAllProducts()
        fixture.createSellerThenSetAsDefaultUser()
        val contactEmail = fixture.getSeller().contactEmail
        fixture.registerProduct()

        fixture.createShopperThenSetAsDefaultUser()

        // Act
        val response =
            fixture
                .client
                .exchange<PageCarrier<ProductView>>(
                    get("/shopper/products")
                        .build(),
                )

        // Assert
        val body = response.body!!
        val actual = body.items[0].seller
        assertThat(actual).isNotNull
        assertThat(actual.contactEmail).isEqualTo(contactEmail)
    }
}
