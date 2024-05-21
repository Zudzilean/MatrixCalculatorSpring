package com.zudzilean.matrixspring.service;

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
                printStep("Swapped row " + nonZeroRowIndex + " with row " + (row+1));
                printMatrix(matrix);

            }
            // 归一化首非零元素为1
            double factor = matrix[nonZeroRowIndex][col];
            if(factor != 1) {
                multiplyRowByConstant(matrix, row, 1 / factor);
                printStep("Multiplied row " + (row+1) + " by " + (1 / factor));
                printMatrix(matrix);
            }

            // 将当前列下面的所有行的对应元素置为0
            for (int i = row + 1; i < rows; i++) {
                if(matrix[i][col] != 0) {
                    // 计算置零所需的倍数
                    double multiplier = 1/matrix[i][col]  ;
                    // 归一
                    if(multiplier != 1) {
                        printStep("Multiplied row " + (i+1) + " by " + multiplier);
                        multiplyRowByConstant(matrix, i, multiplier);
                        printMatrix(matrix);
                    }
                    // 减去当前行的倍数以置零
                    printStep("Subtracted row " + (i+1) + " with row " + (row+1));
                    subtractRows(matrix, i, row);
                    printMatrix(matrix);
                }
            }

            row++; // 移动到下一行继续处理
        }

        // 上三角继续简化
        for (row = rows-1; row>=0; row--) {

            // 找到当前行第一个非零元素的行索列
            int nonZeroColIndex = findLastNonZero(matrix, row);
            if (nonZeroColIndex == -1) {
                continue; // 如果整列都是零，则跳过
            }

            // 将当前列上面的所有行的对应元素置为0
            for (int i = 0; i<row;i++) {
                if(matrix[i][nonZeroColIndex] != 0) {
                    // 计算倍数所需的倍数
                    double multiplier = matrix[i][nonZeroColIndex]/matrix[row][nonZeroColIndex];  ;
                    // 倍数相减
                    printStep("Subtracted row " + (i+1) + " with row " + (row+1) +
                                " multiplied row " + (i+1) + " by " + multiplier);
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

    private int[] findFirstNonZero(double[][] matrix, int col, int startRow, int endRow) {
        for (int i = startRow; i < endRow; i++) {
            if (matrix[i][col] != 0) {
                return new int[]{i, col}; // 返回非零元素所在的行和列索引
            }
        }
        return new int[]{-1, -1}; // 如果没有找到非零元素
    }

    private int findLastNonZero(double[][] matrix, int row) {
        int col = matrix[row].length;
        for (int i=row; i>=0; i--) {
            for(int j=0; j<col; j++) {
                if (matrix[i][j] != 0) {
                    return i;
                }
            }
        }
        return -1;

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

    // 计算矩阵的行列式
    public double determinant(double[][] matrix) {
            int n = matrix.length;
            if (n == 0 || n != matrix[0].length) {
                throw new IllegalArgumentException("Matrix must be non-empty and square.");
            }

            // 对于1x1矩阵，行列式就是矩阵中唯一的元素
            if (n == 1) {
                return matrix[0][0];
            }

            // 对于2x2矩阵，使用ad - bc公式计算行列式
            if (n == 2) {
                return matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0];
            }


            // 对于大于2x2的矩阵，使用递归的拉普拉斯展开
            return laplacianExpansion(matrix);
    }

    // 拉普拉斯展开递归方法
    private double laplacianExpansion(double[][] matrix) {
        double det = 0;
        double[][] subMatrix;
        for (int i = 0; i < matrix.length; i++) {
            // 创建子矩阵，排除第0行和第i列
            subMatrix = new double[matrix.length - 1][];
            for (int j = 1; j < matrix.length; j++) {
                subMatrix[j - 1] = new double[matrix[j].length - 1];
                for (int k = 0, col = 0; k < matrix[j].length; k++) {
                    if (k != i) {
                        subMatrix[j - 1][col++] = matrix[j][k];
                    }
                }
            }
            // 递归计算子矩阵的行列式，并累加结果
            det += Math.pow(-1, i) * matrix[0][i] * determinant(subMatrix);
        }
        return det;
    }

    //计算二维矩阵的inverse逆矩阵
    public double[][] inverse(double[][] matrix) throws Exception {
        int n = matrix.length;

        // 创建增广矩阵，左边是原矩阵，右边是单位矩阵
        double[][] augmentedMatrix = new double[n][2 * n];
        for (int i = 0; i < n; i++) {
            System.arraycopy(matrix[i], 0, augmentedMatrix[i], 0, n);
            for (int j = n; j < 2 * n; j++) {
                augmentedMatrix[i][j] = (i == (j - n)) ? 1 : 0;
            }
        }

        // 对增广矩阵进行高斯消元
        simplifyMatrix(augmentedMatrix);

        // 从增广矩阵中提取逆矩阵
        double[][] inverseMatrix = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                inverseMatrix[i][j] = augmentedMatrix[i][j + n];
            }
        }

        return inverseMatrix;
    }



}
