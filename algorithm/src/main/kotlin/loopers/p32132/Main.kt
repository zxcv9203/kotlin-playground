package loopers.p32132

fun main() {
    val n = readln().toInt()

    val s = readln()

    val answer = s.fold(StringBuilder()) { acc, c ->
        if (acc.endsWith("PS") && c in "45") acc else acc.append(c)
    }

    println(answer)
}
