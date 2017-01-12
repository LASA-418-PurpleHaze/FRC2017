package org.lasarobotics.frc2017.command;

public class TurnAngle extends Command {

    private final double angle;

    public TurnAngle(String name, double t, double angle) {
        super(name, t);
        this.angle = angle;
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