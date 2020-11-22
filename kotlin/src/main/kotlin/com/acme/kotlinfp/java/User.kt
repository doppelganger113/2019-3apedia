package com.acme.kotlinfp.java

data class Address(val location: String)

data class User(val name: String, val age: Int, val address: Address?)
