package com.zudzilean.matrixspring.service;

public class MatrixOutput {

    /**
     * 打印二维矩阵。
     *
     * @param matrix 二维矩阵
     */
    public static void printMatrix(double[][] matrix) {
        if (matrix == null) {
            System.out.println("Matrix is null.");
            return;
        }
        for (double[] row : matrix) {
            for (double val : row) {
                System.out.print(val + " ");
            }
            System.out.println();
        }
    }
}