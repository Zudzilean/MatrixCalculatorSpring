package com.zudzilean.matrixspring.service;

//矩阵内部的计算
public class MatrixCalculatorV2lmpl implements MatrixCalculatorV2{
    //二维矩阵化简并打印过程
    public void simplifyMatrix() {

    }

    //计算二维矩阵的det行列式值
    public double determinant() {
        return 0;
    }

    //计算二维矩阵的inverse逆矩阵
    public double[][] inverse() throws Exception {
        return null;
    }

    //计算二维矩阵的转换
    public double[][] transpose(double[][] matrix) {
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
    public Complex[] eigenvalues(double[][] matrix) {
        return new Complex[0];
    }

    @Override
    public Pair<Complex[], double[][]> eigenvectors(double[][] matrix) {
        return null;
    }

    @Override
    public int rank(double[][] matrix) {
        return 0;
    }

    @Override
    public LUDecompositionResult luDecompose(double[][] matrix) {
        return null;
    }

    @Override
    public QRDecompositionResult qrDecompose(double[][] matrix) {
        return null;
    }

    @Override
    public double[] solveLinearSystem(double[][] a, double[] b) {
        return new double[0];
    }

}
