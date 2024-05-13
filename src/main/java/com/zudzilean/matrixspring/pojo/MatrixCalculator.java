package com.zudzilean.matrixspring.pojo;

public class MatrixCalculator {

    // 矩阵加法
    public static double[][] add(double[][] matrixA, double[][] matrixB) {
        if (matrixA.length != matrixB.length || matrixA[0].length != matrixB[0].length) {
            throw new IllegalArgumentException("Matrices must be the same size for addition.");
        }
        int size = matrixA.length;
        double[][] result = new double[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                result[i][j] = matrixA[i][j] + matrixB[i][j];
            }
        }
        return result;
    }

    // 矩阵减法
    public static double[][] subtract(double[][] matrixA, double[][] matrixB) {
        // 减法的逻辑与加法类似，只是操作不同
        // ...
        return add(matrixA, negate(matrixB));
    }

    // 矩阵乘法
    public static double[][] multiply(double[][] matrixA, double[][] matrixB) {
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

    // 矩阵转置（用于后续实现矩阵乘法）
    private static double[][] transpose(double[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        double[][] transposed = new double[cols][rows];
        for (int i = 0; i < cols; i++) {
            for (int j = 0; j < rows; j++) {
                transposed[i][j] = matrix[j][i];
            }
        }
        return transposed;
    }

    // 矩阵求负
    private static double[][] negate(double[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        double[][] negated = new double[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                negated[i][j] = -matrix[i][j];
            }
        }
        return negated;
    }



}