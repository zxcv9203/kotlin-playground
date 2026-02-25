package loopers.p25643

fun canStack(a: String, b: String): Boolean {
    val m = a.length
    for (len in 1..m)
        if (a.endsWith(b.take(len))) return true
    for (len in 1 until m)
        if (a.startsWith(b.takeLast(len))) return true
    return false
}

fun main() {
    val (n, _) = readln().split(" ").map { it.toInt() }
    val strings = List(n) { readln() }

    val result = strings.zipWithNext().all { (a, b) -> canStack(a, b) }
    println(if (result) 1 else 0)
}
