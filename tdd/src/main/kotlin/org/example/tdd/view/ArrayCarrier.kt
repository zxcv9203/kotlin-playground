package org.example.tdd.view

data class ArrayCarrier<T>(
    val items: Array<T>,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is ArrayCarrier<*>) return false

        if (!items.contentEquals(other.items)) return false

        return true
    }

    override fun hashCode(): Int = items.contentHashCode()
}
