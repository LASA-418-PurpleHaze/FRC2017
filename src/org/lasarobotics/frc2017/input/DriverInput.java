package org.lasarobotics.frc2017.input;

public class DriverInput implements Runnable{
    
    private static DriverInput instance;
    
    private DriverInput(){
        
    }
    
    public static DriverInput getInstance() {
        return (instance == null) ? instance = new DriverInput() : instance;
    }

    @Override
    public void run() {
    }
}
