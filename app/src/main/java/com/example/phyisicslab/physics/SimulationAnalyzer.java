package com.example.phyisicslab.physics;

public class SimulationAnalyzer {

    public double findMaximumHeight(SimulationData data) {

        double maxHeight = Double.NEGATIVE_INFINITY;

        for (int i = 0; i < data.getPositions().size(); i++) {

            double y = data.getPositions().get(i).y;

            if (y > maxHeight) {
                maxHeight = y;
            }
        }

        return maxHeight;
    }
}