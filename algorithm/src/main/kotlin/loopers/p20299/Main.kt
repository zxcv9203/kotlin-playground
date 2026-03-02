package loopers.p20299

fun main() {
    val (n, k, l) = readln().split(" ").map(String::toInt)

    val passed =
        List(n) { readln().split(" ").map(String::toInt) }
            .filter { (a, b, c) -> a >= l && b >= l && c >= l && a + b + c >= k }

    println(passed.size)
    println(passed.flatten().joinToString(" "))
}
