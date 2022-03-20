/*
 *  Made for college assignments/personal projects.
 *  Do not use without permission
 */

package echa.matrixinverse;

/**
 *
 * @author echa
 */
public class MIMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        double[][] matrix = {
            {1,29,12},
            {6,1,34},
            {21,4,2},
            };
        MatrixInverse mi=new MatrixInverse(matrix,0);
        System.out.println(mi);
    }

}
