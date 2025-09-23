package programmers.level0.l181926

fun solution(
    n: Int,
    control: String,
): Int = control.fold(n) { acc, ch -> acc + Command.of(ch).value }

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
        private val byCode = values().associateBy { it.command }

        fun of(ch: Char): Command = byCode[ch] ?: throw IllegalArgumentException("Invalid command: $ch")
    }
}
