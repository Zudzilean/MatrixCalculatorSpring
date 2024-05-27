package com.zudzilean.matrixspring.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MatrixServiceImplTest {

    MatrixServiceImpl matrixCalculator = new MatrixServiceImpl();
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

        assertArrayEquals(matrixCalculator.add(matrixA, matrixB), expectedSum);
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
        assertArrayEquals(matrixCalculator.subtract(matrixA, matrixB), expectedDifference);
    }

    @Test
    void multiply() {
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
        assertArrayEquals(expectedProduct, matrixCalculator.multiply(matrixA, matrixB));

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
    @Test
    void testDeterminantFor1x1Matrix() {
        double[][] matrix = {{3}};
        double[][] expected = {{3}};
        double[][] actual = matrixCalculator.determinant(matrix);
        assertArrayEquals(expected, actual);
    }

    @Test
    void testDeterminantFor2x2Matrix() {
        double[][] matrix = {{1, 2}, {3, 4}};
        double[][] expected = {{-2}};
        double[][] actual = matrixCalculator.determinant(matrix);
        assertArrayEquals(expected, actual);
    }

    @Test
    void testDeterminantFor3x3Matrix() {
        double[][] matrix = {{1, 0, 2}, {0, 1, 3}, {2, 3, 4}};
        double[][] expected = {{-9}}; // 这里需要根据实际的行列式计算结果来设置期望值
        double[][] actual = matrixCalculator.determinant(matrix);
        assertArrayEquals(expected, actual);
    }


    @Test
    void testDeterminantForEmptyMatrix() {
        double[][] emptyMatrix = new double[0][0];
        assertThrows(IllegalArgumentException.class, () -> matrixCalculator.determinant(emptyMatrix));
    }

}