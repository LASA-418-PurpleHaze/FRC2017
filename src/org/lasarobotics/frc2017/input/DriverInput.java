package org.lasarobotics.frc2017.input;

import org.lasarobotics.frc2017.ConstantsList;
import org.lasarobotics.frc2017.command.CommandManager;
import org.lasarobotics.frc2017.command.GrabGear;
import org.lasarobotics.frc2017.command.SetGearIntakePosition;
import org.lasarobotics.frc2017.command.SetGearIntakeRollerSpeed;
import org.lasarobotics.lib.datalogging.Loggable;
import org.lasarobotics.frc2017.hardware.Hardware;
import org.lasarobotics.frc2017.subsystem.Climber;
import org.lasarobotics.lib.HazyJoystick;
import org.lasarobotics.lib.CheesyDriveHelper;
import org.lasarobotics.frc2017.subsystem.Drivetrain;
import org.lasarobotics.frc2017.subsystem.GearIntake;
import org.lasarobotics.frc2017.subsystem.Intake;
import org.lasarobotics.frc2017.subsystem.Shooter;
import org.lasarobotics.lib.TorqueToggle;

public class DriverInput implements Runnable, Loggable {

    private static DriverInput instance;
    private Hardware hardware;
    private final Drivetrain drivetrain;
    private Intake intake;
    private Shooter shooter;
    private Climber climber;
    private GearIntake gearintake;

    private final HazyJoystick driverLeft = new HazyJoystick(0, ConstantsList.J_deadband.getValue());
    private final HazyJoystick driverRight = new HazyJoystick(1, ConstantsList.J_deadband.getValue());

    private final CheesyDriveHelper cheesyDrive;
    private final TorqueToggle gearToggle;

    private double throttle, wheel;
    private boolean quickTurn;
    private double leftOverrideSpeed;
    private double rightOverrideSpeed;

    private DriverInput() {
        drivetrain = Drivetrain.getInstance();
        cheesyDrive = new CheesyDriveHelper();
        gearToggle = new TorqueToggle();
        hardware = Hardware.getInstance();
        intake = Intake.getInstance();
        shooter = Shooter.getInstance();
        climber = Climber.getInstance();
        gearintake = GearIntake.getInstance();

    }

    public static DriverInput getInstance() {
        return (instance == null) ? instance = new DriverInput() : instance;
    }

    @Override
    public void run() {
        drivetrainControl();
        shooterControl();
        intakeControl();
        gearControl();

        climber.setClimberSpeed((driverLeft.getTopBackButton()) ? 0.7 : 0.0);

        gearToggle.calc(driverRight.getTopFrontButton());
        hardware.actuateGear(!gearToggle.get());

    }

    private void drivetrainControl() {
        throttle = -driverLeft.getYAxis();
        wheel = driverRight.getXAxis();
        quickTurn = driverLeft.getTrigger();

        cheesyDrive.cheesyDrive(throttle, wheel, quickTurn);
        leftOverrideSpeed = cheesyDrive.getLeftPWM();
        rightOverrideSpeed = cheesyDrive.getRightPWM();
        drivetrain.setDriveSpeeds(leftOverrideSpeed, rightOverrideSpeed);
    }

    private boolean shooting;

    private void shooterControl() {
        //if (driverRight.getTopFrontButton()) {
        //  shooter.setMode(Shooter.Mode.SHOOTING);
        // shooting = true;
        //} 
        if (driverLeft.getTopFrontButton()) {
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

    boolean lastBackLeftButtonPressed;
    boolean lastBackRightButtonPressed;
    boolean lastTriggerPressed;

    private void gearControl() {
        if (driverRight.getTopBackButton() && !lastBackLeftButtonPressed) {
            //CommandManager.addCommand(new GrabGear());
            gearintake.setAngle(ConstantsList.G_intake_angle.getValue());
            gearintake.setRollerSpeed(ConstantsList.G_intake_speed.getValue());
        } else if (!driverRight.getTopBackButton() && lastBackLeftButtonPressed) {
            //CommandManager.addCommand(new GrabGear());
            gearintake.setAngle(ConstantsList.G_carry_angle.getValue());
            gearintake.setRollerSpeed(0.0);
        } else if (driverRight.getTrigger() && !lastTriggerPressed) {
            gearintake.setAngle(ConstantsList.G_release_angle.getValue());
            gearintake.setRollerSpeed(ConstantsList.G_release_speed.getValue());
        } else if (!driverRight.getTrigger() && lastTriggerPressed) {
            gearintake.setRollerSpeed(0);
            gearintake.setAngle(ConstantsList.G_carry_angle.getValue());
        }

        lastTriggerPressed = driverRight.getTrigger();
        lastBackLeftButtonPressed = driverRight.getTopBackButton();
        lastBackRightButtonPressed = driverRight.getBackRightButton();
    }

    @Override
    public String getNames() {
        return "leftOverrideSpeed, rightOverrideSpeed";
    }

    @Override
    public String getValues() {
        return leftOverrideSpeed + ", " + rightOverrideSpeed;
    }
}
