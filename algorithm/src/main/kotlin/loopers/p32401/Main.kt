package loopers.p32401

fun main() {
    readln()
    val t = readln()

    val answer = t.indices.filter { t[it] == 'A' }
        .zipWithNext()
        .count { (i, j) -> t.substring(i + 1, j).count { it == 'N' } == 1 }

    println(answer)
}