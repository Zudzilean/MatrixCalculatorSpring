package com.zudzilean.matrixspring.util;

import com.zudzilean.matrixspring.domain.MatrixRequest;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author
 * @since 2024/5/27
 */
public class MatrixOperationUtils {


    /**
     * 根据操作类型检查矩阵B是否需要存在，并进行验证。
     *
     * @param request 请求对象，包含操作类型和矩阵B
     * @return 返回验证后的矩阵B，如果操作类型不需要矩阵B，则返回null
     */

    public static double[][] validateMatrixB(MatrixRequest request) {
        String operation = request.getOperation();
        // 检查操作类型是否需要B
        if ("add".equals(operation) ||
                "subtract".equals(operation)||
                "multiply".equals(operation)) {
            // 如果需要矩阵B，则验证并返回矩阵B
            return MatrixInputUtils.validateMatrix(request.getMatrixB(), request.getSizeB());
        } else {
            // 如果不需要矩阵B，则返回null
            return null;
        }
    }

    public static List<List<Double>> convertMatrixToListOfLists(double[][] matrix) {
        return java.util.Arrays.stream(matrix).map(row -> java.util.Arrays.stream(row).boxed().collect(Collectors.toList())).collect(Collectors.toList());
    }



}
