package org.lasarobotics.frc2017.command;

public class DriveForTime extends Command {

    private final double time;

    public DriveForTime(String name, double to, double time) {
        super(name, to);
        this.time = time;
    }

    @Override
    public void start() {
    }

    @Override
    public boolean isDone() {
        return false;
    }

    @Override
    public void run() {
    }

    @Override
    public void stop() {
    }

}
