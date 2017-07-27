package org.lasarobotics.frc2017.subsystem;

import org.lasarobotics.frc2017.hardware.Hardware;
import org.lasarobotics.lib.datalogging.Loggable;

public class GearIntake extends HazySubsystem implements Loggable {

    private double targetAngle;
    private double rollerSpeed;
    private boolean hasGear;
    
    private static GearIntake instance;
    private GearIntake() {
        
    }
    
    public static GearIntake getInstance()
    {
        return (instance == null) ? instance = new GearIntake() : instance;
    }

    @Override
    public String getNames() {
        return "G_rollerSpeed, G_targetAngle";
    }

    @Override
    public String getValues() {
        return rollerSpeed + ", " + targetAngle;
    }
    
    public static enum Mode {
        Intaking, Placing, Stowed, Extended, Spitting
    }
    
    @Override
    public void run() {
        
    }

    public void setAngle(double angle)
    {
        targetAngle = angle;
        hardware.setGearAngle(angle);
    }
    
    public void setRollerSpeed(double speed)
    {
        rollerSpeed = speed;
        hardware.setGearSpeed(speed);
    }
    
    @Override
    public void initSubsystem() {
    }

    @Override
    public void pushToDashboard() {
        Hardware.putDash("G_Roller_Speed", rollerSpeed);
        Hardware.putDash("G_Intake_Angle", targetAngle);
    }
    
}
