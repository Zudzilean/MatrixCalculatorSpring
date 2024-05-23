package com.zudzilean.matrixspring.service;

import static org.junit.jupiter.api.Assertions.*;

import com.zudzilean.matrixspring.util.MatrixInputUtils;
import org.junit.jupiter.api.Test;

public class MatrixInputUtilsTest {

    @Test
    public void testValidateMatrixWithValidMatrix() {
        MatrixInputUtils matrixInputUtils = new MatrixInputUtils();
        int[] size = {3, 3}; // 期望的矩阵大小
        double[][] matrix = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };

        double[][] validatedMatrix = matrixInputUtils.validateMatrix(matrix, size);
        assertNotNull(validatedMatrix);
        assertArrayEquals(matrix, validatedMatrix);
    }

    @Test
    public void testValidateMatrixWithNullMatrix() {
        MatrixInputUtils matrixInputUtils = new MatrixInputUtils();
        int[] size = {3, 3}; // 期望的矩阵大小

        assertThrows(IllegalArgumentException.class, () -> {
            matrixInputUtils.validateMatrix(null, size);
        });
    }

    @Test
    public void testValidateMatrixWithInvalidSizeArray() {
        MatrixInputUtils matrixInputUtils = new MatrixInputUtils();
        double[][] matrix = {
                {1, 2},
                {3, 4}
        };
        int[] size = {2}; // 无效的大小数组

        assertThrows(IllegalArgumentException.class, () -> {
            matrixInputUtils.validateMatrix(matrix, size);
        });
    }

    @Test
    public void testValidateMatrixWithMatrixDimensionsMismatch() {
        MatrixInputUtils matrixInputUtils = new MatrixInputUtils();
        int[] size = {2, 2}; // 期望的矩阵大小与实际矩阵不匹配
        double[][] matrix = {
                {1, 2, 3},
                {4, 5, 6}
        };

        assertThrows(IllegalArgumentException.class, () -> {
            matrixInputUtils.validateMatrix(matrix, size);
        });
    }


}