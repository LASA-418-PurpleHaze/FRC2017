package org.lasarobotics.frc2017;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.lasarobotics.frc2017.command.Shoot;
import org.lasarobotics.frc2017.command.CommandManager;
import org.lasarobotics.frc2017.command.DriveDistance;
import org.lasarobotics.frc2017.command.DriveTurn;
import org.lasarobotics.frc2017.command.EndAutoCommand;
import org.lasarobotics.frc2017.command.StartAutoCommand;
import org.lasarobotics.frc2017.command.WaitCommand;

class Autonomous implements Runnable {

    public final int DO_NOTHING = 0;
    public final int STRAIGHT_GEAR = 1;
    public final int TURN_RIGHT_GEAR = 2;
    public final int TURN_LEFT_GEAR = 3;
    public final int TEST = 100;

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

        CommandManager.addCommand(new StartAutoCommand());

        switch (mode) {
            case DO_NOTHING:
                break;
            case STRAIGHT_GEAR:
                CommandManager.addCommand(new DriveDistance("Drive Center Gear Distance", ConstantsList.A_center_gear_timeout.getValue(),
                        ConstantsList.A_center_gear_distance.getValue()));
                break;
            case TURN_RIGHT_GEAR:
                CommandManager.addCommand(new DriveDistance("Drive Long Gear Distance", ConstantsList.A_long_gear_timeout.getValue(),
                        ConstantsList.A_long_gear_distance.getValue()));
                CommandManager.addCommand(new WaitCommand("wait a second", ConstantsList.A_wait.getValue()));
                CommandManager.addCommand(new DriveTurn("Drive Turn Gear (Right)", ConstantsList.A_gear_angle_timeout.getValue(),
                        ConstantsList.A_gear_angle.getValue()));
                CommandManager.addCommand(new WaitCommand("wait a second", ConstantsList.A_wait.getValue()));
                CommandManager.addCommand(new DriveDistance("Drive Short Gear Distance", ConstantsList.A_short_gear_timeout.getValue(),
                        ConstantsList.A_short_gear_distance.getValue()));
                break;
            case TURN_LEFT_GEAR:
                CommandManager.addCommand(new DriveDistance("Drive Long Gear Distance", ConstantsList.A_long_gear_timeout.getValue(),
                        ConstantsList.A_long_gear_distance.getValue()));
                CommandManager.addCommand(new WaitCommand("wait a second", ConstantsList.A_wait.getValue()));
                CommandManager.addCommand(new DriveTurn("Drive Turn Gear (Left)", ConstantsList.A_gear_angle_timeout.getValue(),
                        -ConstantsList.A_gear_angle.getValue()));
                CommandManager.addCommand(new WaitCommand("wait a second", ConstantsList.A_wait.getValue()));
                CommandManager.addCommand(new DriveDistance("Drive Short Gear Distance", ConstantsList.A_short_gear_timeout.getValue(),
                        ConstantsList.A_short_gear_distance.getValue()));
                break;
            case TEST:
                //CommandManager.addCommand(new DriveTurn("Drive Turn Gear (Left)", ConstantsList.A_gear_angle_timeout.getValue(), 
                //        -ConstantsList.A_gear_angle.getValue()));
                CommandManager.addCommand(new DriveDistance("Drive test", 10, 96.0));
                break;
        }

        CommandManager.addCommand(new EndAutoCommand());
    }

    private void closeShot() {
        for (int i = 0; i < ConstantsList.S_num_shots.getValue(); i++) {
            CommandManager.addCommand(new Shoot("Close Shot", 1.0));
        }
    }

    public void start() {
        SmartDashboard.putNumber("Auto Mode", mode);
    }

}
