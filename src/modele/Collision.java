/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Gus
 */
public class Collision {

    public static void AppliquerCollisionParticule(Particule pParticuleA, Particule pParticuleB) {
        try {
            Vector2 x = pParticuleA.getPosition();
            x.substract(pParticuleB.getPosition());
            x = x.normalize();

            Vector2 v1 = pParticuleA.getVitesse();
            double x1 = Vector2.Dot(x, v1);

            Vector2 v1x = x.clone();
            v1x.multiplyScalar(x1);
            Vector2 v1y = v1.clone();
            v1y.substract(v1x);

            double m1 = pParticuleA.getMass();

            x.multiplyScalar(-1);
            Vector2 v2 = pParticuleB.getVitesse();
            double x2 = Vector2.Dot(x, v2);

            Vector2 v2x = x.clone();
            v2x.multiplyScalar(x2);
            Vector2 v2y = v2.clone();
            v2y.substract(v2x);

            double m2 = pParticuleB.getMass();

            double massCombinees = m1 + m2;
            
            Vector2 newVitesseA = v1x.clone();
            Vector2 v2xAf = v2x.clone();
            newVitesseA.multiplyScalar((m1 - m2) / massCombinees);
            v2xAf.multiplyScalar((2 * m2) / massCombinees);
            newVitesseA.add(v2xAf);
            newVitesseA.add(v1y);
            
            Vector2 newVitesseB = v1x.clone();
            Vector2 v2xBf = v2x.clone();
            newVitesseB.multiplyScalar((2 * m1) / massCombinees);
            v2xBf.multiplyScalar((2 * m1) / massCombinees);
            newVitesseB.add(v2xAf);
            newVitesseB.add(v2y);
            
            pParticuleA.setVitesse(newVitesseA);
            pParticuleB.setVitesse(newVitesseB);
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(Collision.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void AppliquerCollisionConteneur(Particule pParticule, Terrain pTerrain) {
        if (pParticule.getPosX() + pParticule.getVitesseX() < pTerrain.getMinX()) {
            pParticule.setPosX(pParticule.getRayon());
            pParticule.setVitesseX(-1 * pParticule.getVitesseX());
        } else if (pParticule.getPosX() + pParticule.getVitesseX() > pTerrain.getMaxX()) {
            pParticule.setPosX(pTerrain.getMaxX() - pParticule.getRayon());
            pParticule.setVitesseX(-1 * pParticule.getVitesseX());
        }

        if (pParticule.getPosY() + pParticule.getVitesseY() < pTerrain.getMinY()) {
            pParticule.setPosY(pParticule.getRayon());
            pParticule.setVitesseY(-1 * pParticule.getVitesseY());
        } else if (pParticule.getPosY() + pParticule.getVitesseY() > pTerrain.getMaxY()) {
            pParticule.setPosY(pTerrain.getMaxY() - pParticule.getRayon());
            pParticule.setVitesseY(-1 * pParticule.getVitesseY());
        }
    }
}
