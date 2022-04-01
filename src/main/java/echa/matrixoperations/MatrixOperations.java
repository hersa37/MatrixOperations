/*
 *  Made for college assignments/personal projects.
 *  Do not use without permission
 */

package echa.matrixoperations;

import java.util.Arrays;


/**
 *
 * @author echa
 */
public class MatrixOperations {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        double[][] matrix = {
            {-1,4,-2,1},
            {2,-1,3,7},
            {1,-3,2,2}
            };
//        Inverse inverse=new Inverse(matrix,0);
//        System.out.println(inverse);
        
        System.out.println(Arrays.deepToString(matrix));
        GaussJordan gj=new GaussJordan(matrix);
        gj.GaussJordan();
        System.out.println(Arrays.deepToString(gj.getMatrixEnd()));
        
        
    }

}
