package org.lasarobotics.frc2017.subsystem;

import org.lasarobotics.frc2017.ConstantsList;
import org.lasarobotics.frc2017.hardware.Hardware;
import org.lasarobotics.lib.datalogging.Loggable;
import org.lasarobotics.lib.controlloop.HazyPID;
import org.lasarobotics.lib.controlloop.HazyPVIff;
import org.lasarobotics.lib.controlloop.HazyTMP;

public class Drivetrain extends HazySubsystem implements Loggable {

    private double leftSpeed, rightSpeed;
    private double dt, prevTime;
    private final HazyPVIff straightPVIff;
    private final HazyPID turnPID, steerPID;
    private HazyTMP motionProfiler;
    private double targetPosition, targetAngle;

    private static Drivetrain instance;

    public static Drivetrain getInstance() {
        return (instance == null) ? instance = new Drivetrain() : instance;
    }

    private Drivetrain() {
        straightPVIff = new HazyPVIff();
        turnPID = new HazyPID();
        steerPID = new HazyPID();

        this.setMode(Mode.OVERRIDE);
        initSubsystem();
    }

    @Override
    public String getNames() {
        return "D_l_pos, D_l_velocity, D_r_pos, D_r_velocity, D_l_pviff_speed, D_r_pviff_speed, D_target_pos, D_target_angle, D_tmp_velocity, D_tmp_pos, D_tmp_accel";
    }

    @Override
    public String getValues() {
        return hardware.getLeftDriveDistance() + ", " + hardware.getLeftDriveVelocity() + ", " + hardware.getRightDriveDistance() + ", " + hardware.getRightDriveVelocity() + ", " + leftSpeed + ", " + rightSpeed + ", "
                + targetPosition + ", " + targetAngle + ", " + motionProfiler.getCurrentVelocity() + ", " + motionProfiler.getCurrentPosition() + ", " + motionProfiler.getCurrentAcceleration();
    }

    public static enum Mode {
        OVERRIDE, STRAIGHT, TURN, ARCING;
    }

    static Mode mode;

    public void setMode(Mode m) {
        mode = m;
    }

    @Override
    public void run() {
        dt = Hardware.getCurrentTime() - prevTime;
        double turn;
        if (null != mode) {
            switch (mode) {
                case OVERRIDE:
                    break;
                case STRAIGHT:
                    motionProfiler.calculateNextSituation(dt);
                    leftSpeed = rightSpeed = straightPVIff.calculate((hardware.getLeftDriveDistance() + hardware.getRightDriveDistance()) * 0.5,
                            (hardware.getRightDriveVelocity() + hardware.getLeftDriveVelocity()) * 0.5, motionProfiler.getCurrentPosition(),
                            motionProfiler.getCurrentVelocity(), motionProfiler.getCurrentAcceleration(), dt);
                    turn = steerPID.calculate(hardware.getRobotAngle(), dt);
                    leftSpeed += turn;
                    rightSpeed -= turn;
                    break;
                case ARCING:
                    double percent = (hardware.getLeftDriveDistance() + hardware.getRightDriveDistance()) * 0.5 / targetPosition;
                    turnPID.setTarget(percent * targetAngle);

                    motionProfiler.calculateNextSituation(dt);
                    leftSpeed = rightSpeed = straightPVIff.calculate((hardware.getLeftDriveDistance() + hardware.getRightDriveDistance()) * 0.5,
                            (hardware.getRightDriveVelocity() + hardware.getLeftDriveVelocity()) * 0.5, motionProfiler.getCurrentPosition(),
                            motionProfiler.getCurrentVelocity(), motionProfiler.getCurrentAcceleration(), dt);

                    turn = steerPID.calculate(hardware.getRobotAngle(), dt);
                    leftSpeed += turn;
                    rightSpeed -= turn;
                case TURN:
                    turn = turnPID.calculate(hardware.getRobotAngle(), dt);
                    leftSpeed = turn;
                    rightSpeed = -turn;
                    break;
            }
        }
        prevTime = Hardware.getCurrentTime();
        hardware.setDriveSpeeds(leftSpeed, rightSpeed);
    }

    public void setDriveSpeeds(double l, double r) {
        leftSpeed = l;
        rightSpeed = r;
    }

