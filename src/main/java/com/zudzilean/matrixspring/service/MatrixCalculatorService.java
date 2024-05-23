package com.zudzilean.matrixspring.service;

import com.zudzilean.matrixspring.domain.MatrixRequest;
import com.zudzilean.matrixspring.operation.MatrixOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * MatrixCalculatorService 类用于执行矩阵操作。
 */
@Service
public class MatrixCalculatorService {
    private final MatrixCalculatorV1 calculator;

    @Autowired
    public MatrixCalculatorService(MatrixCalculatorV1 calculator) {
        this.calculator = calculator;
    }

    /**
     * 执行矩阵操作。
     *
     * @param operation 矩阵操作的策略接口实现。
     * @param request 包含矩阵数据和操作请求的 MatrixRequest 对象。
     * @return 包含操作结果的 ResponseEntity 对象。
     */
    public ResponseEntity<?> performOperation(MatrixOperation operation, MatrixRequest request) {
        // 执行操作并返回结果
        return operation.execute(calculator, request);
    }
}