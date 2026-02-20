package loopers.p34691

private const val END = "end"

private val responses =
    mapOf(
        "animal" to "Panthera tigris",
        "tree" to "Pinus densiflora",
        "flower" to "Forsythia koreana",
    )

fun main() {
    generateSequence(::readlnOrNull)
        .takeWhile { it != END }
        .forEach { println(responses.getOrDefault(it, "")) }
}
