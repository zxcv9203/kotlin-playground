package org.example.tdd.api.seller.products

import org.assertj.core.api.Assertions.assertThat
import org.example.tdd.RegisterProductCommandGenerator
import org.example.tdd.api.CommerceApiTest
import org.example.tdd.api.TestFixture
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.web.client.postForEntity
import org.springframework.http.HttpStatus
import java.util.UUID
import java.util.function.Predicate
import kotlin.test.Test

@CommerceApiTest
@DisplayName("POST /seller/products")
class PostSpecs {
    @Test
    fun `올바르게 요청하면 201 Created 상태코드를 반환한다`(
        @Autowired fixture: TestFixture,
    ) {
        // Arrange
        fixture.createSellerThenSetAsDefaultUser()

        // Act
        val response =
            fixture.client
                .postForEntity<Unit>(
                    "/seller/products",
                    RegisterProductCommandGenerator.generate(),
                )

        // Assert
        assertThat(response.statusCode).isEqualTo(HttpStatus.CREATED)
    }

    @Test
    fun `판매자가 아닌 사용자의 접근 토큰을 사용하면 403 Forbidden 상태코드를 반환한다`(
        @Autowired fixture: TestFixture,
    ) {
        // Arrange
        fixture.createShopperThenSetAsDefaultUser()

        // Act
        val response =
            fixture.client
                .postForEntity<Unit>(
                    "/seller/products",
                    RegisterProductCommandGenerator.generate(),
                )

        // Assert
        assertThat(response.statusCode).isEqualTo(HttpStatus.FORBIDDEN)
    }

    @ParameterizedTest
    @ValueSource(
        strings = [
            "invalid-uri",
            "http://",
            "://missing-scheme.com",
        ],
    )
    fun `imageUri 속성이 URI 형식을 따르지 않으면 400 Bad Request 상태코드를 반환한다`(
        imageUri: String,
        @Autowired fixture: TestFixture,
    ) {
        // Arrange
        fixture.createSellerThenSetAsDefaultUser()

        // Act
        val response =
            fixture.client
                .postForEntity<Unit>(
                    "/seller/products",
                    RegisterProductCommandGenerator.generate(imageUri = imageUri),
                )

        // Assert
        assertThat(response.statusCode).isEqualTo(HttpStatus.BAD_REQUEST)
    }

    @Test
    fun `올바르게 요청하면 등록된 상품 정보에 접근하는 Location 헤더를 반환한다`(
        @Autowired fixture: TestFixture,
    ) {
        // Arrange
        fixture.createSellerThenSetAsDefaultUser()

        // Act
        val response =
            fixture.client
                .postForEntity<Unit>(
                    "/seller/products",
                    RegisterProductCommandGenerator.generate(),
                )

        // Assert
        val actual = response.headers.location
        assertThat(actual).isNotNull
        assertThat(actual!!.isAbsolute).isFalse
        assertThat(actual.path)
            .startsWith("/seller/products/")
            .matches(endsWithUUID())
    }

    private fun endsWithUUID(): Predicate<String> {
        return Predicate { path ->
            val segments = path.split("/")
            if (segments.isEmpty()) return@Predicate false
            val lastSegment = segments.last()
            try {
                UUID.fromString(lastSegment)
                true
            } catch (e: IllegalArgumentException) {
                false
            }
        }
    }
}
