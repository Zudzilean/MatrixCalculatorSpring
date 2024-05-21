package com.zudzilean.matrixspring.service;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.lang.IllegalArgumentException;

public class MatrixInputTest {

    @Test
    public void testDetermineMatrixSize_ValidInput_ShouldReturnCorrectSize() {
        try {
            int[] size = MatrixInput.determineMatrixSize("[2,3]");
            assertAll(
                    () -> assertEquals(2, size[0], "The number of rows should be 2."),
                    () -> assertEquals(3, size[1], "The number of columns should be 3.")
            );
        } catch (IllegalArgumentException e) {
            fail("determineMatrixSize threw an exception for valid input: " + e.getMessage());
        }
    }

    @Test
    public void testDetermineMatrixSize_InvalidInput_ShouldThrowException() {
        String[] invalidInputs = {"[2,three]", "[a,1]", "", null, "[1", "1]", "[1,2,3]"};
        for (String input : invalidInputs) {
            assertThrows(IllegalArgumentException.class, () -> {
                MatrixInput.determineMatrixSize(input);
            }, "determineMatrixSize did not throw an exception for invalid input: " + input);
        }
    }

    @Test
    public void testBuildMatrixWithValues_ValidInput_ShouldReturnPopulatedMatrix() {
        try {
            int[] size = MatrixInput.determineMatrixSize("[2,3]");
            double[][] matrix = MatrixInput.buildMatrixWithValues("[[1,2,3],[4,5,6]]", size);
            assertAll(
                    () -> assertEquals(2, matrix.length, "The matrix should have 2 rows."),
                    () -> assertEquals(3, matrix[0].length, "The first row should have 3 columns."),
                    () -> assertEquals(1, matrix[0][0], "The value at [0][0] should be 1."),
                    () -> assertEquals(2, matrix[0][1], "The value at [0][1] should be 2."),
                    () -> assertEquals(3, matrix[0][2], "The value at [0][2] should be 3."),
                    () -> assertEquals(4, matrix[1][0], "The value at [1][0] should be 4."),
                    () -> assertEquals(6, matrix[1][2], "The value at [1][2] should be 6.")
            );
        } catch (IllegalArgumentException e) {
            fail("buildMatrixWithValues threw an exception for valid input: " + e.getMessage());
        }
    }

    @Test
    public void testBuildMatrixWithValues_InvalidInput_ShouldThrowException() {
        int[] size = MatrixInput.determineMatrixSize("[2,3]");
        String[] invalidInputs = {"[[1,2], [4,5,6,7]]", "[[1,2],[3]", "", null, "[[1,two],[3,4]]", "[[1 2,3],[4,5,6]]"};
        for (String input : invalidInputs) {
            assertThrows(IllegalArgumentException.class, () -> {
                MatrixInput.buildMatrixWithValues(input, size);
            }, "buildMatrixWithValues did not throw an exception for invalid input: " + input);
        }
    }
}