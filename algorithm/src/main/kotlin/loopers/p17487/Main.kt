package loopers.p17487

import kotlin.math.abs

private const val LEFT = "123456qwertyasdfgzxcvbQWERTYASDFGZXCVB"

fun main() {
    val s = readln()
    var l = s.count { it in LEFT }
    var r = s.count { !it.isWhitespace() && it !in LEFT }
    val flex = s.count { it == ' ' } + s.count { it.isUpperCase() }

    if (flex >= abs(l - r)) {
        val total = l + r + flex
        l = (total + 1) / 2
        r = total / 2
    } else if (l < r) l += flex else r += flex

    println("$l $r")
}