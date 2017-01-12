package org.lasarobotics.frc2017.command;

public class DriveDistance extends Command {

    private final double distance;

    public DriveDistance(String name, double t, double distance) {
        super(name, t);
        this.distance = distance;
    }

    @Override
    public boolean isDone() {
        /*
        Not ready for this yet
        return drivetrain.isDistanceDone() && drivetrain.getStraightSetpoint() == distance;
         */
        return false;
    }

    @Override
    public void start() {
        /*
        Also not ready for this yet
        drivetrain.setStraightSetpoint(distance);
         */
    }

    @Override
    public void run() {
    }

    @Override
    public void stop() {
    }

}
