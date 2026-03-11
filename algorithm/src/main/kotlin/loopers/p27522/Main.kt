package loopers.p27522

class CartRider(
    val hour: Int,
    val minute: Int,
    val second: Int,
    val team: String,
) {
    companion object {
        fun fromString(str: String): CartRider {
            val (time, team) = str.split(" ")
            val (hour, minute, second) = time.split(":").map { it.toInt() }
            return CartRider(hour, minute, second, team)
        }

    }
}

fun main() {
    val cartRiders = List(8) { CartRider.fromString(readln()) }
            .sortedWith(compareBy({ it.hour }, { it.minute }, { it.second }))
    val scores = intArrayOf(10, 8, 6, 5, 4, 3, 2, 1)
    var red = 0
    var blue = 0

    for (i in cartRiders.indices) {
        if (cartRiders[i].team == "R") {
            red += scores[i]
        } else {
            blue += scores[i]
        }
    }

    println(if(red > blue) "Red" else "Blue")
}
