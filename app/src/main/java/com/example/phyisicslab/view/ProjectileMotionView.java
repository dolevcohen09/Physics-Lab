package com.example.phyisicslab.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

import com.example.phyisicslab.physics.Body;
import com.example.phyisicslab.physics.Force;
import com.example.phyisicslab.physics.PhysicsEngine;
import com.example.phyisicslab.physics.Vector2;
import com.example.phyisicslab.physics.PhysicsWorld;
import com.example.phyisicslab.physics.SimulationData;
import com.example.phyisicslab.physics.SimulationAnalyzer;

public class ProjectileMotionView extends View {


    private boolean simulationRunning = true;

    private Paint paint;

    private PhysicsWorld world;
    private PhysicsEngine physicsEngine;

    private final int FPS = 60;

    // Time step
    private final double dt = 1.0 / FPS;

    private SimulationData data;
    private SimulationAnalyzer analyzer;

    // Simulation data
    private double simulationTime = 0;
    private double maximumHeight = 0;
    private double landingPosition = 0;

    // Simulation parameters
    private double initialVelocity;
    private double angleDegrees;
    private double gravityField;


    // Analytical solution
    private double analyticalMaximumHeight = 0;
    private double heightError = 0;
    private double heightErrorPercent = 0;

    private double analyticalFlightTime = 0;
    private double flightTimeError = 0;
    private double flightTimeErrorPercent = 0;

    private double analyticalLandingPosition = 0;
    private double landingPositionError = 0;
    private double landingPositionErrorPercent = 0;

    public ProjectileMotionView(Context context, double v0, double angleDegrees, double mass, double gravityField) {
        super(context);

        this.initialVelocity = v0;
        this.angleDegrees = angleDegrees;
        this.gravityField = gravityField;


        paint = new Paint();
        paint.setAntiAlias(true);

        world = new PhysicsWorld();

        physicsEngine = new PhysicsEngine();

        data = new SimulationData();
        analyzer = new SimulationAnalyzer();

        // Throwing direction
        double angle  = Math.toRadians(angleDegrees);

        double vx = v0 * Math.cos(angle);
        double vy = v0 * Math.sin(angle);

        // Create ball
        Body ball = new Body(
                new Vector2(0, 0),
                new Vector2(vx, vy),
                mass
        );


        // Gravity
        Force gravity = new Force(new Vector2(0, -gravityField * ball.getMass()));

        world.addBody(ball);
        world.addForce(gravity);

        // Start animation
        post(updateRunnable);
    }

    private final Runnable updateRunnable = new Runnable() {
        @Override
        public void run() {

            // Stop simulation
            if (!simulationRunning) {
                return;
            }

            // Update physics
            boolean collision = physicsEngine.update(world, dt);

            // Update time
            simulationTime += dt;

            Body ball = world.getBodies().get(0);

            // Store data
            data.add(simulationTime, ball.getPosition(), ball.getVelocity());

            // no bounse
            if (collision) {
                simulationRunning = false;

                maximumHeight = analyzer.findMaximumHeight(data);

                landingPosition = ball.getPosition().x;

                // Calculate analytical maximum height
                double angle = Math.toRadians(angleDegrees);
                double initialVy = initialVelocity * Math.sin(angle);

                analyticalMaximumHeight = (initialVy * initialVy) / (2 * gravityField);

                // Calculate error maximumHeight
                heightError = Math.abs(maximumHeight - analyticalMaximumHeight);  // Error in meters
                heightErrorPercent = (heightError / analyticalMaximumHeight) * 100;  // Error in percentage

                // calculate analytical flight time and landing position
                analyticalFlightTime = (2 * initialVy) / gravityField;
                double initialVx = initialVelocity * Math.cos(angle);
                analyticalLandingPosition = initialVx * analyticalFlightTime;

                // Calculate error flight time and landing position
                flightTimeError = Math.abs(simulationTime - analyticalFlightTime);
                flightTimeErrorPercent = (flightTimeError / analyticalFlightTime) * 100;
                landingPositionError = Math.abs(landingPosition - analyticalLandingPosition);
                landingPositionErrorPercent = (landingPositionError / analyticalLandingPosition) * 100;
            }


            // Redraw screen
            invalidate();

            // Run again
            if (simulationRunning) {
                postDelayed(this, (long)(dt * 1000));
            }

        }
    };

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // White background
        canvas.drawColor(0xFFEAF7FB);

        // Get physics position
        Body ball = world.getBodies().get(0);

        Vector2 position = ball.getPosition();

        // Convert physics coordinates → screen coordinates
        float x = (float) (100 + position.x * 50);

        float y = (float) (getHeight() - 100 - position.y * 50);

        // Draw ball
        canvas.drawCircle(x, y, 30, paint);

        if (!simulationRunning) {

            paint.setTextSize(50);

            String maxHeightText = String.format(
                    "Max Height: %.2f m | Analytical: %.2f m | Error: %.2f%%",
                    maximumHeight,
                    analyticalMaximumHeight,
                    heightErrorPercent
            );

            String flightTimeText = String.format(
                    "Flight Time: %.2f s | Analytical: %.2f s | Error: %.2f%%",
                    simulationTime,
                    analyticalFlightTime,
                    flightTimeErrorPercent
            );

            String landingText = String.format(
                    "Landing: %.2f m | Analytical: %.2f m | Error: %.2f%%",
                    landingPosition,
                    analyticalLandingPosition,
                    landingPositionErrorPercent
            );

            canvas.drawText(maxHeightText, 50, 200, paint);
            canvas.drawText(flightTimeText, 50, 320, paint);
            canvas.drawText(landingText, 50, 440, paint);
        }
    }
}