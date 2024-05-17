package com.zudzilean.matrixspring.service;

public class MatrixInput {
    //输入一个二维矩阵
    //确认矩阵的大小
    //确认矩阵的数值
    private static final int MAX_SIZE = 6;
    private static final long MAX_VALUE = 1000000;

    /**
     * 根据输入的字符串确认矩阵的大小，并检查数值是否合法。
     *
     * @param input 表示矩阵的字符串。
     * @return int数组，第一个元素是行数，第二个元素是列数。
     * @throws IllegalArgumentException 如果输入不符合要求。
     */
    public static int[] determineMatrixSize(String input) throws IllegalArgumentException {
        if (input == null || input.trim().isEmpty()) {
            throw new IllegalArgumentException("Input cannot be null or empty.");
        }

        // 去除输入字符串的方括号
        String row = input.trim().replaceFirst("【", "").replaceFirst("】$", "");

        // 确认输入格式正确，即【数字,数字】
        if (!row.matches("\\d+\\s*,\\s*\\d+")) {
            throw new IllegalArgumentException("Input must be in the format of 【number,number】.");
        }

        // 获取该行的元素
        String[] elements = row.split("\\s*,\\s*");

        // 确认矩阵只包含两个元素
        if (elements.length != 2) {
            throw new IllegalArgumentException("Matrix must contain exactly two elements.");
        }

        for (String element : elements) {
            // 验证每个元素是否为正整数
            if (!element.matches("\\d+")) {
                throw new IllegalArgumentException("Invalid input: '" + element + "' is not a valid positive integer.");
            }
            int value = Integer.parseInt(element);
            if (value < 0 || value > MAX_SIZE) {
                throw new IllegalArgumentException("Matrix dimensions must be between 0 and " + MAX_SIZE + ".");
            }
        }

        // 返回矩阵的确切大小，即输入字符串中的两个数值
        return new int[] {Integer.parseInt(elements[0]), Integer.parseInt(elements[1])};
    }

    /**
     * 根据输入的字符串和已确定的矩阵大小构建矩阵数值。
     *
     * @param input 表示矩阵的字符串。
     * @param size 确定的矩阵大小，包含行数和列数。
     * @return 构建的矩阵。
     * @throws IllegalArgumentException 如果输入不符合要求。
     */
    public static double[][] buildMatrixWithValues(String input, int[] size) throws IllegalArgumentException {
        int rowCount = size[0];
        int colCount = size[1];

        if (input == null || input.trim().isEmpty()) {
            throw new IllegalArgumentException("Input cannot be null or empty.");
        }

        double[][] matrix = new double[rowCount][colCount];

        // 去除输入字符串两端的方括号并分割矩阵行
        String[] rows = input.trim().replaceFirst("【", "").replaceFirst("】$", "").split("\\】\\s*【");

        // 验证输入的行数是否与确定的矩阵大小一致
        if (rows.length != rowCount) {
            throw new IllegalArgumentException("The number of rows in the input does not match the expected matrix size.");
        }

        for (int i = 0; i < rowCount; i++) {
            String rowStr = rows[i].trim();
            if (rowStr.isEmpty()) {
                throw new IllegalArgumentException("Row " + (i + 1) + " is empty.");
            }
            String[] elements = rowStr.split("\\s*,\\s*");

            // 验证每一行的元素数量是否与确定的矩阵大小一致
            if (elements.length != colCount) {
                throw new IllegalArgumentException("Row " + (i + 1) + " does not have the expected number of elements.");
            }

            for (int j = 0; j < colCount; j++) {
                String element = elements[j];
                if (!element.matches("\\d+")) {
                    throw new IllegalArgumentException("Invalid input: '" + element + "' in row " + (i + 1) + " is not a valid number.");
                }
                long value = Long.parseLong(element);
                if (value < 0 || value > MAX_VALUE) {
                    throw new IllegalArgumentException("Value in row " + (i + 1) + " must be between 0 and " + MAX_VALUE + ".");
                }
                matrix[i][j] = value;
            }
        }

        return matrix;
    }
}