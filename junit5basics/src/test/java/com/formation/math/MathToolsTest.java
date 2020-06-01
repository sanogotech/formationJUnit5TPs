package com.formation.math;

import java.lang.IllegalArgumentException;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import com.formation.math.MathTools;

class MathToolsTest {
    @Test
    @DisplayName("Test successful decimal conversion")
    void testConvertToDecimalSuccess() {
        double result = MathTools.convertToDecimal(3, 4);
        Assertions.assertEquals(0.75, result);
    }

    @Test
    void testConvertToDecimalInvalidDenominator() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> MathTools.convertToDecimal(3, 0));
    }

    @Test
    void testIsEvenSuccessful() {
        Assertions.assertTrue(MathTools.isEven(2));
        Assertions.assertFalse(MathTools.isEven(1));
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 2, 4, 6, 8, 10, 100, 1000})
    void testIsEven(int number) {
        Assertions.assertTrue(MathTools.isEven(number));
    }

    @ParameterizedTest
    @MethodSource("generateEvenNumbers")
    void testIsEvenRange(int number) {
        Assertions.assertTrue(MathTools.isEven(number));
    }

    static IntStream generateEvenNumbers() {
        return IntStream.iterate(0, i -> i + 2).limit(500);
    }

}
