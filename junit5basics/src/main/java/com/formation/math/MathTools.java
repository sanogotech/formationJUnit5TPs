package com.formation.math;

public class MathTools {
    public static double convertToDecimal(int numerator, int denominator) {
        if (denominator == 0) {
            throw new IllegalArgumentException("Denominator must not be 0");
        }
        return (double)numerator / (double)denominator;
    }

    public static boolean isEven(int number) {
        return number % 2 == 0;
    }
}
