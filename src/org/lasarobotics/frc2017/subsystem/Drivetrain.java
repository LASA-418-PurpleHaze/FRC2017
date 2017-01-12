package org.lasarobotics.frc2017.subsystem;

import org.lasarobotics.lib.controlloop.HazyPID;
import edu.wpi.first.wpilibj.Timer;
import org.lasarobotics.frc2017.statics.Constants;
import org.lasarobotics.lib.controlloop.HazyTMP;

public class Drivetrain extends HazySubsystem {

    private double leftSpeed, rightSpeed;
    private double dt, prevTime;
    private HazyPID leftPID, rightPID;
    private HazyTMP motionProfiler;

    public Drivetrain() {
        leftPID = new HazyPID();
        rightPID = new HazyPID();
        motionProfiler = new HazyTMP(Constants.maxV, Constants.maxA);
    }

    private static Drivetrain instance;

    public static Drivetrain getInstance() {
        return (instance == null) ? instance = new Drivetrain() : instance;
    }

    public static enum Mode {
        OVERRIDE, STRAIGHT, TURN;
    }

    static Mode mode;

    public void setMode(Mode m) {
        mode = m;
    }

    @Override
    public void run() {
        dt = Timer.getFPGATimestamp() - prevTime;
        if (mode != Mode.OVERRIDE) {
            leftSpeed = leftPID.calculate(/* not ready yet apparently hardware.getLeftLinearVelocity() */, dt);
            rightSpeed = rightPID.calculate(/* not ready yet apparently hardware.getRightLinearVelocity() */, dt);
            motionProfiler.calculateNextSituation(dt);
        }
        prevTime = Timer.getFPGATimestamp();
        hardware.setDriveSpeeds(leftSpeed, rightSpeed);
    }

    public void setDriveSpeeds(double l, double r) {
        leftSpeed = l;
        rightSpeed = r;
    }

    @Override
    public void initSubsystem() {
    }

    @Override
    public void pushToDashboard() {
    }

}
