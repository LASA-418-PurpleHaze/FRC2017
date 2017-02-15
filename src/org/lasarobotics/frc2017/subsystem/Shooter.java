package org.lasarobotics.frc2017.subsystem;

//import org.lasarobotics.frc2017.hardware.Hardware;
import edu.wpi.first.wpilibj.Timer;
import org.lasarobotics.frc2017.ConstantsList;

public class Shooter extends HazySubsystem {

    private static Shooter instance;
    private static double targetRPM;
    private static double dt, prevTime;

    public static Shooter getInstance() {
        return (instance == null) ? instance = new Shooter() : instance;
    }

    public Shooter() {
        setMode(Mode.OVERRIDE);
    }

    public static enum Mode {
        OVERRIDE, LOADING, SHOOTING;
    }

    static Mode mode;

    public void setMode(Mode m) {
        mode = m;
    }

    public Mode newMode;

    @Override
    public void run() {

        dt = Timer.getFPGATimestamp() - prevTime;

        if (null != mode) {
            switch (mode) {
                case OVERRIDE:
                    break;
                case LOADING:
                    //Fill out later
                    break;
                case SHOOTING:
                    hardware.setShooterRPM(ConstantsList.S_RPM.getValue());
                    break;
            }
        }

        prevTime = Timer.getFPGATimestamp();
    }

    public boolean isUpToRPM() {
        return hardware.isShooterRPMDone();
    }

    @Override
    public void initSubsystem() {
    }

    @Override
    public void pushToDashboard() {
    }

}
