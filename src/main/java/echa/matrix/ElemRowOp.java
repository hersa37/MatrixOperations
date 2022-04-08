/*
 *  Made for college assignments/personal projects.
 *  Do not use without permission
 */

package echa.matrix;

import java.util.Arrays;

/**
 *
 * @author echa
 * Bernardus Hersa Galih Prakoso - 215314018
 * Informatika - Universitas Sanata Dharma
 */
public class ElemRowOp {
    
    private ElemRowOp(){
        
    }
    
    static double[][] copyArray(double[][] matrix){
        return Arrays.stream(matrix).map(double[]::clone).toArray(double[][]::new);
    }
    
    static double[][] multiply(int row, double scale, double[][] matrixBase){
        double[][] matrix=copyArray(matrixBase);
        double[][] tempMatrix=copyArray(matrix);
        for(int i=0;i<matrix[row].length;i++){
            matrix[row][i]=scale*tempMatrix[row][i];
        }
        return matrix;
    }
    
    static double[][] add(int rowOp, int rowAdd, double scale, double[][] matrixBase){
        double[][] matrix=copyArray(matrixBase);
        double[][] tempMatrix=copyArray(matrix);
        for(int i=0;i<matrix[rowOp].length;i++){
            matrix[rowOp][i]=tempMatrix[rowOp][i]+(scale*tempMatrix[rowAdd][i]);
        }
        return matrix;
    }
    
    static double[][] check(int row, int column, double [][] matrixBase){        
        double[][] matrix=copyArray(matrixBase);
        int i=row+1;
        while(matrix[row][column]==0 && i<matrix.length){
            double[] temp=matrix[i];
            matrix[row]=temp;
            i++;
        }
        return matrix;
    }
}
