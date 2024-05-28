package com.zudzilean.matrixspring.operation;

/**
 * @author
 * @since 2024/5/28
 */
public class TransposeOperation implements MatrixOperation {
    /**
     * 计算矩阵的转置。
     *
     * @param matrix 需要计算转置的矩阵。
     * @return 矩阵的转置。
     */
    @Override
    public double[][] apply(double[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        double[][] ans = new double[n][m];
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                ans[j][i] = matrix[i][j];
            }
        }
        return ans;
    }

    @Override
    public double[][] apply(double[][] matrixA, double[][] matrixB) {
        throw new UnsupportedOperationException("Transpose does not support double matrix operation.");
    }
}
