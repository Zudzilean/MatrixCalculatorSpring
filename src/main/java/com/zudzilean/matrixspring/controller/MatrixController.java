package com.zudzilean.matrixspring.controller;

import com.zudzilean.matrixspring.domain.MatrixRequest;
import com.zudzilean.matrixspring.strategy.MatrixStrategy;
import com.zudzilean.matrixspring.strategy.MatrixStrategyImpl;
import com.zudzilean.matrixspring.util.MatrixInputUtils;
import com.zudzilean.matrixspring.util.MatrixOperationUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.zudzilean.matrixspring.constant.MatrixConstant.*;


/**
 *
 */
@RestController
@RequestMapping("/api/matrix")
public class MatrixController {
    MatrixStrategy strategy = new MatrixStrategyImpl();

    @PostMapping("/calculate")
    public ResponseEntity<?> calculateMatrix(@RequestBody MatrixRequest request) {
        try {
            // 验证矩阵A及其大小
            double[][] matrixA = MatrixInputUtils.validateMatrix(request.getMatrixA(), request.getSizeA());
            // 确认计算方式后，验证矩阵B及其大小
            double[][] matrixB = MatrixOperationUtils.validateMatrixB(request);

            // TODO 根据Operation获取到计算结果 策略模式

            return switch (request.getOperation()) {
                case ADD, SUBTRACT, MULTIPLY ->
                    // 需要两个矩阵
                        ResponseEntity.ok(strategy.executeDoubleMatrixOperation(matrixA, matrixB,request.getOperation()));
                case SIMPLIFY, DETERMINANT, INVERSE, TRANSPOSE ->
                    // 只要一个矩阵
                        ResponseEntity.ok(strategy.executeSingleMatrixOperation(matrixA,request.getOperation()));
                default -> throw new IllegalArgumentException("Unsupported operation: " + request.getOperation());
            };

        } catch (IllegalArgumentException e) {
            // 记录日志
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            // 记录日志
            throw new RuntimeException("An unexpected error occurred", e);
        }
    }

}

