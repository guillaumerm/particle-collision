/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author 1141678
 */
public class TerrainTest {
    
    private Terrain t1, t2, t3;
    
    public TerrainTest() 
    {
        try {
            new Terrain(-1, -1, -1, -1);
            fail();
        } catch (Exception e) 
        {
            
        }
    }
    
    @Before
    public void setUp() {
        try {
            t1 = new Terrain(1, 100, 1, 100);
            t2 = new Terrain(1, 100, 1, 100);
        } catch (TerrainException ex) {
            Logger.getLogger(TerrainTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of setMinX method, of class Terrain.
     */
    @Test
    public void testSetMinX() 
    {
        t1.setMinX(-1);
        assertTrue(t1.getMinX()==1);
        t2.setMinX(0);
        assertTrue(t2.getMinX()==0);
    }

    /**
     * Test of setMaxX method, of class Terrain.
     */
    @Test
    public void testSetMaxX() 
    {
        t1.setMaxX(-1);
        assertTrue(t1.getMaxX()==100);
        t2.setMaxX(1000);
        assertTrue(t2.getMaxX()==1000);
    }

    /**
     * Test of setMinY method, of class Terrain.
     */
    @Test
    public void testSetMinY() 
    {
        t1.setMinY(-1);
        assertTrue(t1.getMinY()==1);
        t2.setMinY(0);
        assertTrue(t2.getMinY()==0);
    }

    /**
     * Test of setMaxY method, of class Terrain.
     */
    @Test
    public void testSetMaxY() 
    {
        t1.setMaxY(-1);
        assertTrue(t1.getMaxY()==100);
        t2.setMaxY(1000);
        assertTrue(t2.getMaxY()==1000);
    }

    /**
     * Test of getMinX method, of class Terrain.
     */
    @Test
    public void testGetMinX() 
    {
        assertTrue(t1.getMinX()==1);
        assertTrue(t2.getMinX()==1);
    }

    /**
     * Test of getMaxX method, of class Terrain.
     */
    @Test
    public void testGetMaxX() 
    {
        assertTrue(t1.getMaxX()==100);
        assertTrue(t2.getMaxX()==100);
    }

    /**
     * Test of getMinY method, of class Terrain.
     */
    @Test
    public void testGetMinY() {
        assertTrue(t1.getMinY()==1);
        assertTrue(t2.getMaxX()==100);
    }

    /**
     * Test of getMaxY method, of class Terrain.
     */
    @Test
    public void testGetMaxY() {
        assertTrue(t1.getMaxY()==100);
        assertTrue(t2.getMaxX()==100);
    }
    
}
