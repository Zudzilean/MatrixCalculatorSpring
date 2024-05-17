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
    public double[][] transpose(double[][] matrix);
    // 其他方法定义...
}
