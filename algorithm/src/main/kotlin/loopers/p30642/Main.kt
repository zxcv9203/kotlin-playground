package loopers.p30642

fun main() {
    val n = readln().toInt()
    val mascot = readln()
    val k = readln().toInt()

    val toilet =
        when (mascot) {
            "annyong" -> if (k.isOdd()) k else k - 1
            "induck" -> if (k.isEven()) k else if (k == 1) k + 1 else k - 1
            else -> throw IllegalArgumentException("Invalid mascot")
        }

    println(toilet)
}

fun Int.isEven() = this % 2 == 0

fun Int.isOdd() = this % 2 != 0
