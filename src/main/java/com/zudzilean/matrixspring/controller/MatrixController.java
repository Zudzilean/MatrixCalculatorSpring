package com.zudzilean.matrixspring.controller;

import com.zudzilean.matrixspring.service.*;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/matrix")
public class MatrixController {

    private MatrixInput matrixInput = new MatrixInput();
    private MatrixCalculatorV1 calculatorV1 = new MatrixCalculatorV1Impl();

    @Data
    public static class MatrixRequest {
        private int[] sizeA;
        private double[][] matrixA;
        private int[] sizeB;
        private double[][] matrixB;
        private String operation;
    }

    @PostMapping("/calculate")
    public ResponseEntity<?> calculateMatrix(@RequestBody MatrixRequest request) {
        try {
            // 验证矩阵A及其大小
            double[][] matrixA = matrixInput.validateMatrix(request.getMatrixA(), request.getSizeA());
            // 确认计算方式后，验证矩阵B及其大小
            double[][] matrixB = ("add".equals(request.getOperation()) || "subtract".equals(request.getOperation()))
                    ? matrixInput.validateMatrix(request.getMatrixB(), request.getSizeB())
                    : null;

            // 根据操作类型执行不同的矩阵运算7
            switch (request.getOperation()) {
                case "add":
                    return ResponseEntity.ok(convertMatrixToListOfLists(calculatorV1.add(matrixA, matrixB)));
                case "subtract":
                    return ResponseEntity.ok(convertMatrixToListOfLists(calculatorV1.subtract(matrixA, matrixB)));
                case "multiply":
                    return ResponseEntity.ok(convertMatrixToListOfLists(calculatorV1.multiply(matrixA, matrixB)));
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
        } catch (IllegalArgumentException e) {
            // 记录日志
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            // 记录日志
            throw new RuntimeException("An unexpected error occurred", e);
        }
    }


    private List<List<Double>> convertMatrixToListOfLists(double[][] matrix) {
        return java.util.Arrays.stream(matrix)
                .map(row -> java.util.Arrays.stream(row).boxed().collect(Collectors.toList()))
                .collect(Collectors.toList());
    }
}