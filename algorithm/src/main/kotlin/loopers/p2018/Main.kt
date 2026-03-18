package loopers.p2018

fun main() {
    val n = readln().toInt()

    var count = 0
    var start = 1
    var sum = 0

    for (end in 1..n) {
        sum += end
        while (sum > n) sum -= start++
        if (sum == n) count++
    }

    println(count)
}
