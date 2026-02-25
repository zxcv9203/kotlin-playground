package loopers.p25180

fun main() {
    val n = readln().toInt()
    val k = (n + 8) / 9
    println(if (k % 2 == 0 && n % 2 != 0) k + 1 else k)
}
