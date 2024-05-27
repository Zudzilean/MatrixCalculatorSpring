package com.zudzilean.matrixspring.strategy;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MatrixOperationStrategyImpl
        extends com.zudzilean.matrixspring.operation.MatrixOperationImpl
        implements MatrixOperationStrategy {

    @Override
    public double[][] execute(String operation, List<double[][]> matrices) {
        if (operation == null || matrices == null) {
            throw new IllegalArgumentException("Operation cannot be null and matrices list cannot be null.");
        }

        switch (operation.toLowerCase()) {
            case "add":
            case "subtract":
            case "multiply":
                return performBinaryOperation(operation, matrices);
            case "simplify":
            case "determinant":
            case "inverse":
            case "transpose":
                return performUnaryOperation(operation, matrices);
            default:
                throw new UnsupportedOperationException("Operation '" + operation + "' is not supported.");
        }
    }

    private double[][] performBinaryOperation(String operation, List<double[][]> matrices) {
        if (matrices.size() < 2) {
            throw new IllegalArgumentException("Binary operation requires at least two matrices.");
        }
        double[][] m1 = matrices.get(0);
        double[][] m2 = matrices.get(1);
        switch (operation) {
            case "add":
                return  super.add(m1, m2);
            case "subtract":
                return super.subtract(m1, m2);
            case "multiply":
                return super.multiply(m1, m2);
            default:
                throw new UnsupportedOperationException("Operation '" + operation + "' is not supported for binary operations.");
        }
    }

    private double[][] performUnaryOperation(String operation, List<double[][]> matrices) {
        if (matrices.isEmpty()) {
            throw new IllegalArgumentException("Unary operation requires at least one matrix.");
        }
        double[][] m = matrices.get(0);
        switch (operation) {
            case "simplify":
                return super.simplifyMatrix(m);
            case "determinant":
                return new double[][]{{super.determinant(m)}};
            case "inverse":
                return super.inverse(m);
            case "transpose":
                return super.transpose(m);
            default:
                throw new UnsupportedOperationException("Operation '" + operation + "' is not supported for unary operations.");
        }
    }



}