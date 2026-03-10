package loopers.p12813

fun String.and(b: String): String = this.zip(b) { a, b -> if (a == '1' && b == '1') '1' else '0' }.joinToString("")

fun String.or(b: String): String = this.zip(b) { a, b -> if (a == '1' || b == '1') '1' else '0' }.joinToString("")

fun String.xor(b: String): String = this.zip(b) { a, b -> if (a != b) '1' else '0' }.joinToString("")

fun String.not(): String = this.map { if (it == '1') '0' else '1' }.joinToString("")

fun main() {
    val a = readln()
    val b = readln()

    println(a.and(b))
    println(a.or(b))
    println(a.xor(b))
    println(a.not())
    println(b.not())
}
