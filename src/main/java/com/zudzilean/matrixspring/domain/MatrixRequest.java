package com.zudzilean.matrixspring.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
public class MatrixRequest {

    private String operationType; // 操作类型
    /**
     * 获取要执行的操作类型。
     *
     * @return 操作类型。
     */
    public String getOperation() {
        return operationType;
    }

    // 用于存储单个矩阵的尺寸和数值
    private List<MatrixData> matricesData;

    public MatrixRequest(String operationType, List<MatrixData> matricesData) {
        this.operationType = operationType;
        this.matricesData = matricesData;
    }

    // 获取所有矩阵的列表
    public List<double[][]> getMatrices() {
        List<double[][]> matrices = new ArrayList<>();
        for (MatrixData data : matricesData) {
            matrices.add(createMatrix(data));
        }
        return matrices;
    }

    // 根据MatrixData创建实际的矩阵
    private double[][] createMatrix(MatrixData data) {
        int rows = data.getSize()[0];
        int columns = data.getSize()[1];
        double[][] matrix = new double[rows][columns];
        List<Double> values = data.getValues();
        int index = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                matrix[i][j] = values.get(index++);
            }
        }
        return matrix;
    }

    // 内部类，用于封装矩阵的尺寸和数值
    @Getter
    @Setter
    public static class MatrixData {
        private int[] size; // 矩阵的尺寸，例如：[行数, 列数]
        private List<Double> values; // 矩阵的数值列表

        public MatrixData(int[] size, List<Double> values) {
            this.size = size;
            this.values = values;
        }
    }
}