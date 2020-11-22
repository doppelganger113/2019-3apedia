package generics

open class Animal

class Dog : Animal()
class Cat : Animal()

fun <T> copyData(source: MutableList<out T>, destination: MutableList<in T>) {
    source.forEach(destination::add)
}

interface Consumer<out T> {
    fun handle(e: String): T
}

fun main() {
    val animals = mutableListOf(Cat(), Dog())
    val dogs = mutableListOf(Dog())
    val cats = mutableListOf(Cat())

    copyData(cats, animals)
    copyData(dogs, animals)

//    copyData(cats, dogs)
}