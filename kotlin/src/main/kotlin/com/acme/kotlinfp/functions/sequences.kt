package functions

fun isEven(i: Int) = i % 2 == 0

fun main() {
    val numbers = 1..100
    numbers.asSequence()
        .map { it + 2 }
        .filter(::isEven)
        .map { "Number $it is even." }
        .take(5)
        .forEach(::println)
}