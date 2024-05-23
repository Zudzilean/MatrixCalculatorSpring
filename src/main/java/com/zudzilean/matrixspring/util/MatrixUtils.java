package com.zudzilean.matrixspring.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.stream.Collectors;

public class MatrixUtils {

    private static final int MAX_SIZE = 10;
    private static final long MAX_VALUE = 1000000;

    /**
     * 验证给定的矩阵是否符合特定的尺寸和值范围要求。
     *
     * @param matrix 要验证的矩阵。
     * @param size 一个整型数组，包含矩阵期望的行数和列数，其中size[0]为行数，size[1]为列数。
     * @return 验证通过的矩阵。
     * @throws IllegalArgumentException 如果矩阵或其尺寸信息不符合以下任一条件：
     *                                 <ul>
     *                                 <li>size数组为null或不包含两个元素。</li>
     *                                 <li>矩阵的行数或列数小于等于0，或大于{@value #MAX_SIZE}。</li>
     *                                 <li>矩阵为null。</li>
     *                                 <li>矩阵的实际尺寸与提供的size数组不匹配。</li>
     *                                 <li>矩阵的行长度不一致。</li>
     *                                 <li>矩阵中的值不在0和{@value #MAX_VALUE}之间。</li>
     *                                 <ul>
     */
    public static double[][] validateMatrix(double[][] matrix, int[] size) throws IllegalArgumentException {
        // 检查 size 数组是否为 null 或长度不为 2
        if (size == null || size.length != 2) {
            throw new IllegalArgumentException("Size array must not be null and must have exactly two elements.");
        }
        int expectedRows = size[0];
        int expectedColumns = size[1];

        // 检查矩阵的期望大小是否有效
        if (expectedRows <= 0 || expectedRows > MAX_SIZE || expectedColumns <= 0 || expectedColumns > MAX_SIZE) {
            throw new IllegalArgumentException("Matrix dimensions must be between 1 and " + MAX_SIZE + " each.");
        }

        // 检查矩阵是否为 null
        if (matrix == null) {
            // 如果矩阵是 null，则抛出异常
            throw new IllegalArgumentException("Matrix cannot be null.");
        }

        // 检查矩阵是否符合给定的大小
        if (matrix.length != expectedRows || matrix[0].length != expectedColumns) {
            throw new IllegalArgumentException("Matrix dimensions do not match the provided size.");
        }

        // 检查矩阵的行是否具有相同的列数
        for (int i = 1; i < matrix.length; i++) {
            if (matrix[i].length != matrix[0].length) {
                throw new IllegalArgumentException("All rows in the matrix must have the same number of columns.");
            }
        }

        // 检查矩阵中的值是否在允许的范围内
        for (double[] row : matrix) {
            for (double value : row) {
                if (value < 0 || value > MAX_VALUE) {
                    throw new IllegalArgumentException("Value in matrix must be between 0 and " + MAX_VALUE + ".");
                }
            }
        }

        // 如果所有检查都通过，则返回矩阵
        return matrix;
    }

    /**
     * 将二维数组转换为 List<List<Double>> 格式。
     *
     * @param matrix 二维数组。
     * @return List<List<Double>> 格式的结果。
     */
    public static List<List<Double>> convertMatrixToListOfLists(double[][] matrix) {
        return java.util.Arrays.stream(matrix)
                .map(row -> java.util.Arrays.stream(row).boxed().collect(Collectors.toList()))
                .collect(Collectors.toList());
    }

    /**
     * 创建一个错误的响应实体。
     *
     * @param message 错误信息
     * @return 包含错误信息的 ResponseEntity 对象
     */
    public static ResponseEntity<Object> badRequest(String message) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
    }

}