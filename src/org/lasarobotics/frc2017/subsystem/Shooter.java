package org.lasarobotics.frc2017.subsystem;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.lasarobotics.frc2017.ConstantsList;

public class Shooter extends HazySubsystem {

    private static Shooter instance;

    public static Shooter getInstance() {
        return (instance == null) ? instance = new Shooter() : instance;
    }
    
    public Shooter() {
        setMode(Mode.OVERRIDE);
    }

    public static enum Mode {
        OVERRIDE, LOADING, SHOOTING, OFF;
    }

    static Mode mode;

    public void setMode(Mode m) {
        mode = m;
    }

    public Mode newMode;

    @Override
    public void run() {

        if (null != mode) {
            switch (mode) {
                case OVERRIDE:
                    break;
                case LOADING:
                    hardware.setShooterRPM(ConstantsList.S_loading_speed.getValue());
                    break;
                case SHOOTING:
                    hardware.setShooterRPM(ConstantsList.S_RPM.getValue());
                    break;
                case OFF:
                    hardware.setShooterRPM(0.0);
            }
        }

    }

    public boolean isUpToRPM() {
        return hardware.isShooterRPMDone();
    }

    @Override
    public void initSubsystem() {
    }

    @Override
    public void pushToDashboard() {
        SmartDashboard.putString("S_mode", mode.toString());
    }

}
