package org.lasarobotics.frc2017.command;

import edu.wpi.first.wpilibj.Timer;
import org.lasarobotics.frc2017.hardware.Hardware;
import org.lasarobotics.frc2017.subsystem.Drivetrain;

public abstract class Command {

    protected Drivetrain drivetrain;
    protected Hardware hardware;

    String name;

    protected double startTime;
    protected double currentTime;
    double timeOut;

    protected boolean isDone;
    protected boolean stopped;
    protected boolean shouldRun;
    protected boolean isLeftDone;

    public Command(String name, double timeOut) {
        drivetrain = Drivetrain.getInstance();
        hardware = Hardware.getInstance();

        this.name = name;
        this.timeOut = timeOut;
        this.startTime = Double.MAX_VALUE;
    }

    public abstract void start();

    public boolean isStarted() {
        return startTime != Double.MAX_VALUE;
    }

    public abstract boolean isDone();

    public abstract void run();

    public abstract void stop();

    public boolean isTimedOut() {
        return (Timer.getFPGATimestamp() - startTime) > timeOut;
    }

    public void cancel() {
        stopped = true;
    }
}
