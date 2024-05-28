package com.zudzilean.matrixspring.strategy;

/**
 * @author
 * @since 2024/5/27
 */
public interface MatrixStrategy {
    double[][] executeSingleMatrixOperation(double[][] matrix, String operation);
    double[][] executeDoubleMatrixOperation (double[][] matrixA, double[][] matrixB, String operation);
}
