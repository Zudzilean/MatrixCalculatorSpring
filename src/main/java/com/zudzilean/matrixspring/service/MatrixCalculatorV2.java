package com.zudzilean.matrixspring.service;

//矩阵内部的计算
public interface MatrixCalculatorV2 {
    //二维矩阵化简并打印过程
    void simplifyMatrix();

    //计算二维矩阵的det
    double determinant();

    //计算二维矩阵的inverse
    double[][] inverse() throws Exception; // 可能需要抛出异常以处理不可逆矩阵的情况

    //计算二维矩阵的转换
    double[][] transpose(double[][] matrix);

    // 计算并返回矩阵的特征值
    Complex[] eigenvalues(double[][] matrix);

    // 计算并返回矩阵的特征向量
    Pair<Complex[], double[][]> eigenvectors(double[][] matrix);

    // 返回矩阵的秩
    int rank(double[][] matrix);

    // 对矩阵进行LU分解
    LUDecompositionResult luDecompose(double[][] matrix);

    // 对矩阵进行QR分解
    QRDecompositionResult qrDecompose(double[][] matrix);

    // 使用矩阵方法解线性方程组 Ax = b
    double[] solveLinearSystem(double[][] a, double[] b);

    // 辅助类，用于返回LU分解的结果
    class LUDecompositionResult {
        double[][] lMatrix;
        double[][] uMatrix;
        // 可能还需要pivots等信息
    }

    // 辅助类，用于返回QR分解的结果
    class QRDecompositionResult {
        double[][] qMatrix;
        double[][] rMatrix;
    }

    // 辅助类，用于表示复数特征值和特征向量
    class Complex {
        double real;
        double imaginary;

        Complex(double real, double imaginary) {
            this.real = real;
            this.imaginary = imaginary;
        }

        // 实现复数所需的方法，如加法、减法、乘法等
    }

    // 辅助类，用于存储特征值和特征向量
    class Pair<V, W> {
        public final V first;
        public final W second;

        Pair(V first, W second) {
            this.first = first;
            this.second = second;
        }
    }
}
