package com.zudzilean.matrixspring.operation;

import com.zudzilean.matrixspring.domain.MatrixRequest;
import com.zudzilean.matrixspring.service.MatrixCalculatorV1;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class DefaultMatrixOperation implements MatrixOperation {

    @Override
    public ResponseEntity<?> execute(MatrixCalculatorV1 calculator, MatrixRequest request) {
        try {
            switch (request.getOperation()) {
                case "add":
                    return new ResponseEntity<>(calculator.add(request.getMatrices().get(0), request.getMatrices().get(1)), HttpStatus.OK);
                case "subtract":
                    return new ResponseEntity<>(calculator.subtract(request.getMatrices().get(0), request.getMatrices().get(1)), HttpStatus.OK);
                case "multiply":
                    return new ResponseEntity<>(calculator.multiply(request.getMatrices().get(0), request.getMatrices().get(1)), HttpStatus.OK);
                case "determinant":
                    return new ResponseEntity<>(calculator.determinant(request.getMatrices().get(0)), HttpStatus.OK);
                case "inverse":
                    return new ResponseEntity<>(calculator.inverse(request.getMatrices().get(0)), HttpStatus.OK);
                case "transpose":
                    return new ResponseEntity<>(calculator.transpose(request.getMatrices().get(0)), HttpStatus.OK);
                default:
                    return new ResponseEntity<>("Unsupported operation", HttpStatus.BAD_REQUEST);
            }
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}