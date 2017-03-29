package org.lasarobotics.frc2017.subsystem;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.lasarobotics.frc2017.ConstantsList;
import org.lasarobotics.frc2017.dataLogging.Loggable;

public class Climber extends HazySubsystem implements Loggable{

    private static Climber instance;

    private double climberSpeed;

    public static Climber getInstance() {
        return (instance == null) ? instance = new Climber() : instance;
    }

    private Mode mode;

    @Override
    public String getNames() {
        return "climberSpeed";
    }

    @Override
    public String getValues() {
        return climberSpeed + "";
    }

    public static enum Mode {
        OFF, CLIMB;
    }

    public final void setMode(Mode m) {
        mode = m;
    }

    public Climber() {
        setMode(Mode.OFF);
    }

    @Override
    public void run() {
        if (this.mode == Mode.CLIMB) {
            climberSpeed = ConstantsList.C_climber_speed.getValue();
        } else {
            climberSpeed = 0.0;
        }
        hardware.setClimberSpeed(climberSpeed);
    }

    @Override
    public void initSubsystem() {
    }

    @Override
    public void pushToDashboard() {
        SmartDashboard.putNumber("C_speed", climberSpeed);
        SmartDashboard.putNumber("C_current", hardware.getClimberCurrent());
    }

}
