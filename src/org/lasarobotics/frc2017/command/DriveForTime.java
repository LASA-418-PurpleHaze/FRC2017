package org.lasarobotics.frc2017.command;

public class DriveForTime extends Command {

    private final double speed;
    
    public DriveForTime(String name, double to) {
        this(name, to, 1.0);
    }
    
    public DriveForTime(String name, double to, double speed){
        super(name, to);
        this.speed = speed;
    }
    @Override
    public void start() {
        hardware.setDriveSpeeds(1.0, 1.0);   
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
        hardware.setDriveSpeeds(0.0, 0.0);
    }

}
