package com.zudzilean.matrixspring.strategy;

import com.zudzilean.matrixspring.domain.MatrixRequest;
import com.zudzilean.matrixspring.service.MatrixCalculatorV1;
import com.zudzilean.matrixspring.service.MatrixCalculatorV1Impl;

import java.util.List;

import static com.zudzilean.matrixspring.util.MatrixOperationUtils.convertMatrixToListOfLists;

/**
 * @author
 * @since 2024/5/27
 */
public class MatrixStrategyImpl implements MatrixStrategy {
    private final MatrixRequest request = new MatrixRequest(); ;
    private final MatrixCalculatorV1 calculatorV1 = new MatrixCalculatorV1Impl();

    public List<List<Double>> executeSingleMatrixOperation(double[][] matrix, String operation) {
        //simplify", "determinant", "inverse", "transpose
        return switch (operation) {
            case "simplify" -> convertMatrixToListOfLists(calculatorV1.simplify(matrix));
            case "determinant" -> convertMatrixToListOfLists(calculatorV1.determinant(matrix));
            case "inverse" -> convertMatrixToListOfLists(calculatorV1.inverse(matrix));
            case "transpose" -> convertMatrixToListOfLists(calculatorV1.transpose(matrix));
            // 其他操作可以在这里添加
            default -> throw new IllegalStateException("Unexpected value: " + request.getOperation());
        };
    }

    public List<List<Double>> executeDoubleMatrixOperation ( double[][] matrixA, double[][] matrixB,String operation) {
        // 根据operation调用不同的方法
        return switch (operation) {
            case "add" -> convertMatrixToListOfLists(calculatorV1.add(matrixA, matrixB));
            case "subtract" -> convertMatrixToListOfLists(calculatorV1.subtract(matrixA, matrixB));
            case "multiply" -> convertMatrixToListOfLists(calculatorV1.multiply(matrixA, matrixB));
            // 其他操作可以在这里添加
            default -> throw new IllegalStateException("Unexpected value: " + request.getOperation());
        };
    }
}
