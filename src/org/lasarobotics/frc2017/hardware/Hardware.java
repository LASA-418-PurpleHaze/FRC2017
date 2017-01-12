package org.lasarobotics.frc2017.hardware;

import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.VictorSP;
import org.lasarobotics.frc2017.statics.Ports;

public class Hardware implements Runnable {

    private static Hardware instance;

    private final VictorSP leftDriveMotorA, leftDriveMotorB;
    private final VictorSP rightDriveMotorA, rightDriveMotorB;

    private AHRS navX;

    private volatile double navXAngle;

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
    }

    @Override
    public void run() {
        navXAngle = navX.getAngle();
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

    public double getNavXAngle() {
        return navXAngle;
    }

}
