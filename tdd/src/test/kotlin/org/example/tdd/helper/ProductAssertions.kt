package org.example.tdd.helper

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.ThrowingConsumer
import org.example.tdd.command.RegisterProductCommand
import org.example.tdd.view.ProductView
import org.example.tdd.view.SellerProductView

object ProductAssertions {
    fun isDerivedFrom(command: RegisterProductCommand): ThrowingConsumer<SellerProductView> =
        ThrowingConsumer { view ->
            assertThat(view).isNotNull
            assertThat(view.name).isEqualTo(command.name)
            assertThat(view.description).isEqualTo(command.description)
            assertThat(view.priceAmount).matches { it.compareTo(command.priceAmount) == 0 }
            assertThat(view.imageUri).isEqualTo(command.imageUri)
            assertThat(view.stockQuantity).isEqualTo(command.stockQuantity)
        }

    fun isViewDerivedFrom(command: RegisterProductCommand): ThrowingConsumer<ProductView> =
        ThrowingConsumer { productView ->
            assertThat(productView).isNotNull
            assertThat(productView.id).isNotNull
            assertThat(productView.name).isEqualTo(command.name)
            assertThat(productView.description).isEqualTo(command.description)
            assertThat(productView.priceAmount).matches { it.compareTo(command.priceAmount) == 0 }
            assertThat(productView.imageUri).isEqualTo(command.imageUri)
            assertThat(productView.stockQuantity).isEqualTo(command.stockQuantity)
        }
}
