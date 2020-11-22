package com.acme.exercises.file

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import java.io.File

const val fileName = "data/hearthstone_data_set.json"

fun main() {
    // Read from file
    val file = File(fileName)
    if (!file.isFile) {
        println("Invalid fileName: $fileName")
        return
    }

    val jsonEncoder = Json { ignoreUnknownKeys = true }
    val string = file.readText()
    val dump = jsonEncoder.decodeFromString<DataDump>(string)
    println(dump)
}
