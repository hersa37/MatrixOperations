/*
 *  Made for college assignments/personal projects.
 *  Do not use without permission
 */

package echa.matrix;

import static echa.matrix.ElemRowOp.*;
import java.util.Arrays;

/**
 *
 * @author echa
 * Bernardus Hersa Galih Prakoso - 215314018
 * Informatika - Universitas Sanata Dharma
 */
public class Matrix {
    
    
    private Matrix(){
        
    }
    
    private static int order(double[][] matrix){
        int order=0;
        if(matrix.length==matrix[0].length){
            order=matrix.length;
        }
        return order;
    }
    
    private static double[][] minorMatrix(int rowExclude, int columnExclude, double[][] matrix){
        int order=order(matrix);
        int mOrder=order-1;
        double[][] minor=new double[mOrder][mOrder];
        int mRow=0;

        for(int i=0;i<order;i++){
            if(i==rowExclude) {
                continue;}
            int mColumn=0;
            for(int j=0;j<order;j++){
                if(j==columnExclude) {
                    continue;}
                minor[mRow][mColumn]=matrix[i][j];
                mColumn++;
            }
            mRow++;
        }
        return minor;
    }
    
    private static double minorDet(double [][] mMatrix){
        double minorDet;
        minorDet=(mMatrix[0][0]*mMatrix[1][1])-(mMatrix[1][0]*mMatrix[0][1]);
        return minorDet;
    }
    
    private static double [][] cofactor(double[][] matrix){
        int order=order(matrix);
        double[][] cofactMatrix=new double[order][order];
        int sign=1;
        for(int i=0;i<order;i++){
            for(int j=0;j<order;j++){
                cofactMatrix[i][j]=sign*minorDet(minorMatrix(i, j,matrix));
                sign=sign*-1;
            }
        }
        return cofactMatrix;
    }
    /**
     * Finds determinant of a matrix
     * @param matrix the matrix whose determinant is to be found
     * @return the determinant of matrix. Uses row expansion on the 1st row
     */
    public static double determinant(double[][] matrix){
        return rowExpansion(0, matrix);
    }
    
    private static double rowExpansion(int row, double[][] matrix){
        double[][] cofactor=cofactor(matrix);
        double determinant=0;
        
        for(int i=0;i<matrix.length;i++){
            determinant+=matrix[row][i]*cofactor[row][i];
        }
        return determinant;
    }
    
    private static double[][] adjoint(double[][] matrix){
        int order=order(matrix);
        double[][] adjointMatrix=new double[order][order];
        for(int i=0;i<order;i++){
            for(int j=0;j<order;j++){
                adjointMatrix[i][j]=cofactor(matrix)[j][i];
            }
        }
        return adjointMatrix;
    }
    
    /**
     * Calculates inverse of a matrix using 
     * @param matrixBase the matrix used to find its inverse
     * @return the inverse
     * @throws echa.matrix.UnsopportedMatrixException
     */
    public static double[][] inverse(double[][] matrixBase) throws UnsopportedMatrixException{
        if(matrixBase.length!=matrixBase[0].length){
            throw new UnsopportedMatrixException("Not square matrix");
        }
        int order=order(matrixBase);
        
        double[][] matrix=copyArray(matrixBase);
        double[][] inverse=new double[order][order];
        double[][] adjoint=adjoint(matrix);
        double determinant=rowExpansion(0,matrix);
        
        for(int i=0;i<order;i++){
            for(int j=0;j<order;j++){
                inverse[i][j]=(1/determinant)*adjoint[i][j];
            }
        }
        return inverse;
    }
    
    /**
     * Applies Gauss operation on a matrix
     * @param matrixBase the matrix used to do Gauss operation
     * @return the Gauss matrix
     */
    public static double[][] Gauss(double[][] matrixBase){
        double[][] matrix = copyArray(matrixBase);
        for(int row=0;row<matrix.length;row++){            
            matrix=ElemRowOp.check(row, row,matrix);
            double scale;
            if(matrix[row][row]!=1){
                scale=1/matrix[row][row];
                matrix=ElemRowOp.multiply(row, scale,matrix);
            }
            for(int i=row+1;i<matrix.length;i++){
                double scale2;
                if(matrix[i][row]!=0){
                    scale2=-(matrix[i][row]/matrix[row][row]);
                    matrix=add(i, row, scale2,matrix);
                }           
            }
        }
        return matrix;
    }
    
    public static double[][] GaussJordan(double[][] matrixBase){
        double[][] matrix=Gauss(matrixBase);
        for(int row=matrix.length-1;row>=0;row--){
            for(int i=row-1;i>=0;i--){
                double scale=-(matrix[i][row]/matrix[row][row]);
                matrix=add(i, row, scale,matrix);
            }
        }
        return matrix;
    }
    
    
    
    
    public static String toString(double [][] matrix){
        String print="";
        for (double[] matrix1 : matrix) {
            print+="|";
            for (int j = 0; j < matrix1.length; j++) {
                print += (matrix1[j]+0.0) + " ";
            }
            print+="|"+"\n";
        }
        return print;
    }
    
}
