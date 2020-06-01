package com.formation.tags;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag("Development")
class TestTwo {
    @Test
    void testTwo() {
        System.out.println("Test 2");
    }
}
