package loopers.p11722

fun main() {
    val n = readln().toInt()
    val list = readln().split(" ").map(String::toInt)

    val dp = IntArray(n) { 1 }
    var answer = 1
    for (i in 0 until n) {
        dp[i] = (0 until i)
            .maxOfOrNull { j -> if (list[j] > list[i]) dp[j] + 1 else 1 } ?: 1
        answer = maxOf(answer, dp[i])
    }

    println(answer)
}
