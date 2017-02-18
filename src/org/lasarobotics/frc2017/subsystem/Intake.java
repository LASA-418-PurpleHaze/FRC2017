package org.lasarobotics.frc2017.subsystem;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.lasarobotics.frc2017.ConstantsList;

public class Intake extends HazySubsystem {

    private static Intake instance;

    private double intakeSpeed;
    private double operatingSpeed;

    private Mode mode;

    private Intake() {
        setMode(Mode.OFF);
    }

    public static enum Mode {
        OFF, INTAKING, OUTTAKING
    }

    public final void setMode(Mode m) {
        mode = m;
    }

    public Mode getMode() {
        return mode;
    }

    public static Intake getInstance() {
        return (instance == null) ? instance = new Intake() : instance;
    }

    @Override
    public void run() {

        if (null != mode) {
            switch (mode) {
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

        hardware.setIntakeOutput(intakeSpeed);
    }

    @Override
    public void initSubsystem() {
        operatingSpeed = ConstantsList.I_intake_speed.getValue();
    }

    @Override
    public void pushToDashboard() {
        SmartDashboard.getString("inputState", mode.toString());
    }

}
