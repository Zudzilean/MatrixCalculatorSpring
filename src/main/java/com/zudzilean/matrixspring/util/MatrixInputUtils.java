package com.zudzilean.matrixspring.util;

public class MatrixInputUtils {

    private static final int MAX_SIZE = 10;
    private static final long MAX_VALUE = 1000000;


    public static double[][] validateMatrix(double[][] matrix, int[] size) throws IllegalArgumentException {
        // 检查 size 数组是否为 null 或长度不为 2
        if (size == null || size.length != 2) {
            throw new IllegalArgumentException("Size array must not be null and must have exactly two elements.");
        }
        int expectedRows = size[0];
        int expectedColumns = size[1];

        // 检查矩阵的期望大小是否有效
        if (expectedRows <= 0 || expectedRows > MAX_SIZE || expectedColumns <= 0 || expectedColumns > MAX_SIZE) {
            throw new IllegalArgumentException("Matrix dimensions must be between 1 and " + MAX_SIZE + " each.");
        }

        // 检查矩阵是否为 null
        if (matrix == null) {
            // 如果矩阵是 null，则抛出异常
            throw new IllegalArgumentException("Matrix cannot be null.");
        }

        // 检查矩阵是否符合给定的大小
        if (matrix.length != expectedRows || (matrix.length > 0 && matrix[0].length != expectedColumns)) {
            throw new IllegalArgumentException("Matrix dimensions do not match the provided size.");
        }

        // 检查矩阵的行是否具有相同的列数
        for (int i = 1; i < matrix.length; i++) {
            if (matrix[i].length != matrix[0].length) {
                throw new IllegalArgumentException("All rows in the matrix must have the same number of columns.");
            }
        }

        // 检查矩阵中的值是否在允许的范围内
        for (double[] row : matrix) {
            for (double value : row) {
                if (value < 0 || value > MAX_VALUE) {
                    throw new IllegalArgumentException("Value in matrix must be between 0 and " + MAX_VALUE + ".");
                }
            }
        }

        // 如果所有检查都通过，则返回矩阵
        return matrix;
    }

}