package com.zudzilean.matrixspring.service;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;

public class MatrixInputTest {

    @Test
    void determineMatrixSizeValidInput() {
        String input = "【1,2】";
        int[] expectedSize = {1, 2};
        int[] size = MatrixInput.determineMatrixSize(input);
        assertArrayEquals(expectedSize, size, "The determined size of the matrix is incorrect.");
    }

    @Test
    void determineMatrixSizeInvalidFormat() {
        String input = "【1, 2, 3】";
        assertThrows(IllegalArgumentException.class, () -> {
            MatrixInput.determineMatrixSize(input);
        }, "determineMatrixSize should throw IllegalArgumentException for invalid format.");
    }

    @Test
    void determineMatrixSizeEmptyInput() {
        String input = "";
        assertThrows(IllegalArgumentException.class, () -> {
            MatrixInput.determineMatrixSize(input);
        }, "determineMatrixSize should throw IllegalArgumentException for empty input.");
    }

    @Test
    void buildMatrixWithValuesValidInput() {
        String input = "【1,2】";
        int[] size = {1, 2};
        double[][] expectedMatrix = {{1.0, 2.0}};
        double[][] matrix = MatrixInput.buildMatrixWithValues(input, size);

        assertEquals(1, matrix.length, "The matrix should have 1 row.");
        assertEquals(2, matrix[0].length, "The matrix should have 2 columns.");
        assertArrayEquals(new double[]{1.0, 2.0}, matrix[0], "The first row of the matrix is incorrect.");
    }

    @Test
    void buildMatrixWithValuesInvalidInput() {
        String input = "【1,two】";
        int[] size = {1, 2};
        assertThrows(IllegalArgumentException.class, () -> {
            MatrixInput.buildMatrixWithValues(input, size);
        }, "buildMatrixWithValues should throw IllegalArgumentException for invalid input.");
    }

    @Test
    void buildMatrixWithValuesNullInput() {
        String input = null;
        int[] size = {1, 2};
        assertThrows(IllegalArgumentException.class, () -> {
            MatrixInput.buildMatrixWithValues(input, size);
        }, "buildMatrixWithValues should throw IllegalArgumentException for null input.");
    }
}