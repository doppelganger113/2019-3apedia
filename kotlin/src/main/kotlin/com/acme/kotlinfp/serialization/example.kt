package com.acme.kotlinfp.serialization

import kotlinx.serialization.*
import kotlinx.serialization.json.*

interface Named {
    val name: String
    val surname: String
    val fullName get() = "${this.name} ${this.surname}"
}

@Serializable
data class User(
    override val name: String,
    override val surname: String,
    val age: Int? = 0
) : Named

fun main(args: Array<String>) {
    val user = User("John", "Doe", 25)

    println(Json.encodeToString(user))
    val jsoned = Json.decodeFromString<User>("{\"name\": \"Mike\", \"surname\": \"Johnson\"}")
    println(jsoned)
    println(jsoned.fullName)
}