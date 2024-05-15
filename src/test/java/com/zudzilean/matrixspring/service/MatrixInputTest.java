package com.zudzilean.matrixspring.service;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import java.util.ArrayList;

public class MatrixInputTest {

    @Test
    void buildMatrixValidInput() {
        String input = "【1,2】【2,3】【4,5】【5,6】";
        List<List<Long>> expectedMatrix = new ArrayList<>();
        expectedMatrix.add(List.of(1L, 2L));
        expectedMatrix.add(List.of(2L, 3L));
        expectedMatrix.add(List.of(4L, 5L));
        expectedMatrix.add(List.of(5L, 6L));

        List<List<Long>> matrix = MatrixInput.buildMatrix(input);
        assertNotNull(matrix, "The matrix should not be null.");
        assertEquals(4, matrix.size(), "The matrix should have 4 rows.");

        for (int i = 0; i < expectedMatrix.size(); i++) {
            assertArrayEquals(expectedMatrix.get(i).stream().mapToLong(Long::longValue).toArray(),
                    matrix.get(i).stream().mapToLong(Long::longValue).toArray(),
                    "The row " + (i + 1) + " of the matrix is incorrect.");
        }
    }

    @Test
    void buildMatrixInvalidInput() {
        String input = "【1,2】【2,three】";
        assertThrows(IllegalArgumentException.class, () -> {
            MatrixInput.buildMatrix(input);
        }, "buildMatrix should throw IllegalArgumentException for invalid input.");
    }
}