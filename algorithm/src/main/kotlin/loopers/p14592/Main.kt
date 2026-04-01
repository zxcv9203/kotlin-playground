package loopers.p14592

fun main() {
    val n = readln().toInt()

    var maxS = 0
    var maxC = 0
    var maxL = 0
    var winner = 0
    for (i in 0 until n) {
        val (s, c, l) = readln().split(" ").map { it.toInt() }

        if (maxS < s) {
            winner = i + 1
            maxS = s
            maxC = c
            maxL = l
        } else if (maxS == s && maxC > c) {
            winner = i + 1
            maxS = s
            maxC = c
            maxL = l
        } else if (maxS == s && maxC == c && maxL > l) {
            winner = i + 1
            maxS = s
            maxC = c
            maxL = l
        }
    }

    println(winner)
}
