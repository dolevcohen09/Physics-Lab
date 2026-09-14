package com.example.phyisicslab.physics;

import java.util.List;

public class PhysicsEngine {

    public boolean update(PhysicsWorld world, double dt) {

        boolean collisionOccurred = false;
        double restitution = 0.8;
        int groundY = 0;

        List<Body> bodies = world.getBodies();
        List<Force> forces = world.getForces();

        for (Body body : bodies) {

            // Calculate net force
            Vector2 netForce = new Vector2(0, 0);

            for (Force force : forces) {
                netForce = netForce.add(force.getValue());
            }

            // F = ma  →  a = F / m
            Vector2 acceleration =
                    netForce.divide(body.getMass());

            // Update velocity
            Vector2 velocity = body.getVelocity();

            velocity = velocity.add(
                    acceleration.multiply(dt)
            );

            body.setVelocity(velocity);

            // Update position
            Vector2 position = body.getPosition();

            position = position.add(
                    velocity.multiply(dt)
            );

            body.setPosition(position);

            if (body.getPosition().y <= groundY)
            {
                collisionOccurred = true;

                body.setVelocity(new Vector2(body.getVelocity().x, body.getVelocity().y * -restitution));
                body.setPosition(new Vector2(body.getPosition().x, groundY));
            }
        }
        return collisionOccurred;
    }
}