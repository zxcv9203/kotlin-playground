package loopers.p29720

import kotlin.math.max

fun main() {
    val (x, d) = readln().split(" ").map { it.toInt() }

    val min = max(0, 1000 - x * d)
    val max = 1000 - x * (d - 1) - 1
    println("$min $max")
}