package com.zudzilean.matrixspring.operation;

/**
 * @author
 * @since 2024/5/28
 */
public class DeterminantOperation implements MatrixOperation {
    /**
     * 计算矩阵的行列式。
     *
     * @param matrix 需要计算行列式的矩阵。
     * @return 矩阵的行列式值。
     * @throws IllegalArgumentException 如果矩阵不是方阵，无法计算行列式。
     */
    @Override
    public double[][] apply(double[][] matrix) {
        int n = matrix.length;
        if (n == 0 || n != matrix[0].length) {
            throw new IllegalArgumentException("Matrix must be non-empty and square.");
        }

        // 对于1x1矩阵，行列式就是矩阵中唯一的元素
        if (n == 1) {
            return convertNumberToMatrix(matrix[0][0]);
        }

        // 对于2x2矩阵，使用ad - bc公式计算行列式
        if (n == 2) {
            return convertNumberToMatrix(matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0]);
        }


        // 对于大于2x2的矩阵，使用递归的拉普拉斯展开
        return convertNumberToMatrix(laplacianExpansion(matrix));
    }

    //将一个数字转化为二维数组
    private double[][] convertNumberToMatrix(double n) {
        // 创建一个1x1的二维数组
        double[][] matrix = new double[1][1];

        // 将数字n赋值给数组的第一个元素
        matrix[0][0] = n;

        // 返回这个1x1的二维数组
        return matrix;
    }

    // 拉普拉斯展开递归方法
    private double laplacianExpansion(double[][] matrix) {
        double det = 0;
        double[][] subMatrix;
        for (int i = 0; i < matrix.length; i++) {
            // 创建子矩阵，排除第0行和第i列
            subMatrix = new double[matrix.length - 1][];
            for (int j = 1; j < matrix.length; j++) {
                subMatrix[j - 1] = new double[matrix[j].length - 1];
                for (int k = 0, col = 0; k < matrix[j].length; k++) {
                    if (k != i) {
                        subMatrix[j - 1][col++] = matrix[j][k];
                    }
                }
            }
            // 递归计算子矩阵的行列式，并累加结果
            double[][] deter =apply(subMatrix);
            det += Math.pow(-1, i) * matrix[0][i] * deter[0][0];
        }
        return det;
    }



    @Override
    public double[][] apply(double[][] matrixA, double[][] matrixB) {
        throw new UnsupportedOperationException("Determination does not support double matrix operation.");
    }
}
