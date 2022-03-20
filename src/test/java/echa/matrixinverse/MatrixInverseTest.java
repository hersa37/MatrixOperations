/*
 *  Made for college assignments/personal projects.
 *  Do not use without permission
 */
package echa.matrixinverse;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author echa
 */
public class MatrixInverseTest {
    
    public MatrixInverseTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of getMatrix method, of class MatrixInverse.
     */
    @Test
    public void testGetMatrix() {
        System.out.println("getMatrix");
        MatrixInverse instance = null;
        double[][] expResult = null;
        double[][] result = instance.getMatrix();
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setMatrix method, of class MatrixInverse.
     */
    @Test
    public void testSetMatrix() {
        System.out.println("setMatrix");
        double[][] matrix = null;
        MatrixInverse instance = null;
        instance.setMatrix(matrix);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getOrder method, of class MatrixInverse.
     */
    @Test
    public void testGetOrder() {
        System.out.println("getOrder");
        MatrixInverse instance = null;
        int expResult = 0;
        int result = instance.getOrder();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of minorMatrix method, of class MatrixInverse.
     */
    @Test
    public void testMinorMatrix() {
        System.out.println("minorMatrix");
        int rowExclude = 0;
        int columnExclude = 0;
        double[][] matrix = null;
        MatrixInverse instance = null;
        double[][] expResult = null;
        double[][] result = instance.minorMatrix(rowExclude, columnExclude, matrix);
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of minorDet method, of class MatrixInverse.
     */
    @Test
    public void testMinorDet() {
        System.out.println("minorDet");
        double[][] matrix = null;
        MatrixInverse instance = null;
        double expResult = 0.0;
        double result = instance.minorDet(matrix);
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of cofactor method, of class MatrixInverse.
     */
    @Test
    public void testCofactor() {
        System.out.println("cofactor");
        MatrixInverse instance = null;
        double[][] expResult = null;
        double[][] result = instance.cofactor();
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class MatrixInverse.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        MatrixInverse instance = null;
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
