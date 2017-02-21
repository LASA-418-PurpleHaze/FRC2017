package org.lasarobotics.frc2017.input;

import org.lasarobotics.frc2017.ConstantsList;
import org.lasarobotics.frc2017.hardware.Hardware;
import org.lasarobotics.lib.HazyJoystick;
import org.lasarobotics.lib.CheesyDriveHelper;
import org.lasarobotics.frc2017.subsystem.Drivetrain;
import org.lasarobotics.frc2017.subsystem.Intake;
import org.lasarobotics.frc2017.subsystem.Shooter;

public class DriverInput implements Runnable {

    private static DriverInput instance;
    private Hardware hardware;
    private final Drivetrain drivetrain;
    private Intake intake;
    private Shooter shooter;

    private final HazyJoystick driverLeft = new HazyJoystick(0, ConstantsList.J_deadband.getValue());
    private final HazyJoystick driverRight = new HazyJoystick(1, ConstantsList.J_deadband.getValue());

    private final CheesyDriveHelper cheesyDrive;

    private double throttle, wheel;
    private boolean quickTurn;

    private DriverInput() {
        drivetrain = Drivetrain.getInstance();
        cheesyDrive = new CheesyDriveHelper();
        hardware = Hardware.getInstance();
        intake = Intake.getInstance();
        shooter = Shooter.getInstance();
    }

    public static DriverInput getInstance() {
        return (instance == null) ? instance = new DriverInput() : instance;
    }

    @Override
    public void run() {
        drivetrainControl();
        shooterControl();
        intakeControl();
    }

    private void drivetrainControl() {
        throttle = -driverLeft.getYAxis();
        wheel = driverRight.getXAxis();
        quickTurn = driverLeft.getTrigger();

        cheesyDrive.cheesyDrive(throttle, wheel, quickTurn);
        drivetrain.setDriveSpeeds(cheesyDrive.getLeftPWM(), cheesyDrive.getRightPWM());
    }

    private void shooterControl() {
        if (driverRight.getTopFrontButton()) {
            shooter.setMode(Shooter.Mode.SHOOTING);
        } else if (driverLeft.getTopFrontButton()) {
            shooter.setMode(Shooter.Mode.LOADING);
        } else if (driverRight.getTopBackButton()) {
            shooter.setMode(Shooter.Mode.OFF);
        }
    }

    private void intakeControl() {
        if (driverRight.getTrigger()) {
            intake.setMode(Intake.Mode.SHOOTING);
        } else if (driverLeft.getTopFrontButton()) {
            intake.setMode(Intake.Mode.INTAKING);
        } else {
            intake.setMode(Intake.Mode.OFF);
        }
    }
}
