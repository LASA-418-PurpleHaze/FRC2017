package org.lasarobotics.frc2017.subsystem;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.lasarobotics.frc2017.ConstantsList;
import org.lasarobotics.frc2017.hardware.Hardware;

public class Intake extends HazySubsystem {

    private static Intake instance;

    private double intakeSpeed;

    private Mode mode;

    private Intake() {
        setMode(Mode.OFF);
    }

    public static enum Mode {
        OFF, INTAKING, OUTTAKING, SHOOTING
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
                    hardware.setConveyorRollerSpeed(0);
                    hardware.setIntakeSolenoid(false);
                    break;

                case INTAKING:
                    hardware.setIntakeMode(Hardware.IntakeMode.intaking);
                    hardware.setIntakeSolenoid(true);
                    intakeSpeed = ConstantsList.I_intake_current.getValue();
                    hardware.setConveyorRollerSpeed(0);
                    break;

                case OUTTAKING:
                    hardware.setIntakeMode(Hardware.IntakeMode.intaking);
                    hardware.setIntakeSolenoid(false);
                    intakeSpeed = ConstantsList.I_outtake_current.getValue();
                    break;
                    
                case SHOOTING:
                    hardware.setIntakeMode(Hardware.IntakeMode.shooting);
                    hardware.setIntakeSolenoid(false);
                    intakeSpeed = ConstantsList.I_shooting_voltage.getValue();
                    hardware.setConveyorRollerSpeed(ConstantsList.I_conveyor_speed.getValue());
                    break;
            }
        }

        hardware.setIntakeOutput(intakeSpeed);
    }

    @Override
    public void initSubsystem() {
    }

    @Override
    public void pushToDashboard() {
        SmartDashboard.putString("inputState", mode.toString());
    }

}
