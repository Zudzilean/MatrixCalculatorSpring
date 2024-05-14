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
            for (int j = 0; j < expected[i].length; j++) {
                if (expected[i][j] != actual[i][j]) {
                    System.out.println("Mismatch in row " + i + " and colum " + j);
                }
            }
        }


    }}

