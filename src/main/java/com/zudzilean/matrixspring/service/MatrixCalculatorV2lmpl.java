package com.zudzilean.matrixspring.service;

import java.util.ArrayList;
import java.util.List;

//矩阵内部的计算
public class MatrixCalculatorV2lmpl implements MatrixCalculatorV2{
    //二维矩阵化简并打印过程
    public double[][] simplifyMatrix(double[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        int row = 0; // 当前处理的行

        // 打印当前状态
        System.out.println("Current matrix state:");
        printMatrix(matrix);

        for (int col = 0; col < cols && row < rows; col++) {

            // 找到当前列第一个非零元素的行索引
            int[] result = findFirstNonZero(matrix, col, row, rows);
            int nonZeroRowIndex = result[0];
            if (nonZeroRowIndex == -1) {
                continue; // 如果整列都是零，则跳过
            }

            // 交换行，将非零行移动到当前处理的行,设为主行
            if(nonZeroRowIndex!=row) {
                swapRows(matrix, nonZeroRowIndex, row);
                printStep("Swapped row " + nonZeroRowIndex + " with row " + row);
                printMatrix(matrix);

            }
            // 归一化首非零元素为1
            double factor = matrix[nonZeroRowIndex][col];
            if(factor != 1) {
                multiplyRowByConstant(matrix, row, 1 / factor);
                printStep("Multiplied row " + row + " by " + (1 / factor));
                printMatrix(matrix);
            }

            // 将当前列下面的所有行的对应元素置为0
            for (int i = row + 1; i < rows; i++) {
                if(matrix[i][col] != 0) {
                    // 计算置零所需的倍数
                    double multiplier = 1/matrix[i][col]  ;
                    // 归一
                    if(multiplier != 1) {
                        printStep("Multiplied row " + i + " by " + multiplier);
                        multiplyRowByConstant(matrix, i, multiplier);
                        printMatrix(matrix);
                    }
                    // 减去当前行的倍数以置零
                    printStep("Subtracted row " + i + " with row " + row);
                    subtractRows(matrix, i, row);
                    printMatrix(matrix);
                }
            }

            row++; // 移动到下一行继续处理
        }

     /*   // 打印当前状态
        System.out.println("New matrix state:");
        printMatrix(matrix);

        for (int col = cols-1; 0 <= col && 0 <= row; col--) {

            // 找到当前列第一个非零元素的行索引
            int[] result = findLastNonZero(matrix, col, row, rows);
            int nonZeroRowIndex = result[0];
            if (nonZeroRowIndex == -1) {
                continue; // 如果整列都是零，则跳过
            }

            // 将当前列上面的所有行的对应元素置为0
            for (int i = nonZeroRowIndex-1; i >= 0; i--) {
                if(matrix[i][col] != 0) {
                    // 计算倍数所需的倍数
                    double multiplier = matrix[i][col]  ;
                    // 归一
                    if(multiplier != 1) {
                        printStep("Multiplied row " + i + " by " + multiplier);
                        multiplyRowByConstant(matrix, i, multiplier);
                        printMatrix(matrix);
                    }
                    // 减去当前行的倍数以置零
                    printStep("Subtracted row " + i + " with row " + row);
                    subtractRows(matrix, i, row);
                    printMatrix(matrix);
                }
            }

            row--; // 移动到下一行继续处理
        }

      */

        // 完成高斯消元后，检查解的类型
        checkAndPrintSolutionType(matrix);
        return matrix;
    }

    private void checkAndPrintSolutionType(double[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        int pivotRows = cols; // 假设方阵情况，即行数等于列数

        // 检查是否有全零行
        boolean hasZeroRow = false;
        for (int i = 0; i < rows; i++) {
            if (isRowAllZeros(matrix, i)) {
                hasZeroRow = true;
                break;
            }
        }

        // 检查最后一行
        boolean lastRowHasZeroPivot = isRowAllZeros(matrix, rows - 1) && matrix[rows - 1][cols - 1] == 0;

        if (lastRowHasZeroPivot) {
            // 如果最后一行是全零行，但右下角的元素不是零，则无解
            System.out.println("The system has no solution.");
        } else if (hasZeroRow) {
            // 如果存在全零行，则无限解
            System.out.println("The system has infinitely many solutions.");
        } else {
            // 如果没有全零行，则唯一解
            System.out.println("The system has a unique solution.");
        }
    }

    private boolean isRowAllZeros(double[][] matrix, int rowIndex) {
        for (int j = 0; j < matrix[rowIndex].length; j++) {
            if (matrix[rowIndex][j] != 0) {
                return false;
            }
        }
        return true;
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

    private int[] findFirstNonZero(double[][] matrix, int col, int startRow, int endRow) {
        for (int i = startRow; i < endRow; i++) {
            if (matrix[i][col] != 0) {
                return new int[]{i, col}; // 返回非零元素所在的行和列索引
            }
        }
        return new int[]{-1, -1}; // 如果没有找到非零元素
    }

    private int[] findLastNonZero(double[][] matrix, int col, int startRow, int endRow) {
        for (int i = endRow; startRow<=i; i--) {
            if (matrix[i][col] != 0) {
                return new int[]{i, col}; // 返回非零元素所在的行和列索引
            }
        }
        return new int[]{-1, -1}; // 如果没有找到非零元素
    }

    //计算二维矩阵的det行列式值
    public double determinant(double[][] matrix) {
        return 0;
    }

    //计算二维矩阵的inverse逆矩阵
    public double[][] inverse(double[][] matrix) throws Exception {
        return null;
    }

    //计算二维矩阵的转换
    public double[][] transpose(double[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        double[][] ans = new double[n][m];
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                ans[j][i] = matrix[i][j];
            }
        }
        return ans;
    }

    @Override
    public Complex[] eigenvalues(double[][] matrix) {
        return new Complex[0];
    }

    @Override
    public Pair<Complex[], double[][]> eigenvectors(double[][] matrix) {
        return null;
    }

    @Override
    public int rank(double[][] matrix) {
        return 0;
    }

    @Override
    public LUDecompositionResult luDecompose(double[][] matrix) {
        return null;
    }

    @Override
    public QRDecompositionResult qrDecompose(double[][] matrix) {
        return null;
    }

    @Override
    public double[] solveLinearSystem(double[][] a, double[] b) {
        return new double[0];
    }

}
