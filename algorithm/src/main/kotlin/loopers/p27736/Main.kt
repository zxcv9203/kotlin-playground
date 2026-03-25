package loopers.p27736

fun main() {
    val n = readln().toInt()

    var agree = 0
    var disagree = 0
    var invalid = 0
    var count = 0
    readln()
        .split(" ")
        .map { it.toInt() }
        .forEach {
            count++
            when (it) {
                -1 -> {
                    disagree++
                }

                1 -> {
                    agree++
                }

                else -> {
                    invalid++
                }
            }
        }

    println(if (invalid * 2 >= count) "INVALID" else if (agree > disagree) "APPROVED" else "REJECTED")
}
