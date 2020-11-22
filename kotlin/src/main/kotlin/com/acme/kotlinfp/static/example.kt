package com.acme.kotlinfp.static

class Handler {
    companion object {
        @JvmStatic
        fun createHello(name: String): String {
            return "Hello $name"
        }
    }
}

object Other {
    fun createHello(name: String): String {
        return "Hello $name"
    }
}