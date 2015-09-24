/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

/**
 *
 * @author Gus
 */
public class Collision {

    public static void AppliquerCollisionParticule(Particule pParticuleA, Particule pParticuleB) {

        double m1 = pParticuleA.getMass();
        double m2 = pParticuleB.getMass();
        double massCombinees = m1 + m2;
        
        Vector2 x = new Vector2(pParticuleA.getPosition());
        x.substract(pParticuleB.getPosition());
        x = x.normalize();

        Vector2 v1 = new Vector2(pParticuleA.getVitesse());
        double x1 = Vector2.Dot(x, v1);
        Vector2 v1x = new Vector2(x);
        v1x.multiplyScalar(x1);

        Vector2 v1y = new Vector2(v1);
        v1y.substract(v1x);

        x.multiplyScalar(-1);

        Vector2 v2 = new Vector2(pParticuleB.getVitesse());
        double x2 = Vector2.Dot(x, v2);
        Vector2 v2x = new Vector2(x);
        v2x.multiplyScalar(x2);

        Vector2 v2y = new Vector2(v2);
        v2y.substract(v2x);

        Vector2 newVitesseA = new Vector2(v1x);
        Vector2 v2xAf = new Vector2(v2x);
        newVitesseA.multiplyScalar(((m1 - m2) / massCombinees));
        v2xAf.multiplyScalar(((2 * m2) / massCombinees));
        newVitesseA.add(v2xAf);
        newVitesseA.add(v1y);
        
        Vector2 newVitesseB = new Vector2(v1x);
        Vector2 v2xBf = new Vector2(v2x);
        newVitesseB.multiplyScalar(((2 * m1) / massCombinees));
        v2xBf.multiplyScalar(((m2 - m1) / massCombinees));
        newVitesseB.add(v2xBf);
        newVitesseB.add(v2y);
        
        pParticuleA.setVitesse(newVitesseA);
        pParticuleB.setVitesse(newVitesseB);
    }

    public static void AppliquerCollisionConteneur(Particule pParticule, Terrain pTerrain) {
        if (pParticule.getPosition().getX() + pParticule.getVitesse().getX() < pTerrain.getMinX()) {
            pParticule.setPosX(pParticule.getRayon());
            pParticule.setVitesseX(-1 * pParticule.getVitesse().getX());
        } else if (pParticule.getPosition().getX() + pParticule.getVitesse().getX() > pTerrain.getMaxX()) {
            pParticule.setPosX(pTerrain.getMaxX() - pParticule.getRayon());
            pParticule.setVitesseX(-1 * pParticule.getVitesse().getX());
        }

        if (pParticule.getPosition().getY() + pParticule.getVitesse().getY() < pTerrain.getMinY()) {
            pParticule.setPosY(pParticule.getRayon());
            pParticule.setVitesseY(-1 * pParticule.getVitesse().getY());
        } else if (pParticule.getPosition().getY() + pParticule.getVitesse().getY() > pTerrain.getMaxY()) {
            pParticule.setPosY(pTerrain.getMaxY() - pParticule.getRayon());
            pParticule.setVitesseY(-1 * pParticule.getVitesse().getY());
        }
    }
}
