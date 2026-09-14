package com.example.phyisicslab.physics;

import java.util.ArrayList;
import java.util.List;

public class PhysicsWorld {

    private List<Body> bodies;
    private List<Force> forces;

    public PhysicsWorld() {
        bodies = new ArrayList<>();
        forces = new ArrayList<>();
    }

    // Bodies

    public void addBody(Body body) {
        bodies.add(body);
    }

    public void removeBody(Body body) {
        bodies.remove(body);
    }

    public List<Body> getBodies() {
        return bodies;
    }

    // Forces

    public void addForce(Force force) {
        forces.add(force);
    }

    public void removeForce(Force force) {
        forces.remove(force);
    }

    public List<Force> getForces() {
        return forces;
    }
}