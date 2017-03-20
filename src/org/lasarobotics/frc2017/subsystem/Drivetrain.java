package org.lasarobotics.frc2017.subsystem;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.lasarobotics.frc2017.ConstantsList;
import org.lasarobotics.frc2017.dataLogging.Loggable;
import org.lasarobotics.lib.controlloop.HazyPID;
import org.lasarobotics.lib.controlloop.HazyPVIff;
import org.lasarobotics.lib.controlloop.HazyTMP;

public class Drivetrain extends HazySubsystem implements Loggable {

    private double leftSpeed, rightSpeed;
    private double dt, prevTime;
    private final HazyPVIff leftPVIff, rightPVIff;
    private final HazyPID turnPID;
    private HazyTMP motionProfiler;
    private double targetPosition, targetAngle;

    private static Drivetrain instance;

    public static Drivetrain getInstance() {
        return (instance == null) ? instance = new Drivetrain() : instance;
    }

    private Drivetrain() {
        leftPVIff = new HazyPVIff();
        rightPVIff = new HazyPVIff();
        turnPID = new HazyPID();

        this.setMode(Mode.OVERRIDE);
        initSubsystem();
    }

    @Override
    public String getNames() {
        return "leftSpeed, rightSpeed";
    }

    @Override
    public String getValues() {
        return leftSpeed + ", " + rightSpeed;
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
        double turn;
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
                    turn = turnPID.calculate(hardware.getRobotAngle(), dt);
                    leftSpeed += turn;
                    rightSpeed -= turn;
                    break;
                case TURN:
                    turn = turnPID.calculate(hardware.getRobotAngle(), dt);
                    leftSpeed = turn;
                    rightSpeed = -turn;
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
        turnPID.setTarget(0.0);
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
                ConstantsList.D_turn_doneBound.getValue());
        leftPVIff.setPID(ConstantsList.D_left_kP.getValue(), ConstantsList.D_left_kI.getValue(),
                ConstantsList.D_left_kV.getValue(), ConstantsList.D_left_kFFV.getValue(),
                ConstantsList.D_left_kFFA.getValue(), ConstantsList.D_left_doneBound.getValue());
        rightPVIff.setPID(ConstantsList.D_right_kP.getValue(), ConstantsList.D_right_kI.getValue(),
                ConstantsList.D_right_kV.getValue(), ConstantsList.D_right_kFFV.getValue(),
                ConstantsList.D_right_kFFA.getValue(), ConstantsList.D_right_doneBound.getValue());
        motionProfiler = new HazyTMP(ConstantsList.D_tmp_maxV.getValue(), ConstantsList.D_tmp_maxA.getValue());

        leftPVIff.setMaxMin(ConstantsList.D_left_maxU.getValue(), -ConstantsList.D_left_maxU.getValue());
        rightPVIff.setMaxMin(ConstantsList.D_right_maxU.getValue(), -ConstantsList.D_right_maxU.getValue());

        prevTime = Timer.getFPGATimestamp();
    }

    @Override
    public void pushToDashboard() {
        SmartDashboard.putNumber("leftspeed", leftSpeed);
        SmartDashboard.putNumber("rightspeed", rightSpeed);
        SmartDashboard.putNumber("distancetarget", targetPosition);
        SmartDashboard.putNumber("angletarget", targetAngle);
        SmartDashboard.putNumber("currentVelocity", motionProfiler.getCurrentVelocity());
        SmartDashboard.putNumber("currentPosition", motionProfiler.getCurrentPosition());
        SmartDashboard.putNumber("currentAcceleration", motionProfiler.getCurrentAcceleration());
        SmartDashboard.putBoolean("isDistanceDone", isDistanceDone());
        SmartDashboard.putBoolean("isTurnPID", isTurnPIDDone());
        SmartDashboard.putString("mode", mode.toString());
    }

}
