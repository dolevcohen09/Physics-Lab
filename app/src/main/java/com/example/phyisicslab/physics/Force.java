package com.example.phyisicslab.physics;

public class Force {

    private Vector2 value;

    public Force(Vector2 value) {
        this.value = value;
    }

    public Vector2 getValue() {
        return value;
    }

    public void setValue(Vector2 value) {
        this.value = value;
    }

}
