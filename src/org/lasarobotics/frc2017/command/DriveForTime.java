package org.lasarobotics.frc2017.command;

public class DriveForTime extends Command {

    private final double time;
    
    
    public DriveForTime(String name, double to, double time) {
        super(name, to);
        this.time = time;
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
