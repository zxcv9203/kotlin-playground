package loopers.p34850

fun main() {
    val (x, y, p, a, b) = readln().split(" ").map { it.toLong() }

    var price = p + (y - 1) * b
    var revenue = 0L
    for (i in 0 until x) {
        revenue += price
        price -= a
    }
    println(revenue)
}