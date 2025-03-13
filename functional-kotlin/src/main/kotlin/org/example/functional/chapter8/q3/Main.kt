package org.example.functional.chapter8.q3

fun Shop.getWaitingCustomers(): List<Customer> = customers
    .filterNot {
        it.orders.all { order -> order.isDelivered }
    }

fun Shop.countProductSales(product: Product): Int =
    customers
        .flatMap { it.orders }
        .flatMap { it.products }
        .count { it == product }

fun Shop.getCustomers(minAmount: Double): List<Customer> =
    customers
        .filter {
            it.orders.flatMap { order -> order.products }
                .sumOf { product -> product.price } >= minAmount
        }

data class Shop(
    val name: String,
    val customers: List<Customer>
) {
}

data class Customer(
    val name: String,
    val city: City,
    val orders: List<Order>
)

data class Order(
    val products: List<Product>,
    val isDelivered: Boolean
)

data class Product(
    val name: String,
    val price: Double
)

data class City(
    val name: String
)

fun main() {
    val product = Product("product", 100.0)
    val shop = Shop(
        "shop",
        listOf(
            Customer(
                "customer1",
                City("city1"),
                listOf(
                    Order(
                        listOf(product),
                        false
                    )
                )
            ),
            Customer(
                "customer2",
                City("city2"),
                listOf(
                    Order(
                        listOf(product),
                        true
                    )
                )
            )
        )
    )
    println(shop.getWaitingCustomers())
    println(shop.countProductSales(product))
    println(shop.getCustomers(100.0))
}