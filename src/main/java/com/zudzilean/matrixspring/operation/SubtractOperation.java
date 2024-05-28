package com.zudzilean.matrixspring.operation;

/**
 * @author
 * @since 2024/5/28
 */
public class SubtractOperation implements MatrixOperation {
    @Override
    public double[][] apply(double[][] matrix) {
        throw new UnsupportedOperationException("Subtraction does not support single matrix operation.");

    }

    /**
     * 对两个矩阵进行减法运算。
     *
     * @param matrixA 被减矩阵。
     * @param matrixB 减去的矩阵。
     * @return 两个矩阵相减的结果矩阵。
     * @throws IllegalArgumentException 如果矩阵尺寸不匹配，无法进行减法运算。
     */
    @Override
    public double[][] apply(double[][] matrixA, double[][] matrixB) {
        if (matrixA.length != matrixB.length || matrixA[0].length != matrixB[0].length) {
            throw new IllegalArgumentException("Matrices must be the same size for subtraction.");
        }
        int size = matrixA.length;
        double[][] result = new double[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                result[i][j] = matrixA[i][j] - matrixB[i][j];
            }
        }
        return result;
    }

}
