package org.lasarobotics.frc2017;

//import com.ctre.CANTalon.MotionProfileStatus;

import edu.wpi.first.wpilibj.CANSpeedController.ControlMode;
import java.util.TimerTask;

//import com.ctre.CANTalon.TrajectoryPoint;
//import edu.wpi.first.wpilibj.PIDSourceType;

//What Z-Money just realized : The modes are actually important and need to be implemented bc depending on the mode, set() does different things

public class FakeTalon {
    
    private int portNumber;
    private double target;
    private double current;
    
    private double p;
    private double i;
    private double d;
    private double f;
    private int profile;
    private int izone;
    private double closeLoopRampRate;
    
    private double position;
    private double velocity;
    
    private boolean notFirst = false;
    
    
    private TalonControlMode m;
    
    
    public enum TalonControlMode /*implements ControlMode*/ {

        PercentVbus, Position, Speed, Current, Voltage, Follower, MotionProfile, MotionMagic, Disabled;
        public int value;

        //@Override
        public boolean isPID() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        //@Override
        public int getValue() {
            return this.value;
        }

        public static TalonControlMode valueOf(int value) {
            return TalonControlMode.values()[value];
        }

        private TalonControlMode() {
        }
        
        public void setControlMode(int value){
            this.value = value;
        }
    }
    
    
    public FakeTalon(int deviceNumber) {
        portNumber = deviceNumber;
    }

    public void set(double outputValue) {
        //based on mode, should set outputValue to target and do a closed control loop based on that target
    }
    
    public class closeLoopControl extends TimerTask{
        /*Alright so this is the big money right here. Ultimately, AFAIK, this should be able
        to take some input that I put in, and based on the mode the motor controller is in,
        get there. The problem, I have no idea what I'm doing. */
        
        int outputValue;
        
        public void setOutputValue(){
            this.outputValue = outputValue;
        }

        @Override
        public void run() {
        target = outputValue;
        double error = target - current;
        double absError = error;
        if(error < 0){
            absError = -error;
        }        }
        
        
    }

    public void setInverted(boolean isInverted) {

    }

    public boolean getInverted() {
        return false;
    }

    public void reset() {
        
    }

    public boolean isEnabled() {
        return false;
    }

    public void setSetpoint(double setpoint) {
        
    }

    public void reverseSensor(boolean flip) {
        
    }

    public void reverseOutput(boolean flip) {
        
    }

    public int getPulseWidthPosition() {
        return 0;
    }

    public void setPulseWidthPosition(int newPosition) {
        
    }

    public int getPulseWidthVelocity() {
        return 0;
    }

    public int getPulseWidthRiseToFallUs() {
        return 0;
    }

    public int getPulseWidthRiseToRiseUs() {
        return 0;
    }

    public void setAnalogPosition(int newPosition) {
        
    }

    public int getAnalogInPosition() {
        return 0;
    }

    public int getAnalogInRaw() {
        return 0;
    }

    public int getAnalogInVelocity() {
        return 0;
    }

    public double getTemperature() {
        return 418;
    }

    public double getOutputCurrent() {
        return 0;
    }

    public double getOutputVoltage() {
        return 0;
    }

    public double getBusVoltage() {
        return 0;
    }

    public double getPosition() {
        return 0;
    }

    public void setPosition(double pos) {
        
    }

    public double getSpeed() {
        return 0;
    }


    public void setControlMode(int mode) {

    }

    public void enableControl() {
        
    }

    public void enable() {
        
    }

    public double getP() {
        return p;
    }

    public double getI() {
        return i;
    }

    public double getD() {
        return d;
    }

    public double getF() {
        return f;
    }

    public void setP(double p) {
        this.p = p;
    }

    public void setI(double i) {
        this.i = i;
    }

    public void setD(double d) {
        this.d = d;
    }

    public void setF(double f) {
        this.f = f;
    }

    public void setPID(double p, double i, double d, double f, int izone, double closeLoopRampRate, int profile) {
        this.p = p;
        this.i = i;
        this.d = d;
        this.f = f;
        this.izone = izone;
        this.closeLoopRampRate = closeLoopRampRate;
        this.profile = profile;
    }

    public void setPID(double p, double i, double d) {
        this.p = p;
        this.i = i;
        this.d = d;
    }

    public double getSetpoint() {
        return 0;
    }

    public void setProfile(int profile) {
        
    }


    public void configMaxOutputVoltage(double voltage) {
        
    }

    public void configPeakOutputVoltage(double forwardVoltage, double reverseVoltage) {
        
    }

    public void configNominalOutputVoltage(double forwardVoltage, double reverseVoltage) {
        
    }
    
    public void changeMotionControlFramePeriod(int periodMs) {
        
    }

    public void clearMotionProfileTrajectories() {
        
    }

    public int getMotionProfileTopLevelBufferCount() {
        return 0;
    }

//    public boolean pushMotionProfileTrajectory(TrajectoryPoint trajPt) {
//        return false;
//    }

    public boolean isMotionProfileTopLevelBufferFull() {
        return false;
    }

    public void processMotionProfileBuffer() {
        
    }

//    public void getMotionProfileStatus(MotionProfileStatus motionProfileStatus) {
//        
//    }

//    protected void setMotionProfileStatusFromJNI(MotionProfileStatus motionProfileStatus, int flags, int profileSlotSelect, int targPos, int targVel, int topBufferRem, int topBufferCnt, int btmBufferCnt, int outputEnable) {
//        
//    }

    public void clearMotionProfileHasUnderrun() {
        
    }

    public void setMotionMagicCruiseVelocity(double motMagicCruiseVeloc) {
        
    }

    public void setMotionMagicAcceleration(double motMagicAccel) {
        
    }

    public double getMotionMagicCruiseVelocity() {
        return 0;
    }

    public double getMotionMagicAcceleration() {
        return 0;
    }

    public double getMotionMagicActTrajVelocity() {
        return 0;
    }

    public double getMotionMagicActTrajPosition() {
        return 0;
    }

    public void setCurrentLimit(int amps) {
        
    }

    public void EnableCurrentLimit(boolean enable) {
        
    }

    public boolean isAlive() {
        return false;
    }
}
