package org.example.practicetesting.practice.unit.order

import org.example.practicetesting.practice.unit.beverage.Beverage
import java.time.LocalDateTime

class Order(
    val orderTime: LocalDateTime,
    val beverages: List<Beverage>
) {
}