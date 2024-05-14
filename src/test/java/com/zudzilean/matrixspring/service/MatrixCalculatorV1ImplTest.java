package com.zudzilean.matrixspring.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MatrixCalculatorV1ImplTest {

    private MatrixCalculatorV1 calculatorV1 = new MatrixCalculatorV1Impl();

    @Test
    void add() {
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
        // 静态方法和实例方法有什么区别，如何调用？
        // Java的多态和封装？

        assertArrayEquals(calculatorV1.add(matrixA, matrixB), expectedSum);
    }

    @Test
    void subtract() {
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
    }

    @Test
    void multiply() {
    }

    private void assertArrayEquals(double[][] expected, double[][] actual) {
        assertEquals(expected.length, actual.length);
        for (int i = 0; i < expected.length; i++) {
            assertArrayEquals(expected[i], actual[i], "Mismatch in row " + i);
        }
    }

    private void assertArrayEquals(double[] expected, double[] actual, String message) {
        assertEquals(expected.length, actual.length, message);
        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i], actual[i], message + " at index " + i);
        }
    }
}