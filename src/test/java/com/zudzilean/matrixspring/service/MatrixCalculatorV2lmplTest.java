package com.zudzilean.matrixspring.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MatrixCalculatorV2lmplTest {
    // 确保这是实现类的名称
    private MatrixCalculatorV2 calculator = new MatrixCalculatorV2lmpl();

    @Test
    void simplifyMatrix() {
        // 定义一个测试矩阵
        double[][] testMatrix = {
                {1, 1, 1},
                {1, 2, 2},
                {1, 1, 2}
        };

        try {
            // 尝试化简矩阵
            calculator.simplifyMatrix(testMatrix);

            // 如果没有异常，检查结果
            double[][] expectedMatrix = {
                    {1, 0, 0},
                    {0, 1, 0},
                    {0, 0, 1}
            };

            // 检查化简后的矩阵是否与期望矩阵相等
            assertMatrixEquals(expectedMatrix, testMatrix, 0.001);
        } catch (Exception e) {
            // 确保异常是期望的类型
            assertEquals("No unique solution exists, matrix is singular.", e.getMessage());
        }
    }

    // 自定义断言方法，用于比较两个矩阵是否相等
    private void assertMatrixEquals(double[][] expected, double[][] actual, double delta) {
        assertNotNull(expected);
        assertNotNull(actual);
        assertEquals(expected.length, actual.length);
        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i].length, actual[i].length);
            for (int j = 0; j < expected[i].length; j++) {
                assertEquals(expected[i][j], actual[i][j], delta, "Values at " + i + ", " + j + " do not match.");
            }
        }
    }

    @Test
    public void testTranspose() {
        // 创建一个测试矩阵
        double[][] testMatrix = {
                {1, 2, 3},
                {4, 5, 6}
        };

        // 预期的转置矩阵结果
        double[][] expectedTranspose = {
                {1, 4},
                {2, 5},
                {3, 6}
        };

        // 实例化MatrixUtils类（假设您的transpose方法在这个类中）
        MatrixCalculatorV2lmpl utils = new MatrixCalculatorV2lmpl();

        // 调用transpose方法
        double[][] transposedMatrix = utils.transpose(testMatrix);

        // 验证转置后的矩阵是否与预期结果相匹配
        for (int i = 0; i < expectedTranspose.length; i++) {
            Assertions.assertArrayEquals(expectedTranspose[i], transposedMatrix[i], "Row " + i + " does not match.");
        }
    }


    @Test
    void testDeterminant1x1() {

        double[][] matrix1x1 = {{5}};
        double expected1x1 = 5;
        double actual1x1 = calculator.determinant(matrix1x1);
        assertEquals(expected1x1, actual1x1, "1x1 matrix determinant should be the single element.");
    }

    @Test
    void testDeterminant2x2() {

        double[][] matrix2x2 = {{1, 2}, {3, 4}};
        double expected2x2 = (1 * 4) - (2 * 3); // 2x2 matrix determinant calculation
        double actual2x2 = calculator.determinant(matrix2x2);
        assertEquals(expected2x2, actual2x2, "2x2 matrix determinant calculation is incorrect.");
    }

    @Test
    void testDeterminant3x3() {

        double[][] matrix3x3 = {{1, 2, 3}, {0, 1, 4}, {5, 6, 0}};
        double expected3x3 = (1 * (1 * 0 - 4 * 6) - 2 * (0 * 0 - 4 * 5) + 3 * (0 * 6 - 1 * 5));
        double actual3x3 = calculator.determinant(matrix3x3);
        assertEquals(expected3x3, actual3x3, "3x3 matrix determinant calculation is incorrect.");
    }

    @Test
    void testDeterminantNonSquareMatrix() {

        double[][] nonSquareMatrix = {{1, 2, 3}, {4, 5, 6}};
        assertThrows(IllegalArgumentException.class, () -> {
            calculator.determinant(nonSquareMatrix);
        }, "Determinant calculation should throw an exception for non-square matrices.");
    }

    @Test
    void testDeterminantEmptyMatrix() {

        double[][] emptyMatrix = {};
        assertThrows(IllegalArgumentException.class, () -> {
            calculator.determinant(emptyMatrix);
        }, "Determinant calculation should throw an exception for empty matrices.");
    }


    @Test
    void inverse() {
        // 测试逆矩阵计算是否正确
        double[][] matrix = {
                {1, 2, 4},
                {4, 6, 8},
                {8, 10, 12}
        };
        double[][] expectedInverse = {
                {1, -2, 1},
                {-2,  2.5,  -1},
                {1,  -0.75,   0.25}
        };

        try {
            double[][] actualInverse = calculator.inverse(matrix);
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix.length; j++) {
                    assertEquals(expectedInverse[i][j], actualInverse[i][j], 0.000001,
                            "Inverse matrix element at [" + i + "][" + j + "] is incorrect.");
                }
            }
        } catch (Exception e) {
            fail("Exception was thrown during inverse matrix calculation: " + e.getMessage());
        }
    }
}