package org.lasarobotics.frc2017.statics;

public class ConstantsList{

    public Constants.Constant kP, kI, kV, kFFV, kFFA;
    public Constants.Constant maxV, maxA;
    public Constants.Constant drive_sensitivity;
    
    public ConstantsList(){
        
        kP = new Constants.Constant("kP", 0.0);
        kI = new Constants.Constant("kI", 0.0);
        kV = new Constants.Constant("kV", 0.0);
        kFFV = new Constants.Constant("kFFV", 0.0);
        kFFA = new Constants.Constant("kFFA", 0.0);
        maxV = new Constants.Constant("maxV", 0.0);
        maxA = new Constants.Constant("maxA", 0.0);
        drive_sensitivity = new Constants.Constant("drive_sensitivity", 0.0);
        
        Constants.init();
        Constants.load();
    }
    
    public static double getConstant(String constant){
        double val = 0;
        for(Constants.Constant c : Constants.ConstantsList){
            if (c.getName().equals(constant)){
                val = c.getValue();
            }
        }
        return val;
    }
}
