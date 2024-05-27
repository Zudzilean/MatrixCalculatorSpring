package com.zudzilean.matrixspring.service;

import static org.junit.jupiter.api.Assertions.*;

import com.zudzilean.matrixspring.util.MatrixUtils;
import org.junit.jupiter.api.Test;

public class MatrixUtilsTest {

    @Test
    public void testValidateMatrixWithValidMatrix() {
        MatrixUtils matrixUtils = new MatrixUtils();
        int[] size = {3, 3}; // 期望的矩阵大小
        double[][] matrix = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };

        double[][] validatedMatrix = matrixUtils.validateMatrix(matrix, size);
        assertNotNull(validatedMatrix);
        assertArrayEquals(matrix, validatedMatrix);
    }

    @Test
    public void testValidateMatrixWithNullMatrix() {
        MatrixUtils matrixUtils = new MatrixUtils();
        int[] size = {3, 3}; // 期望的矩阵大小

        assertThrows(IllegalArgumentException.class, () -> {
            matrixUtils.validateMatrix(null, size);
        });
    }

    @Test
    public void testValidateMatrixWithInvalidSizeArray() {
        MatrixUtils matrixUtils = new MatrixUtils();
        double[][] matrix = {
                {1, 2},
                {3, 4}
        };
        int[] size = {2}; // 无效的大小数组

        assertThrows(IllegalArgumentException.class, () -> {
            matrixUtils.validateMatrix(matrix, size);
        });
    }

    @Test
    public void testValidateMatrixWithMatrixDimensionsMismatch() {
        MatrixUtils matrixUtils = new MatrixUtils();
        int[] size = {2, 2}; // 期望的矩阵大小与实际矩阵不匹配
        double[][] matrix = {
                {1, 2, 3},
                {4, 5, 6}
        };

        assertThrows(IllegalArgumentException.class, () -> {
            matrixUtils.validateMatrix(matrix, size);
        });
    }


}