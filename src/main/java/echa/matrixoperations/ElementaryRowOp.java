/*
 *  Made for college assignments/personal projects.
 *  Do not use without permission
 */

package echa.matrixoperations;

/**
 *
 * @author echa
 * Bernardus Hersa Galih Prakoso - 215314018
 * Informatika - Universitas Sanata Dharma
 */
public class ElementaryRowOp{
    protected double[][] matrix;
    protected double[][] matrixEnd;
       
    public double[][] getMatrix() {
        return matrix;
    }
    
    public double[][] getMatrixEnd(){
        return matrixEnd;
    }

    public void setMatrix(double[][] matrix) {
        this.matrix = matrix;
        matrixEnd=matrix;
    }
    
    public void exchange(int rowA, int rowB){
        double[] tempRowA=matrixEnd[rowB];
        double[] tempRowB=matrixEnd[rowA];
        
        matrixEnd[rowA]=tempRowA;
        matrixEnd[rowB]=tempRowB;
    }
    
    public void multiply(int row, double scale){
        double[] tempRow=matrixEnd[row];
        for(int i=0;i<matrixEnd[row].length;i++){
            matrixEnd[row][i]=scale*tempRow[i];
        }
    }
    
    public void add(int rowOp, int rowAdd, double scale){
        double[] tempRowA=matrixEnd[rowOp];
        double[] tempRowB=matrixEnd[rowAdd];
        for(int i=0;i<matrixEnd[rowOp].length;i++){
            matrixEnd[rowOp][i]=tempRowA[i]+(scale*tempRowB[i]);
        }
    }
}
