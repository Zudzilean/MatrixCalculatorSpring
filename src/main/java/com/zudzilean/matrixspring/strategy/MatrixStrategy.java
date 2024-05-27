package com.zudzilean.matrixspring.strategy;

import java.util.List;

/**
 * @author
 * @since 2024/5/27
 */
public interface MatrixStrategy {
    List<List<Double>> executeSingleMatrixOperation(double[][] matrix, String operation);
    List<List<Double>> executeDoubleMatrixOperation ( double[][] matrixA, double[][] matrixB, String operation);
}
