package com.zudzilean.matrixspring.factory;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MatrixFactoryImpl implements MatrixFactory {

    /**
     * 创建一个矩阵并填充给定的数值。
     *
     * @param size    一个整数数组，其中 size[0] 表示矩阵的行数，size[1] 表示矩阵的列数。
     * @param values  一个包含矩阵数值的列表，这些数值应该按行主序排列。
     * @return        返回一个由给定数值填充的二维矩阵。
     * @throws IllegalArgumentException 如果提供的数据不足以填满矩阵。
     */
    @Override
    public double[][] createMatrix(int[] size, List<Double> values) {
        if (size == null || size.length != 2) {
            throw new IllegalArgumentException("Size must be an array of length 2.");
        }

        int rows = size[0];
        int columns = size[1];
        double[][] matrix = new double[rows][columns];

        int index = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (index < values.size()) {
                    matrix[i][j] = values.get(index++);
                } else {
                    throw new IllegalArgumentException("Not enough values provided to fill the matrix.");
                }
            }
        }

        // If there are more values than needed, that's okay. We just won't use the extra values.
        // If there are not enough, an exception will have been thrown in the loop.

        return matrix;
    }
}