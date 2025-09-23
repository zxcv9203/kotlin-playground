package programmers.level0.l181925

fun solution(numLog: IntArray): String {
    val answer =
        buildString {
            for (i in 1 until numLog.size) {
                val command = Command.of((numLog[i] - numLog[i - 1]))
                append(command.command)
            }
        }
    return answer
}

enum class Command(
    val command: Char,
    val value: Int,
) {
    W('w', 1),
    S('s', -1),
    D('d', 10),
    A('a', -10),
    ;

    companion object {
        private val byValue = values().associateBy { it.value }

        fun of(value: Int): Command = byValue[value] ?: throw IllegalArgumentException("Invalid command: $value")
    }
}
