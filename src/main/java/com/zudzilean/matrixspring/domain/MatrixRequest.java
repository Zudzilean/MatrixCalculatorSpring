package com.zudzilean.matrixspring.domain;

import lombok.Getter;
import lombok.Setter;

/**
 * @author
 * @since 2024/5/23
 */
@Getter
@Setter
public class MatrixRequest {
    /**
     *
     */
    private int[] sizeA;
    /**
     *
     */
    private double[][] matrixA;
    private int[] sizeB;
    private double[][] matrixB;
    private String operation;
}
