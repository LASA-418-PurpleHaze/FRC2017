package org.lasarobotics.frc2017.subsystem;

public class Shooter extends HazySubsystem{

    private static Shooter instance;

    public static Shooter getInstance() {
        return (instance == null) ? instance = new Shooter() : instance;
    }

    public static enum Mode {
        OVERRIDE, IN, OUT;
    }

    static Mode mode;

    public void setMode(Mode m) {
        mode = m;
    }
    
    @Override
    public void run() {
        if(this.mode == Mode.OVERRIDE){
            //fill in later
        }
        else{
            //fill in later
        }
    }

    @Override
    public void initSubsystem() {
    }

    @Override
    public void pushToDashboard() {
    }
    
}
