package org.example.section3

class Penguin(
    species: String
) : Animal(species, 2), Flyable {

    private val wingCount: Int = 2

    override fun move() {
        println("penguin move")
    }

    override val legCount: Int
        get() = super.legCount + this.wingCount

    override fun act() {
        super<Flyable>.act()
    }
}