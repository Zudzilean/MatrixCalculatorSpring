package com.zudzilean.matrixspring.service;

//矩阵内部的计算
public interface MatrixCalculatorV2 {
    //二维矩阵化简并打印过程
    double[][] simplifyMatrix(double[][] matrix);

    //计算二维矩阵的det
    double determinant(double[][] matrix);

    //计算二维矩阵的inverse
    double[][] inverse(double[][] matrix) throws Exception; // 可能需要抛出异常以处理不可逆矩阵的情况

    //计算二维矩阵的转换
    double[][] transpose(double[][] matrix);

}
