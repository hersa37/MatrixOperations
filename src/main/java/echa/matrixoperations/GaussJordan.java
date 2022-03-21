/*
 *  Made for college assignments/personal projects.
 *  Do not use without permission
 */

package echa.matrixoperations;

import java.util.Arrays;

/**
 *
 * @author echa
 * Bernardus Hersa Galih Prakoso - 215314018
 * Informatika - Universitas Sanata Dharma
 */
public class GaussJordan extends ElementaryRowOp{
    private double [][] matrix;
    
    public GaussJordan(double[][] matrix){
        setMatrix(matrix);
        this.matrix=matrix;
    }
            
    private void check(int row, int column){        
        int i=row+1;
        while(matrix[row][column]==0 && i<matrix.length){
            double[] temp=matrix[i];
            matrix[row]=temp;
            i++;
        }
        setMatrix(matrix);
    }
    
    public void Gauss(){
        for(int index=0;index<matrix.length;index++){
            check(index, index);
            if(matrix[index][index]!=1){
                double scale=1/matrix[index][index];
                multiply(index, scale);
            }
            for(int i=index+1;i<matrix.length;i++){
                if(matrix[i][index]!=0){
                    double scale=-(matrix[i][index]/matrix[index][index]);
                    add(i, index, scale);
                }
            }
        }
    }
    
    public void GaussJordan(){
        Gauss();
        for(int index=matrix.length-1;index>0;index--){
            for(int i=index-1;i>0;i--){
                double scale=-(matrix[i][index]/matrix[index][index]);
                add(i, index, scale);
            }
        }
    }
    @Override
    public String toString(){
        return Arrays.deepToString(matrix);
    }
}
