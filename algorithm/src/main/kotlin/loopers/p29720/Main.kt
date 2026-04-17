package loopers.p29720

import kotlin.math.max

fun main() {
    val (n, m, k) = readln().split(" ").map { it.toInt() }

    val min = max(0, n - m * k)
    val max = n - m * (k - 1) - 1
    println("$min $max")
}