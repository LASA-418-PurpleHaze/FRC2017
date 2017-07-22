package org.lasarobotics.frc2017;


import org.lasarobotics.frc2017.command.CommandManager;
import org.lasarobotics.frc2017.hardware.Hardware;
import org.lasarobotics.frc2017.input.DriverInput;
import org.lasarobotics.frc2017.subsystem.Climber;
import org.lasarobotics.lib.Constants;
import org.lasarobotics.frc2017.subsystem.Drivetrain;
import org.lasarobotics.frc2017.subsystem.GearIntake;
import org.lasarobotics.frc2017.subsystem.Intake;
import org.lasarobotics.frc2017.subsystem.Shooter;
import org.lasarobotics.lib.HazyIterative;
import org.lasarobotics.lib.datalogging.Logger;

public class Robot extends HazyIterative {

    DriverInput driverInput;
    Autonomous autonomous;
    Hardware hardware;

    private static int time;

    private Drivetrain drivetrain;
    private Shooter shooter;
    private Intake intake;
    private Climber climber;
    private GearIntake gearintake;

    @Override
    public void robotInit() {
        drivetrain = Drivetrain.getInstance();
        shooter = Shooter.getInstance();
        intake = Intake.getInstance();
        climber = Climber.getInstance();
        gearintake = GearIntake.getInstance();

        driverInput = DriverInput.getInstance();
        hardware = Hardware.getInstance();

        autonomous = Autonomous.getInstance();
        autonomous.start();
        Constants.init();
        Logger.init();
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
        gearintake.initSubsystem();
        hardware.reset();
        Logger.makeFile();
    }

    @Override
    public void teleopInit() {
        System.out.println("teleop init");
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
        
        Logger.log();
        Logger.writeToFile();
    }

    @Override
    public void teleopContinuous() {
        hardware.run();
        shooter.run();
        intake.run();
        climber.run();
        gearintake.run();
        

    }

    @Override
    public void autonomousInit() {
        System.out.println("autonomous init");
        initSubsystems();
        CommandManager.cancelAll();
        autonomous.run();
        time = 0;
        hardware.actuateGear(true);
    }

    @Override
    public void autonomousPeriodic() {
        
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
        pushToDashboard();
        Logger.log();
    }

    @Override
    public void disabledInit() {
        System.out.println("disabled init");
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
        Hardware.putDash("Time", time++);
        hardware.pushToDashboard();
        drivetrain.pushToDashboard();
        shooter.pushToDashboard();
        intake.pushToDashboard();
        climber.pushToDashboard();
    }

}
