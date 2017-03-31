package org.lasarobotics.frc2017.input;

import org.lasarobotics.frc2017.ConstantsList;
import org.lasarobotics.frc2017.hardware.Hardware;
import org.lasarobotics.frc2017.subsystem.Climber;
import org.lasarobotics.lib.HazyJoystick;
import org.lasarobotics.lib.CheesyDriveHelper;
import org.lasarobotics.frc2017.subsystem.Drivetrain;
import org.lasarobotics.frc2017.subsystem.Intake;
import org.lasarobotics.frc2017.subsystem.Shooter;
import org.lasarobotics.lib.TorqueToggle;

public class DriverInput implements Runnable {

    private static DriverInput instance;
    private Hardware hardware;
    private final Drivetrain drivetrain;
    private Intake intake;
    private Shooter shooter;
    private Climber climber;

    private final HazyJoystick driverLeft = new HazyJoystick(0, ConstantsList.J_deadband.getValue());
    private final HazyJoystick driverRight = new HazyJoystick(1, ConstantsList.J_deadband.getValue());

    private final CheesyDriveHelper cheesyDrive;
    private final TorqueToggle gearToggle;

    private double throttle, wheel;
    private boolean quickTurn;

    private DriverInput() {
        drivetrain = Drivetrain.getInstance();
        cheesyDrive = new CheesyDriveHelper();
        gearToggle = new TorqueToggle(true);
        hardware = Hardware.getInstance();
        intake = Intake.getInstance();
        shooter = Shooter.getInstance();
        climber = Climber.getInstance();
    }

    public static DriverInput getInstance() {
        return (instance == null) ? instance = new DriverInput() : instance;
    }

    @Override
    public void run() {
        drivetrainControl();
        shooterControl();
        intakeControl();

        if (!shooting && driverRight.getTrigger()) {
            climber.setMode(Climber.Mode.CLIMB);
        } else {
            climber.setMode(Climber.Mode.OFF);
        }

        gearToggle.calc(driverRight.getTopleftButton());
        hardware.actuateGear(gearToggle.get());

        /*if (driverLeft.getLeftBackButton() || driverRight.getBackRightButton()) {
            hardware.actuateGear(false);
        } else {
            hardware.actuateGear(true);
        }*/
    }

    private void drivetrainControl() {
        throttle = -driverLeft.getYAxis();
        wheel = driverRight.getXAxis();
        quickTurn = driverLeft.getTrigger();

        cheesyDrive.cheesyDrive(throttle, wheel, quickTurn);
        drivetrain.setDriveSpeeds(cheesyDrive.getLeftPWM(), cheesyDrive.getRightPWM());
    }

    private boolean shooting;

    private void shooterControl() {
        if (driverRight.getTopFrontButton()) {
            shooter.setMode(Shooter.Mode.SHOOTING);
            shooting = true;
        } else if (driverLeft.getTopFrontButton()) {
            shooter.setMode(Shooter.Mode.LOADING);
            shooting = false;
        } else if (driverRight.getTopBackButton()) {
            shooter.setMode(Shooter.Mode.OFF);
            shooting = false;
        } else if (!shooting) {
            shooter.setMode(Shooter.Mode.OFF);
        }
    }

    private void intakeControl() {
        if (driverRight.getTrigger() && shooting) {
            intake.setMode(Intake.Mode.FEEDING);
        } else if (driverLeft.getTopFrontButton()) {
            intake.setMode(Intake.Mode.INTAKING);
        } else {
            intake.setMode(Intake.Mode.OFF);
        }
    }

}
