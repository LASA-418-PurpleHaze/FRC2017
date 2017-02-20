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
import org.lasarobotics.frc2017.dataLogging.Logger;

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
        
        Logger.addLog(intake);
        Logger.addLog(shooter);
        Logger.addLog(drivetrain);
        Logger.addLog(climber);
        Logger.addLog(driverInput);
    }

    private void initSubsystems() {
        Constants.load();

        drivetrain.initSubsystem();
        intake.initSubsystem();
        shooter.initSubsystem();
        climber.initSubsystem();
        hardware.reset();
        Logger.makeFile();
    }

    @Override
    public void teleopInit() {
        CommandManager.cancelAll();
        drivetrain.setMode(Drivetrain.Mode.OVERRIDE);
        initSubsystems();
        time = 0;
    }

    @Override
    public void teleopPeriodic() {
        CommandManager.run();
        driverInput.run();
        drivetrain.run();
        pushToDashboard();
        
        Logger.writeToFile();
    }

    @Override
    public void teleopContinuous() {
        hardware.run();
        shooter.run();
        intake.run();
        climber.run();
        
        Logger.log();
    }

    @Override
    public void autonomousInit() {
        initSubsystems();
        CommandManager.cancelAll();
        autonomous.run();
        time = 0;
        hardware.actuateGear(true);
    }

    @Override
    public void autonomousPeriodic() {
        pushToDashboard();
        Logger.writeToFile();
    }

    @Override
    public void autonomousContinuous() {
        CommandManager.run();
        hardware.run();
        drivetrain.run();
        shooter.run();
        intake.run();
        climber.run();
        
        Logger.log();
        
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
        Logger.closeFile();
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
