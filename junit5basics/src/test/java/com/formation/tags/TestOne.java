package com.formation.tags;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag("Development")
class TestOne {
    @Test
    void testOne() {
        System.out.println("Test 1");
    }
}
