package com.example.phyisicslab.physics;

import java.util.ArrayList;
import java.util.List;

public class SimulationData {

    private List<Vector2> positions;
    private List<Vector2> velocities;
    private List<Double> times;

    public SimulationData() {
        positions = new ArrayList<>();
        velocities = new ArrayList<>();
        times = new ArrayList<>();
    }

    public void add(double time, Vector2 position, Vector2 velocity) {

        times.add(time);

        positions.add(new Vector2(position.x, position.y));

        velocities.add(new Vector2(velocity.x, velocity.y));
    }

    public List<Vector2> getPositions() {
        return positions;
    }

    public List<Vector2> getVelocities() {
        return velocities;
    }

    public List<Double> getTimes() {
        return times;
    }
}