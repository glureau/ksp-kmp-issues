package ksp.example

import kotlin.test.Test
import kotlin.test.assertEquals

class FooBarTest {

    @Test
    fun test() {
        assertEquals(2, FooBar().compute())
    }
}
