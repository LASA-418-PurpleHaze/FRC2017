package org.lasarobotics.frc2017.hardware;

import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.lasarobotics.frc2017.statics.Ports;

public class Hardware implements Runnable {

    private static Hardware instance;

    private final VictorSP leftDriveMotorA, leftDriveMotorB;
    private final VictorSP rightDriveMotorA, rightDriveMotorB;

    private Encoder leftDriveEncoder, rightDriveEncoder;
    private final double leftDriveEncoderPosition, leftDriveEncoderVelocity;
    private final double rightDriveEncoderPosition, rightDriveEncoderVelocity;
    
    private final AHRS navX;

    private final Talon shooterFlywheel;
    private final Talon intakeMotor;
    
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

        leftDriveEncoderPosition = leftDriveEncoder.get();
        rightDriveEncoderPosition = rightDriveEncoder.get();
        leftDriveEncoderVelocity = leftDriveEncoder.getRate();
        rightDriveEncoderVelocity = rightDriveEncoder.getRate();
        
        shooterFlywheel = new Talon(Ports.SHOOTER_MOTOR);
        intakeMotor = new Talon(Ports.INTAKE_MOTOR);
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

    public void start() {
        navX.reset();
    }

    public void setDriveSpeeds(double left, double right) {
        leftDriveMotorA.set(left);
        leftDriveMotorB.set(left);
        rightDriveMotorA.set(right);
        rightDriveMotorB.set(right);
    }
    
    public void setShooterSpeed(double speed){
        //temp. pid needs to be used
        shooterFlywheel.set(speed);
    }
    
    public void setIntakeSpeed(double speed){
        intakeMotor.set(speed);
    }

    public double getNavXAngle() {
        return navXAngle;
    }

    public double getRobotAngle() {
        return robotAngle;
    }

    //wheel measurements based on Lowrider
    public double getLeftDriveDistance() {
        return -(leftDriveEncoderPosition / 250) * 3.5 * Math.PI;
    }

    public double getRightDriveDistance() {
        return (rightDriveEncoderPosition / 250) * 3.5 * Math.PI;
    }

    public double getLeftDriveVelocity() {
        return -(leftDriveEncoderVelocity / 250) * 3.5 * Math.PI;
    }

    public double getRightDriveVelocity() {
        return rightDriveEncoderVelocity / 250 * 3.5 * Math.PI;
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
