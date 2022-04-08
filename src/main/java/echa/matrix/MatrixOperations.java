/*
 *  Made for college assignments/personal projects.
 *  Do not use without permission
 */

package echa.matrix;

import java.util.Arrays;
import echa.matrix.Matrix.*;


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
            {-1,0,-1},
            {0,2,3},
            {1,0,-1}
            };
        System.out.println(Matrix.toString(Matrix.GaussJordan(matrix)));
        
//        GaussJordan gj=new GaussJordan(matrix);
//        System.out.println(gj.toString());
//        gj.GaussJordan();
        
    }

}
