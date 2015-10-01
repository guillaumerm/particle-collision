package modele;

import modele.exception.Vector2Exception;

/**
 *
 * @author Guillaume Rochefort-Mathieu & Antoine Laplante
 */
public class Vector2 {

    private double x;
    private double y;

    public Vector2() {
        this.x = 0.0;
        this.y = 0.0;
    }

    public Vector2(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Vector2(Vector2 v) throws Vector2Exception {
        if (v != null) {
            this.x = v.x;
            this.y = v.y;
        } else {
            throw new Vector2Exception();
        }

    }

    public void add(Vector2 v) throws Vector2Exception {
        if (v != null) {
            this.x += v.x;
            this.y += v.y;
        } else {
            throw new Vector2Exception();
        }
    }

    public void substract(Vector2 v) throws Vector2Exception {
        if (v != null) {
            this.x -= v.x;
            this.y -= v.y;
        } else {
            throw new Vector2Exception();
        }
    }

    public void multiplyScalar(double scalar) {
        this.x *= scalar;
        this.y *= scalar;
    }

    public void divideScalar(double scalar) throws Vector2Exception {
        if (scalar != 0) {
            this.x /= scalar;
            this.y /= scalar;
        } else {
            throw new Vector2Exception();
        }
    }

    public Vector2 normalize() {
        return new Vector2(this.x / magnitude(), this.y / magnitude());
    }

    static double Dot(Vector2 v1, Vector2 v2) {
        return v1.getX() * v2.getX() + v1.getY() * v2.getY();
    }

    public double magnitude() {
        return Math.sqrt((this.x * this.x) + (this.y * this.y));
    }

    public void setX(double pX) {
        this.x = pX;
    }

    public void setY(double pY) {
        this.y = pY;
    }

    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }
}
