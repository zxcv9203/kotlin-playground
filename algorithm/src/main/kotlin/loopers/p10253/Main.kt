package loopers.p10253

tailrec fun gcd(a: Long, b: Long): Long = if (b == 0L) a else gcd(b, a % b)

tailrec fun lastDenominator(a: Long, b: Long): Long {
    if (a == 1L) return b
    val x = (b + a - 1) / a
    val na = a * x - b
    val nb = b * x
    val g = gcd(na, nb)
    return lastDenominator(na / g, nb / g)
}

fun main() {
    repeat(readln().trim().toInt()) {
        val (a, b) = readln().trim().split(" ").map(String::toLong)
        println(lastDenominator(a, b))
    }
}