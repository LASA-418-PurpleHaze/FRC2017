package org.lasarobotics.frc2017.subsystem;

import org.lasarobotics.frc2017.ConstantsList;
import org.lasarobotics.lib.datalogging.Loggable;
import org.lasarobotics.frc2017.hardware.Hardware;

public class Climber extends HazySubsystem implements Loggable {

    private static Climber instance;

    private double climberSpeed;
    private double multiplier;

    public static Climber getInstance() {
        return (instance == null) ? instance = new Climber() : instance;
    }

    @Override
    public String getNames() {
        return "climberSpeed, climberCurrent";
    }

    @Override
    public String getValues() {
        return climberSpeed + ", " + hardware.getClimberCurrent() + ", ";
    }

    public Climber() {
        climberSpeed = 0;
    }

    @Override
    public void run() {
        hardware.setClimberSpeed(climberSpeed * multiplier);
    }

    @Override
    public void initSubsystem() {
        multiplier = ConstantsList.C_climber_multiplier.getValue();
        climberSpeed = 0.0;
    }

    @Override
    public void pushToDashboard() {
        Hardware.putDash("C_speed", climberSpeed);
        Hardware.putDash("C_current", hardware.getClimberCurrent());
    }

    public void setClimberSpeed(double zAxis) {
        this.climberSpeed = zAxis;
    }

}
