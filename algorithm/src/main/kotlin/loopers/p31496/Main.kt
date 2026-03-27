package loopers.p31496

fun main() {
    val (n, s) = readln().split(" ")
    var total = 0
    repeat(n.toInt()) {
        val (name, count) = readln().split(" ")
        if (s in name.split("_")) {
            total += count.toInt()
        }
    }
    println(total)
}
