package com.zudzilean.matrixspring.strategy;

import java.util.List;

/**
 * @author Zudiliean
 * @since 2024/5/24
 */
public interface MatrixOperationStrategy {
    /**
     * 执行矩阵操作。
     *
     * @param operation
     * @param matrices  一个包含所有参与操作的矩阵的列表。
     * @return 操作结果矩阵。
     */
     double[][] execute(String operation, List<double[][]> matrices);
}
