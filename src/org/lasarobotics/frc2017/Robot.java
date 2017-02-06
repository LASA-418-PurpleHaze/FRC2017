package org.lasarobotics.frc2017;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.lasarobotics.frc2017.command.CommandManager;
import org.lasarobotics.frc2017.hardware.Hardware;
import org.lasarobotics.frc2017.input.DriverInput;
import org.lasarobotics.lib.Constants;
import org.lasarobotics.frc2017.subsystem.Drivetrain;
import org.lasarobotics.frc2017.subsystem.Intake;
import org.lasarobotics.frc2017.subsystem.Shooter;
import org.lasarobotics.lib.HazyIterative;
public class Robot extends HazyIterative {
    Drivetrain drivetrain;
    DriverInput driverInput;
    
    Constants constants;
    Autonomous autonomous;
    Hardware hardware;
    
    private static int time;
    private Shooter shooter;
    private Intake intake;
    
    private void pushToDashboard() {
        time++;
        SmartDashboard.putNumber("Time", time);
        drivetrain.pushToDashboard();
        shooter.pushToDashboard();
        intake.pushToDashboard();
        
    }
    
    private void initSubsystems() {
        Constants.load();
        //If we use vision we would update constants here
        drivetrain.initSubsystem();
        intake.initSubsystem();
        shooter.initSubsystem();
        hardware.start();
    }
    
    public static int getTime(){
        return time;
    }
    
    @Override
    public void robotInit(){
        drivetrain = Drivetrain.getInstance();
        shooter = Shooter.getInstance();
        intake = Intake.getInstance();
        
        driverInput = DriverInput.getInstance();
        hardware = Hardware.getInstance();
        autonomous = Autonomous.getInstance();
        autonomous.start();
        
        constants = new Constants();
        
    }
    
    @Override
    public void teleopInit(){
        CommandManager.cancelAll();
        initSubsystems();
        time=0;
    }
    
    @Override
    public void teleopPeriodic(){
        CommandManager.run();
        driverInput.run();
        drivetrain.run();
        pushToDashboard();
    }
    
    public void teleopContinuous(){
        hardware.run();
        shooter.run();
        intake.run();
    }
    @Override
    public void autonomousInit(){
        initSubsystems();
        CommandManager.cancelAll();
        autonomous.run();
    }
    
    @Override
    public void autonomousPeriodic(){
        pushToDashboard();
        
    }
    
    public void autonomousContinuous(){
        CommandManager.run();
        hardware.run();
        drivetrain.run();
        shooter.run();
    }
    
    @Override
    public void disabledInit(){
        CommandManager.cancelAll();
        initSubsystems();
        time=0;
    }
    
    @Override
    public void disabledPeriodic() {
        pushToDashboard();
    }
    
    public void disableContinuous(){
        hardware.run();
    }
   

    
    
}
