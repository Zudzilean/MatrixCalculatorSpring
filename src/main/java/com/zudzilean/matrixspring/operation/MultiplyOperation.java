package com.zudzilean.matrixspring.operation;

/**
 * @author
 * @since 2024/5/28
 */
public class MultiplyOperation implements MatrixOperation {
    @Override
    public double[][] apply(double[][] matrix) {
        throw new UnsupportedOperationException("Multiplication does not support single matrix operation.");

    }

    /**
     * 对两个矩阵进行乘法运算。
     *
     * @param matrixA 第一个矩阵。
     * @param matrixB 第二个矩阵。
     * @return 两个矩阵相乘的结果矩阵。
     * @throws IllegalArgumentException 如果矩阵的列数不匹配，无法进行乘法运算。
     */
    @Override
    public double[][] apply(double[][] matrixA, double[][] matrixB) {
        if (matrixA[0].length != matrixB.length) {
            throw new IllegalArgumentException("The number of columns in the first matrix must equal the number of rows in the second for multiplication.");
        }
        int rowsA = matrixA.length;
        int colsA = matrixA[0].length;
        int colsB = matrixB[0].length;
        double[][] result = new double[rowsA][colsB];
        for (int i = 0; i < rowsA; i++) {
            for (int j = 0; j < colsB; j++) {
                for (int k = 0; k < colsA; k++) {
                    result[i][j] += matrixA[i][k] * matrixB[k][j];
                }
            }
        }
        return result;
    }

}
