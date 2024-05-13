package com.zudzilean.matrixspring.controller;

import com.zudzilean.matrixspring.pojo.MatrixCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import lombok.Data;

@RestController
@RequestMapping("/api/matrix")
public class MatrixController {

    private final MatrixCalculator matrixCalculator;

    @Autowired
    public MatrixController(MatrixCalculator matrixCalculator) {
        this.matrixCalculator = matrixCalculator;
    }

    // 接收矩阵加法请求
    @PostMapping("/add")
    public Result add(@RequestBody MatrixInput input) {
        try {
            double[][] result = matrixCalculator.add(input.getMatrixA(), input.getMatrixB());
            return new Result(true, "Addition successful", result);
        } catch (IllegalArgumentException e) {
            return new Result(false, e.getMessage(), null);
        }
    }

    // 接收矩阵减法请求
    @PostMapping("/subtract")
    public Result subtract(@RequestBody MatrixInput input) {
        try {
            double[][] result = matrixCalculator.subtract(input.getMatrixA(), input.getMatrixB());
            return new Result(true, "Subtraction successful", result);
        } catch (IllegalArgumentException e) {
            return new Result(false, e.getMessage(), null);
        }
    }

    // 接收矩阵乘法请求
    @PostMapping("/multiply")
    public Result multiply(@RequestBody MatrixInput input) {
        try {
            double[][] result = matrixCalculator.multiply(input.getMatrixA(), input.getMatrixB());
            return new Result(true, "Multiplication successful", result);
        } catch (IllegalArgumentException e) {
            return new Result(false, e.getMessage(), null);
        }
    }

    @Data
    public static class MatrixInput {
        private double[][] matrixA;
        private double[][] matrixB;
    }

    @Data
    public static class Result {
        private boolean success;
        private String message;
        private double[][] matrixResult;

        public Result(boolean success, String message, double[][] matrixResult) {
            this.success = success;
            this.message = message;
            this.matrixResult = matrixResult;
        }
    }
}