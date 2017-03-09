package org.lasarobotics.frc2017;

import org.lasarobotics.lib.Constants;
import org.lasarobotics.lib.Constants.Constant;

public class ConstantsList {

    //leftPVIff
    public static final Constant D_left_kP = new Constant("D_l_kP", 0.0);
    public static final Constant D_left_kI = new Constant("D_l_kI", 0.0);
    public static final Constant D_left_kV = new Constant("D_l_kV", 0.0);
    public static final Constant D_left_kFFV = new Constant("D_l_kFFV", 0.0);
    public static final Constant D_left_kFFA = new Constant("D_l_kFFA", 0.0);
    public static final Constant D_left_doneBound = new Constant("D_l_doneBound", 0.0);
    public static final Constant D_left_maxU = new Constant("D_l_maxU", 0.0);
    
    //rightPVIff
    public static final Constant D_right_kP = new Constant("D_r_kP", 0.0);
    public static final Constant D_right_kI = new Constant("D_r_kI", 0.0);
    public static final Constant D_right_kV = new Constant("D_r_kV", 0.0);
    public static final Constant D_right_kFFV = new Constant("D_r_kFFV", 0.0);
    public static final Constant D_right_kFFA = new Constant("D_r_kFFA", 0.0);
    public static final Constant D_right_doneBound = new Constant("D_r_doneBound", 0.0);
    public static final Constant D_right_maxU = new Constant("D_r_maxU", 0.0);

    //turnPID
    public static final Constant D_turn_kP = new Constant("D_t_kP", 0.0);
    public static final Constant D_turn_kI = new Constant("D_t_kI", 0.0);
    public static final Constant D_turn_kD = new Constant("D_t_kD", 0.0);
    public static final Constant D_turn_kFF = new Constant("D_t_kFF", 0.0);
    public static final Constant D_turn_doneBound = new Constant("D_t_doneBound", 0.0);
    public static final Constant D_done_cycles = new Constant("D_done_cycles", 50);
    
    //shooterPID
    public static final Constant S_kP = new Constant("S_kP", 0.0);
    public static final Constant S_kI = new Constant("S_kI", 0.0);
    public static final Constant S_kD = new Constant("S_kD", 0.0);
    public static final Constant S_kFF = new Constant("S_kFF", 0.0);
    public static final Constant S_doneBound = new Constant("S_doneBound", 0);
    public static final Constant S_RPM = new Constant("S_RPM", 0);
    public static final Constant S_loading_speed = new Constant("S_loading_speed", 0);

    //TMP
    public static final Constant D_tmp_maxV = new Constant("D_maxV", 0.0);
    public static final Constant D_tmp_maxA = new Constant("D_maxA", 0.0);

    public static final Constant D_drive_sensitivity = new Constant("D_drive_sensitivity", 0.0);

    public static final Constant J_deadband = new Constant("deadband", 0);
    public static final Constant S_close_angle = new Constant("S_close_angle", 0.0);
    public static final Constant S_far_angle = new Constant("S_far_angle", 0.0);
    public static final Constant S_num_shots = new Constant("S_num_shots", 0.0);

    //Intake
    public static final Constant I_intake_current = new Constant("I_intake_current", 0.0);
    public static final Constant I_outtake_current = new Constant("I_outtake_current", 0.0);
    public static final Constant I_shooting_voltage = new Constant("I_shooting_voltage", 12);
    public static final Constant I_conveyor_speed = new Constant("I_conveyor_speed", 0.0);

    public static final Constant C_climber_speed = new Constant("C_climber_speed", 1.0);
    public static final Constant C_climber_max_current = new Constant("C_climber_max_current", 0.0);
    
    public static final Constant A_long_gear_distance = new Constant("A_long_gear_distance", 0.0);
    public static final Constant A_short_gear_distance = new Constant("A_short_gear_distance", 0.0);
    public static final Constant A_center_gear_distance = new Constant("A_center_gear_distance", 0.0);
    public static final Constant A_gear_angle = new Constant("A_gear_angle", 0.0);
    public static final Constant A_long_gear_timeout = new Constant("A_long_gear_timeout", 0.0);
    public static final Constant A_short_gear_timeout = new Constant("A_short_gear_timeout", 0.0);
    public static final Constant A_center_gear_timeout = new Constant("A_center_gear_timeout", 0.0);
    public static final Constant A_gear_angle_timeout = new Constant("A_gear_angle_timeout", 0.0);
    public static final Constant A_wait = new Constant("A_wait", 0.0);
    
    public static void init() {
        init();
        Constants.load();
    }
}
