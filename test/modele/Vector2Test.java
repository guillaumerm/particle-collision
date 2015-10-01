/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import modele.exception.Vector2Exception;
import java.util.logging.Level;
import java.util.logging.Logger;
import static modele.Vector2.Dot;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author 1141678
 */
public class Vector2Test {

    private Vector2 v1, v2, v3, v4, v5, v6;

    public Vector2Test() 
    {
        try {
            new Vector2();
        } catch (Exception e) 
        {
            
        }
        try {
            new Vector2(100,100);
        } catch (Exception e) 
        {
            
        }
        try {
            new Vector2(v1);
        } catch (Exception e) 
        {
            
        }
    }

    @Before
    public void setUp() {
        try {
            v1 = new Vector2(5, 5);
            v2 = new Vector2(10, 10);
            v3 = new Vector2(15, 15);
            v4 = new Vector2(v1);
            v5 = new Vector2(v2);
        } catch (Vector2Exception ex) {
            Logger.getLogger(Vector2Test.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of add method, of class Vector2.
     */
    @Test
    public void testAdd() {

        try {
            v1.add(v2);
            assertTrue(v1.getX() == 15);
            v2.add(v4);
            assertTrue(v2.getX() == 15);
            v3.add(v6);
            assertTrue(v3.getX() == 10);
        } catch (Vector2Exception ex) {
            Logger.getLogger(Vector2Test.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Test of substract method, of class Vector2.
     */
    @Test
    public void testSubstract() {
        try {
            v2.substract(v1);
            assertTrue(v2.getX() == 5);
            v3.substract(v4);
            assertTrue(v2.getX() == 10);
            
        } catch (Vector2Exception ex) {
            Logger.getLogger(Vector2Test.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            v1.substract(v6);
            fail();
        } catch (Exception e) {
        }
    }

    /**
     * Test of multiplyScalar method, of class Vector2.
     */
    @Test
    public void testMultiplyScalar() {
        v1.multiplyScalar(-1);
        assertTrue(v1.getX() == -5);
        v2.multiplyScalar(0);
        assertTrue(v2.getX() == 0);
        v3.multiplyScalar(2);
        assertTrue(v3.getX() == 30);
    }

    /**
     * Test of divideScalar method, of class Vector2.
     */
    @Test
    public void testDivideScalar() {
        try {
            v1.divideScalar(-1);
            assertTrue(v1.getX() == -5);
            v2.divideScalar(2);
            assertTrue(v2.getX() == 5);
            v3.divideScalar(0);
            assertTrue(v3.getX() == 15);
        } catch (Vector2Exception ex) {
            Logger.getLogger(Vector2Test.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of normalize method, of class Vector2.
     */
    @Test
    public void testNormalize() 
    {
        v1.normalize();
        assertTrue(v1.getX() == 5/v1.magnitude());
        assertTrue(v1.getY() == 5/v1.magnitude());
    }

    /**
     * Test of magnitude method, of class Vector2.
     */
    @Test
    public void testMagnitude() 
    {
       assertTrue(v1.magnitude() == Math.sqrt((v1.getX() * v1.getX()) + (v1.getY() * v1.getY())));
    }

    /**
     * Test of setX method, of class Vector2.
     */
    @Test
    public void testSetX() {
        v1.setX(-1);
        assertTrue(v1.getY() == -1);
        v2.setX(0);
        assertTrue(v2.getY() == 0);
        v3.setX(2);
        assertTrue(v3.getY() == 2);
    }

    /**
     * Test of setY method, of class Vector2.
     */
    @Test
    public void testSetY() {
        v1.setY(-1);
        assertTrue(v1.getY() == -1);
        v2.setY(0);
        assertTrue(v2.getY() == 0);
        v3.setY(2);
        assertTrue(v3.getY() == 2);
    }

    /**
     * Test of getX method, of class Vector2.
     */
    @Test
    public void testGetX() {
        assertTrue(v1.getX() == 5);
        assertTrue(v5.getX() == 10);
    }

    /**
     * Test of getY method, of class Vector2.
     */
    @Test
    public void testGetY() {
        assertTrue(v1.getY() == 5);
        assertTrue(v5.getY() == 10);
    }

    /**
     * Test of Dot method, of class Vector2.
     */
    @Test
    public void testDot() {
        assertTrue(Dot(v1,v2) == 125);
    }
}
