package org.lasarobotics.frc2017;

import com.ctre.CANTalon.MotionProfileStatus;
import com.ctre.CANTalon.TrajectoryPoint;
import edu.wpi.first.wpilibj.PIDSourceType;

public class FakeTalon {
    public FakeTalon(int deviceNumber) {

    }

    public FakeTalon(int deviceNumber, int controlPeriodMs) {
        
    }

    public FakeTalon(int deviceNumber, int controlPeriodMs, int enablePeriodMs) {
        
    }

    public void set(double outputValue) {
        
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
        return 0;
    }

    public double getI() {
        return 0;
    }

    public double getD() {
        return 0;
    }

    public double getF() {
        return 0;
    }

    public void setP(double p) {
        
    }

    public void setI(double i) {
        
    }

    public void setD(double d) {
        
    }

    public void setF(double f) {
        
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

    public boolean pushMotionProfileTrajectory(TrajectoryPoint trajPt) {
        return false;
    }

    public boolean isMotionProfileTopLevelBufferFull() {
        return false;
    }

    public void processMotionProfileBuffer() {
        
    }

    public void getMotionProfileStatus(MotionProfileStatus motionProfileStatus) {
        
    }

    protected void setMotionProfileStatusFromJNI(MotionProfileStatus motionProfileStatus, int flags, int profileSlotSelect, int targPos, int targVel, int topBufferRem, int topBufferCnt, int btmBufferCnt, int outputEnable) {
        
    }

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
