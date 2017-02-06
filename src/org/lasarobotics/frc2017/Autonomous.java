package org.lasarobotics.frc2017;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.lasarobotics.frc2017.command.Shoot;
import org.lasarobotics.frc2017.command.CommandManager;

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
                closeShot();
                break;
            case FAR_SHOT:
                //start in far position
                break;
            case PLACE_GEAR:
                //drive & turn some
                //wait while human player lifts gear
                break;
            case PLACE_GEAR_THEN_FAR_SHOT:
                //drive & turn some
                //place the gear
                //drive & turn some more
                break;
            case PLACE_GEAR_THEN_CLOSE_SHOT:
                //drive & turn some
                //wait while human player lifts gear
                //drive & turn some more
                closeShot();
                break;
            case DRIVE_TO_HOPPER:
                //drive & turn some
                break;
            case CLOSE_SHOT_AND_DRIVE_TO_HOPPER:
                //drive & turn some
                closeShot();
                //drive & turn some more
                break;
            case FAR_SHOT_AND_DRIVE_TO_HOPPER:
                //drive & turn some
                //drive and turn some more
                break;
        }

    }

    private void closeShot() {   
        for(int i=0; i<ConstantsList.S_num_shots.getValue(); i++){
            CommandManager.addCommand(new Shoot("Close Shot", 1.0));
        }
    }
    

    public void start() {
        SmartDashboard.putNumber("AutoMode", DO_NOTHING);
    }

}
