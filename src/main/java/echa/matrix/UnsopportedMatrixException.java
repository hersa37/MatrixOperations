/*
 *  Made for college assignments/personal projects.
 *  Do not use without permission
 */

package echa.matrix;

/**
 *
 * @author echa
 * Bernardus Hersa Galih Prakoso - 215314018
 * Informatika - Universitas Sanata Dharma
 */
public class UnsopportedMatrixException extends Exception {
    public UnsopportedMatrixException(){
        super("Invalid matrix");
    }
    
    public UnsopportedMatrixException(String message){
        super(message);
    }
}
