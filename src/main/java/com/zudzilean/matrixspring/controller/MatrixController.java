package com.zudzilean.matrixspring.controller;

import com.zudzilean.matrixspring.service.MatrixInput;
import com.zudzilean.matrixspring.service.MatrixCalculatorV1Impl;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/matrix")
public class MatrixController {

    @PostMapping("/calculate")
    public ResponseEntity<?> calculateMatrix(@RequestBody MatrixRequest request) {
        try {
            // 确定矩阵A和矩阵B的大小
            int[] sizeA = MatrixInput.determineMatrixSize(request.getMatrixSizeA());
            int[] sizeB = MatrixInput.determineMatrixSize(request.getMatrixSizeB());

            // 根据确定的大小构建矩阵A和矩阵B的数值
            double[][] matrixA = MatrixInput.buildMatrixWithValues(request.getMatrixA(), sizeA);
            double[][] matrixB = MatrixInput.buildMatrixWithValues(request.getMatrixB(), sizeB);


            double[][] resultMatrix = null;
            switch (request.getOperation()) {
                case "add":
                    resultMatrix = MatrixCalculatorV1Impl.add(matrixA, matrixB);
                    break;
                case "subtract":
                    resultMatrix = MatrixCalculatorV1Impl.subtract(matrixA, matrixB);
                    break;
                case "multiply":
                    resultMatrix = MatrixCalculatorV1Impl.multiply(matrixA, matrixB);
                    break;
                default:
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Unsupported operation: " + request.getOperation());
            }

            // 将结果矩阵转换为 List of Lists 以返回 JSON 响应
            List<List<Double>> result = convertMatrixToListOfLists(resultMatrix);

            return ResponseEntity.ok(result);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    private List<List<Double>> convertMatrixToListOfLists(double[][] matrix) {
        return java.util.Arrays.stream(matrix)
                .map(row -> java.util.Arrays.stream(row).boxed().collect(Collectors.toList()))
                .collect(Collectors.toList());
    }

    // Lombok 注解来生成 getters, setters, toString 等
    @Data
    public static class MatrixRequest {
        private String matrixSizeA; // 矩阵A的大小字符串，如 "【1,2】"
        private String matrixA;      // 矩阵A的数值字符串，如 "【1,2】【3,4】"
        private String matrixSizeB;  // 矩阵B的大小字符串，如 "【2,2】"
        private String matrixB;      // 矩阵B的数值字符串，如 "【5,6】【7,8】"
        private String operation;    // 操作，如 "add", "subtract" 或 "multiply"
    }
}