package org.lasarobotics.frc2017.input;

import org.lasarobotics.lib.HazyJoystick;
import org.lasarobotics.lib.CheesyDriveHelper;
import org.lasarobotics.frc2017.subsystem.Drivetrain;

public class DriverInput implements Runnable{
    
    private static DriverInput instance;
    private Drivetrain drivetrain;
    
    private HazyJoystick driverLeft = new HazyJoystick(0, 0.15);
    private HazyJoystick driverRight = new HazyJoystick(1, 0.15);
    
    private CheesyDriveHelper cheesyDrive;
    
    private double throttle, wheel;
    private boolean quickTurn;
    private DriverInput(){
        drivetrain = Drivetrain.getInstance();
        cheesyDrive = new CheesyDriveHelper();
    }
    
    public static DriverInput getInstance() {
        return (instance == null) ? instance = new DriverInput() : instance;
    }

    @Override
    public void run() {
        drivetrainControl();
    }

    private void drivetrainControl() {
        throttle = -driverLeft.getYAxis();
        wheel = driverRight.getXAxis();
        quickTurn = driverRight.getTrigger();
        
        cheesyDrive.cheesyDrive(throttle,wheel, quickTurn);
        drivetrain.setDriveSpeeds(cheesyDrive.getLeftPWM(), cheesyDrive.getRightPWM());
    }
}
