package com.formation.tags;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag("Production")
class TestThree {
    @Test
    void testThree() {
        System.out.println("Test 3");
    }
}
