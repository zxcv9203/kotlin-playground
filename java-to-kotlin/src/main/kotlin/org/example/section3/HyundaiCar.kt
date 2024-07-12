package org.example.section3

sealed class HyundaiCar(
    val name: String,
    val price: Long
)

class Sonata(
    name: String,
    price: Long
) : HyundaiCar(name, price)

class Grandeur(
    name: String,
    price: Long
) : HyundaiCar(name, price)

class Avante(
    name: String,
    price: Long
) : HyundaiCar(name, price)