package com.zudzilean.matrixspring.service;


import org.springframework.http.ResponseEntity;

import java.util.List;

public interface MatrixService {
    /**
     * 执行矩阵操作。
     *
     * @param matrices 包含矩阵数据和所需操作的 MatrixRequest 对象。
     * @return 包含操作结果的 ResponseEntity 对象。
     */


    ResponseEntity<?> performOperation(String operation, List<double[][]> matrices);
}