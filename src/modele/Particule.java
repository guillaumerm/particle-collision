package modele;

import modele.exception.ParticuleException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Guillaume Rochefort-Mathieu & Antoine Laplante
 */
public class Particule {

    private Vector2 vitesse;
    private double angle;
    private double rayon;
    private Vector2 position;
    private String couleur;
    private static final String HEX_PATTERN_COULEUR = "^#([A-Fa-f0-9]{6}|[A-Fa-f0-9]{3})$";

    public Particule(double magnitudeVitesse, double angle, double rayon, double posX, double posY, String couleur) throws ParticuleException {
        if (valideMagnitudeVitesse(magnitudeVitesse) && valideAngle(angle) && valideRayon(rayon) && validePosX(posX) && validePosY(posY) && valideCouleur(couleur)) {
            this.vitesse = new Vector2(magnitudeVitesse * Math.cos(Math.toRadians(angle)), magnitudeVitesse * Math.sin(Math.toRadians(angle)));
            this.angle = angle;
            this.rayon = rayon;
            this.position = new Vector2(posX, posY);
            this.couleur = couleur;
        } else {
            throw new ParticuleException();
        }
    }
    
    public void avancePas(){
        this.position.setX(this.position.getX() + this.vitesse.getX());
        this.position.setY(this.position.getY() + this.vitesse.getY());
    }

    public void setCouleur(String couleur) {
        if (valideCouleur(couleur)) {
            this.couleur = couleur;
        }
    }

    public void setMagnitudeVitesse(double magnitudeVitesse) {
        if (valideMagnitudeVitesse(magnitudeVitesse)) {
            this.vitesse.setX(magnitudeVitesse * Math.cos(Math.toRadians(this.angle)));
            this.vitesse.setY(magnitudeVitesse * Math.sin(Math.toRadians(this.angle)));
        }
    }

    public void setAngle(double angle) {
        if (valideAngle(angle)) {
            this.angle = angle;
        }

    }

    public void setRayon(double rayon) {
        if (valideRayon(rayon)) {
            this.rayon = rayon;
        }
    }

    public void setPosX(double posX) {
        if (validePosX(posX)) {
            this.position.setX(posX);
        }
    }

    public void setPosY(double posY) {
        if (validePosY(posY)) {
            this.position.setY(posY);
        }
    }

    public void setVitesseX(double vitesseX) {
        this.vitesse.setX(vitesseX);
    }

    public void setVitesseY(double vitesseY) {
        this.vitesse.setY(vitesseY);
    }

    public void setVitesse(Vector2 pVitesse) {
        this.vitesse = pVitesse;
    }

    public String getCouleur() {
        return couleur;
    }

    public double getMass() {
        return Math.PI * (rayon * rayon);
    }

    public Vector2 getVitesse() {
        return vitesse;
    }

    public double getAngle() {
        return angle;
    }

    public double getRayon() {
        return rayon;
    }

    public Vector2 getPosition() {
        return position;
    }

    private boolean valideMagnitudeVitesse(double v) {
        return (v > -1);
    }

    private boolean valideAngle(double a) {
        return (a > -1 && a < 361);
    }

    private boolean valideRayon(double r) {
        return (r > 0);
    }

    private boolean validePosX(double x) {
        return (x > -1);
    }

    private boolean validePosY(double y) {
        return (y > -1);
    }

    private boolean valideCouleur(String couleur) {
        boolean valide = false;
        if (couleur != null) {
            Pattern pattern = Pattern.compile(HEX_PATTERN_COULEUR);
            Matcher matcher = pattern.matcher(couleur);
            valide = matcher.matches();
        }
        return valide;
    }

}
