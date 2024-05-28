package com.zudzilean.matrixspring.strategy;

import com.zudzilean.matrixspring.domain.MatrixRequest;
import com.zudzilean.matrixspring.service.MatrixService;
import com.zudzilean.matrixspring.service.MatrixServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.zudzilean.matrixspring.util.MatrixOperationUtils.convertMatrixToListOfLists;

/**
 * @author
 * @since 2024/5/27
 */
@Service
public class MatrixStrategyImpl implements MatrixStrategy {

    private MatrixService calculatorV1 = new MatrixServiceImpl();

    @Autowired
    public void setCalculatorV1(MatrixService calculatorV1) {
        this.calculatorV1 = calculatorV1;
    }


    public List<List<Double>> executeSingleMatrixOperation(double[][] matrix, String operation) {
        //simplify", "determinant", "inverse", "transpose
        return switch (operation) {
            case "simplify" -> convertMatrixToListOfLists(calculatorV1.simplify(matrix));
            case "determinant" -> convertMatrixToListOfLists(calculatorV1.determinant(matrix));
            case "inverse" -> convertMatrixToListOfLists(calculatorV1.inverse(matrix));
            case "transpose" -> convertMatrixToListOfLists(calculatorV1.transpose(matrix));
            // 其他操作可以在这里添加
            default -> throw new IllegalStateException("Unexpected value: " + operation);
        };
    }

    public List<List<Double>> executeDoubleMatrixOperation(double[][] matrixA, double[][] matrixB, String operation) {
        // 根据operation调用不同的方法
        return switch (operation) {
            case "add" -> convertMatrixToListOfLists(calculatorV1.add(matrixA, matrixB));
            case "subtract" -> convertMatrixToListOfLists(calculatorV1.subtract(matrixA, matrixB));
            case "multiply" -> convertMatrixToListOfLists(calculatorV1.multiply(matrixA, matrixB));
            // 其他操作可以在这里添加
            default -> throw new IllegalStateException("Unexpected value: " + operation);
        };
    }

    // TODO:寻找另一种策略模式
}
