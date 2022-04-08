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
     * @param matrixBase the matrix whose determinant is to be found
     * @return the determinant of matrix. Uses row expansion on the 1st row
     * @throws echa.matrix.UnsopportedMatrixException
     */
    public static double determinant(double[][] matrixBase) throws UnsopportedMatrixException{
        double[][] matrix=copyArray(matrixBase);
        if(order(matrix)<=3){
            return rowExpansion(0, matrix);
        }
        for(int row=0;row<matrix.length;row++){
            matrix=ElemRowOp.check(row, row,matrix);
            for(int i=row+1;i<matrix.length;i++){
                double scale2;
                if(matrix[i][row]!=0){
                    scale2=-(matrix[i][row]/matrix[row][row]);
                    matrix=add(i, row, scale2,matrix);
                }           
            }
        }
        double determinant=1;
        for(int row=0;row<matrix.length;row++){
            determinant=determinant*matrix[row][row];
        }
        return determinant;
    }
    
    private static double rowExpansion(int row, double[][] matrix) throws UnsopportedMatrixException{
        double[][] cofactor=cofactor(matrix);
        double determinant=0;
        
        try{
            for(int i=0;i<matrix.length;i++){
                determinant+=matrix[row][i]*cofactor[row][i];
            }
        } catch (Exception e){
            throw new UnsopportedMatrixException("No inverse");
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
     * Calculates inverse of a matrix 
     * @param matrix the matrix used to find its inverse
     * @return the inverse
     * @throws echa.matrix.UnsopportedMatrixException
     */
    public static double[][] inverse(double[][] matrix) throws UnsopportedMatrixException{
        if(!checkSquare(matrix)){
            throw new UnsopportedMatrixException("Not square matrix");
        }
        if(matrix.length<=3){
            return inverseRowExp(matrix);
        }else return inverseElemOp(matrix);
    }
    
    private static double[][] inverseRowExp(double[][] matrix) throws UnsopportedMatrixException{
        double determinant=determinant(matrix);
        if(determinant==0){
            return matrix;
        }
        double[][] inverse=new double[matrix.length][matrix.length];
        double[][] adjoint=adjoint(matrix);


        for(int i=0;i<matrix.length;i++){
            for(int j=0;j<matrix.length;j++){
                inverse[i][j]=(1/determinant)*adjoint[i][j];
            }
        }
        return inverse;

    }
    
    private static double[][] inverseElemOp(double[][] matrix) throws UnsopportedMatrixException{
        if(determinant(matrix)==0){
            return matrix;
        }
        double[][] matrixTemp=GaussJordan(compoundMatrix(matrix));
        double[][] inverse=new double[matrix.length][matrix.length];
        for(int row=0;row<inverse.length;row++){
            for(int column=0;column<inverse.length;column++){
                inverse[row][column]=matrixTemp[row][column+inverse.length];
            }
        }
        return inverse;
    }
    
    private static double[][] compoundMatrix(double[][] matrix){
        double[][] matrixTemp=new double[matrix.length][2*matrix.length];
        for(int row=0;row<matrix.length;row++){
            for(int column=0;column<matrix[0].length;column++){
                matrixTemp[row][column]=matrix[row][column];
            }
            for(int column=matrix.length;column<matrixTemp[0].length;column++){
                if(column==row+matrix.length){
                    matrixTemp[row][column]=1;
                }
            }
        }
        return matrixTemp;
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
    
    /**
     * Applies Gauss-Jordan operation on a matrix
     * @param matrixBase the matrix used to do Gauss-Jordan operation
     * @return the Gauss-Jordan matrix
     */
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
    
    private static boolean checkSquare(double[][] matrix){
        return(matrix.length==matrix[0].length);
    }
    
    /**
     * Prints matrix in grid format
     * @param matrix the matrix to print
     * @return string format of matrix
     */
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
