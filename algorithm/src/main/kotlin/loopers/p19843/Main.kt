package loopers.p19843

fun main() {
    val (t, n) = readln().split(" ").map { it.toInt() }

    val dayOffset =
        mapOf(
            "Mon" to 0,
            "Tue" to 24,
            "Wed" to 48,
            "Thu" to 72,
            "Fri" to 96,
        )

    var weekdaySleep = 0

    repeat(n) {
        val parts = readln().split(" ")
        val start = dayOffset[parts[0]]!! + parts[1].toInt()
        val end = dayOffset[parts[2]]!! + parts[3].toInt()
        weekdaySleep += end - start
    }

    val need = t - weekdaySleep
    println(
        when {
            need <= 0 -> 0
            need > 48 -> -1
            else -> need
        }
    )
}
