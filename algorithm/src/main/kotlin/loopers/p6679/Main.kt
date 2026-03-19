package loopers.p6679

import kotlin.math.absoluteValue

fun Int.toTwelveDigits(): String {
    if (this == 0) return "0"

    val digits = "0123456789AB"

    return generateSequence(absoluteValue) { it / 12 }
        .takeWhile { it > 0 }
        .map { digits[it % 12] }
        .toList()
        .reversed()
        .joinToString("")
        .let { if (this < 0) "-$it" else it }
}

fun Int.toSixteenDigits(): String {
    if (this == 0) return "0"

    val digits = "0123456789ABCDEFG"

    return generateSequence(absoluteValue) { it / 16 }
        .takeWhile { it > 0 }
        .map { digits[it % 16] }
        .toList()
        .reversed()
        .joinToString("")
        .let { if (this < 0) "-$it" else it }
}

fun String.sum(): Int {
    return this.map { it.digitToIntOrNull(16) ?: 0 }.sum()
}

fun main() {
    for (i in 1000..9999) {
        val tenDigits = i.toString().sum()
        val twelveDigits = i.toTwelveDigits().sum()
        val sixteenDigits = i.toSixteenDigits().sum()

        if (tenDigits == twelveDigits && tenDigits == sixteenDigits) {
            println(i)
        }
    }
}
