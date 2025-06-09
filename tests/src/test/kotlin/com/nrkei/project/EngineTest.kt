package com.example.tests

import com.example.engine.Engine
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class EngineTest {
    @Test
    fun adds() {
        assertEquals(5, Engine.compute(2, 3))
    }
}
