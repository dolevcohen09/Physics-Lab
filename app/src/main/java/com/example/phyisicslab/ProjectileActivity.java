package com.example.phyisicslab;

import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.phyisicslab.view.ProjectileMotionView;

public class ProjectileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_projectile);

        // Get values from MainActivity
        double v0 = getIntent().getDoubleExtra("v0", 15);
        double angle = getIntent().getDoubleExtra("angle", 45);
        double mass = getIntent().getDoubleExtra("mass", 2);
        double gravity = getIntent().getDoubleExtra("gravity", 9.81);

        TextView parametersText =
                findViewById(R.id.parametersText);

        String parameters = String.format(
                "v₀ = %.2f m/s   θ = %.2f°   m = %.2f kg   g = %.2f m/s²",
                v0,
                angle,
                mass,
                gravity
        );

        parametersText.setText(parameters);

        // Create the projectile simulation view
        ProjectileMotionView projectileMotionView =
                new ProjectileMotionView(
                        this,
                        v0,
                        angle,
                        mass,
                        gravity
                );

        // Find the FrameLayout from activity_projectile.xml
        FrameLayout simulationContainer =
                findViewById(R.id.simulationContainer);

        // Put the simulation inside the FrameLayout
        simulationContainer.addView(projectileMotionView);
    }
}