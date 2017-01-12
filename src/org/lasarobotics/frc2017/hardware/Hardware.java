package org.lasarobotics.frc2017.hardware;

import edu.wpi.first.wpilibj.VictorSP;
import org.lasarobotics.frc2017.statics.Ports;

public class Hardware implements Runnable {

    private static Hardware instance;

    private VictorSP leftDriveMotorA, leftDriveMotorB;
    private VictorSP rightDriveMotorA, rightDriveMotorB;

    public static Hardware getInstance() {
        return (instance == null) ? instance = new Hardware() : instance;
    }

    public Hardware() {
        leftDriveMotorA = new VictorSP(Ports.LEFT_DRIVE_MOTOR_A);
        leftDriveMotorB = new VictorSP(Ports.LEFT_DRIVE_MOTOR_B);
        rightDriveMotorA = new VictorSP(Ports.RIGHT_DRIVE_MOTOR_A);
        rightDriveMotorB = new VictorSP(Ports.RIGHT_DRIVE_MOTOR_B);

        rightDriveMotorA.setInverted(true);
        rightDriveMotorB.setInverted(true);
    }

    @Override
    public void run() {
    }
}
