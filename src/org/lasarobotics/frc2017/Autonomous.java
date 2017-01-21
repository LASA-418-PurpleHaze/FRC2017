package org.lasarobotics.frc2017;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.lasarobotics.frc2017.command.CloseShot;
import org.lasarobotics.frc2017.command.CommandManager;
import org.lasarobotics.frc2017.command.SetHoodPosition;
import static org.lasarobotics.frc2017.statics.Constants.ConstantsList;

class Autonomous implements Runnable {

    public final int DO_NOTHING = 0;
    public final int SINGLE_CLOSE_SHOT = 1;
    public final int CONSTANT_CLOSE_SHOT = 2;
    public final int SINGLE_FAR_SHOT = 3;
    public final int CONSTANT_FAR_SHOT = 4;
    public final int PLACE_GEAR = 5;
    public final int PLACE_GEAR_THEN_FAR_SHOT = 6;
    public final int PLACE_GEAR_THEN_CLOSE_SHOT = 7;
    public final int DRIVER_TO_HOPPER = 8;
    public final int FAR_SHOT_AND_DRIVE_TO_HOPPER = 9;
    public final int CLOSE_SHOT_AND_DRIVE_TO_HOPPER = 10;

    private int mode = DO_NOTHING;

    private Autonomous() {
    }

    private static Autonomous instance;

    public static Autonomous getInstance() {
        return (instance == null) ? instance = new Autonomous() : instance;
    }

    @Override
    public void run() {
        mode = (int) SmartDashboard.getNumber("AutoMode", DO_NOTHING);

        switch (mode) {
            case DO_NOTHING:
                break;
            case SINGLE_CLOSE_SHOT:
                prepCloseShot();
                closeShot();
                break;
            case CONSTANT_CLOSE_SHOT:
                prepCloseShot();
                closeShot();
                break;
        }

    }

    private void prepCloseShot() {
        CommandManager.addCommand(new SetHoodPosition("Close Shot Hood Prep", 1.0, ConstantsList.S_far_angle.getValue()));
    }

    private void closeShot() {
        CommandManager.addCommand(new CloseShot("Close Shot", 1.0));
    }

    public void start() {
        SmartDashboard.putNumber("AutoMode", DO_NOTHING);
    }

}
