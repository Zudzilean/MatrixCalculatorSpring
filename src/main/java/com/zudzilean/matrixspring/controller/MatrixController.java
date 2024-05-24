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
    private final MatrixFactory matrixFactory;

    @Autowired
    public MatrixController(MatrixService matrixService, MatrixFactory matrixFactory) {
        this.matrixService = matrixService;
        this.matrixFactory = matrixFactory; // Spring 将注入 MatrixFactoryImpl 的实例
    }

    @PostMapping("/calculate")
    public ResponseEntity<?> calculateMatrix(@RequestBody MatrixRequest request) {
        try {
            // 使用工厂根据请求创建矩阵
            List<double[][]> matrices = request.getMatrices().stream()
                    .map(sizeValuesPair -> matrixFactory.createMatrix(sizeValuesPair.getSize(), sizeValuesPair.getValues()))
                    .collect(Collectors.toList());

            // 调用服务层执行操作
            return matrixService.performOperation(request.getOperation(), matrices);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException("An unexpected error occurred", e);
        }
    }
}