package com.zudzilean.matrixspring.controller;

import com.zudzilean.matrixspring.domain.MatrixRequest;
import com.zudzilean.matrixspring.service.MatrixCalculatorV1;
import com.zudzilean.matrixspring.strategy.MatrixOperationStrategy;
import com.zudzilean.matrixspring.strategy.MatrixOperationStrategyImpl;
import com.zudzilean.matrixspring.util.MatrixUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/matrix")
public class MatrixController {

    private final MatrixCalculatorV1 calculatorV1;
    private final MatrixOperationStrategy operationStrategy;

    @Autowired
    public MatrixController(MatrixCalculatorV1 calculatorV1, MatrixOperationStrategy operationStrategy) {
        this.calculatorV1 = calculatorV1;
        this.operationStrategy = operationStrategy;
    }

    @PostMapping("/calculate")
    public ResponseEntity<?> calculateMatrix(@RequestBody MatrixRequest request) {
        try {
            // 验证矩阵A及其大小
            double[][] matrixA = MatrixUtils.validateMatrix(request.getMatrixA(), request.getSizeA());
            // 根据请求的操作类型，可能需要矩阵B
            double[][] matrixB = request.getOperation() != null &&
                    (request.getOperation().equals("add") || request.getOperation().equals("subtract"))
                    ? MatrixUtils.validateMatrix(request.getMatrixB(), request.getSizeB())
                    : null;

            // 根据请求的操作类型执行不同的矩阵运算
            switch (request.getOperation()) {
                case "add":
                case "subtract":
                    // 使用策略模式执行加法或减法
                    return ResponseEntity.ok(convertMatrixToListOfLists(operationStrategy.execute(List.of(matrixA, matrixB))));
                case "multiply":
                case "simplify":
                case "determinant":
                case "inverse":
                case "transpose":
                    // 对于不需要矩阵B的操作，只传递矩阵A
                    return executeSingleMatrixOperation(request, matrixA);
                default:
                    throw new IllegalArgumentException("Unsupported operation: " + request.getOperation());
            }
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException("An unexpected error occurred", e);
        }
    }

    // 用于执行单个矩阵操作的私有方法
    private ResponseEntity<?> executeSingleMatrixOperation(MatrixRequest request, double[][] matrixA) {
        switch (request.getOperation()) {
            case "multiply":
                // 需要额外的逻辑来处理乘法，可能还需要矩阵B
                break;
            case "simplify":
                return ResponseEntity.ok(convertMatrixToListOfLists(calculatorV1.simplifyMatrix(matrixA)));
            case "determinant":
                double det = calculatorV1.determinant(matrixA);
                return ResponseEntity.ok(Map.of("determinant", det));
            case "inverse":
                double[][] inverseMatrix = calculatorV1.inverse(matrixA);
                if (inverseMatrix == null) {
                    throw new IllegalArgumentException("Matrix inverse is null");
                }
                return ResponseEntity.ok(convertMatrixToListOfLists(inverseMatrix));
            case "transpose":
                return ResponseEntity.ok(convertMatrixToListOfLists(calculatorV1.transpose(matrixA)));
            default:
                throw new IllegalArgumentException("Unsupported operation: " + request.getOperation());
        }
    }

    private List<List<Double>> convertMatrixToListOfLists(double[][] matrix) {
        return java.util.Arrays.stream(matrix)
                .map(row -> java.util.Arrays.stream(row).boxed().collect(Collectors.toList()))
                .collect(Collectors.toList());
    }
}