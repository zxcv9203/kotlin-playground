package loopers.p15726


fun main() {
    val list = readln().split(" ").map { it.toLong() }

    val a = list[0] * list[2] / list[1]
    val b = list[0] * list[1] / list[2]

    val result = maxOf(a, b)

    println(result)
}
