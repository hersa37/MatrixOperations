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
    private double[][] matrix;
       
    public double[][] getMatrix() {
        return matrix;
    }

    public void setMatrix(double[][] matrix) {
        this.matrix = matrix;
    }
    
    public void swtich(int rowA, int rowB){
        double[] tempRowA=matrix[rowB];
        double[] tempRowB=matrix[rowA];
        
        matrix[rowA]=tempRowA;
        matrix[rowB]=tempRowB;
    }
    
    public void multiply(int row, double scale){
        double[] tempRow=matrix[row];
        for(int i=0;i<matrix.length;i++){
            matrix[row][i]=scale*tempRow[i];
        }
    }
    
    public void add(int rowOp, int rowAdd, double scale){
        double[] tempRowA=matrix[rowOp];
        double[] tempRowB=matrix[rowAdd];
        for(int i=0;i<matrix.length;i++){
            matrix[rowOp][i]=tempRowA[i]+(scale*tempRowB[i]);
        }
    }
}
