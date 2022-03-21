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
            {0,3,-1},
            {1,1,0},
            {-2,0,1},
            };
        Inverse inverse=new Inverse(matrix,0);
        System.out.println(inverse);
        
        GaussJordan gj=new GaussJordan(matrix);
        gj.GaussJordan();
        System.out.println(gj);
    }

}
