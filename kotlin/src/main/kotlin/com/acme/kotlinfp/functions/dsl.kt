package com.acme.kotlinfp.functions

data class Server(var name: String = "", var port: Int = 8080, var metaData: MetaData? = null)
data class MetaData(var description: String)

fun server(fn: Server.() -> Unit): Server {
    val server = Server()
    server.fn()
    return server
}

fun metaData(fn: MetaData.() -> Unit): MetaData {
    val metadata = MetaData("")
    metadata.fn()
    return metadata
}


fun main() {
    println("hello")
    val myServer = server {
        name = "My server"
        port = 3000
        metaData {
            description = "Additional information"
        }
    }

    println(myServer)
}