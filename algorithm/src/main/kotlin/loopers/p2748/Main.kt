package loopers.p2748

fun main() {
    val n = readln()
    println(fibonacci(n.toInt()))
}

tailrec fun fibonacci(
    n: Int,
    a: Long = 0,
    b: Long = 1,
): Long = if (n == 0) a else fibonacci(n - 1, b, a + b)
