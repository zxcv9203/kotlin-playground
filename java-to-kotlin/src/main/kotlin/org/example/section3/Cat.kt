package org.example.section3

class Cat(
    species: String
) : Animal(species, 4) {

    override fun move() {
        println("cat move")
    }

}