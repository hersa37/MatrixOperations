/*
 *  Made for college assignments/personal projects.
 *  Do not use without permission
 */

package echa.matrixoperations;

/**
 *
 * @author echa
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
        System.out.println("Gauss");
        for(int row=0;row<matrixEnd.length;row++){            
            check(row, row);
            double scale=1;
            if(matrixEnd[row][row]!=1){
                scale=1/matrixEnd[row][row];
                multiply(row, scale);
            }
            System.out.println("Pivot row "+(row+1)+"\nScale:"+scale+"\n"
                    +toString());          
            if(row!=matrixEnd.length-1){
                System.out.println("Zeroing column "+(row+1));
            }
            for(int i=row+1;i<matrixEnd.length;i++){
                
                double scale2=0;
                if(matrixEnd[i][row]!=0){
                    scale2=-(matrixEnd[i][row]/matrixEnd[row][row]);
                    add(i, row, scale2);
                }
                System.out.println("Row "+(i+1)+"; Scale: "+scale2);
                System.out.println(toString());              
            }
        }
    }
    
    public void GaussJordan(){
        Gauss();
        System.out.println("Gauss-Jordan");
        for(int row=matrixEnd.length-1;row>=0;row--){
            for(int i=row-1;i>=0;i--){
                double scale=-(matrixEnd[i][row]/matrixEnd[row][row]);
                add(i, row, scale);
            }
        }
        System.out.println(toString());
    }
    @Override
    public String toString(){
        String print="";
        for (double[] matrixEnd1 : matrixEnd) {
            print+="|";
            for (int j = 0; j < matrixEnd1.length; j++) {
                print += matrixEnd1[j] + " ";
            }
            print+="|"+"\n";
        }
        return print;
    }
}
