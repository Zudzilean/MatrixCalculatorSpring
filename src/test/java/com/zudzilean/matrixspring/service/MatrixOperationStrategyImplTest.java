package com.zudzilean.matrixspring.service;

import com.zudzilean.matrixspring.strategy.MatrixOperationStrategyImpl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MatrixOperationStrategyImplTest {

    MatrixOperationStrategyImpl matrixCalculator = new MatrixOperationStrategyImpl();
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
    void simplifyMatrixTest() {
        // 测试用例1：单位矩阵
        double[][] identityMatrix = {
                {1, 0, 0},
                {0, 1, 0},
                {0, 0, 1}
        };
        double[][] simplifiedIdentityMatrix = matrixCalculator.simplifyMatrix(identityMatrix);
        assertArrayEquals(identityMatrix, simplifiedIdentityMatrix);

        // 测试用例2：全零矩阵
        double[][] zeroMatrix = {
                {0, 0, 0},
                {0, 0, 0},
                {0, 0, 0}
        };
        double[][] simplifiedZeroMatrix = matrixCalculator.simplifyMatrix(zeroMatrix);
        assertArrayEquals(zeroMatrix, simplifiedZeroMatrix);

        // 测试用例3：具有特定结构的矩阵
        double[][] specificMatrix = {
                {2, 4, 1},
                {4, 6, 2},
                {6, 0, 3}
        };
        double[][] expectedSimplifiedMatrix = {
                {1, 0, 0.5},
                {0, 1, 0},
                {0, 0, 0}
        };
        double[][] simplifiedSpecificMatrix = matrixCalculator.simplifyMatrix(specificMatrix);

        double delta = 1e-10; // 根据需要选择合适的容差值

        for (int i = 0; i < expectedSimplifiedMatrix.length; i++) {
            for (int j = 0; j < expectedSimplifiedMatrix[i].length; j++) {
                // 四舍五入到小数点后两位
                double roundedExpected = Math.round(expectedSimplifiedMatrix[i][j] * 100.0) / 100.0;
                double roundedActual = Math.round(simplifiedSpecificMatrix[i][j] * 100.0) / 100.0;

                // 使用容差值比较两个浮点数是否相等
                assertEquals(roundedExpected, roundedActual, delta,
                        String.format(
                                "Mismatch at [%d][%d] Expected: %.1f, Actual: %.1f",
                                i, j, roundedExpected, roundedActual
                        ));
            }
        }

        assertArrayEquals(expectedSimplifiedMatrix, simplifiedSpecificMatrix, "Mismatch in simplified matrix");



    }

    private void assertArrayEquals(double[][] expected, double[][] actual, String message) {
        assertEquals(expected.length, actual.length, message + " - Number of rows mismatch");
        for (int i = 0; i < expected.length; i++) {
            assertArrayEquals(expected[i], actual[i], "Mismatch in row " + i + ": ");
        }
    }
}