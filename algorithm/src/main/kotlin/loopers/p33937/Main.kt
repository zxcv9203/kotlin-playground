package loopers.p33937

private const val VOWELS = "aeiou"

fun firstSyllable(s: String): String? {
    val v = s.indexOfFirst { it in VOWELS }
    if (v == -1) return null
    val c = (v + 1 until s.length).firstOrNull { s[it] !in VOWELS } ?: return null
    return s.substring(0, c)
}

fun main() {

    val sa = firstSyllable(readln())
    val sb = firstSyllable(readln())

    println(if (sa != null && sb != null) sa + sb else "no such exercise")
}