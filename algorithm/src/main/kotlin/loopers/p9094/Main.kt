package loopers.p9094

fun main() {

    val t = readln().toInt()

    repeat(t) {
        val (n, m) = readln().split(" ").map { it.toInt() }
        var count = 0
        for (a: Int in 1 until n) {
            for (b: Int in a + 1 until n) {
                if ((a * a + b * b + m) % (a * b) == 0) {
                    count++
                }
            }
        }
        println(count)
    }
}
