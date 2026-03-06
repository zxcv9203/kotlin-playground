package loopers.p34760

fun main() {
    val elephants = readln().split(" ").map { it.toInt() }.toList()

    val sortElephants = elephants.sortedByDescending { it }

    if (sortElephants[0] == elephants[elephants.size - 1] && sortElephants[0] != sortElephants[1]) {
        println(sortElephants[0])
    } else {
        println(sortElephants[0] + 1)
    }
}
