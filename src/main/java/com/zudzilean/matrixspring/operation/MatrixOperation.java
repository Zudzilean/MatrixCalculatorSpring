package com.zudzilean.matrixspring.operation;

import com.zudzilean.matrixspring.domain.MatrixRequest;
import com.zudzilean.matrixspring.service.MatrixCalculatorV1;
import org.springframework.http.ResponseEntity;

/**
 * MatrixOperation 接口定义了执行矩阵操作的策略。
 */
public interface MatrixOperation {
    /**
     * 根据提供的矩阵请求和计算器执行特定的矩阵操作。
     *
     * @param calculator 实现了矩阵计算逻辑的 MatrixCalculatorV1 接口。
     * @param request 包含矩阵数据和所需操作的 MatrixRequest 对象。
     * @return 包含操作结果的 ResponseEntity 对象。
     */
    ResponseEntity<?> execute(MatrixCalculatorV1 calculator, MatrixRequest request);
}

