package functions

fun interface IntPredicate {
    fun accept(i: Int): Boolean
}

val isEven = object : IntPredicate {
    override fun accept(i: Int): Boolean {
        return i % 2 == 0
    }
}

typealias Predicate<T> = (T) -> Boolean