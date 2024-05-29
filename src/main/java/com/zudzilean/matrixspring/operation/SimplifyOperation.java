package com.zudzilean.matrixspring.operation;

/**
 * @author
 * @since 2024/5/28
 */
public class SimplifyOperation implements MatrixOperation {

    /**
     * 对矩阵进行化简，使用高斯消元法。
     *
     * @param matrix 需要化简的矩阵。
     * @return 化简后的矩阵。
     */
    @Override
    public double[][] apply(double[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        int row = 0; // 当前处理的行

        // 打印当前状态
        System.out.println("Current matrix state:");
        printMatrix(matrix);

        for (int col = 0; col < cols && row < rows; col++) {

            // 找到当前列第一个非零元素的行索引
            int nonZeroRowIndex = findFirstNonZero(matrix, col, row, rows);
            if (nonZeroRowIndex == -1) {
                continue; // 如果整列都是零，则跳过
            }

            // 交换行，将非零行移动到当前处理的行,设为主行
            if (nonZeroRowIndex != row) {
                swapRows(matrix, nonZeroRowIndex, row);
                printStep("Swapped row " + nonZeroRowIndex + " with row " + (row + 1));
                printMatrix(matrix);

            }
            nonZeroRowIndex = row;
            // 归一化首非零元素为1
            double factor = matrix[nonZeroRowIndex][col];
            if (factor != 1) {
                multiplyRowByConstant(matrix, row, 1 / factor);
                printStep("Multiplied row " + (row + 1) + " by " + (1 / factor));
                printMatrix(matrix);
            }

            // 将当前列下面的所有行的对应元素置为0
            for (int i = row + 1; i < rows; i++) {
                if (matrix[i][col] != 0) {
                    // 计算置零所需的倍数
                    double multiplier = 1 / matrix[i][col];
                    // 归一
                    if (multiplier != 1) {
                        printStep("Multiplied row " + (i + 1) + " by " + multiplier);
                        multiplyRowByConstant(matrix, i, multiplier);
                        printMatrix(matrix);
                    }
                    // 减去当前行的倍数以置零
                    printStep("Subtracted row " + (i + 1) + " with row " + (row + 1));
                    subtractRows(matrix, i, row);
                    printMatrix(matrix);
                }
            }

            row++; // 移动到下一行继续处理
        }

        // 上三角继续简化
        for (row = rows - 1; row >= 0; row--) {
            // 找到当前行第一个非零元素的行索列
            int nonZeroColIndex = findLastNonZero(matrix, row);
            if (nonZeroColIndex == -1) {
                continue; // 如果整行都是零，则跳过
            }

            // 将当前列上面的所有行的对应元素置为0
            for (int i = 0; i < row; i++) {
                if (matrix[i][nonZeroColIndex] != 0) {
                    // 计算倍数所需的倍数
                    double multiplier = matrix[i][nonZeroColIndex] / matrix[row][nonZeroColIndex];
                    ;
                    // 倍数相减
                    printStep("Subtracted row " + (i + 1) + " with row " + (row + 1) +
                            " multiplied row " + (i + 1) + " by " + multiplier);
                    for (int k = nonZeroColIndex; k < cols; k++) {
                        matrix[i][k] -= matrix[row][k] * multiplier;
                    }
                    printMatrix(matrix);
                }
            }

        }

        return matrix;
    }

    private void printMatrix(double[][] matrix) {
        for (double[] row : matrix) {
            for (double val : row) {
                System.out.print(val + " ");
            }
            System.out.println();
        }
    }

    private void printStep(String step) {
        System.out.println(step);
    }

    private void swapRows(double[][] matrix, int i, int j) {
        double[] temp = matrix[i];
        matrix[i] = matrix[j];
        matrix[j] = temp;
    }

    private void multiplyRowByConstant(double[][] matrix, int row, double constant) {
        if (constant == 0) {
            throw new IllegalArgumentException("Multiplication by zero is not allowed.");
        }
        for (int j = 0; j < matrix[row].length; j++) {
            matrix[row][j] *= constant;
        }
    }

    private void subtractRows(double[][] matrix, int targetRow, int sourceRow) {
        for (int j = 0; j < matrix[sourceRow].length; j++) {
            matrix[targetRow][j] -= matrix[sourceRow][j];
        }
    }

    private int findFirstNonZero(double[][] matrix, int col, int startRow, int endRow) {
        for (int i = startRow; i < endRow; i++) {
            if (matrix[i][col] != 0) {
                return i; // 只返回非零元素所在的行索引
            }
        }
        return -1; // 如果没有找到非零元素
    }

    private int findLastNonZero(double[][] matrix, int row) {
        int cols = matrix[row].length;
        for(int col=0;col<cols;col++){
            if(matrix[row][col] != 0)
                return col;
        }
        return -1;

    }


    @Override
    public double[][] apply(double[][] matrixA, double[][] matrixB) {
        throw new UnsupportedOperationException("Simplify does not support double matrix operation.");
    }
}

// 其他操作类以类似的方式实现