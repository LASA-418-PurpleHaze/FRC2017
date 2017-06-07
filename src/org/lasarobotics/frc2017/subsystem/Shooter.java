package org.lasarobotics.frc2017.subsystem;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.lasarobotics.frc2017.ConstantsList;
import org.lasarobotics.lib.datalogging.Loggable;
import org.lasarobotics.frc2017.hardware.Hardware;

public class Shooter extends HazySubsystem implements Loggable{

    private static Shooter instance;
    private double shooterSpeed;

    public static Shooter getInstance() {
        return (instance == null) ? instance = new Shooter() : instance;
    }
    
    public Shooter() {
        setMode(Mode.OVERRIDE);
    }

    @Override
    public String getNames() {
        return "shooterSpeed";
    }

    @Override
    public String getValues() {
        return shooterSpeed + "";
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
                    shooterSpeed = ConstantsList.S_loading_speed.getValue();
                    break;
                case SHOOTING:
                    shooterSpeed = ConstantsList.S_RPM.getValue();
                    break;
                case OFF:
                    shooterSpeed = 0.0;
            }
        }
        hardware.setShooterRPM(shooterSpeed);
    }

    public boolean isUpToRPM() {
        return hardware.isShooterRPMDone();
    }

    @Override
    public void initSubsystem() {
    }

    @Override
    public void pushToDashboard() {
        Hardware.putDash("S_mode", mode.toString());
    }

}
