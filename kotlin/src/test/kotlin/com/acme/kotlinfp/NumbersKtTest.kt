package com.acme.kotlinfp

import org.junit.Test
import kotlin.test.assertEquals

internal class NumbersKtTest {

    @Test
    fun add() {
        assertEquals(add(1, 4), 5)
    }
}