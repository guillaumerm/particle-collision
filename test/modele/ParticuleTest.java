/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import modele.exception.ParticuleException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author 1141678
 */
public class ParticuleTest {
    
    private Particule p1, p2, p3, p4, p5;
    
    public ParticuleTest() 
    {
        try {
            new Particule(-1,1,1,1,1, "#255");
            fail();
        } catch (Exception e) 
        {
            
        }
        
        try {
            new Particule(1,-1,1,1,1, "#255");
            new Particule(1,361,1,1,1, "#255");
            fail();
        } catch (Exception e) 
        {
            
        }
        
        try {
            new Particule(1,1,0,1,1, "#255");
            fail();
        } catch (Exception e) 
        {
            
        }
        
        try {
            new Particule(-1,1,1,-1,1, "#255");
            fail();
        } catch (Exception e) 
        {
            
        }
        
        try {
            new Particule(1,1,1,1,-1, "#255");
            fail();
        } catch (Exception e) 
        {
            
        }
        try {
            new Particule(1,1,1,1,-1, "255");
            fail();
        } catch (Exception e) 
        {
            
        }
        
    }
    
    @Before
    public void setUp() {
        try {
            p1 = new Particule(2, 25, 10, 2, 2, "#255");
            p2 = new Particule(4, 50, 5, 10, 30, "#225555");
            p3 = new Particule(6, 90, 15, 400, 100, "#255");
            p4 = new Particule(6, 90, 15, 400, 100, "#255");
        } catch (ParticuleException ex) {
            Logger.getLogger(ParticuleTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of setMagnitudeVitesse method, of class Particule.
     */
    @Test
    public void testSetMagnitudeVitesse() {
        p1.setMagnitudeVitesse(-1);
        assertTrue(p1.getVitesse().magnitude() == 2);
        p2.setMagnitudeVitesse(0);
        assertTrue(p2.getVitesse().magnitude() == 0);
        p3.setMagnitudeVitesse(10);
        assertTrue(p3.getVitesse().magnitude() == 10);
    }

    /**
     * Test of setAngle method, of class Particule.
     */
    @Test
    public void testSetAngle() {
        p1.setAngle(-1);
        assertTrue(p1.getAngle() == 25);
        p2.setAngle(10);
        assertTrue(p2.getAngle() == 10);
        p3.setAngle(361);
        assertTrue(p3.getAngle() == 90);
    }

    /**
     * Test of setRayon method, of class Particule.
     */
    @Test
    public void testSetRayon() {
        p1.setRayon(-1);
        assertTrue(p1.getRayon() == 10);
        p2.setRayon(0);
        assertTrue(p2.getRayon() == 5);
        p3.setRayon(20);
        assertTrue(p3.getRayon() == 20);
    }

    /**
     * Test of setPosX method, of class Particule.
     */
    @Test
    public void testSetPosX() 
    {
        p1.setPosX(-1);
        assertTrue(p1.getPosition().getX() == 2);
        p2.setPosX(0);
        assertTrue(p2.getPosition().getX() == 0);
        p3.setPosX(1);
        assertTrue(p3.getPosition().getX() == 1);
    }

    /**
     * Test of setPosY method, of class Particule.
     */
    @Test
    public void testSetPosY() 
    {
        p1.setPosY(-1);
        assertTrue(p1.getPosition().getY() == 2);
        p2.setPosY(0);
        assertTrue(p2.getPosition().getY() == 0);
        p3.setPosY(1);
        assertTrue(p3.getPosition().getY() == 1);
    }
        
    /**
     * Test of setCouleur method, of class Particule.
     */
    @Test
    public void testSetCouleur() 
    {
        p1.setCouleur(null);
        assertTrue("#255".equals(p1.getCouleur()));
        p2.setCouleur("255");
        assertTrue("#225555".equals(p2.getCouleur()));
        p3.setCouleur("#225555555");
        assertTrue("#255".equals(p3.getCouleur()));
        p4.setCouleur("#FF99FF");
        assertTrue("#FF99FF".equals(p4.getCouleur()));
    }
    

    /**
     * Test of getMagnitudeVitesse method, of class Particule.
     */
    @Test
    public void testGetMagnitudeVitesse() 
    {
        assertTrue(p1.getVitesse().magnitude() == 2);
        assertTrue(p2.getVitesse().magnitude() == 4);
    }

    /**
     * Test of getAngle method, of class Particule.
     */
    @Test
    public void testGetAngle() 
    {
        assertTrue(p1.getAngle() == 25);
        assertTrue(p2.getAngle() == 50);
    }

    /**
     * Test of getRayon method, of class Particule.
     */
    @Test
    public void testGetRayon() 
    {
        assertTrue(p1.getRayon() == 10);
        assertTrue(p2.getRayon() == 5);
    }

    /**
     * Test of getPosX method, of class Particule.
     */
    @Test
    public void testGetPosX() 
    {
        assertTrue(p1.getPosition().getX() == 2);
        assertTrue(p2.getPosition().getX() == 10);
    }

    /**
     * Test of getPosY method, of class Particule.
     */
    @Test
    public void testGetPosY() {
        assertTrue(p1.getPosition().getY() == 2);
        assertTrue(p2.getPosition().getY() == 30);
    }  
    
    /**
     * Test of getCouleur method, of class Particule.
     */
    @Test
    public void testGetCouleur() {
        assertTrue("#255".equals(p1.getCouleur()));
        assertTrue("#225555".equals(p2.getCouleur()));
    }  
    
    
    
}
