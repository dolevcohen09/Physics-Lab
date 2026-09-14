package com.example.phyisicslab;

import android.os.Bundle;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.phyisicslab.view.ProjectileMotionView;

public class ProjectileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_projectile);

        double v0 = getIntent().getDoubleExtra("v0", 15);
        double angle = getIntent().getDoubleExtra("angle", 45);
        double mass = getIntent().getDoubleExtra("mass", 2);
        double gravity = getIntent().getDoubleExtra("gravity", 9.81);

        TextView parametersText = findViewById(R.id.parametersText);
        parametersText.setText(String.format(
                "v₀ = %.2f m/s   θ = %.2f°   m = %.2f kg   g = %.2f m/s²",
                v0, angle, mass, gravity
        ));

        TextView maxHeightNumericalText = findViewById(R.id.maxHeightNumericalText);
        TextView maxHeightAnalyticalText = findViewById(R.id.maxHeightAnalyticalText);
        TextView maxHeightErrorText = findViewById(R.id.maxHeightErrorText);

        TextView flightTimeNumericalText = findViewById(R.id.flightTimeNumericalText);
        TextView flightTimeAnalyticalText = findViewById(R.id.flightTimeAnalyticalText);
        TextView flightTimeErrorText = findViewById(R.id.flightTimeErrorText);

        TextView landingNumericalText = findViewById(R.id.landingNumericalText);
        TextView landingAnalyticalText = findViewById(R.id.landingAnalyticalText);
        TextView landingErrorText = findViewById(R.id.landingErrorText);

        ProjectileMotionView projectileMotionView = new ProjectileMotionView(
                this,
                v0,
                angle,
                mass,
                gravity,
                (maximumHeight, flightTime, landingPosition,
                 analyticalMaximumHeight, analyticalFlightTime, analyticalLandingPosition,
                 heightErrorPercent, flightTimeErrorPercent, landingPositionErrorPercent) -> {

                    maxHeightNumericalText.setText(String.format("Numerical: %.2f m", maximumHeight));
                    maxHeightAnalyticalText.setText(String.format("Analytical: %.2f m", analyticalMaximumHeight));
                    maxHeightErrorText.setText(String.format("Error: %.2f%%", heightErrorPercent));

                    flightTimeNumericalText.setText(String.format("Numerical: %.2f s", flightTime));
                    flightTimeAnalyticalText.setText(String.format("Analytical: %.2f s", analyticalFlightTime));
                    flightTimeErrorText.setText(String.format("Error: %.2f%%", flightTimeErrorPercent));

                    landingNumericalText.setText(String.format("Numerical: %.2f m", landingPosition));
                    landingAnalyticalText.setText(String.format("Analytical: %.2f m", analyticalLandingPosition));
                    landingErrorText.setText(String.format("Error: %.2f%%", landingPositionErrorPercent));
                }
        );

        FrameLayout simulationContainer = findViewById(R.id.simulationContainer);
        simulationContainer.addView(projectileMotionView);

        Button runAgainButton = findViewById(R.id.runAgainButton);
        runAgainButton.setOnClickListener(v -> finish());
    }
}