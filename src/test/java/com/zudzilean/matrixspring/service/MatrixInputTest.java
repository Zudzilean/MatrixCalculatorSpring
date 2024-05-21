package com.zudzilean.matrixspring.service;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class MatrixInputTest {

    @Test
    public void testValidateMatrixWithValidMatrix() {
        MatrixInput matrixInput = new MatrixInput();
        int[] size = {3, 3}; // 期望的矩阵大小
        double[][] matrix = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };

        double[][] validatedMatrix = matrixInput.validateMatrix(matrix, size);
        assertNotNull(validatedMatrix);
        assertArrayEquals(matrix, validatedMatrix);
    }

    @Test
    public void testValidateMatrixWithNullMatrix() {
        MatrixInput matrixInput = new MatrixInput();
        int[] size = {3, 3}; // 期望的矩阵大小

        assertThrows(IllegalArgumentException.class, () -> {
            matrixInput.validateMatrix(null, size);
        });
    }

    @Test
    public void testValidateMatrixWithInvalidSizeArray() {
        MatrixInput matrixInput = new MatrixInput();
        double[][] matrix = {
                {1, 2},
                {3, 4}
        };
        int[] size = {2}; // 无效的大小数组

        assertThrows(IllegalArgumentException.class, () -> {
            matrixInput.validateMatrix(matrix, size);
        });
    }

    @Test
    public void testValidateMatrixWithMatrixDimensionsMismatch() {
        MatrixInput matrixInput = new MatrixInput();
        int[] size = {2, 2}; // 期望的矩阵大小与实际矩阵不匹配
        double[][] matrix = {
                {1, 2, 3},
                {4, 5, 6}
        };

        assertThrows(IllegalArgumentException.class, () -> {
            matrixInput.validateMatrix(matrix, size);
        });
    }


}