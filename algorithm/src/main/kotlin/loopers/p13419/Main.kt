package loopers.p13419

fun main() {
    repeat(readln().toInt()) {
        val s = readln()
        val n = s.length
        val len = if (n % 2 == 0) n / 2 else n
        for (start in 0..1) {
            println((0 until len).map { s[(start + it * 2) % n] }.joinToString(""))
        }
    }
}
