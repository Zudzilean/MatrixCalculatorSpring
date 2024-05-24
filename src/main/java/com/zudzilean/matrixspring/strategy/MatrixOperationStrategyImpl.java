package com.zudzilean.matrixspring.strategy;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MatrixOperationStrategyImpl
        extends com.zudzilean.matrixspring.operation.MatrixOperationImpl
        implements MatrixOperationStrategy {

    @Override
    public double[][] execute(String operation, List<double[][]> matrices) {
        // 检查操作参数是否为空
        if (operation == null || matrices == null || matrices.isEmpty()) {
            throw new IllegalArgumentException("Operation or matrices list cannot be null or empty.");
        }

        switch (operation.toLowerCase()) {
            case "add":
                return super.add(matrices.get(0), matrices.get(1));
            case "subtract":
                return super.subtract(matrices.get(0), matrices.get(1));
            case "multiply":
                return super.multiply(matrices.get(0), matrices.get(1));
            case "simplify":
                return super.simplifyMatrix(matrices.getFirst());
            case "determinant":
                // 包装determinant方法返回的double数值到一个double[][]矩阵中
                double det = super.determinant(matrices.getFirst());
                return new double[][]{{det}};
            case "inverse":
                return super.inverse(matrices.getFirst());
            case "transpose":
                return super.transpose(matrices.getFirst());
            default:
                throw new UnsupportedOperationException("Operation '" + operation + "' is not supported.");
        }
    }



}