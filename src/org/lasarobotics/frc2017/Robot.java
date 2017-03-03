package org.lasarobotics.frc2017;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.lasarobotics.frc2017.command.CommandManager;
import org.lasarobotics.frc2017.hardware.Hardware;
import org.lasarobotics.frc2017.input.DriverInput;
import org.lasarobotics.frc2017.subsystem.Climber;
import org.lasarobotics.lib.Constants;
import org.lasarobotics.frc2017.subsystem.Drivetrain;
import org.lasarobotics.frc2017.subsystem.Intake;
import org.lasarobotics.frc2017.subsystem.Shooter;
import org.lasarobotics.lib.HazyIterative;

public class Robot extends HazyIterative {

    DriverInput driverInput;
    Autonomous autonomous;
    Hardware hardware;

    private static int time;

    private Drivetrain drivetrain;
    private Shooter shooter;
    private Intake intake;
    private Climber climber;

    @Override
    public void robotInit() {
        drivetrain = Drivetrain.getInstance();
        shooter = Shooter.getInstance();
        intake = Intake.getInstance();
        climber = Climber.getInstance();

        driverInput = DriverInput.getInstance();
        hardware = Hardware.getInstance();

        autonomous = Autonomous.getInstance();
        autonomous.start();
        Constants.init();
    }

    private void initSubsystems() {
        Constants.load();

        drivetrain.initSubsystem();
        intake.initSubsystem();
        shooter.initSubsystem();
        climber.initSubsystem();
        hardware.reset();
        hardware.actuateGear(true);
    }

    @Override
    public void teleopInit() {
        initSubsystems();
        time = 0;
    }

    @Override
    public void teleopPeriodic() {
        SmartDashboard.putNumber("bla", 4);
        CommandManager.run();
        driverInput.run();
        drivetrain.run();
        pushToDashboard();
    }

    @Override
    public void teleopContinuous() {
        hardware.run();
        shooter.run();
        intake.run();
        climber.run();
    }

    @Override
    public void autonomousInit() {
        initSubsystems();
        CommandManager.cancelAll();
        autonomous.run();
        time = 0;
    }

    @Override
    public void autonomousPeriodic() {
        pushToDashboard();
    }

    @Override
    public void autonomousContinuous() {
        CommandManager.run();
        hardware.run();

        drivetrain.run();
        shooter.run();
        intake.run();
        climber.run();
    }

    @Override
    public void disabledInit() {
        CommandManager.cancelAll();
        time = 0;
    }

    @Override
    public void disabledPeriodic() {
        //pushToDashboard();
    }

    public void disableContinuous() {
        hardware.run();
    }

    private void pushToDashboard() {
        SmartDashboard.putNumber("Time", time++);
        hardware.pushToDashboard();
        drivetrain.pushToDashboard();
        shooter.pushToDashboard();
        intake.pushToDashboard();
        climber.pushToDashboard();
    }

}
