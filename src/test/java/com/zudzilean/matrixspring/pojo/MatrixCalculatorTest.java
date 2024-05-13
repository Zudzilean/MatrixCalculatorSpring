package com.zudzilean.matrixspring.pojo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MatrixCalculatorTest {

    @Test
    public void testAdd() {
        double[][] matrixA = {
                {1, 2},
                {3, 4}
        };
        double[][] matrixB = {
                {5, 6},
                {7, 8}
        };
        double[][] expectedSum = {
                {6, 8},
                {10, 12}
        };
        assertArrayEquals(expectedSum, MatrixCalculator.add(matrixA, matrixB));
    }

    @Test
    public void testSubtract() {
        double[][] matrixA = {
                {7, 8},
                {9, 10}
        };
        double[][] matrixB = {
                {5, 6},
                {7, 8}
        };
        double[][] expectedDifference = {
                {2, 2},
                {2, 2}
        };
        assertArrayEquals(expectedDifference, MatrixCalculator.subtract(matrixA, matrixB));
    }

    @Test
    public void testMultiply() {
        double[][] matrixA = {
                {1, 2},
                {3, 4}
        };
        double[][] matrixB = {
                {5, 6},
                {7, 8}
        };
        double[][] expectedProduct = {
                {19, 22},
                {43, 50}
        };
        assertArrayEquals(expectedProduct, MatrixCalculator.multiply(matrixA, matrixB));
    }

    // Helper method to assert array of arrays
    private void assertArrayEquals(double[][] expected, double[][] actual) {
        assertEquals(expected.length, actual.length);
        for (int i = 0; i < expected.length; i++) {
            assertArrayEquals(expected[i], actual[i], "Mismatch in row " + i);
        }
    }

    // Helper method to assert equality of two doubles
    private void assertArrayEquals(double[] expected, double[] actual, String message) {
        assertEquals(expected.length, actual.length, message);
        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i], actual[i], message + " at index " + i);
        }
    }
}