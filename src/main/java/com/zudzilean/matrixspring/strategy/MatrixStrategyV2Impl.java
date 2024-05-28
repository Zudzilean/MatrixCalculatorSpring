package com.zudzilean.matrixspring.strategy;

import com.zudzilean.matrixspring.operation.MatrixOperation;
import com.zudzilean.matrixspring.operation.MatrixOperationMap;
import org.springframework.stereotype.Service;

/**
 * 矩阵策略实现类，版本2。
 *
 * <p>这个类实现了{@link MatrixStrategy}接口，提供了矩阵操作的策略实现。
 * 它使用{@link MatrixOperationMap}来获取对应的矩阵操作，并执行这些操作。
 * <p>此类利用了Spring框架的{@link Service}注解，表明它是一个服务组件。
 *
 * @author Zudzilean
 * @since 2024/5/28 类创建日期
 */
@Service
public class MatrixStrategyV2Impl implements MatrixStrategy {

    /**
     * 执行单一矩阵操作。
     *
     * <p>根据提供的字符串标识{@code operation}，从{@link MatrixOperationMap}中获取相应的矩阵操作，
     * 然后对给定的矩阵{@code matrix}执行该操作。
     *
     * @param matrix 需要执行操作的矩阵。
     * @param operation 操作的标识符，用于确定具体的矩阵操作。
     * @return 操作后的矩阵。
     * @throws IllegalStateException 如果没有找到对应的操作，将抛出此异常。
     */
    @Override
    public double[][] executeSingleMatrixOperation(double[][] matrix, String operation) {
        MatrixOperation op = MatrixOperationMap.getOperation(operation);
        if (op == null) {
            throw new IllegalStateException("Unexpected value: " + operation);
        }
        return op.apply(matrix);
    }

    /**
     * 执行双矩阵操作。
     *
     * <p>根据提供的字符串标识{@code operation}，从{@link MatrixOperationMap}中获取相应的矩阵操作，
     * 然后对给定的两个矩阵{@code matrixA}和{@code matrixB}执行该操作。
     *
     * @param matrixA 第一个需要执行操作的矩阵。
     * @param matrixB 第二个需要执行操作的矩阵。
     * @param operation 操作的标识符，用于确定具体的矩阵操作。
     * @return 操作后的矩阵。
     * @throws IllegalStateException 如果没有找到对应的操作，将抛出此异常。
     */
    @Override
    public double[][] executeDoubleMatrixOperation(double[][] matrixA, double[][] matrixB, String operation) {
        MatrixOperation op = MatrixOperationMap.getOperation(operation);
        if (op == null) {
            throw new IllegalStateException("Unexpected value: " + operation);
        }
        return op.apply(matrixA, matrixB);
    }
}