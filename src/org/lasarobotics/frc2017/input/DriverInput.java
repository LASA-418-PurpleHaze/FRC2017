package org.lasarobotics.frc2017.input;

import org.lasarobotics.lib.HazyJoystick;
import org.lasarobotics.lib.
import org.lasarobotics.frc2017.subsystem.Drivetrain;

public class DriverInput implements Runnable{
    
    private static DriverInput instance;
    private Drivetrain drivetrain;
    
    private HazyJoystick driver = new HazyJoystick(0, 0.15);
    private HazyJoystick operator = new HazyJoystick(1, 0.15);
    
    private CheesyDriveHelper cheesyDrive;
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
