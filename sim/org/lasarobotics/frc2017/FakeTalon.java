package org.lasarobotics.frc2017;

//import com.ctre.CANTalon.MotionProfileStatus;
//import com.ctre.CANTalon.TrajectoryPoint;
//import edu.wpi.first.wpilibj.PIDSourceType;

//What Z-Money just realized : The modes are actually important and need to be implemented bc depending on the mode, set() does different things

public class FakeTalon {
    
    int portNumber;
    double target;
    
    double p;
    double i;
    double d;
    double f;
    
    public FakeTalon(int deviceNumber) {
        portNumber = deviceNumber;
    }

    public void set(double outputValue) {
        target = outputValue;
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
        
    }

    public void setPID(double p, double i, double d) {
        
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
