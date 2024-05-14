package com.zudzilean.matrixspring.service;

/**
 * 实现矩阵相关计算
 */
public interface MatrixCalculatorV1 {

    /**
     * 矩阵加法
     *
     * @param matrixA 二维数组
     * @param matrixB 二维数组
     * @return 二维数组
     */
    double[][] add(double[][] matrixA, double[][] matrixB);

    /**
     * 矩阵减法
     *
     * @param matrixA 二维数组
     * @param matrixB 二维数组
     * @return 二维数组
     */
    double[][] subtract(double[][] matrixA, double[][] matrixB);

    /**
     * 矩阵乘法
     *
     * @param matrixA 二维数组
     * @param matrixB 二维数组
     * @return 二维数组
     */
    double[][] multiply(double[][] matrixA, double[][] matrixB);
}
