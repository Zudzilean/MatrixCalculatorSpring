package com.zudzilean.matrixspring.operation;

import java.util.HashMap;
import java.util.Map;

import static com.zudzilean.matrixspring.constant.MatrixConstant.*;

/**
 * @author Zudilean
 * @since 2024/5/28
 */
public class MatrixOperationMap {
    private static final Map<String, MatrixOperation> operationMap = new HashMap<>();

    static {
        operationMap.put(ADD, new AddOperation());
        operationMap.put(SUBTRACT, new SubtractOperation());
        operationMap.put(MULTIPLY, new MultiplyOperation());
        operationMap.put(SIMPLIFY, new SimplifyOperation());
        operationMap.put(DETERMINANT, new DeterminantOperation());
        operationMap.put(INVERSE, new InverseOperation());
        operationMap.put(TRANSPOSE, new TransposeOperation());
        // 其他操作的映射
    }

    public static MatrixOperation getOperation(String operation) {
        return operationMap.get(operation);
    }
}
