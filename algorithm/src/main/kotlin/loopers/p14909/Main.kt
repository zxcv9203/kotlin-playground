package loopers.p14909

fun Int.isPositive(): Boolean {
    return this > 0
}

fun main() {
    val numbers = readln().split(" ").map { it.toInt() }

    val positiveCount = numbers.count { it.isPositive() }

    println(positiveCount)
}
