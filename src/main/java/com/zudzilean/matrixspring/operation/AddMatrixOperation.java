package com.zudzilean.matrixspring.operation;

import com.zudzilean.matrixspring.domain.MatrixRequest;
import com.zudzilean.matrixspring.service.MatrixCalculatorV1;
import com.zudzilean.matrixspring.util.MatrixUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static com.zudzilean.matrixspring.util.MatrixUtils.badRequest;

public class AddMatrixOperation implements MatrixOperation {

    @Override
    public ResponseEntity<?> execute(MatrixCalculatorV1 calculator, MatrixRequest request) {
        List<double[][]> matrices = request.getMatrices();

        // 检查请求中是否恰好有两个矩阵
        if (matrices.size() != 2) {
            return badRequest("Exactly two matrices are required for addition.");
        }

        double[][] matrixA = matrices.get(0);
        double[][] matrixB = matrices.get(1);

        // 检查矩阵尺寸是否匹配
        if (matrixA.length != matrixB.length || matrixA[0].length != matrixB[0].length) {
            return badRequest("Matrices dimensions must match for addition.");
        }

        try {
            double[][] resultMatrix = calculator.add(matrixA, matrixB);
            List<List<Double>> resultList = MatrixUtils.convertMatrixToListOfLists(resultMatrix);
            return ResponseEntity.ok(resultList);
        } catch (IllegalArgumentException e) {
            // 处理由 MatrixCalculatorV1 接口方法抛出的异常
            return badRequest(e.getMessage());
        }
    }
}