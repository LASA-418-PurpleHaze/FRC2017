package org.lasarobotics.frc2017.statics;

public class ConstantsList {

    public static final Constants.Constant kP = new Constants.Constant("kP", 0.0);
    public static final Constants.Constant kI = new Constants.Constant("kI", 0.0);
    public static final Constants.Constant kV = new Constants.Constant("kV", 0.0);
    public static final Constants.Constant kFFV = new Constants.Constant("kFFV", 0.0);
    public static final Constants.Constant kFFA = new Constants.Constant("kFFA", 0.0);
    public static final Constants.Constant maxV = new Constants.Constant("maxV", 0.0);
    public static final Constants.Constant maxA = new Constants.Constant("maxA", 0.0);
    public static final Constants.Constant drive_sense = new Constants.Constant("drive_sensitivity", 0.0);
    public static final Constants.Constant intake_speed = new Constants.Constant("intake_speed", 0.0);

    public static void getConstant() {
        Constants.init();
        Constants.load();
    }
}
