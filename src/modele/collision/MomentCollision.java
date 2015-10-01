package modele.collision;

/**
 * Classe qui permet de determiner le moment de collision entre deux particule.
 * 
 * @author Gus
 */
public class MomentCollision {

    public double temps;

    private static final double T_EPSILON = 0.005;

    public double newVitesseX;

    public double newVitesseY;

    public MomentCollision() {
        reset();
    }

    public void reset() {
        this.temps = Double.MAX_VALUE;
    }

    public void copy(MomentCollision pMomentCollision) {
        this.temps = pMomentCollision.temps;
        this.newVitesseX = pMomentCollision.newVitesseX;
        this.newVitesseY = pMomentCollision.newVitesseY;
    }

    public double getNewX(double posX, double vitesseX) {
        return (temps > T_EPSILON) ? (double) (posX + vitesseX * (temps - T_EPSILON)) : posX;
    }

    public double getNewY(double posY, double vitesseY) {
        return (temps > T_EPSILON) ? (double) (posY + vitesseY * (temps - T_EPSILON)) : posY;
    } 
}
