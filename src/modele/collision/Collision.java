/**
 *
 * @author Guillaume Rochefort-Mathieu & Antoine Laplante
 */
package modele.collision;

import modele.exception.Vector2Exception;
import java.util.logging.Level;
import java.util.logging.Logger;
import modele.Particule;
import modele.Terrain;
import modele.Vector2;

/**
 *
 * @author Gus
 */
public class Collision {

    /**
     * 
     * @param particuleCourrante
     * @param particule 
     */
    public static void AppliquerCollisionParticule(Particule particuleCourrante, Particule particule) {
        if (detecterCollisionParticule(particuleCourrante, particule)) {
            collisionParticule(particuleCourrante, particule);
        }
    }

    /**
     * 
     * @param pParticuleA
     * @param pParticuleB 
     */
    private static void collisionParticule(Particule pParticuleA, Particule pParticuleB) {
        try {
            //Obtenir mass combinées
            double m1 = pParticuleA.getMass();
            double m2 = pParticuleB.getMass();
            double massCombinees = m1 + m2;
            
            // Obtien la norm du vector de collision
            Vector2 x = new Vector2(pParticuleA.getPosition());
            x.substract(pParticuleB.getPosition());
            x = x.normalize();
            
            // Obtien le composant x du nouveau vector de vitesse de la particule A
            Vector2 v1 = new Vector2(pParticuleA.getVitesse());
            double x1 = Vector2.Dot(x, v1);
            Vector2 v1x = new Vector2(x);
            v1x.multiplyScalar(x1);
            
            // Obtien le composant y du nouveau vector de vitesse de la particule A
            Vector2 v1y = new Vector2(v1);
            v1y.substract(v1x);
            
            // Inverse la norm pour calculer la vitesse de la particule B
            x.multiplyScalar(-1);
            
            // Obtien le composant x du nouveau vector de vitesse de la particule B
            Vector2 v2 = new Vector2(pParticuleB.getVitesse());
            double x2 = Vector2.Dot(x, v2);
            Vector2 v2x = new Vector2(x);
            v2x.multiplyScalar(x2);
            
            //Obtien le composant y du nouveau vector de vitesse de la particule B
            Vector2 v2y = new Vector2(v2);
            v2y.substract(v2x);
            
            // Calcule le nouveau vector de vitesse de la particule A avec la prise
            // en charge du quantité du movement
            Vector2 newVitesseA = new Vector2(v1x);
            Vector2 v2xAf = new Vector2(v2x);
            newVitesseA.multiplyScalar(((m1 - m2) / massCombinees));
            v2xAf.multiplyScalar(((2 * m2) / massCombinees));
            newVitesseA.add(v2xAf);
            newVitesseA.add(v1y);
            
            // Calcule le nouveau vector de vitesse de la particule B avec la prise
            // en charge du quantité du movement
            Vector2 newVitesseB = new Vector2(v1x);
            Vector2 v2xBf = new Vector2(v2x);
            newVitesseB.multiplyScalar(((2 * m1) / massCombinees));
            v2xBf.multiplyScalar(((m2 - m1) / massCombinees));
            newVitesseB.add(v2xBf);
            newVitesseB.add(v2y);
            
            pParticuleA.setVitesse(newVitesseA);
            pParticuleB.setVitesse(newVitesseB);
        } catch (Vector2Exception ex) {
            Logger.getLogger(Collision.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * 
     * @param particuleCourrante
     * @param particule
     * @return 
     */
    private static boolean detecterCollisionParticule(Particule particuleCourrante, Particule particule) {
        double dx = particuleCourrante.getPosition().getX() - particule.getPosition().getX();
        double dy = particuleCourrante.getPosition().getY() - particule.getPosition().getY();
        double distance = Math.sqrt(dx * dx + dy * dy);
        boolean collision = false;
        if (distance <= particuleCourrante.getRayon() + particule.getRayon()) {
            repositionneParticuleCollision(particuleCourrante, particule, distance);
            collision = true;
        }

        return collision;
    }

    /**
     * 
     * @param particuleCourrante
     * @param particule
     * @param distance 
     */
    private static void repositionneParticuleCollision(Particule particuleCourrante, Particule particule, double distance) {
        double delta = distance - (particuleCourrante.getRayon() + particule.getRayon());
        double angle = Math.atan(particule.getVitesse().getY() / particule.getVitesse().getX());
        
        double newPosX = particule.getPosition().getX() - (delta * Math.cos(angle));
        double newPosY = particule.getPosition().getY() - (delta * Math.sin(angle));

        particule.getPosition().setX(newPosX + 1);
        particule.getPosition().setY(newPosY + 1);
        
        detecterCollisionParticule(particuleCourrante, particule);
    }

    /**
     * 
     * @param pParticule
     * @param pTerrain 
     */
    public static void AppliquerCollisionConteneur(Particule pParticule, Terrain pTerrain) {
        if (pParticule.getPosition().getX() + pParticule.getVitesse().getX() < pTerrain.getMinX() + pParticule.getRayon()) {
            pParticule.setPosX(pParticule.getRayon());
            pParticule.setVitesseX(-1 * pParticule.getVitesse().getX());
        } else if (pParticule.getPosition().getX() + pParticule.getRayon() + pParticule.getVitesse().getX() > pTerrain.getMaxX()) {
            pParticule.setPosX(pTerrain.getMaxX() - pParticule.getRayon());
            pParticule.setVitesseX(-1 * pParticule.getVitesse().getX());
        }

        if (pParticule.getPosition().getY() + pParticule.getVitesse().getY() < pTerrain.getMinY() + pParticule.getRayon()) {
            pParticule.setPosY(pParticule.getRayon());
            pParticule.setVitesseY(-1 * pParticule.getVitesse().getY());
        } else if (pParticule.getPosition().getY() + pParticule.getRayon() + pParticule.getVitesse().getY() > pTerrain.getMaxY()) {
            pParticule.setPosY(pTerrain.getMaxY() - pParticule.getRayon());
            pParticule.setVitesseY(-1 * pParticule.getVitesse().getY());
        }
    }
}
