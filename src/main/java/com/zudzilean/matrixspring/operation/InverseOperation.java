package com.zudzilean.matrixspring.operation;

/**
 * @author
 * @since 2024/5/28
 */
public class InverseOperation implements MatrixOperation {
    private DeterminantOperation determinantOperation;
    private SimplifyOperation simplifyOperation;
    /**
     * 计算矩阵的逆矩阵。
     *
     * @param matrix 需要计算逆矩阵的方阵。
     * @return 矩阵的逆矩阵。
     * @throws IllegalArgumentException 如果矩阵不是方阵或不可逆。
     */
    @Override
    public double[][] apply(double[][] matrix) {
        int n = matrix.length;
        if (n == 0 || matrix[0].length != n) {
            throw new IllegalArgumentException("Matrix must be non-empty and square.");
        }

        // 检查矩阵是否可逆
        double[][] deter =determinantOperation.apply(matrix);
        if (deter[0][0] == 0) {
            throw new IllegalArgumentException("Matrix is not invertible because its determinant is zero.");
        }

        // 创建增广矩阵，左边是原矩阵，右边是单位矩阵
        double[][] augmentedMatrix = new double[n][2 * n];
        for (int i = 0; i < n; i++) {
            System.arraycopy(matrix[i], 0, augmentedMatrix[i], 0, n);
            for (int j = n; j < 2 * n; j++) {
                augmentedMatrix[i][j] = (i == (j - n)) ? 1 : 0;
            }
        }

        // 对增广矩阵进行高斯消元
        simplifyOperation.apply(augmentedMatrix);

        // 从增广矩阵中提取逆矩阵
        double[][] inverseMatrix = new double[n][n];
        for (int i = 0; i < n; i++) {
            System.arraycopy(augmentedMatrix[i], n, inverseMatrix[i], 0, n);
        }

        return inverseMatrix;
    }

    @Override
    public double[][] apply(double[][] matrixA, double[][] matrixB) {
        throw new UnsupportedOperationException("Inverse does not support double matrix operation.");
    }
}
