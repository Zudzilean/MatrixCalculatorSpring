package com.zudzilean.matrixspring.service;

/**
 * 定义了矩阵计算的操作接口。
 * <p>
 * 该接口包含矩阵的基本运算方法，如加法、减法、乘法等，以及更高级的操作，如行列式、逆矩阵和转置。
 * </p>
 *
 * @author Zudiliean
 * @version 1.0
 * @since 1.0
 */
public interface MatrixService {

    /**
     * 对两个相同大小的矩阵进行加法运算。
     *
     * @param matrixA 第一个矩阵。
     * @param matrixB 第二个矩阵。
     * @return 两个矩阵相加的结果矩阵。
     * @throws IllegalArgumentException 如果矩阵A和矩阵B的大小不一致。
     */
    double[][] add(double[][] matrixA, double[][] matrixB);

    /**
     * 对两个矩阵进行减法运算。
     * <p>
     * 要求两个矩阵具有相同的维度。
     *
     * @param matrixA 被减矩阵。
     * @param matrixB 减去的矩阵。
     * @return 两个矩阵相减的结果矩阵。
     * @throws IllegalArgumentException 如果矩阵A和矩阵B的大小不一致。
     */
    double[][] subtract(double[][] matrixA, double[][] matrixB);

    /**
     * 对两个矩阵进行乘法运算。
     * <p>
     * 第一个矩阵的列数必须与第二个矩阵的行数相等。
     *
     * @param matrixA 第一个矩阵。
     * @param matrixB 第二个矩阵。
     * @return 两个矩阵相乘的结果矩阵。
     * @throws IllegalArgumentException 如果矩阵的维度不满足乘法运算的条件。
     */
    double[][] multiply(double[][] matrixA, double[][] matrixB);

    /**
     * 使用高斯消元法简化矩阵至行简化阶梯形式。
     *
     * @param matrix 需要简化的矩阵。
     * @return 简化后的矩阵。
     */
    double[][] simplify(double[][] matrix);

    /**
     * 计算矩阵的行列式。
     * <p>
     * 只有方阵（行数和列数相等的矩阵）才有行列式。
     *
     * @param matrix 需要计算行列式的方阵。
     * @return 矩阵的行列式值。
     * @throws IllegalArgumentException 如果矩阵不是方阵。
     */
    double[][] determinant(double[][] matrix);

    /**
     * 计算矩阵的逆矩阵。
     * <p>
     * 只有方阵才有逆矩阵，且该方阵必须可逆（行列式不为零）。
     *
     * @param matrix 需要求逆的方阵。
     * @return 矩阵的逆矩阵。
     * @throws IllegalArgumentException 如果矩阵不是方阵或不可逆。
     */
    double[][] inverse(double[][] matrix) throws IllegalArgumentException;

    /**
     * 计算并返回矩阵的转置。
     *
     * @param matrix 需要转置的矩阵。
     * @return 转置后的矩阵。
     */
    double[][] transpose(double[][] matrix);

    // 可以根据需要添加其他方法 ...
}