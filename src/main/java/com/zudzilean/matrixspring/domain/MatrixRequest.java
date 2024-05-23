package com.zudzilean.matrixspring.domain;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

/**
 * 表示矩阵计算请求的实体类。
 * <p>
 * 此类用于封装执行矩阵操作所需的所有信息，包括矩阵的数值、要执行的操作类型，以及矩阵的尺寸信息。
 * </p>
 *
 * @author Zudziliean
 * @since 2024/5/23
 */
@Getter
@Setter
public class MatrixRequest {

    /**
     * 要执行操作的矩阵列表。
     */
    private List<double[][]> matrices;

    /**
     * 要执行的矩阵操作类型。
     * 例如，可以是 "add", "subtract", "multiply", "determinant", "inverse", "transpose" 等。
     */
    private String operation;

    /**
     * 构造函数，初始化矩阵列表和操作类型。
     *
     * @param matrices 要操作的矩阵列表。
     * @param operation 要执行的操作类型。
     */
    public MatrixRequest(List<double[][]> matrices, String operation) {
        this.matrices = matrices;
        this.operation = operation;
    }

    /**
     * 获取矩阵的行数。
     * 假设所有矩阵都是规则的，即每行的列数相同。
     *
     * @return 矩阵的行数。
     */
    public int getRowCount() {
        // 假设 matrices 非空且至少有一个矩阵
        return matrices.getFirst().length;
    }

    /**
     * 获取矩阵的列数。
     * 假设所有矩阵都是规则的，即每行的列数相同。
     *
     * @return 矩阵的列数。
     */
    public int getColumnCount() {
        // 假设 matrices 非空且至少有一个矩阵，且矩阵非空且至少有一列
        return matrices.getFirst()[0].length;
    }

    // 如果需要，可以添加其他辅助方法
}