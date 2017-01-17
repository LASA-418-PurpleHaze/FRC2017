package org.lasarobotics.frc2017.subsystem;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.lasarobotics.frc2017.statics.Constants;
import org.lasarobotics.frc2017.hardware.Hardware;


public class Intake extends HazySubsystem {
    
    private static Intake instance;
    
    private double intakeSpeed;
    private double operatingSpeed;
    
    private Mode mode;
    
    private Intake(){
        setMode(Mode.OFF);
    }
    
    public static enum Mode{
        OFF, INTAKING, OUTTAKING
    }
    
    public final void setMode(Mode m){
        mode = m;
    }
    
    public static Intake getInstance(){
        return (instance == null) ? instance = new Intake() : instance;
    }
    
    Mode newMode;
    
    @Override
    public void run() {
        
        newMode = mode;
        
        if(null != mode){
            switch(mode){
                case OFF:
                    intakeSpeed = 0.0;
                    break;
                
                case INTAKING:
                    intakeSpeed = operatingSpeed;
                    break;
                
                case OUTTAKING:
                    intakeSpeed = -operatingSpeed;
                    break;
            }
        }
        
        if(newMode != mode){
            mode = newMode;
            this.run();
        }
        
        
        hardware.setIntakeSpeed(intakeSpeed);
    }

    @Override
    public void initSubsystem() {
        operatingSpeed = Constants.INTAKE_SPEED.getDouble();
    }

    @Override
    public void pushToDashboard() {
        SmartDashboard.getString("inputState", mode.toString());
    }
    
    
    
    
}
