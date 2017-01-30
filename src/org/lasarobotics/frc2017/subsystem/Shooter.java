package org.lasarobotics.frc2017.subsystem;

//import org.lasarobotics.frc2017.hardware.Hardware;

public class Shooter extends HazySubsystem{

    private static Shooter instance;
    private boolean shooterFar;

    public static Shooter getInstance() {
        return (instance == null) ? instance = new Shooter() : instance;
    }
    
    public Shooter(){
        setMode(Mode.OVERRIDE);
    }

    public static enum Mode {
        OVERRIDE, IN, CLOSE, FAR;
    }

    static Mode mode;

    public void setMode(Mode m) {
        mode = m;
    }
    
    public Mode newMode;
    
    @Override
    public void run() {
        if (null != mode) {
            //WARNING : So far I'm only accounting for the Pneumatics of the shooter,
            //other things(such as setting shooterSpeed) will have to be later
            switch (mode) {
                case OVERRIDE:
                    //Fill out later
                    break;
                case IN:
                    //Fill out later
                    break;
                case CLOSE:
                    shooterFar = false;
                    break;
                case FAR:
                    shooterFar = true;
                    break;
            }
        }

        hardware.setShooterFar(shooterFar);
    }

    @Override
    public void initSubsystem() {
    }

    @Override
    public void pushToDashboard() {
    }
    
}
