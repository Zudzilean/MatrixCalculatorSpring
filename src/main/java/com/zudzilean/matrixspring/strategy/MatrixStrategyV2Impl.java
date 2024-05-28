package com.zudzilean.matrixspring.strategy;

import com.zudzilean.matrixspring.operation.MatrixOperation;
import com.zudzilean.matrixspring.operation.MatrixOperationMap;
import org.springframework.stereotype.Service;

/**
 * @author
 * @since 2024/5/28
 */
@Service
public class MatrixStrategyV2Impl implements MatrixStrategy {

    @Override
    public double[][] executeSingleMatrixOperation(double[][] matrix, String operation) {
        MatrixOperation op = MatrixOperationMap.getOperation(operation);
        if (op == null) {
            throw new IllegalStateException("Unexpected value: " + operation);
        }
        return op.apply(matrix);
    }

    @Override
    public double[][] executeDoubleMatrixOperation(double[][] matrixA, double[][] matrixB, String operation) {
        // 这里需要确保MatrixOperation接口支持双矩阵操作
        MatrixOperation op = MatrixOperationMap.getOperation(operation);
        if (op == null) {
            throw new IllegalStateException("Unexpected value: " + operation);
        }
        return op.apply(matrixA, matrixB);
    }
}