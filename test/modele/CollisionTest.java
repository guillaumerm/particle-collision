package modele;

import java.util.logging.Level;
import java.util.logging.Logger;
import modele.exception.ParticuleException;
import modele.exception.TerrainException;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Guillaume Rochefort-Mathieu & Antoine Laplante
 */
public class CollisionTest {

    Particule p1, p2, p3, p4, p5;
    Terrain t1;

    @Before
    public void setUp() {
        try {
            p1 = new Particule(10, 0, 10, 20, 0, "#255");
            p2 = new Particule(0, 0, 10, 30, 0, "#255");
            p3 = new Particule(10, 351, 5, 100, 100, "#255");
            p4 = new Particule(10, 171, 5, 110, 110, "#255");
            p5 = new Particule(10, 180, 5, 0, 0, "#255");
        } catch (ParticuleException ex) {
            Logger.getLogger(CollisionTest.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            t1 = new Terrain(0, 10, 0, 10);
        } catch (TerrainException ex) {
            Logger.getLogger(CollisionTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of AppliquerCollisionParticule method, of class Collision.
     */
    @Test
    public void testAppliquerCollisionParticule() {
        Collision.AppliquerCollisionParticule(p1, p2);
        assertTrue(p1.getPosition().getX() == 20);
        assertTrue(p1.getPosition().getY() == 0);

    }

    /**
     * Test of AppliquerCollisionConteneur method, of class Collision.
     */
    @Test
    public void testAppliquerCollisionConteneur() {
        Collision.AppliquerCollisionConteneur(p1, t1);
        assertTrue(p1.getPosition().getX() == t1.getMaxX() - p1.getRayon());
        Collision.AppliquerCollisionConteneur(p3, t1);
        assertTrue(p3.getPosition().getY() == t1.getMaxY() - p3.getRayon());
        Collision.AppliquerCollisionConteneur(p5, t1);
        assertTrue(p5.getPosition().getX() == t1.getMinX() + p5.getRayon());
        assertTrue(p5.getPosition().getX() == t1.getMinY() + p5.getRayon());
    }

}
