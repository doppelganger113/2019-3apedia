package classes_objects

fun main() {
    val s = Secondary("John")
    println(s.name)
    println(s.children)
    val t = Secondary("Mary", s)
    println(t.name)
    println(t.children)

    println(s.children)
}

// class User public @Inject constructor(name: String)

// Initialization block

class Person(name: String) {
    val firstProperty = "First property: ${name.also(::println)}"

    init {
        println("First init block that prints name $name")
    }

    val secondProperty = "Second property: ${name.length.also(::println)}"

    init {
        println("Second init block that prints ${name.length}")
    }

    val thirdProperty = name.toUpperCase()
}

// Secondary constructor with primary constructor

class Secondary(val name: String) {
    var children: MutableList<Secondary> = mutableListOf()

    constructor(name: String, parent: Secondary) : this(name) {
        parent.children.add(this)
    }
}


// Inheritance
open class Base(p: Int)
class Derived(p: Int) : Base(p)

// Overriding
open class Shape {
    open fun draw() {}
    fun fill() {}
}

class Circle() : Shape() {
    override fun draw() {
        super.draw()
    }
    // or use final override to prevent further overriding
}

interface MyContract {
    val vertexCount: Int
}

class Rectangle(override val vertexCount: Int = 4) : MyContract

class Polygon : MyContract {
    override var vertexCount = 0
}

// Accessing outer class
open class Filled {
    open fun draw() {
        println("Drawing a shape")
    }

    val borderColor: String get() = "black"
}

class FilledCircle : Filled() {
    override fun draw() {
        super.draw()
        println("Drawing the circle")
    }

    val fillColor: String get() = super.borderColor

    inner class Filler {
        fun fill() {}
        fun drawAndFill() {
            super@FilledCircle.draw()
            fill()
            println("Drawn a filled rectangle with color ${super@FilledCircle.borderColor}")
        }
    }
}