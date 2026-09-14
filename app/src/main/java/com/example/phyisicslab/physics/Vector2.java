package com.example.phyisicslab.physics;

public class Vector2 {

    public double x;
    public double y;

    public Vector2(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Vector2 add (Vector2 other) {
        return new Vector2(this.x + other.x, this.y + other.y);
    }

    public Vector2 subtract (Vector2 other) {
        return new Vector2(this.x - other.x, this.y - other.y);
    }

    public Vector2 multiply (double scalar) {
        return new Vector2(this.x * scalar, this.y * scalar);
    }

    public Vector2 divide (double scalar) {
        if(scalar == 0) {
            throw new IllegalArgumentException("Cannot divide by zero");
        }
        return new Vector2(this.x / scalar, this.y / scalar);
    }

    public double magnitude() {
        return Math.sqrt(this.x * this.x + this.y * this.y);
    }

    public Vector2 normalize() {
        double mag = this.magnitude();
        if(mag == 0) {
            throw new IllegalArgumentException("Cannot normalize a zero vector");
        }
        return new Vector2(this.x / mag, this.y / mag);
    }

    public double dot(Vector2 other) {
        return this.x * other.x + this.y * other.y;
    }

    public String toString() {
        return "(" + this.x + ", " + this.y + ")";
    }

}
