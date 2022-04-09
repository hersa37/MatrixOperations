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
     */
    public static double determinant(double[][] matrixBase){
        double[][] matrix=copyArray(matrixBase);
        if(order(matrix)==3){
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
    
    private static double[][] inverseRowExp(double[][] matrix){
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
    
    private static double[][] inverseElemOp(double[][] matrix){
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
            System.arraycopy(matrix[row], 0, matrixTemp[row], 0, matrix[0].length);
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
     * Calculates whether a set of vectors can be linearly combined to produce a certain vector
     * @param setOfVectors the vector that wants to be checked if they're a linear combination
     * @param productVector the vector that is the supposed product of the list of vector
     * @return the Gauss-Jordan operation that shows each scalar constant's value
     */
    
    public static double[][] linearCombination(double[][] setOfVectors, double[] productVector){
        double[][] vectorCombination=combineVector(setOfVectors, productVector);
        return GaussJordan(vectorCombination);       
    }
    
    private static double[][] combineVector(double[][] setOfVectors, double[] productVector){
        double[][] vectorCombination=new double[setOfVectors[0].length][setOfVectors.length+1];
        double[][] transpose=transpose(setOfVectors);
        
        for(int row=0;row<vectorCombination.length;row++){
            System.arraycopy(transpose[row], 0, vectorCombination[row], 0, transpose[0].length);
            vectorCombination[0][vectorCombination.length-1]=productVector[row];
        }
        return vectorCombination;
    }
    
    private static double[][] transpose(double[][] matrix){
        double[][] transpose=new double[matrix[0].length][matrix.length]; 
        for(int row=0;row<transpose.length;row++){
            for(int column=0;column<transpose[0].length;column++){
                transpose[row][column]=matrix[column][row];
            }
        }
        return transpose;
    }
    
    private static boolean linearIndependence(double[][] setOfVectors){
        double[] productVector=new double[setOfVectors.length];
        double[][] vectorCombination=combineVector(setOfVectors, productVector);
        vectorCombination=GaussJordan(vectorCombination);
        int productColumn=vectorCombination[0].length-1;
        
        for (double[] vectorCombination1 : vectorCombination) {
            if (vectorCombination1[productColumn] != 0) {
                return false;
            }
        }
        
        return true;
    }
    
    private static boolean spans(double[][] setOfVector){
        double[][] transpose=transpose(setOfVector);
        return determinant(transpose) != 0;
    }
    
    /**
     * Checks if a set of vector is a basis of vectors
     * @param setOfVector the vectors checked to see if they're a basis
     * @return whether the set is a basis or not
     */
    public static boolean isBasis(double[][] setOfVector){
        return spans(setOfVector) && linearIndependence(setOfVector);
    }
    
    /**
     * Transforms a vector into a vector of another base
     * @param vector the vector to be transformed
     * @param oldBase the base of the initial vector
     * @param newBase the base of the transformed vector
     * @return the transformed vector in the new base
     */
    public static double[] transformVector(double [] vector, double[][] oldBase,double[][] newBase){
        double[][] transMatrix=transformationMatrix(oldBase, newBase);
        double[] transformed=new double[vector.length];
        for(int i=0; i<transformed.length;i++){
            transformed[i]=dotProduct(transMatrix[i], vector);
        }
        return transformed;
    }
    
    private static double[][] transformationMatrix(double[][] oldBase, double[][] newBase){
        double[][] transformation=new double[oldBase.length][oldBase[0].length];
        for(int row=0;row<transformation.length;row++){
            double[] tempMatrix=transformationMinor(oldBase[row], newBase);
            for(int column=0;column<transformation[0].length;column++){
                
                transformation[column][row]=tempMatrix[column];
            }
        }
        return transformation;
    }
    
    private static double[] transformationMinor(double[] baseVector, double[][] resultVector){
        double[][] minor=new double[resultVector[0].length][resultVector.length+1];
        for(int row=0;row<minor.length;row++){
            for(int column=0;column<minor[0].length-1;column++){
                minor[row][column]=resultVector[column][row];
            }
            minor[row][minor[0].length-1]=baseVector[row];
        }
        minor=GaussJordan(minor);
        double[] scale=new double[minor.length];
        for(int row=0;row<scale.length;row++){
            scale[row]=minor[row][minor[0].length-1];
        }
        return scale;
    }
    
    private static double dotProduct(double[] matrix, double[] scale){
        double product=0;
        for(int i=0;i<matrix.length;i++){
            product+=matrix[i]*scale[i];
        }
        return product;
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
    
    public static String toString(double[] matrix){
        String print="|";
            for (int j = 0; j < matrix.length; j++) {
                print += (matrix[j]+0.0) + " ";
            }
            print+="|"+"\n";        
        return print;
    }
    
}
