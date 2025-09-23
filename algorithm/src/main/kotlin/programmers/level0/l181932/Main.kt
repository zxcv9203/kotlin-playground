package programmers.level0.l181932

fun solution(code: String): String =
    buildString {
        var mode = 0
        code.forEachIndexed { i, ch ->
            if (ch == '1') {
                mode = mode xor 1
            } else if (i % 2 == mode) {
                append(ch)
            }
        }
    }.ifEmpty { "EMPTY" }
