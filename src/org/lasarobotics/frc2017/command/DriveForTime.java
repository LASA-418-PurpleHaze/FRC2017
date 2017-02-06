package org.lasarobotics.frc2017.command;

import org.lasarobotics.frc2017.subsystem.Drivetrain;

public class DriveForTime extends Command {

    private final double speed;
    
    public DriveForTime(String name, double timeout) {
        this(name, timeout, 1.0);
    }
    
    public DriveForTime(String name, double timeout, double speed){
        super(name, timeout);
        this.speed = speed;
    }
    @Override
    public void start() {
        drivetrain.setMode(Drivetrain.Mode.OVERRIDE);
        drivetrain.setDriveSpeeds(speed, speed);   
    }

    @Override
    public boolean isDone() {
        return isTimedOut();
    }

    @Override
    public void run() {
    }

    @Override
    public void stop() {
        drivetrain.setMode(Drivetrain.Mode.OVERRIDE);
        drivetrain.setDriveSpeeds(0.0, 0.0);
    }

}
