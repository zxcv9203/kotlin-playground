package loopers.p2596

fun hammingDistance(a: String, b: String): Int =
    a.zip(b).count { (x, y) -> x != y }

fun decode(chunk: String, codes: Map<String, Char>): Char? =
    codes.entries.firstOrNull { (code, _) -> hammingDistance(chunk, code) <= 1 }?.value

fun main() {
    val codes = mapOf(
        "000000" to 'A',
        "001111" to 'B',
        "010011" to 'C',
        "011100" to 'D',
        "100110" to 'E',
        "101001" to 'F',
        "110101" to 'G',
        "111010" to 'H',
    )

    val n = readln().toInt()
    val input = readln()
    val result = StringBuilder()

    for (i in 0 until n) {
        val chunk = input.substring(i * 6, i * 6 + 6)
        val ch = decode(chunk, codes) ?: return println(i + 1)
        result.append(ch)
    }

    println(result)
}
