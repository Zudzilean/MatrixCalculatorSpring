package com.zudzilean.matrixspring.controller;

import com.zudzilean.matrixspring.domain.MatrixRequest;
import com.zudzilean.matrixspring.factory.MatrixFactory;
import com.zudzilean.matrixspring.service.MatrixService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/matrix")
public class MatrixController {

    private final MatrixService matrixService;

    @Autowired
    public MatrixController(MatrixService matrixService, MatrixFactory matrixFactory) {
        this.matrixService = matrixService;
    }

    @PostMapping("/calculate")
    public ResponseEntity<?> calculateMatrix(@RequestBody MatrixRequest request) {
        try {
            // 直接调用MatrixRequest的getMatrices方法获取已经创建好的矩阵列表
            List<double[][]> matrices = request.getMatrices();

            // 调用服务层执行操作
            return matrixService.performOperation(request.getOperationType(), matrices);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException("An unexpected error occurred", e);
        }
    }
}