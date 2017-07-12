package org.lasarobotics.frc2017.subsystem;

public class GearIntake extends HazySubsystem {

    private double targetAngle;
    private double angle;
    private double rollerSpeed;
    private boolean hasGear;
    
    private static GearIntake instance;
    private GearIntake() {
        
    }
    
    public static GearIntake getInstance()
    {
        return (instance == null) ? instance = new GearIntake() : instance;
    }
    
    public static enum Mode {
        Intaking, Placing, Stowed, Extended, Spitting
    }
    
    @Override
    public void run() {
    }

    public void setAngle(double angle)
    {
    }
    
    public void setRollerSpeed(double speed)
    {
    }
    
    @Override
    public void initSubsystem() {
    }

    @Override
    public void pushToDashboard() {
    }
    
}
