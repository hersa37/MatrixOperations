/*
 *  Made for college assignments/personal projects.
 *  Do not use without permission
 */
package echa.matrixinverse;


/**
 *
 * @author echa
 */
public class MatrixInverse {

    private double [][] matrix;
    private int order;
    private int row;
    
    public MatrixInverse(double[][] matrix,int row){
        setMatrix(matrix, row);
    }

    public double[][] getMatrix() {
        return matrix;
    }

    public void setMatrix(double[][] matrix,int row) {
        this.matrix = matrix;
        order=order(matrix);
        this.row=row;
        
    }
    
    private int order(double[][] matrix){
        int orderMethod=0;
        if(matrix.length==matrix[0].length){
            orderMethod=matrix.length;
        }
        return orderMethod;
    }
    
    public int getOrder(){
        return order;
    }
    
    private double[][] minorMatrix(int rowExclude, int columnExclude){
        int mOrder=order(matrix)-1;
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
    
    private double minorDet(double [][] mMatrix){
        double minorDet;
        minorDet=(mMatrix[0][0]*mMatrix[1][1])-(mMatrix[1][0]*mMatrix[0][1]);
        return minorDet;
    }
    
    private double [][] cofactor(){
        double[][] cofactMatrix=new double[order][order];
        int sign=1;
        for(int i=0;i<order;i++){
            for(int j=0;j<order;j++){
                cofactMatrix[i][j]=sign*minorDet(minorMatrix(i, j));
                sign=sign*-1;
            }
        }
        return cofactMatrix;
    }
    
    private double rowExpansion(int row){
        double[][] cofactor=cofactor();
        double determinant=0;
        
        for(int i=0;i<matrix.length;i++){
            determinant+=matrix[row][i]*cofactor[row][i];
        }
        return determinant;
    }
    
    private double[][] adjoint(){
        double[][] adjointMatrix=new double[order][order];
        for(int i=0;i<order;i++){
            for(int j=0;j<order;j++){
                adjointMatrix[i][j]=matrix[j][i];
            }
        }
        return adjointMatrix;
    }
    
    public double[][] inverse(){
        double[][] inverse=new double[order][order];
        double[][] adjoint=adjoint();
        double determinant=rowExpansion(row);
        for(int i=0;i<order;i++){
            for(int j=0;j<order;j++){
                inverse[i][j]=(1/determinant)*adjoint[i][j];
            }
        }
        return inverse;
    }
        
    @Override
    public String toString(){
        String outString="";
        double[][] inverse=inverse();
        for(int i=0;i<order;i++){
            outString+="|";
            for(int j=0;j<order;j++){
                outString+=String.format("%.4f", matrix[i][j])+" ";
            }
            outString+="|\n";
        }
        outString+="\ninverse\n";
        for(int i=0;i<order;i++){
            outString+="|";
            for(int j=0;j<order;j++){
                outString+=String.format("%.4f", inverse[i][j])+" ";
            }
            outString+="|\n";
        }
        return outString;
    }
}
