package org.lasarobotics.frc2017.subsystem;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.lasarobotics.frc2017.statics.Constants;
import org.lasarobotics.frc2017.statics.ConstantsList;
import org.lasarobotics.lib.controlloop.HazyPVIff;
import org.lasarobotics.lib.controlloop.HazyTMP;

public class Drivetrain extends HazySubsystem {

    private double leftSpeed, rightSpeed;
    private double dt, prevTime;
    private final HazyPVIff leftPVIff, rightPVIff;
    private final HazyTMP motionProfiler;
    private double targetPosition, targetAngle;

    public Drivetrain() {

        leftPVIff = new HazyPVIff(ConstantsList.getConstant("kP"), ConstantsList.getConstant("kI"),
                ConstantsList.getConstant("kV"), ConstantsList.getConstant("kFFV"),
                ConstantsList.getConstant("kFFA"));
        rightPVIff = new HazyPVIff(ConstantsList.getConstant("kP"), ConstantsList.getConstant("kI"),
                ConstantsList.getConstant("kV"), ConstantsList.getConstant("kFFV"),
                ConstantsList.getConstant("kFFA"));
        motionProfiler = new HazyTMP(ConstantsList.getConstant("maxV"), ConstantsList.getConstant("maxA"));

        this.setMode(Mode.OVERRIDE);
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
            leftSpeed = leftPVIff.calculate(hardware.getLeftDriveDistance(),
                    hardware.getLeftDriveVelocity(), motionProfiler.getCurrentPosition(),
                    motionProfiler.getCurrentVelocity(), motionProfiler.getCurrentAcceleration(), dt);
            rightSpeed = rightPVIff.calculate(hardware.getRightDriveDistance(),
                    hardware.getRightDriveVelocity(), motionProfiler.getCurrentPosition(),
                    motionProfiler.getCurrentVelocity(), motionProfiler.getCurrentAcceleration(), dt);
            motionProfiler.calculateNextSituation(dt);
        }
        prevTime = Timer.getFPGATimestamp();
        hardware.setDriveSpeeds(leftSpeed, rightSpeed);
    }

    public void setDriveSpeeds(double l, double r) {
        leftSpeed = l;
        rightSpeed = r;
    }

    public void setStraightSetpoint(double d) {
        targetPosition = d;
        motionProfiler.generateTrapezoid(targetPosition,
                (hardware.getLeftDriveDistance() + hardware.getRightDriveDistance() * 0.5),
                (Math.abs(hardware.getLeftDriveVelocity()) + Math.abs(hardware.getRightDriveVelocity())) * 0.5);
    }

    public void setTurnSetpoint(double a) {
        targetAngle = a;
        motionProfiler.generateTrapezoid(targetAngle, hardware.getRobotAngle(),
                (hardware.getLeftDriveVelocity() + hardware.getRightDriveVelocity()) * 0.5);
    }

    public double getStraightSetpoint() {
        return targetPosition;
    }

    public double getTurnSetpoint() {
        return targetAngle;
    }

    public boolean isLeftPIDDone() {
        return leftPVIff.onTarget();
    }

    public boolean isRightPIDDone() {
        return rightPVIff.onTarget();
    }

    @Override
    public void initSubsystem() {
    }

    @Override
    public void pushToDashboard() {
        SmartDashboard.putNumber("leftspeed", leftSpeed);
        SmartDashboard.putNumber("rightspeed", rightSpeed);
        SmartDashboard.putNumber("distancetarget", targetPosition);
        SmartDashboard.putNumber("angletarget", targetAngle);
        SmartDashboard.putString("mode", mode.toString());
    }

}
