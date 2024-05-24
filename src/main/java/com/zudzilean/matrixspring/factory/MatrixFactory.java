package com.zudzilean.matrixspring.factory;

import java.util.List;

/**
 * @author
 * @since 2024/5/24
 */
public interface MatrixFactory {
    public double[][] createMatrix(int[] size, List<Double> values);
}
