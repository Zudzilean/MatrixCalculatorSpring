package com.zudzilean.matrixspring.service;

/**
 * 实现矩阵之间的计算
 */
public interface MatrixCalculatorV1 {

    /**
     * 矩阵加法
     *
     * @param matrixA 二维数组
     * @param matrixB 二维数组
     * @return 二维数组
     */
     double[][] add(double[][] matrixA, double[][] matrixB) ;

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
     double[][] multiply(double[][] matrixA, double[][] matrixB) ;

    //二维矩阵化简并打印过程 // TODO: 注释调整
    double[][] simplifyMatrix(double[][] matrix);

    //计算二维矩阵的det
    double determinant(double[][] matrix);

    //计算二维矩阵的inverse
    double[][] inverse(double[][] matrix) throws Exception; // 可能需要抛出异常以处理不可逆矩阵的情况

    //计算二维矩阵的转换
    double[][] transpose(double[][] matrix);

}
