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

    public Vector2(Vector2 v) {
        this.x = v.x;
        this.y = v.y;
    }

    public void add(Vector2 v) {
        this.x += v.x;
        this.y += v.y;
    }

    public void substract(Vector2 v) {
        this.x -= v.x;
        this.y -= v.y;
    }

    public void multiplyScalar(double scalar) {
        this.x *= scalar;
        this.y *= scalar;
    }

    public void divideScalar(double scalar) {
        this.x /= scalar;
        this.y /= scalar;
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
