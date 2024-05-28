package com.zudzilean.matrixspring.operation;

/**
 * 矩阵操作接口，定义了对矩阵进行操作的方法。
 *
 * <p>此接口采用函数式接口的设计，允许通过匿名内部类或者Lambda表达式来实现。
 * <p>它定义了两种类型的矩阵操作：
 * <ul>
 *     <li>单一矩阵操作，即对单个矩阵执行操作。</li>
 *     <li>双矩阵操作，即对两个矩阵执行操作，如矩阵加法、减法或乘法。</li>
 * </ul>
 * <p>实现此接口的类需要提供具体的操作逻辑，以满足不同的矩阵运算需求。
 *
 * @author Zudilean
 * @since 2024/5/28 接口创建日期
 */
public interface MatrixOperation {
    /**
     * 对单个矩阵执行操作。
     *
     * @param matrix 需要操作的矩阵。
     * @return 操作后的矩阵。
     */
    double[][] apply(double[][] matrix);

    /**
     * 对两个矩阵执行操作。
     *
     * @param matrixA 第一个矩阵。
     * @param matrixB 第二个矩阵。
     * @return 操作后的矩阵。
     */
    double[][] apply(double[][] matrixA, double[][] matrixB);
}