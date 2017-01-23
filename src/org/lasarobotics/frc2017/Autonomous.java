package org.lasarobotics.frc2017;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.lasarobotics.frc2017.command.CloseShot;
import org.lasarobotics.frc2017.command.CommandGroup;
import org.lasarobotics.frc2017.command.CommandManager;
import org.lasarobotics.frc2017.command.FarShot;
import org.lasarobotics.frc2017.command.SetHoodPosition;
import org.lasarobotics.frc2017.statics.ConstantsList;

class Autonomous implements Runnable {

    public final int DO_NOTHING = 0;
    public final int CLOSE_SHOT = 1;
    public final int FAR_SHOT = 2;
    public final int PLACE_GEAR = 3;
    public final int PLACE_GEAR_THEN_FAR_SHOT = 4;
    public final int PLACE_GEAR_THEN_CLOSE_SHOT = 5;
    public final int DRIVE_TO_HOPPER = 6;
    public final int FAR_SHOT_AND_DRIVE_TO_HOPPER = 7;
    public final int CLOSE_SHOT_AND_DRIVE_TO_HOPPER = 8;

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
            case CLOSE_SHOT:
                //start out in close position
                prepCloseShot();
                closeShot();
                break;
            case FAR_SHOT:
                //start in far position
                prepFarShot();
                farShot();
                break;
            case PLACE_GEAR:
                //drive & turn some
                //wait while human player lifts gear
                break;
            case PLACE_GEAR_THEN_FAR_SHOT:
                //drive & turn some
                //place the gear
                //drive & turn some more
                prepFarShot();
                farShot();
                break;
            case PLACE_GEAR_THEN_CLOSE_SHOT:
                //drive & turn some
                //wait while human player lifts gear
                //drive & turn some more
                prepCloseShot();
                closeShot();
                break;
            case DRIVE_TO_HOPPER:
                //drive & turn some
                break;
            case CLOSE_SHOT_AND_DRIVE_TO_HOPPER:
                //drive & turn some
                prepCloseShot();
                closeShot();
                //drive & turn some more
                break;
            case FAR_SHOT_AND_DRIVE_TO_HOPPER:
                //drive & turn some
                prepFarShot();
                farShot();
                //drive and turn some more
                break;
        }

    }

    private void prepCloseShot() {
        CommandManager.addCommand(new SetHoodPosition("Close Shot Hood Prep", 1.0, ConstantsList.S_close_angle.getValue()));
    }

    private void closeShot() {   
        for(int i=0; i<ConstantsList.S_num_shots.getValue(); i++){
            CommandManager.addCommand(new CloseShot("Close Shot", 1.0));
        }
    }
    
    private void prepFarShot() {
        CommandManager.addCommand(new SetHoodPosition("Far Shot Hood Prep", 1.0, ConstantsList.S_far_angle.getValue()));
    }

    private void farShot() {
        for(int i=0; i<ConstantsList.S_num_shots.getValue(); i++){
            CommandManager.addCommand(new FarShot("Far Shot", 1.0));
        }
        
    }

    public void start() {
        SmartDashboard.putNumber("AutoMode", DO_NOTHING);
    }

}
