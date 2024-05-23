package com.zudzilean.matrixspring.domain;

import lombok.Getter;
import lombok.Setter;

/**
 * 表示矩阵计算请求的实体类。
 * <p>
 * 此类用于封装执行矩阵操作所需的所有信息，包括两个矩阵的尺寸和数值，以及要执行的操作类型。
 * </p>
 *
 * @author Zudziliean
 * @since 2024/5/23
 */
@Getter
@Setter
public class MatrixRequest {
    /**
     * 第一个矩阵的行数和列数。
     * <p>
     * 数组的第一个元素表示行数，第二个元素表示列数。
     * </p>
     */
    private int[] sizeA;

    /**
     * 第一个矩阵的数据。
     */
    private double[][] matrixA;

    /**
     * 第二个矩阵的行数和列数。
     * <p>
     * 数组的第一个元素表示行数，第二个元素表示列数。
     * </p>
     */
    private int[] sizeB;

    /**
     * 第二个矩阵的数据。
     */
    private double[][] matrixB;

    /**
     * 要执行的矩阵操作类型。
     * <p>
     * 例如，可以是 "add", "subtract", "multiply", "determinant", "inverse", "transpose" 等。
     * </p>
     */
    private String operation;

    // 可以根据需要添加构造函数、方法或内部类 ...
}