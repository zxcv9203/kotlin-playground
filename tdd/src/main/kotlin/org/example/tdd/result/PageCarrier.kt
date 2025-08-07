package org.example.tdd.result

data class PageCarrier<T>(
    val items: Array<T>,
    val continuationToken: String,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is PageCarrier<*>) return false

        if (!items.contentEquals(other.items)) return false

        return true
    }

    override fun hashCode(): Int = items.contentHashCode()
}
