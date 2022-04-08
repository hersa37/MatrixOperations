/*
 *  Made for college assignments/personal projects.
 *  Do not use without permission
 */

package echa.matrix;

import static echa.matrix.ElemRowOp.copyArray;
import java.util.Arrays;
import echa.matrix.Matrix.*;


/**
 *
 * @author echa
 */
public class MatrixOperations {

    /**
     * @param args the command line arguments
     * @throws echa.matrix.UnsopportedMatrixException
     */
    public static void main(String[] args) throws UnsopportedMatrixException {
        double[][] matrix = {
            {1,3,0,0},
            {0,1,0,0},
            {0,0,1,0},
            {0,0,0,1}
            };
        
        
        System.out.println(Matrix.toString(Matrix.inverse(matrix)));
        System.out.println("");
        
//        GaussJordan gj=new GaussJordan(matrix);
//        System.out.println(gj.toString());
//        gj.GaussJordan();
        
    }

}
