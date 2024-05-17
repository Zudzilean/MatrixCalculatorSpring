package com.zudzilean.matrixspring.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MatrixCalculatorV2lmplTest {

    @Test
    void simplifyMatrix() {
        MatrixCalculatorV2 calculator = new MatrixCalculatorV2lmpl(); // 确保这是实现类的名称

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
                    {1, 1, 1},
                    {0, 1, 1},
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
}