/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele.collision;

import modele.Terrain;
import modele.Vector2;

/**
 *
 * @author Gus
 */
public class PhysiqueCollision {

    private static MomentCollision momentTemp = new MomentCollision();

    public static void pointIntersectContourRectangular(Vector2 position, Vector2 vitesse, double rayon, Terrain terrain, double tempLimite, MomentCollision moment) {

        moment.reset();

        pointIntersectLigneVerticale(position, vitesse, rayon, terrain.getMaxX(), tempLimite, moment);

        if (momentTemp.temps < moment.temps) {
            moment.copy(momentTemp);
        }

        pointIntersectLigneHorizontale(position, vitesse, rayon, terrain.getMinY(), tempLimite, moment);

        if (momentTemp.temps < moment.temps) {
            moment.copy(momentTemp);
        }

        pointIntersectLigneVerticale(position, vitesse, rayon, terrain.getMinX(), tempLimite, moment);

        if (momentTemp.temps < moment.temps) {
            moment.copy(momentTemp);
        }

        pointIntersectLigneHorizontale(position, vitesse, rayon, terrain.getMaxY(), tempLimite, moment);

        if (momentTemp.temps < moment.temps) {
            moment.copy(momentTemp);
        }
    }

    public static void pointIntersectLigneVerticale(Vector2 position, Vector2 vitesse, double rayon, double ligneX, double tempLimite, MomentCollision moment) {

        moment.reset();

        if (vitesse.getX() != 0) {
            double distance;
            if (ligneX > position.getX()) {
                distance = ligneX - position.getX() - rayon;
            } else {
                distance = ligneX - position.getX() + rayon;
            }

            double temps = distance / vitesse.getX();
            if (temps > 0 && temps < tempLimite) {
                moment.temps = temps;
                moment.newVitesseX = -vitesse.getX();
                moment.newVitesseY = vitesse.getY();
            }
        }
    }

    private static void pointIntersectLigneHorizontale(Vector2 position, Vector2 vitesse, double rayon, double ligneY, double tempLimite, MomentCollision moment) {
        moment.reset();

        if (vitesse.getY() != 0) {
            double distance;
            if (ligneY > position.getY()) {
                distance = ligneY - position.getY() - rayon;
            } else {
                distance = ligneY - position.getY() + rayon;
            }

            double temps = distance / vitesse.getY();
            if (temps > 0 && temps < tempLimite) {
                moment.temps = temps;
                moment.newVitesseY = -vitesse.getY();
                moment.newVitesseX = vitesse.getX();
            }
        }
    }

    public static void pointIntersectPointEnMovement(Vector2 position1, Vector2 vitesse1, double rayon1, Vector2 position2, Vector2 vitesse2, double rayon2, double tempsLimit, MomentCollision momentCollision1, MomentCollision momentCollision2) {

    }
}
