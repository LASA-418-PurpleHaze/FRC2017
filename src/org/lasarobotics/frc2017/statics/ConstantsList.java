package org.lasarobotics.frc2017.statics;

public class ConstantsList {

    public static final Constants.Constant D_pviff_kP = new Constants.Constant("D_kP", 0.0);
    public static final Constants.Constant D_pviff_kI = new Constants.Constant("D_kI", 0.0);
    public static final Constants.Constant D_pviff_kV = new Constants.Constant("D_kV", 0.0);
    public static final Constants.Constant D_pviff_kFFV = new Constants.Constant("D_kFFV", 0.0);
    public static final Constants.Constant D_pviff_kFFA = new Constants.Constant("D_kFFA", 0.0);
    public static final Constants.Constant D_tmp_maxV = new Constants.Constant("D_maxV", 0.0);
    public static final Constants.Constant D_tmp_maxA = new Constants.Constant("D_maxA", 0.0);
    public static final Constants.Constant D_drive_sense = new Constants.Constant("D_drive_sensitivity", 0.0);
    public static final Constants.Constant I_intake_speed = new Constants.Constant("I_intake_speed", 0.0);
    public static final Constants.Constant J_deadband = new Constants.Constant("deadband", 0);
            

    public static void init() {
        Constants.init();
        Constants.load();
    }
}
