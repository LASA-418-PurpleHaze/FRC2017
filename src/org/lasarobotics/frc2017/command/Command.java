package org.lasarobotics.frc2017.command;

import org.lasarobotics.frc2017.hardware.Hardware;
import org.lasarobotics.frc2017.subsystem.Drivetrain;
import org.lasarobotics.frc2017.subsystem.GearIntake;
import org.lasarobotics.frc2017.subsystem.Intake;
import org.lasarobotics.frc2017.subsystem.Shooter;

public abstract class Command {

    protected Hardware hardware;
    protected Drivetrain drivetrain;
    protected Shooter shooter;
    protected Intake intake;
    protected GearIntake gearIntake;

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
        gearIntake = GearIntake.getInstance();

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
        return (Hardware.getCurrentTime() - startTime) > timeOut;
    }

    public void cancel() {
        stopped = true;
    }
}
