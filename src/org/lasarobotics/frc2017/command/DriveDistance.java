package org.lasarobotics.frc2017.command;

import org.lasarobotics.frc2017.subsystem.Drivetrain;

public class DriveDistance extends Command {

    private final double distance;

    public DriveDistance(String name, double t, double distance) {
        super(name, t);
        this.distance = distance;
    }

    @Override
    public void start() {
        drivetrain.setStraightSetpoint(distance);   
        drivetrain.setMode(Drivetrain.Mode.STRAIGHT);
    }
    
    @Override
    public boolean isDone() {
        return drivetrain.isDistanceDone() && drivetrain.getStraightSetpoint() == distance;
    }

    @Override
    public void run() {
    }

    @Override
    public void stop() {
        drivetrain.setMode(Drivetrain.Mode.OVERRIDE);
        drivetrain.setStraightSetpoint(0.0);
        drivetrain.setDriveSpeeds(0.0, 0.0);
    }

}
