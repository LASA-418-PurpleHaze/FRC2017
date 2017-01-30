package org.lasarobotics.frc2017.subsystem;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.lasarobotics.frc2017.statics.ConstantsList;
import org.lasarobotics.lib.controlloop.HazyPID;
import org.lasarobotics.lib.controlloop.HazyPVIff;
import org.lasarobotics.lib.controlloop.HazyTMP;

public class Drivetrain extends HazySubsystem {

    private double leftSpeed, rightSpeed;
    private double dt, prevTime;
    private final HazyPVIff leftPVIff, rightPVIff;
    private final HazyPID turnPID;
    private final HazyTMP motionProfiler;
    private double targetPosition, targetAngle;

    public Drivetrain() {

        leftPVIff = new HazyPVIff(ConstantsList.D_left_kP.getValue(), ConstantsList.D_left_kI.getValue(),
                ConstantsList.D_left_kV.getValue(), ConstantsList.D_left_kFFV.getValue(),
                ConstantsList.D_left_kFFA.getValue());
        rightPVIff = new HazyPVIff(ConstantsList.D_right_kP.getValue(), ConstantsList.D_right_kI.getValue(),
                ConstantsList.D_right_kV.getValue(), ConstantsList.D_right_kFFV.getValue(),
                ConstantsList.D_right_kFFA.getValue());
        turnPID = new HazyPID();

        motionProfiler = new HazyTMP(ConstantsList.D_tmp_maxV.getValue(), ConstantsList.D_tmp_maxA.getValue());

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
        if (null != mode) {
            switch (mode) {
                case OVERRIDE:
                    break;
                case STRAIGHT:
                    leftSpeed = leftPVIff.calculate(hardware.getLeftDriveDistance(),
                            hardware.getLeftDriveVelocity(), motionProfiler.getCurrentPosition(),
                            motionProfiler.getCurrentVelocity(), motionProfiler.getCurrentAcceleration(), dt);
                    rightSpeed = rightPVIff.calculate(hardware.getRightDriveDistance(),
                            hardware.getRightDriveVelocity(), motionProfiler.getCurrentPosition(),
                            motionProfiler.getCurrentVelocity(), motionProfiler.getCurrentAcceleration(), dt);
                    motionProfiler.calculateNextSituation(dt);
                    break;
                case TURN:
                    double turn = turnPID.calculate(hardware.getRobotAngle(), dt);
                    leftSpeed = -turn;
                    rightSpeed = turn;
                    break;
            }
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
        turnPID.setTarget(targetAngle);
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

    public boolean isTurnPIDDone() {
        return turnPID.onTarget();
    }

    public boolean isDistanceDone() {
        return isLeftPIDDone() && isRightPIDDone();
    }

    @Override
    public void initSubsystem() {
        turnPID.setPID(ConstantsList.D_turn_kP.getValue(), ConstantsList.D_turn_kI.getValue(),
                ConstantsList.D_turn_kD.getValue(), ConstantsList.D_turn_kFF.getValue(),
                ConstantsList.D_turn_kD.getValue());
    }

    public void updatePVILoops() {
        leftPVIff.setPID(ConstantsList.D_left_kP.getValue(), ConstantsList.D_left_kI.getValue(),
                ConstantsList.D_left_kV.getValue(), ConstantsList.D_left_kFFV.getValue(),
                ConstantsList.D_left_kFFA.getValue(), ConstantsList.D_left_doneBound.getValue());
        rightPVIff.setPID(ConstantsList.D_right_kP.getValue(), ConstantsList.D_right_kI.getValue(),
                ConstantsList.D_right_kV.getValue(), ConstantsList.D_right_kFFV.getValue(),
                ConstantsList.D_right_kFFA.getValue(), ConstantsList.D_right_doneBound.getValue());
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
