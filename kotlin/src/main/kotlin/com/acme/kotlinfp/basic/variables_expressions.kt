package basic

fun main() {
    val x: Int = 5
    val y = 2.5
    var z = 1
    z = 5
}

// For loop/range

fun doFor() {
    val items = listOf("apple", "bananas", "orange")
    for (fruit in items) {
        println(fruit)
    }

    val y = 5
    val numbers = 1..10 + y
    for (number in numbers) {
        println(number)
    }

    val x = 2
    if (x in numbers) {
        println("x is numbers")
    }

    // Is number out of range?
    if (items.size !in items.indices) {
        println("items size is out of valid indices range")
    }

    // Stepping
    for (value in 1..10 step 2) {
        println(value)
    }
    for (value in 9 downTo 0 step 3) {
        println(value)
    }
}

// When expression
fun describe(obj: Any): String = when (obj) {
    1 -> "One"
    "Hello" -> "Greeting"
    is Long -> "Long"
    !is String -> "Not a string"
    else -> "Unknown"
}


// Conditional expressions

fun maxOf(a: Int, b: Int): Int {
    return if (a > b) {
        return a
    } else {
        return b
    }
}


// Nullable and null checks

fun parseInt(aNumber: String): Int? {
    return aNumber.toInt(10)
}

fun calculateAndPrint() {
    val a = parseInt("10")
    val b = parseInt("5")
    if (a == null) return
    if (b == null) return

    println(a * b)
}

// Type checks and automatic casts

fun getStringLength(obj: Any): Int? {
    if (obj is String) {
        return obj.length
    }

    return null
}



