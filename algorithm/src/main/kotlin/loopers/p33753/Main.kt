package loopers.p33753

fun main() {
    val (a, b, c) = readln().split(" ").map { it.toInt() }
    var answer = a
    var t = readln().toInt()

    t -= 30

    while (t > 0) {
        answer += c
        t -= b
    }

    println(answer)
}
