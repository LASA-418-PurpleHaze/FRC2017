package org.lasarobotics.frc2017;

import org.lasarobotics.lib.Constants;

public class ConstantsList {

    //leftPVIff
    public static final Constants.Constant D_left_kP = new Constants.Constant("D_l_kP", 0.0);
    public static final Constants.Constant D_left_kI = new Constants.Constant("D_l_kI", 0.0);
    public static final Constants.Constant D_left_kV = new Constants.Constant("D_l_kV", 0.0);
    public static final Constants.Constant D_left_kFFV = new Constants.Constant("D_l_kFFV", 0.0);
    public static final Constants.Constant D_left_kFFA = new Constants.Constant("D_l_kFFA", 0.0);
    public static final Constants.Constant D_left_doneBound = new Constants.Constant("D_l_doneBound", 0.0);
    //rightPVIff
    public static final Constants.Constant D_right_kP = new Constants.Constant("D_r_kP", 0.0);
    public static final Constants.Constant D_right_kI = new Constants.Constant("D_r_kI", 0.0);
    public static final Constants.Constant D_right_kV = new Constants.Constant("D_r_kV", 0.0);
    public static final Constants.Constant D_right_kFFV = new Constants.Constant("D_r_kFFV", 0.0);
    public static final Constants.Constant D_right_kFFA = new Constants.Constant("D_r_kFFA", 0.0);
    public static final Constants.Constant D_right_doneBound = new Constants.Constant("D_r_doneBound", 0.0);
    //turnPID
    public static final Constants.Constant D_turn_kP = new Constants.Constant("D_t_kP", 0.0);
    public static final Constants.Constant D_turn_kI = new Constants.Constant("D_t_kI", 0.0);
    public static final Constants.Constant D_turn_kD = new Constants.Constant("D_t_kD", 0.0);
    public static final Constants.Constant D_turn_kFF = new Constants.Constant("D_t_kFF", 0.0);
    public static final Constants.Constant D_turn_doneBound = new Constants.Constant("D_t_doneBound", 0);

    //TMP
    public static final Constants.Constant D_tmp_maxV = new Constants.Constant("D_maxV", 0.0);
    public static final Constants.Constant D_tmp_maxA = new Constants.Constant("D_maxA", 0.0);

    public static final Constants.Constant D_drive_sensitivity = new Constants.Constant("D_drive_sensitivity", 0.0);
    public static final Constants.Constant I_intake_speed = new Constants.Constant("I_intake_speed", 0.0);
    public static final Constants.Constant J_deadband = new Constants.Constant("deadband", 0);
    public static final Constants.Constant S_close_angle = new Constants.Constant("S_close_angle", 0.0);
    public static final Constants.Constant S_far_angle = new Constants.Constant("S_far_angle", 0.0);
    public static final Constants.Constant S_num_shots = new Constants.Constant("S_num_shots", 0.0);
    

    public static void init() {
        Constants.init();
        Constants.load();
    }
}
