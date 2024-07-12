package org.example.section3

class House(
    private val address: String,
    var livingRoom: LivingRoom = LivingRoom(10.0),
) {
    // Java의 static 중첩 클래스와 동일합니다.
    class LivingRoom(
        private var area: Double
    )

    // Java의 내부 클래스와 동일합니다.
    inner class Bedroom(
        private var area: Double
    ) {
        // 바깥 클래스 참조를 위해 this@바깥클래스를 사용
        val address: String
            get() = this@House.address
    }
}