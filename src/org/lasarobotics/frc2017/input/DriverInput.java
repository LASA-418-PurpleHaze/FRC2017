package org.lasarobotics.frc2017.input;

import org.lasarobotics.frc2017.ConstantsList;
import org.lasarobotics.frc2017.hardware.Hardware;
import org.lasarobotics.lib.HazyJoystick;
import org.lasarobotics.lib.CheesyDriveHelper;
import org.lasarobotics.frc2017.subsystem.Drivetrain;
import org.lasarobotics.frc2017.subsystem.Shooter;

public class DriverInput implements Runnable{
    
    private static DriverInput instance;
    private static Hardware hardware;
    private final Drivetrain drivetrain;
    
    private final HazyJoystick driverLeft = new HazyJoystick(0, ConstantsList.J_deadband.getValue());
    private final HazyJoystick driverRight = new HazyJoystick(1, ConstantsList.J_deadband.getValue());
    
    private final CheesyDriveHelper cheesyDrive;
    
    private double throttle, wheel;
    private boolean quickTurn;
    private DriverInput(){
        drivetrain = Drivetrain.getInstance();
        cheesyDrive = new CheesyDriveHelper();
        hardware=Hardware.getInstance();
    }
    
    public static DriverInput getInstance() {
        return (instance == null) ? instance = new DriverInput() : instance;
    }

    @Override
    public void run() {
        drivetrainControl();
        
        if(driverRight.getTopFrontButton()){
            hardware.setShooterRPM(5000);
        }else{
            hardware.setShooterRPM(0);
        }
        if(driverLeft.getTopFrontButton()){
            hardware.setIntakeOutput(2.0);
        }else{
            hardware.setIntakeOutput(0);
        }
    }

    private void drivetrainControl() {
        throttle = -driverLeft.getYAxis();
        wheel = driverRight.getXAxis();
        quickTurn = driverRight.getTrigger();
        
        cheesyDrive.cheesyDrive(throttle,wheel, quickTurn);
        drivetrain.setDriveSpeeds(cheesyDrive.getLeftPWM(), cheesyDrive.getRightPWM());
    }
    
    private void shooterControl() {
        
    }
}
