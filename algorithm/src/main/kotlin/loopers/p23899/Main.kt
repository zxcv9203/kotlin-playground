package loopers.p23899


fun main() {
    val n = readln().toInt()
    val elementsA = readln().split(" ").map { it.toInt() }.toMutableList()
    val elementsB = readln().split(" ").map { it.toInt() }.toMutableList()

    if (elementsA == elementsB) {
        println(1)
        return
    }

    for (last in elementsA.size - 1 downTo 1) {
        var maxIdx = 0
        for (i in 1..last) {
            if (elementsA[i] > elementsA[maxIdx]) maxIdx = i
        }
        if (last != maxIdx) {
            val tmp = elementsA[last]
            elementsA[last] = elementsA[maxIdx]
            elementsA[maxIdx] = tmp
        }
        if (elementsA == elementsB) {
            println(1)
            return
        }
    }
    println(0)
}
