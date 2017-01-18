package org.lasarobotics.frc2017;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.lasarobotics.frc2017.command.CommandManager;
import org.lasarobotics.frc2017.input.DriverInput;
import org.lasarobotics.frc2017.statics.Constants;
import org.lasarobotics.frc2017.subsystem.Drivetrain;

public class Robot extends IterativeRobot {
    Drivetrain drivetrain;
    DriverInput driverInput;
    //SensorInput sensorInput;
    Constants constants;
    
    private static int time;
    
    private void pushToDashboard() {
        time++;
        SmartDashboard.putNumber("Time", time);
        drivetrain.pushToDashboard();
    }
    
    private void initSubsystems() {
        //constants.loadFromFile();
        //hazyVision.updateConstants();
        drivetrain.initSubsystem();
        //sensorInput.start();
    }
    
    public static int getTime(){
        return time;
    }
    
    @Override
    public void robotInit(){
        
    }
    
    @Override
    public void teleopInit(){
        
    }
    
    @Override
    public void teleopPeriodic(){
        
    }
    
    
    @Override
    public void autonomousInit(){
        
    }
    
    @Override
    public void autonomousPeriodic(){
        
    }
    
    @Override
    public void disabledInit(){
        CommandManager.cancelAll();
        initSubsystems();
    }
    
    @Override
    public void disabledPeriodic() {
        //sensorInput.run();
        pushToDashboard();
    }

    
    
}
