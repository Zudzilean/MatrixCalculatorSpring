package com.zudzilean.matrixspring.service;

import com.zudzilean.matrixspring.strategy.MatrixOperationStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MatrixServiceImpl implements MatrixService {

    private final MatrixOperationStrategy strategy;

    @Autowired
    public MatrixServiceImpl(MatrixOperationStrategy strategy) {
        this.strategy = strategy;
    }

    @Override
    public ResponseEntity<?> performOperation(String operation, List<double[][]> matrices) {
        try {
            // 委托给策略层执行操作
            double[][] result = strategy.execute(operation, matrices);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            // 处理异常情况
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}