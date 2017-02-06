package org.lasarobotics.frc2017.command;

import edu.wpi.first.wpilibj.Timer;
import org.lasarobotics.frc2017.hardware.Hardware;
import org.lasarobotics.frc2017.subsystem.Drivetrain;
import org.lasarobotics.frc2017.subsystem.Intake;
import org.lasarobotics.frc2017.subsystem.Shooter;

public abstract class Command {

    protected Hardware hardware;
    protected Drivetrain drivetrain;
    protected Shooter shooter;
    protected Intake intake;

    String name;

    protected double startTime;
    protected double currentTime;
    double timeOut;

    protected boolean isDone;
    protected boolean stopped;
    protected boolean shouldRun;
    protected boolean isLeftDone;

    public Command(String name, double timeOut) {
        hardware = Hardware.getInstance();
        drivetrain = Drivetrain.getInstance();
        shooter = Shooter.getInstance();
        intake = Intake.getInstance();

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