    public void setArcSetpoint(double radius, double angle) {
        targetPosition = Math.abs(angle) / 360 * 2 * radius * Math.PI;
        targetAngle = angle;
        motionProfiler.generateTrapezoid(targetPosition,
                (hardware.getLeftDriveDistance() + hardware.getRightDriveDistance() * 0.5),
                (Math.abs(hardware.getLeftDriveVelocity()) + Math.abs(hardware.getRightDriveVelocity())) * 0.5);
        straightPVIff.reset();
        turnPID.reset();
    }

    public void setStraightSetpoint(double d) {
        targetPosition = d;
        turnPID.setTarget(0.0);
        motionProfiler.generateTrapezoid(targetPosition,
                (hardware.getLeftDriveDistance() + hardware.getRightDriveDistance() * 0.5),
                (Math.abs(hardware.getLeftDriveVelocity()) + Math.abs(hardware.getRightDriveVelocity())) * 0.5);
        straightPVIff.reset();
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

    public boolean isTurnPIDDone() {
        return turnPID.onTarget();
    }

    public boolean isDistanceDone() {
        return straightPVIff.onTarget();
    }
    
    public boolean isArcDone(){
        return straightPVIff.onTarget() && turnPID.onTarget();
    }

    @Override
    public void initSubsystem() {
        turnPID.setPID(ConstantsList.D_turn_kP.getValue(), ConstantsList.D_turn_kI.getValue(),
                ConstantsList.D_turn_kD.getValue(), ConstantsList.D_turn_kFF.getValue(),
                ConstantsList.D_turn_doneBound.getValue());
        straightPVIff.setPID(ConstantsList.D_left_kP.getValue(),
                ConstantsList.D_left_kI.getValue(),
                ConstantsList.D_left_kV.getValue(),
                ConstantsList.D_left_kFFV.getValue(),
                ConstantsList.D_left_kFFA.getValue(),
                ConstantsList.D_left_doneBound.getValue());
        steerPID.setPID(ConstantsList.D_steer_kP.getValue(), ConstantsList.D_steer_kI.getValue(),
                ConstantsList.D_steer_kD.getValue(), ConstantsList.D_steer_kFF.getValue(),
                ConstantsList.D_steer_doneBound.getValue());
        motionProfiler = new HazyTMP(ConstantsList.D_tmp_maxV.getValue(), ConstantsList.D_tmp_maxA.getValue());

        straightPVIff.setMaxMin(ConstantsList.D_left_maxU.getValue(), -ConstantsList.D_left_maxU.getValue());
        straightPVIff.setMinCount((int) ConstantsList.D_done_cycles.getValue());
        turnPID.setMinCount((int) ConstantsList.D_done_cycles.getValue());

        prevTime = Hardware.getCurrentTime();
    }

    @Override
    public void pushToDashboard() {
        Hardware.putDash("D_l_pos", hardware.getLeftDriveDistance());
        Hardware.putDash("D_l_velocity", hardware.getLeftDriveVelocity());
        Hardware.putDash("D_r_pos", hardware.getRightDriveDistance());
        Hardware.putDash("D_r_velocity", hardware.getRightDriveVelocity());
        Hardware.putDash("D_l_pviff_speed", leftSpeed);
        Hardware.putDash("D_r_pviff_speed", rightSpeed);
        Hardware.putDash("D_target_pos", targetPosition);
        Hardware.putDash("D_target_angle", targetAngle);
        Hardware.putDash("D_tmp_velocity", motionProfiler.getCurrentVelocity());
        Hardware.putDash("D_tmp_pos", motionProfiler.getCurrentPosition());
        Hardware.putDash("D_tmp_accel", motionProfiler.getCurrentAcceleration());
        Hardware.putDash("D_dist_done", isDistanceDone());
        Hardware.putDash("D_turn_done", isTurnPIDDone());
        Hardware.putDash("D_mode", mode.toString());
        Hardware.putDash("D_avg_v", 0.5 * (hardware.getLeftDriveVelocity() + hardware.getRightDriveVelocity()));
        Hardware.putDash("D_avg_p", 0.5 * (hardware.getLeftDriveDistance() + hardware.getRightDriveDistance()));
        Hardware.putDash("D_avg_pviff_speed", 0.5 * (leftSpeed + rightSpeed));

    }

}
