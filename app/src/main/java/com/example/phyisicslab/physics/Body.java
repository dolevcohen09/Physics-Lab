package com.example.phyisicslab.physics;

public class Body {

    private Vector2 position;
    private Vector2 velocity;
    private double mass;

    public Body(Vector2 position, Vector2 velocity, double mass) {
        this.position = position;
        this.velocity = velocity;
        this.mass = mass;
    }

    public Vector2 getPosition() {
        return position;
    }

    public Vector2 getVelocity() {
        return velocity;
    }

    public double getMass() {
        return mass;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public void setVelocity(Vector2 velocity) {
        this.velocity = velocity;
    }

    public void setMass(double mass) {
        this.mass = mass;
    }

}
