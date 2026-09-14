package com.example.phyisicslab;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText inputVelocity;
    private EditText inputAngle;
    private EditText inputMass;
    private EditText inputGravity;

    private Button startButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        inputVelocity = findViewById(R.id.inputVelocity);
        inputAngle = findViewById(R.id.inputAngle);
        inputMass = findViewById(R.id.inputMass);
        inputGravity = findViewById(R.id.inputGravity);

        startButton = findViewById(R.id.startButton);

        startButton.setOnClickListener(v -> startSimulation());
    }

    private void startSimulation() {

        String velocityText = inputVelocity.getText().toString();
        String angleText = inputAngle.getText().toString();
        String massText = inputMass.getText().toString();
        String gravityText = inputGravity.getText().toString();

        if (velocityText.isEmpty()
                || angleText.isEmpty()
                || massText.isEmpty()
                || gravityText.isEmpty()) {

            Toast.makeText(
                    this,
                    "Please fill in all fields",
                    Toast.LENGTH_SHORT
            ).show();

            return;
        }

        try {

            double v0 = Double.parseDouble(velocityText);
            double angle = Double.parseDouble(angleText);
            double mass = Double.parseDouble(massText);
            double gravity = Double.parseDouble(gravityText);

            if (mass <= 0) {
                Toast.makeText(
                        this,
                        "Mass must be greater than 0",
                        Toast.LENGTH_SHORT
                ).show();

                return;
            }

            if (gravity < 0) {
                Toast.makeText(
                        this,
                        "Gravity cannot be negative",
                        Toast.LENGTH_SHORT
                ).show();

                return;
            }

            Intent intent = new Intent(
                    MainActivity.this,
                    ProjectileActivity.class
            );

            intent.putExtra("v0", v0);
            intent.putExtra("angle", angle);
            intent.putExtra("mass", mass);
            intent.putExtra("gravity", gravity);

            startActivity(intent);

        } catch (NumberFormatException e) {

            Toast.makeText(
                    this,
                    "Please enter valid numbers",
                    Toast.LENGTH_SHORT
            ).show();
        }
    }
}