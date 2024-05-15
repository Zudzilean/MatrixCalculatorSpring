package com.zudzilean.matrixspring.service;

import java.util.ArrayList;
import java.util.List;

public class MatrixInput {

    private static final int MAX_SIZE = 6;
    private static final long MAX_VALUE = 1000000;

    public static List<List<Long>> buildMatrix(String input) throws IllegalArgumentException {
        if (input == null || input.trim().isEmpty()) {
            throw new IllegalArgumentException("Input cannot be null or empty.");
        }

        // 去除输入字符串两端的方括号并分割矩阵行
        String[] rows = input.trim().replaceFirst("【", "").replaceFirst("】$", "").split("\\】\\s*【");
        if (rows.length > MAX_SIZE * MAX_SIZE) {
            throw new IllegalArgumentException("Matrix cannot exceed " + MAX_SIZE + "x" + MAX_SIZE + " size.");
        }

        List<List<Long>> matrix = new ArrayList<>();
        List<Long> currentRow = null;

        for (String rowStr : rows) {
            if (!rowStr.trim().isEmpty()) { // 确保不处理空行
                currentRow = new ArrayList<>();
                String[] elements = rowStr.split("\\s*,\\s*");
                for (String element : elements) {
                    // 验证元素是否为有效的数字
                    if (!element.matches("\\d+")) {
                        throw new IllegalArgumentException("Invalid input: '" + element + "' is not a valid number.");
                    }
                    long value = Long.parseLong(element);
                    if (value < 0 || value > MAX_VALUE) {
                        throw new IllegalArgumentException("Matrix values must be between 0 and " + MAX_VALUE + ".");
                    }
                    currentRow.add(value); // 添加数字到当前行
                }
                matrix.add(currentRow); // 添加当前行到矩阵
            } else {
                // 如果当前字符串为空，但不应在此处发生，因为我们已经通过 split 处理了空行
            }
        }

        return matrix;
    }
}