package org.example.tdd.api.seller.changecontactemail

import org.assertj.core.api.Assertions.assertThat
import org.example.tdd.api.seller.signup.EmailGenerator
import org.example.tdd.command.ChangeContactEmailCommand
import org.example.tdd.helper.CommerceApiTest
import org.example.tdd.helper.InvalidEmailSource
import org.example.tdd.helper.TestFixture
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.params.ParameterizedTest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.web.client.postForEntity
import org.springframework.http.HttpStatus
import kotlin.test.Test

@CommerceApiTest
@DisplayName("POST /seller/changeContactEmail")
class POSTSpecs {
    @Test
    fun `올바르게 요청하면 204 No Content를 반환한다`(
        @Autowired fixture: TestFixture,
    ) {
        fixture.createSellerThenSetAsDefaultUser()
        val contactEmail = EmailGenerator.generateEmail()

        val response =
            fixture.client
                .postForEntity<Unit>(
                    "/seller/changeContactEmail",
                    ChangeContactEmailCommand(contactEmail),
                )

        assertThat(response.statusCode).isEqualTo(HttpStatus.NO_CONTENT)
    }

    @ParameterizedTest
    @InvalidEmailSource
    fun `contactEmail 속성이 올바르게 지정되지 않으면 400 Bad Request를 반환한다`(
        contactEmail: String,
        @Autowired fixture: TestFixture,
    ) {
        fixture.createSellerThenSetAsDefaultUser()

        val response =
            fixture.client
                .postForEntity<Unit>(
                    "/seller/changeContactEmail",
                    ChangeContactEmailCommand(contactEmail),
                )

        assertThat(response.statusCode).isEqualTo(HttpStatus.BAD_REQUEST)
    }

    @Test
    fun `문의 이메일 주소를 올바르게 변경한다`(
        @Autowired fixture: TestFixture,
    ) {
        val seller = fixture.createSellerThenSetAsDefaultUser()
        val newContactEmail = EmailGenerator.generateEmail()

        fixture.client
            .postForEntity<Unit>(
                "/seller/changeContactEmail",
                ChangeContactEmailCommand(newContactEmail),
            )

        val updatedSeller = fixture.getSeller()
        assertThat(updatedSeller.contactEmail).isEqualTo(newContactEmail)
    }
}
