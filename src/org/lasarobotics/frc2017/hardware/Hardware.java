package org.lasarobotics.frc2017.hardware;

import com.ctre.CANTalon;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.lasarobotics.frc2017.ConstantsList;

public class Hardware implements Runnable {

    private static Hardware instance;

    private final VictorSP leftDriveMotorA, leftDriveMotorB;
    private final VictorSP rightDriveMotorA, rightDriveMotorB;

    private final Encoder leftDriveEncoder, rightDriveEncoder;
    private final double leftDriveEncoderPosition, leftDriveEncoderVelocity;
    private final double rightDriveEncoderPosition, rightDriveEncoderVelocity;
    private double targetRPM;

    private final AHRS navX;

    private final Solenoid gearSolenoid;

    private final CANTalon shooterFlywheel;
    private final Talon intakeMotorL;
    private final Talon intakeMotorR;

    private volatile double navXAngle, robotAngle;
    private volatile int rotations;

    public static Hardware getInstance() {
        return (instance == null) ? instance = new Hardware() : instance;
    }

    public Hardware() {
        navX = new AHRS(SPI.Port.kMXP);

        leftDriveMotorA = new VictorSP(Ports.LEFT_DRIVE_MOTOR_A);
        leftDriveMotorB = new VictorSP(Ports.LEFT_DRIVE_MOTOR_B);
        rightDriveMotorA = new VictorSP(Ports.RIGHT_DRIVE_MOTOR_A);
        rightDriveMotorB = new VictorSP(Ports.RIGHT_DRIVE_MOTOR_B);

        rightDriveMotorA.setInverted(true);
        rightDriveMotorB.setInverted(true);

        leftDriveEncoder = new Encoder(Ports.DRIVE_ENCODER_L_A, Ports.DRIVE_ENCODER_L_B);
        rightDriveEncoder = new Encoder(Ports.DRIVE_ENCODER_R_A, Ports.DRIVE_ENCODER_R_B);

        leftDriveEncoder.setReverseDirection(true);

        leftDriveEncoderPosition = leftDriveEncoder.get();
        rightDriveEncoderPosition = rightDriveEncoder.get();
        leftDriveEncoderVelocity = leftDriveEncoder.getRate();
        rightDriveEncoderVelocity = rightDriveEncoder.getRate();

        shooterFlywheel = new CANTalon(Ports.SHOOTER_MOTOR);
        intakeMotorL = new Talon(Ports.INTAKE_MOTOR_L);
        intakeMotorR = new Talon(Ports.INTAKE_MOTOR_R);
        intakeMotorR.setInverted(true);

        shooterFlywheel.setPID(ConstantsList.S_kP.getValue(), ConstantsList.S_kI.getValue(),
                ConstantsList.S_kD.getValue());

        gearSolenoid = new Solenoid(Ports.GEARSOLENOID);
    }

    @Override
    public void run() {
        double newAngle = navX.getFusedHeading();

        if (Math.abs(navXAngle - newAngle) > 180.0) {
            if (newAngle < 180.0) {
                rotations++;
            } else {
                rotations--;
            }
        }

        navXAngle = navX.getAngle();
        robotAngle = navXAngle + rotations * 360.0;
    }

    public void resetRobotAngle() {
        navX.reset();
    }

    public void start() {
        navX.reset();
    }

    public void setDriveSpeeds(double left, double right) {
        leftDriveMotorA.set(left);
        leftDriveMotorB.set(left);
        rightDriveMotorA.set(right);
        rightDriveMotorB.set(right);
    }

    public void setShooterRPM(double rpm) {
        targetRPM = rpm;
        shooterFlywheel.set(rpm);
    }

    public boolean isShooterRPMDone() {
        return (shooterFlywheel.pidGet() == targetRPM);
    }

    public void setIntakeSpeed(double speed) {
        intakeMotorL.set(speed);
        intakeMotorR.set(speed);
    }

    public void actuateGear(boolean out) {
        gearSolenoid.set(out);
    }

    public double getNavXAngle() {
        return navXAngle;
    }

    public double getRobotAngle() {
        return robotAngle;
    }

    //wheel measurements based on Lowrider
    public double getLeftDriveDistance() {
        return leftDriveEncoderPosition / 250 * 3.5 * Math.PI * (36 / 48);
    }

    public double getRightDriveDistance() {
        return rightDriveEncoderPosition / 250 * 3.5 * Math.PI * (36 / 48);
    }

    public double getLeftDriveVelocity() {
        return leftDriveEncoderVelocity / 250 * 3.5 * Math.PI * (36 / 48);
    }

    public double getRightDriveVelocity() {
        return rightDriveEncoderVelocity / 250 * 3.5 * Math.PI * (36 / 48);
    }

    /*
    public double getTime(){
        return time;
    }*/
    public void pushToDashboard() {
        SmartDashboard.putNumber("NavX Angle", navXAngle);
        SmartDashboard.putNumber("Robot Angle", robotAngle);
        SmartDashboard.putNumber("Left Drive Velocity", getLeftDriveVelocity());
        SmartDashboard.putNumber("Right Drive Velocity", getRightDriveVelocity());
        //SmartDashboard.putNumber("Time", getTime());
    }

}
