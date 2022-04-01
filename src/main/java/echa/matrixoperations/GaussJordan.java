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
        
    public GaussJordan(double[][] matrix){
        setMatrix(matrix);
        matrixEnd=matrix;
    }
            
    private void check(int row, int column){        
        int i=row+1;
        while(matrixEnd[row][column]==0 && i<matrixEnd.length){
            double[] temp=matrixEnd[i];
            matrixEnd[row]=temp;
            i++;
        }        
    }
    
    public void Gauss(){
        for(int index=0;index<matrixEnd.length;index++){
            check(index, index);
            if(matrixEnd[index][index]!=1){
                double scale=1/matrixEnd[index][index];
                multiply(index, scale);
            }
            for(int i=index+1;i<matrixEnd.length;i++){
                if(matrixEnd[i][index]!=0){
                    double scale=-(matrixEnd[i][index]/matrixEnd[index][index]);
                    add(i, index, scale);
                }
            }
        }
    }
    
    public void GaussJordan(){
        Gauss();
        for(int index=matrixEnd.length-1;index>=0;index--){
            for(int i=index-1;i>=0;i--){
                double scale=-(matrixEnd[i][index]/matrixEnd[index][index]);
                add(i, index, scale);
            }
        }
    }
    @Override
    public String toString(){
        return Arrays.deepToString(matrixEnd);
    }
}
