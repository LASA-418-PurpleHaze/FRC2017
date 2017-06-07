package org.lasarobotics.frc2017.subsystem;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.lasarobotics.frc2017.ConstantsList;
import org.lasarobotics.frc2017.hardware.Hardware;
import org.lasarobotics.lib.datalogging.Loggable;

public class Intake extends HazySubsystem implements Loggable {

    private static Intake instance;

    private double intakeSpeed;
    private boolean intakeDown;
    private double conveyorSpeed;

    private Mode mode;

    private Intake() {
        setMode(Mode.OFF);
    }

    @Override
    public String getNames() {
        return "intakeDown, intakeSpeed, conveyerSpeed";
    }

    @Override
    public String getValues() {
        return intakeDown + "," + intakeSpeed + ", " + conveyorSpeed;
    }

    public static enum Mode {
        OFF, INTAKING, OUTTAKING, FEEDING
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
                    conveyorSpeed = 0.0;
                    intakeDown = false;
                    break;

                case INTAKING:
                    hardware.setIntakeMode(Hardware.IntakeMode.intaking);
                    intakeDown = true;
                    conveyorSpeed = 0.0;
                    intakeSpeed = ConstantsList.I_intake_current.getValue();
                    break;

                case OUTTAKING:
                    hardware.setIntakeMode(Hardware.IntakeMode.intaking);
                    intakeDown = false;
                    conveyorSpeed = 0.0;
                    intakeSpeed = ConstantsList.I_outtake_current.getValue();
                    break;

                case FEEDING:
                    hardware.setIntakeMode(Hardware.IntakeMode.shooting);
                    intakeDown = false;
                    conveyorSpeed = ConstantsList.I_conveyor_speed.getValue();
                    intakeSpeed = ConstantsList.I_shooting_voltage.getValue();
                    break;
            }
        }
        hardware.setConveyorRollerSpeed(conveyorSpeed);
        hardware.setIntakeSolenoid(intakeDown);
        hardware.setIntakeOutput(intakeSpeed);
    }

    @Override
    public void initSubsystem() {
    }

    @Override
    public void pushToDashboard() {
        Hardware.putDash("I_mode", mode.toString());
        Hardware.putDash("I_down", intakeDown);
        Hardware.putDash("I_conveyor_speed", conveyorSpeed);

    }

}
