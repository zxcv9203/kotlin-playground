package loopers.p32399

private val answers = mapOf(
    "(1)" to 0,
    "()1" to 1,
    "1()" to 1,
    "1)(" to 1,
    ")(1" to 1,
    ")1(" to 2,
)

fun main() {
    println(answers[readln()])
}
