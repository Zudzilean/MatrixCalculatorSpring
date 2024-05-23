package com.zudzilean.matrixspring.strategy;

import java.util.List;

/**
 * MatrixOperationStrategy 接口定义了执行矩阵操作的策略。
 */
public interface MatrixOperationStrategy {
    /**
     * 执行矩阵操作。
     *
     * @param matrices 一个包含所有参与操作的矩阵的列表。
     * @return 操作结果矩阵。
     */
    double[][] execute(List<double[][]> matrices);
}