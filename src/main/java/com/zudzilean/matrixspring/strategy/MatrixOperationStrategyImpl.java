package com.zudzilean.matrixspring.strategy;

import com.zudzilean.matrixspring.strategy.MatrixOperationStrategy;
import java.util.List;

public class MatrixOperationStrategyImpl implements MatrixOperationStrategy {

    @Override
    public double[][] execute(List<double[][]> matrices) {
        // 检查输入列表至少包含两个矩阵
        if (matrices == null || matrices.size() < 2) {
            throw new IllegalArgumentException("需要至少两个矩阵来进行操作");
        }

        // 假设所有矩阵具有相同的维度
        double[][] result = matrices.getFirst();
        for (int i = 1; i < matrices.size(); i++) {
            double[][] currentMatrix = matrices.get(i);
            // 执行加法操作
            for (int row = 0; row < result.length; row++) {
                for (int col = 0; col < result[row].length; col++) {
                    result[row][col] += currentMatrix[row][col];
                }
            }
        }
        return result;
    }
}