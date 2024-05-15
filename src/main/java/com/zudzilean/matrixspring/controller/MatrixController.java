package com.zudzilean.matrixspring.controller;

import com.zudzilean.matrixspring.service.MatrixCalculatorV1;
import com.zudzilean.matrixspring.service.MatrixCalculatorV1Impl;
import org.springframework.web.bind.annotation.*;
import lombok.Data;

@RestController
@RequestMapping("/api/matrix")
public class MatrixController {

    private MatrixCalculatorV1 calculatorV1 = new MatrixCalculatorV1Impl();
    // 接收矩阵加法请求
    @PostMapping("/add")
    public Result add(@RequestBody MatrixInput input) {
        try {
            calculatorV1.add(input.getMatrixA(), input.getMatrixB());

            double[][] result = calculatorV1.add(input.getMatrixA(), input.getMatrixB());

            return new Result(true, "Addition successful", result);
        } catch (IllegalArgumentException e) {
            return new Result(false, e.getMessage(), null);
        }
    }

    // 接收矩阵减法请求
    @PostMapping("/subtract")
    public Result subtract(@RequestBody MatrixInput input) {
        try {
            double[][] result = calculatorV1.subtract(input.getMatrixA(), input.getMatrixB());
            return new Result(true, "Subtraction successful", result);
        } catch (IllegalArgumentException e) {
            return new Result(false, e.getMessage(), null);
        }
    }

    // 接收矩阵乘法请求
    @PostMapping("/multiply")
    public Result multiply(@RequestBody MatrixInput input) {
        try {
            double[][] result = calculatorV1.multiply(input.getMatrixA(), input.getMatrixB());
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