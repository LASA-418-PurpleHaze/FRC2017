package org.lasarobotics.frc2017.command;

import org.lasarobotics.frc2017.subsystem.Drivetrain;

public class DriveTurn extends Command {

    private final double angle;

    public DriveTurn(String name, double t, double angle) {
        super(name, t);
        this.angle = angle;
    }

    @Override
    public void start() {
        hardware.reset();
        drivetrain.setTurnSetpoint(angle);
        drivetrain.setMode(Drivetrain.Mode.TURN);
    }

    @Override
    public boolean isDone() {
        return (drivetrain.isTurnPIDDone());
    }

    @Override
    public void run() {
    }

    @Override
    public void stop() {
        drivetrain.setMode(Drivetrain.Mode.OVERRIDE);
        hardware.reset();
        drivetrain.setTurnSetpoint(0.0);
        drivetrain.setDriveSpeeds(0,0);
    }

}
