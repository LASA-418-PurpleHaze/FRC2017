package org.lasarobotics.frc2017;

import edu.wpi.first.wpilibj.PIDSourceType;

public class FakeTalon {
   public FakeTalon(int deviceNumber) {

    }

    public FakeTalon(int deviceNumber, int controlPeriodMs) {
        
    }

    public FakeTalon(int deviceNumber, int controlPeriodMs, int enablePeriodMs) {
        
    }

    public void pidWrite(double output) {

    }

    public void setPIDSourceType(PIDSourceType pidSource) {
        
    }

    public PIDSourceType getPIDSourceType() {
       PIDSourceType PIDSourceType = null;
        return PIDSourceType;
    }

    public void delete() {
        
    }

    public void set(double outputValue) {
        
    }

    public void setInverted(boolean isInverted) {

    }

    public boolean getInverted() {
        // <editor-fold defaultstate="collapsed" desc="Compiled Code">
        /* 0: aload_0
         * 1: getfield      com/ctre/CANTalon.f:Z
         * 4: ireturn
         *  */
        // </editor-fold>
    }

    public void reset() {
        // <editor-fold defaultstate="collapsed" desc="Compiled Code">
        /* 0: aload_0
         * 1: invokevirtual com/ctre/CANTalon.disable:()V
         * 4: aload_0
         * 5: invokevirtual com/ctre/CANTalon.clearIAccum:()V
         * 8: return
         *  */
        // </editor-fold>
    }

    public boolean isEnabled() {
        // <editor-fold defaultstate="collapsed" desc="Compiled Code">
        /* 0: aload_0
         * 1: invokevirtual com/ctre/CANTalon.isControlEnabled:()Z
         * 4: ireturn
         *  */
        // </editor-fold>
    }

    public void setSetpoint(double setpoint) {
        // <editor-fold defaultstate="collapsed" desc="Compiled Code">
        /* 0: aload_0
         * 1: dload_1
         * 2: invokevirtual com/ctre/CANTalon.set:(D)V
         * 5: return
         *  */
        // </editor-fold>
    }

    public void reverseSensor(boolean flip) {
        // <editor-fold defaultstate="collapsed" desc="Compiled Code">
        /* 0: aload_0
         * 1: getfield      com/ctre/CANTalon.j:J
         * 4: iload_1
         * 5: ifeq          12
         * 8: iconst_1
         * 9: goto          13
         * 12: iconst_0
         * 13: invokestatic  com/ctre/CanTalonJNI.SetRevFeedbackSensor:(JI)V
         * 16: return
         *  */
        // </editor-fold>
    }

    public void reverseOutput(boolean flip) {
        // <editor-fold defaultstate="collapsed" desc="Compiled Code">
        /* 0: aload_0
         * 1: getfield      com/ctre/CANTalon.j:J
         * 4: iload_1
         * 5: ifeq          12
         * 8: iconst_1
         * 9: goto          13
         * 12: iconst_0
         * 13: invokestatic  com/ctre/CanTalonJNI.SetRevMotDuringCloseLoopEn:(JI)V
         * 16: return
         *  */
        // </editor-fold>
    }

    public int getPulseWidthPosition() {
        // <editor-fold defaultstate="collapsed" desc="Compiled Code">
        /* 0: aload_0
         * 1: getfield      com/ctre/CANTalon.j:J
         * 4: invokestatic  com/ctre/CanTalonJNI.GetPulseWidthPosition:(J)I
         * 7: ireturn
         *  */
        // </editor-fold>
    }

    public void setPulseWidthPosition(int newPosition) {
        // <editor-fold defaultstate="collapsed" desc="Compiled Code">
        /* 0: aload_0
         * 1: getstatic     com/ctre/CanTalonJNI$param_t.aE:Lcom/ctre/CanTalonJNI$param_t;
         * 4: iload_1
         * 5: i2d
         * 6: invokevirtual com/ctre/CANTalon.setParameter:(Lcom/ctre/CanTalonJNI$param_t;D)V
         * 9: return
         *  */
        // </editor-fold>
    }

    public int getPulseWidthVelocity() {
        // <editor-fold defaultstate="collapsed" desc="Compiled Code">
        /* 0: aload_0
         * 1: getfield      com/ctre/CANTalon.j:J
         * 4: invokestatic  com/ctre/CanTalonJNI.GetPulseWidthVelocity:(J)I
         * 7: ireturn
         *  */
        // </editor-fold>
    }

    public int getPulseWidthRiseToFallUs() {
        // <editor-fold defaultstate="collapsed" desc="Compiled Code">
        /* 0: aload_0
         * 1: getfield      com/ctre/CANTalon.j:J
         * 4: invokestatic  com/ctre/CanTalonJNI.GetPulseWidthRiseToFallUs:(J)I
         * 7: ireturn
         *  */
        // </editor-fold>
    }

    public int getPulseWidthRiseToRiseUs() {
        // <editor-fold defaultstate="collapsed" desc="Compiled Code">
        /* 0: aload_0
         * 1: getfield      com/ctre/CANTalon.j:J
         * 4: invokestatic  com/ctre/CanTalonJNI.GetPulseWidthRiseToRiseUs:(J)I
         * 7: ireturn
         *  */
        // </editor-fold>
    }

    public void setAnalogPosition(int newPosition) {
        // <editor-fold defaultstate="collapsed" desc="Compiled Code">
        /* 0: aload_0
         * 1: getstatic     com/ctre/CanTalonJNI$param_t.aF:Lcom/ctre/CanTalonJNI$param_t;
         * 4: iload_1
         * 5: i2d
         * 6: invokevirtual com/ctre/CANTalon.setParameter:(Lcom/ctre/CanTalonJNI$param_t;D)V
         * 9: return
         *  */
        // </editor-fold>
    }

    public int getAnalogInPosition() {
        // <editor-fold defaultstate="collapsed" desc="Compiled Code">
        /* 0: aload_0
         * 1: getfield      com/ctre/CANTalon.j:J
         * 4: invokestatic  com/ctre/CanTalonJNI.GetAnalogInWithOv:(J)I
         * 7: ireturn
         *  */
        // </editor-fold>
    }

    public int getAnalogInRaw() {
        // <editor-fold defaultstate="collapsed" desc="Compiled Code">
        /* 0: aload_0
         * 1: invokevirtual com/ctre/CANTalon.getAnalogInPosition:()I
         * 4: sipush        1023
         * 7: iand
         * 8: ireturn
         *  */
        // </editor-fold>
    }

    public int getAnalogInVelocity() {
        // <editor-fold defaultstate="collapsed" desc="Compiled Code">
        /* 0: aload_0
         * 1: getfield      com/ctre/CANTalon.j:J
         * 4: invokestatic  com/ctre/CanTalonJNI.GetAnalogInVel:(J)I
         * 7: ireturn
         *  */
        // </editor-fold>
    }

    public double getTemperature() {
        // <editor-fold defaultstate="collapsed" desc="Compiled Code">
        /* 0: aload_0
         * 1: getfield      com/ctre/CANTalon.j:J
         * 4: invokestatic  com/ctre/CanTalonJNI.GetTemp:(J)D
         * 7: dreturn
         *  */
        // </editor-fold>
    }

    public double getOutputCurrent() {
        // <editor-fold defaultstate="collapsed" desc="Compiled Code">
        /* 0: aload_0
         * 1: getfield      com/ctre/CANTalon.j:J
         * 4: invokestatic  com/ctre/CanTalonJNI.GetCurrent:(J)D
         * 7: dreturn
         *  */
        // </editor-fold>
    }

    public double getOutputVoltage() {
        // <editor-fold defaultstate="collapsed" desc="Compiled Code">
        /* 0: aload_0
         * 1: invokevirtual com/ctre/CANTalon.getBusVoltage:()D
         * 4: aload_0
         * 5: getfield      com/ctre/CANTalon.j:J
         * 8: invokestatic  com/ctre/CanTalonJNI.GetAppliedThrottle:(J)I
         * 11: i2d
         * 12: dmul
         * 13: ldc2_w        1023.0d
         * 16: ddiv
         * 17: dreturn
         *  */
        // </editor-fold>
    }

    public double getBusVoltage() {
        // <editor-fold defaultstate="collapsed" desc="Compiled Code">
        /* 0: aload_0
         * 1: getfield      com/ctre/CANTalon.j:J
         * 4: invokestatic  com/ctre/CanTalonJNI.GetBatteryV:(J)D
         * 7: dreturn
         *  */
        // </editor-fold>
    }

    public double getPosition() {
        // <editor-fold defaultstate="collapsed" desc="Compiled Code">
        /* 0: aload_0
         * 1: aload_0
         * 2: getfield      com/ctre/CANTalon.d:Lcom/ctre/CANTalon$FeedbackDevice;
         * 5: aload_0
         * 6: getfield      com/ctre/CANTalon.j:J
         * 9: invokestatic  com/ctre/CanTalonJNI.GetSensorPosition:(J)I
         * 12: invokespecial com/ctre/CANTalon.a:(Lcom/ctre/CANTalon$FeedbackDevice;I)D
         * 15: dreturn
         *  */
        // </editor-fold>
    }

    public void setPosition(double pos) {
        // <editor-fold defaultstate="collapsed" desc="Compiled Code">
        /* 0: aload_0
         * 1: aload_0
         * 2: getfield      com/ctre/CANTalon.d:Lcom/ctre/CANTalon$FeedbackDevice;
         * 5: dload_1
         * 6: invokespecial com/ctre/CANTalon.a:(Lcom/ctre/CANTalon$FeedbackDevice;D)I
         * 9: istore_3
         * 10: aload_0
         * 11: getfield      com/ctre/CANTalon.j:J
         * 14: iload_3
         * 15: invokestatic  com/ctre/CanTalonJNI.SetSensorPosition:(JI)V
         * 18: return
         *  */
        // </editor-fold>
    }

    public double getSpeed() {
        // <editor-fold defaultstate="collapsed" desc="Compiled Code">
        /* 0: aload_0
         * 1: aload_0
         * 2: getfield      com/ctre/CANTalon.d:Lcom/ctre/CANTalon$FeedbackDevice;
         * 5: aload_0
         * 6: getfield      com/ctre/CANTalon.j:J
         * 9: invokestatic  com/ctre/CanTalonJNI.GetSensorVelocity:(J)I
         * 12: i2l
         * 13: invokespecial com/ctre/CANTalon.a:(Lcom/ctre/CANTalon$FeedbackDevice;J)D
         * 16: dreturn
         *  */
        // </editor-fold>
    }


    public void setControlMode(int mode) {
        // <editor-fold defaultstate="collapsed" desc="Compiled Code">
        /* 0: iload_1
         * 1: invokestatic  com/ctre/CANTalon$TalonControlMode.valueOf:(I)Lcom/ctre/CANTalon$TalonControlMode;
         * 4: astore_2
         * 5: aload_2
         * 6: ifnull        14
         * 9: aload_0
         * 10: aload_2
         * 11: invokevirtual com/ctre/CANTalon.changeControlMode:(Lcom/ctre/CANTalon$TalonControlMode;)V
         * 14: return
         *  */
        // </editor-fold>
    }

    public void enableControl() {
        // <editor-fold defaultstate="collapsed" desc="Compiled Code">
        /* 0: aload_0
         * 1: aload_0
         * 2: getfield      com/ctre/CANTalon.k:Lcom/ctre/CANTalon$TalonControlMode;
         * 5: invokevirtual com/ctre/CANTalon.changeControlMode:(Lcom/ctre/CANTalon$TalonControlMode;)V
         * 8: aload_0
         * 9: iconst_1
         * 10: putfield      com/ctre/CANTalon.n:Z
         * 13: return
         *  */
        // </editor-fold>
    }

    public void enable() {
        // <editor-fold defaultstate="collapsed" desc="Compiled Code">
        /* 0: aload_0
         * 1: invokevirtual com/ctre/CANTalon.enableControl:()V
         * 4: return
         *  */
        // </editor-fold>
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
        // <editor-fold defaultstate="collapsed" desc="Compiled Code">
        /* 0: aload_0
         * 1: getfield      com/ctre/CANTalon.j:J
         * 4: aload_0
         * 5: getfield      com/ctre/CANTalon.p:I
         * 8: dload_1
         * 9: invokestatic  com/ctre/CanTalonJNI.SetPgain:(JID)V
         * 12: return
         *  */
        // </editor-fold>
    }

    public void setI(double i) {
        // <editor-fold defaultstate="collapsed" desc="Compiled Code">
        /* 0: aload_0
         * 1: getfield      com/ctre/CANTalon.j:J
         * 4: aload_0
         * 5: getfield      com/ctre/CANTalon.p:I
         * 8: dload_1
         * 9: invokestatic  com/ctre/CanTalonJNI.SetIgain:(JID)V
         * 12: return
         *  */
        // </editor-fold>
    }

    public void setD(double d) {
        // <editor-fold defaultstate="collapsed" desc="Compiled Code">
        /* 0: aload_0
         * 1: getfield      com/ctre/CANTalon.j:J
         * 4: aload_0
         * 5: getfield      com/ctre/CANTalon.p:I
         * 8: dload_1
         * 9: invokestatic  com/ctre/CanTalonJNI.SetDgain:(JID)V
         * 12: return
         *  */
        // </editor-fold>
    }

    public void setF(double f) {
        // <editor-fold defaultstate="collapsed" desc="Compiled Code">
        /* 0: aload_0
         * 1: getfield      com/ctre/CANTalon.j:J
         * 4: aload_0
         * 5: getfield      com/ctre/CANTalon.p:I
         * 8: dload_1
         * 9: invokestatic  com/ctre/CanTalonJNI.SetFgain:(JID)V
         * 12: return
         *  */
        // </editor-fold>
    }

    public void setPID(double p, double i, double d, double f, int izone, double closeLoopRampRate, int profile) {
        // <editor-fold defaultstate="collapsed" desc="Compiled Code">
        /* 0: iload         12
         * 2: ifeq          21
         * 5: iload         12
         * 7: iconst_1
         * 8: if_icmpeq     21
         * 11: new           java/lang/IllegalArgumentException
         * 14: dup
         * 15: ldc           Talon PID profile must be 0 or 1.
         * 17: invokespecial java/lang/IllegalArgumentException."<init>":(Ljava/lang/String;)V
         * 20: athrow
         * 21: aload_0
         * 22: iload         12
         * 24: putfield      com/ctre/CANTalon.p:I
         * 27: aload_0
         * 28: iload         12
         * 30: invokevirtual com/ctre/CANTalon.setProfile:(I)V
         * 33: aload_0
         * 34: dload_1
         * 35: invokevirtual com/ctre/CANTalon.setP:(D)V
         * 38: aload_0
         * 39: dload_3
         * 40: invokevirtual com/ctre/CANTalon.setI:(D)V
         * 43: aload_0
         * 44: dload         5
         * 46: invokevirtual com/ctre/CANTalon.setD:(D)V
         * 49: aload_0
         * 50: dload         7
         * 52: invokevirtual com/ctre/CANTalon.setF:(D)V
         * 55: aload_0
         * 56: iload         9
         * 58: invokevirtual com/ctre/CANTalon.setIZone:(I)V
         * 61: aload_0
         * 62: dload         10
         * 64: invokevirtual com/ctre/CANTalon.setCloseLoopRampRate:(D)V
         * 67: return
         *  */
        // </editor-fold>
    }

    public void setPID(double p, double i, double d) {
        // <editor-fold defaultstate="collapsed" desc="Compiled Code">
        /* 0: aload_0
         * 1: dload_1
         * 2: dload_3
         * 3: dload         5
         * 5: dconst_0
         * 6: iconst_0
         * 7: dconst_0
         * 8: aload_0
         * 9: getfield      com/ctre/CANTalon.p:I
         * 12: invokevirtual com/ctre/CANTalon.setPID:(DDDDIDI)V
         * 15: return
         *  */
        // </editor-fold>
    }

    public double getSetpoint() {
        // <editor-fold defaultstate="collapsed" desc="Compiled Code">
        /* 0: aload_0
         * 1: getfield      com/ctre/CANTalon.a:D
         * 4: dreturn
         *  */
        // </editor-fold>
    }

    public void setProfile(int profile) {
        // <editor-fold defaultstate="collapsed" desc="Compiled Code">
        /* 0: iload_1
         * 1: ifeq          19
         * 4: iload_1
         * 5: iconst_1
         * 6: if_icmpeq     19
         * 9: new           java/lang/IllegalArgumentException
         * 12: dup
         * 13: ldc           Talon PID profile must be 0 or 1.
         * 15: invokespecial java/lang/IllegalArgumentException."<init>":(Ljava/lang/String;)V
         * 18: athrow
         * 19: aload_0
         * 20: iload_1
         * 21: putfield      com/ctre/CANTalon.p:I
         * 24: aload_0
         * 25: getfield      com/ctre/CANTalon.j:J
         * 28: aload_0
         * 29: getfield      com/ctre/CANTalon.p:I
         * 32: invokestatic  com/ctre/CanTalonJNI.SetProfileSlotSelect:(JI)V
         * 35: aload_0
         * 36: getstatic     com/ctre/CANTalon$UsageFlags.s:Lcom/ctre/CANTalon$UsageFlags;
         * 39: invokespecial com/ctre/CANTalon.a:(Lcom/ctre/CANTalon$UsageFlags;)V
         * 42: return
         *  */
        // </editor-fold>
    }


    public void configMaxOutputVoltage(double voltage) {
        // <editor-fold defaultstate="collapsed" desc="Compiled Code">
        /* 0: aload_0
         * 1: dload_1
         * 2: dload_1
         * 3: dneg
         * 4: invokevirtual com/ctre/CANTalon.configPeakOutputVoltage:(DD)V
         * 7: return
         *  */
        // </editor-fold>
    }

    public void configPeakOutputVoltage(double forwardVoltage, double reverseVoltage) {
        // <editor-fold defaultstate="collapsed" desc="Compiled Code">
        /* 0: dload_1
         * 1: ldc2_w        12.0d
         * 4: dcmpl
         * 5: ifle          15
         * 8: ldc2_w        12.0d
         * 11: dstore_1
         * 12: goto          23
         * 15: dload_1
         * 16: dconst_0
         * 17: dcmpg
         * 18: ifge          23
         * 21: dconst_0
         * 22: dstore_1
         * 23: dload_3
         * 24: dconst_0
         * 25: dcmpl
         * 26: ifle          34
         * 29: dconst_0
         * 30: dstore_3
         * 31: goto          46
         * 34: dload_3
         * 35: ldc2_w        -12.0d
         * 38: dcmpg
         * 39: ifge          46
         * 42: ldc2_w        -12.0d
         * 45: dstore_3
         * 46: aload_0
         * 47: getstatic     com/ctre/CanTalonJNI$param_t.au:Lcom/ctre/CanTalonJNI$param_t;
         * 50: ldc2_w        1023.0d
         * 53: dload_1
         * 54: dmul
         * 55: ldc2_w        12.0d
         * 58: ddiv
         * 59: invokevirtual com/ctre/CANTalon.setParameter:(Lcom/ctre/CanTalonJNI$param_t;D)V
         * 62: aload_0
         * 63: getstatic     com/ctre/CanTalonJNI$param_t.aw:Lcom/ctre/CanTalonJNI$param_t;
         * 66: ldc2_w        1023.0d
         * 69: dload_3
         * 70: dmul
         * 71: ldc2_w        12.0d
         * 74: ddiv
         * 75: invokevirtual com/ctre/CANTalon.setParameter:(Lcom/ctre/CanTalonJNI$param_t;D)V
         * 78: return
         *  */
        // </editor-fold>
    }

    public void configNominalOutputVoltage(double forwardVoltage, double reverseVoltage) {
        // <editor-fold defaultstate="collapsed" desc="Compiled Code">
        /* 0: dload_1
         * 1: ldc2_w        12.0d
         * 4: dcmpl
         * 5: ifle          15
         * 8: ldc2_w        12.0d
         * 11: dstore_1
         * 12: goto          23
         * 15: dload_1
         * 16: dconst_0
         * 17: dcmpg
         * 18: ifge          23
         * 21: dconst_0
         * 22: dstore_1
         * 23: dload_3
         * 24: dconst_0
         * 25: dcmpl
         * 26: ifle          34
         * 29: dconst_0
         * 30: dstore_3
         * 31: goto          46
         * 34: dload_3
         * 35: ldc2_w        -12.0d
         * 38: dcmpg
         * 39: ifge          46
         * 42: ldc2_w        -12.0d
         * 45: dstore_3
         * 46: aload_0
         * 47: getstatic     com/ctre/CanTalonJNI$param_t.av:Lcom/ctre/CanTalonJNI$param_t;
         * 50: ldc2_w        1023.0d
         * 53: dload_1
         * 54: dmul
         * 55: ldc2_w        12.0d
         * 58: ddiv
         * 59: invokevirtual com/ctre/CANTalon.setParameter:(Lcom/ctre/CanTalonJNI$param_t;D)V
         * 62: aload_0
         * 63: getstatic     com/ctre/CanTalonJNI$param_t.ax:Lcom/ctre/CanTalonJNI$param_t;
         * 66: ldc2_w        1023.0d
         * 69: dload_3
         * 70: dmul
         * 71: ldc2_w        12.0d
         * 74: ddiv
         * 75: invokevirtual com/ctre/CANTalon.setParameter:(Lcom/ctre/CanTalonJNI$param_t;D)V
         * 78: return
         *  */
        // </editor-fold>
    }
    
    public void changeMotionControlFramePeriod(int periodMs) {
        // <editor-fold defaultstate="collapsed" desc="Compiled Code">
        /* 0: aload_0
         * 1: getfield      com/ctre/CANTalon.j:J
         * 4: iload_1
         * 5: invokestatic  com/ctre/CanTalonJNI.ChangeMotionControlFramePeriod:(JI)V
         * 8: return
         *  */
        // </editor-fold>
    }

    public void clearMotionProfileTrajectories() {
        // <editor-fold defaultstate="collapsed" desc="Compiled Code">
        /* 0: aload_0
         * 1: getfield      com/ctre/CANTalon.j:J
         * 4: invokestatic  com/ctre/CanTalonJNI.ClearMotionProfileTrajectories:(J)V
         * 7: return
         *  */
        // </editor-fold>
    }

    public int getMotionProfileTopLevelBufferCount() {
        // <editor-fold defaultstate="collapsed" desc="Compiled Code">
        /* 0: aload_0
         * 1: getfield      com/ctre/CANTalon.j:J
         * 4: invokestatic  com/ctre/CanTalonJNI.GetMotionProfileTopLevelBufferCount:(J)I
         * 7: ireturn
         *  */
        // </editor-fold>
    }

    public boolean pushMotionProfileTrajectory(TrajectoryPoint trajPt) {
        // <editor-fold defaultstate="collapsed" desc="Compiled Code">
        /* 0: aload_0
         * 1: invokevirtual com/ctre/CANTalon.isMotionProfileTopLevelBufferFull:()Z
         * 4: ifeq          9
         * 7: iconst_0
         * 8: ireturn
         * 9: aload_0
         * 10: aload_0
         * 11: getfield      com/ctre/CANTalon.d:Lcom/ctre/CANTalon$FeedbackDevice;
         * 14: aload_1
         * 15: getfield      com/ctre/CANTalon$TrajectoryPoint.position:D
         * 18: invokespecial com/ctre/CANTalon.a:(Lcom/ctre/CANTalon$FeedbackDevice;D)I
         * 21: istore_2
         * 22: aload_0
         * 23: aload_0
         * 24: getfield      com/ctre/CANTalon.d:Lcom/ctre/CANTalon$FeedbackDevice;
         * 27: aload_1
         * 28: getfield      com/ctre/CANTalon$TrajectoryPoint.velocity:D
         * 31: invokespecial com/ctre/CANTalon.b:(Lcom/ctre/CANTalon$FeedbackDevice;D)I
         * 34: istore_3
         * 35: aload_1
         * 36: getfield      com/ctre/CANTalon$TrajectoryPoint.profileSlotSelect:I
         * 39: ifle          46
         * 42: iconst_1
         * 43: goto          47
         * 46: iconst_0
         * 47: istore        4
         * 49: aload_1
         * 50: getfield      com/ctre/CANTalon$TrajectoryPoint.timeDurMs:I
         * 53: istore        5
         * 55: iload         5
         * 57: sipush        255
         * 60: if_icmple     68
         * 63: sipush        255
         * 66: istore        5
         * 68: iload         5
         * 70: ifge          76
         * 73: iconst_0
         * 74: istore        5
         * 76: aload_0
         * 77: getfield      com/ctre/CANTalon.j:J
         * 80: iload_2
         * 81: iload_3
         * 82: iload         4
         * 84: iload         5
         * 86: aload_1
         * 87: getfield      com/ctre/CANTalon$TrajectoryPoint.velocityOnly:Z
         * 90: ifeq          97
         * 93: iconst_1
         * 94: goto          98
         * 97: iconst_0
         * 98: aload_1
         * 99: getfield      com/ctre/CANTalon$TrajectoryPoint.isLastPoint:Z
         * 102: ifeq          109
         * 105: iconst_1
         * 106: goto          110
         * 109: iconst_0
         * 110: aload_1
         * 111: getfield      com/ctre/CANTalon$TrajectoryPoint.zeroPos:Z
         * 114: ifeq          121
         * 117: iconst_1
         * 118: goto          122
         * 121: iconst_0
         * 122: invokestatic  com/ctre/CanTalonJNI.PushMotionProfileTrajectory:(JIIIIIII)V
         * 125: iconst_1
         * 126: ireturn
         *  */
        // </editor-fold>
    }

    public boolean isMotionProfileTopLevelBufferFull() {
        // <editor-fold defaultstate="collapsed" desc="Compiled Code">
        /* 0: aload_0
         * 1: getfield      com/ctre/CANTalon.j:J
         * 4: invokestatic  com/ctre/CanTalonJNI.IsMotionProfileTopLevelBufferFull:(J)Z
         * 7: ireturn
         *  */
        // </editor-fold>
    }

    public void processMotionProfileBuffer() {
        // <editor-fold defaultstate="collapsed" desc="Compiled Code">
        /* 0: aload_0
         * 1: getfield      com/ctre/CANTalon.j:J
         * 4: invokestatic  com/ctre/CanTalonJNI.ProcessMotionProfileBuffer:(J)V
         * 7: return
         *  */
        // </editor-fold>
    }

    public void getMotionProfileStatus(MotionProfileStatus motionProfileStatus) {
        // <editor-fold defaultstate="collapsed" desc="Compiled Code">
        /* 0: aload_0
         * 1: getfield      com/ctre/CANTalon.j:J
         * 4: aload_0
         * 5: aload_1
         * 6: invokestatic  com/ctre/CanTalonJNI.GetMotionProfileStatus:(JLjava/lang/Object;Ljava/lang/Object;)V
         * 9: return
         *  */
        // </editor-fold>
    }

    protected void setMotionProfileStatusFromJNI(MotionProfileStatus motionProfileStatus, int flags, int profileSlotSelect, int targPos, int targVel, int topBufferRem, int topBufferCnt, int btmBufferCnt, int outputEnable) {
        // <editor-fold defaultstate="collapsed" desc="Compiled Code">
        /* 0: aload_1
         * 1: iload         6
         * 3: putfield      com/ctre/CANTalon$MotionProfileStatus.topBufferRem:I
         * 6: aload_1
         * 7: iload         7
         * 9: putfield      com/ctre/CANTalon$MotionProfileStatus.topBufferCnt:I
         * 12: aload_1
         * 13: iload         8
         * 15: putfield      com/ctre/CANTalon$MotionProfileStatus.btmBufferCnt:I
         * 18: aload_1
         * 19: iload_2
         * 20: iconst_2
         * 21: iand
         * 22: ifle          29
         * 25: iconst_1
         * 26: goto          30
         * 29: iconst_0
         * 30: putfield      com/ctre/CANTalon$MotionProfileStatus.hasUnderrun:Z
         * 33: aload_1
         * 34: iload_2
         * 35: iconst_4
         * 36: iand
         * 37: ifle          44
         * 40: iconst_1
         * 41: goto          45
         * 44: iconst_0
         * 45: putfield      com/ctre/CANTalon$MotionProfileStatus.isUnderrun:Z
         * 48: aload_1
         * 49: iload_2
         * 50: iconst_1
         * 51: iand
         * 52: ifle          59
         * 55: iconst_1
         * 56: goto          60
         * 59: iconst_0
         * 60: putfield      com/ctre/CANTalon$MotionProfileStatus.activePointValid:Z
         * 63: aload_1
         * 64: getfield      com/ctre/CANTalon$MotionProfileStatus.activePoint:Lcom/ctre/CANTalon$TrajectoryPoint;
         * 67: iload_2
         * 68: bipush        8
         * 70: iand
         * 71: ifle          78
         * 74: iconst_1
         * 75: goto          79
         * 78: iconst_0
         * 79: putfield      com/ctre/CANTalon$TrajectoryPoint.isLastPoint:Z
         * 82: aload_1
         * 83: getfield      com/ctre/CANTalon$MotionProfileStatus.activePoint:Lcom/ctre/CANTalon$TrajectoryPoint;
         * 86: iload_2
         * 87: bipush        16
         * 89: iand
         * 90: ifle          97
         * 93: iconst_1
         * 94: goto          98
         * 97: iconst_0
         * 98: putfield      com/ctre/CANTalon$TrajectoryPoint.velocityOnly:Z
         * 101: aload_1
         * 102: getfield      com/ctre/CANTalon$MotionProfileStatus.activePoint:Lcom/ctre/CANTalon$TrajectoryPoint;
         * 105: aload_0
         * 106: aload_0
         * 107: getfield      com/ctre/CANTalon.d:Lcom/ctre/CANTalon$FeedbackDevice;
         * 110: iload         4
         * 112: invokespecial com/ctre/CANTalon.a:(Lcom/ctre/CANTalon$FeedbackDevice;I)D
         * 115: putfield      com/ctre/CANTalon$TrajectoryPoint.position:D
         * 118: aload_1
         * 119: getfield      com/ctre/CANTalon$MotionProfileStatus.activePoint:Lcom/ctre/CANTalon$TrajectoryPoint;
         * 122: aload_0
         * 123: aload_0
         * 124: getfield      com/ctre/CANTalon.d:Lcom/ctre/CANTalon$FeedbackDevice;
         * 127: iload         5
         * 129: i2l
         * 130: invokespecial com/ctre/CANTalon.a:(Lcom/ctre/CANTalon$FeedbackDevice;J)D
         * 133: putfield      com/ctre/CANTalon$TrajectoryPoint.velocity:D
         * 136: aload_1
         * 137: getfield      com/ctre/CANTalon$MotionProfileStatus.activePoint:Lcom/ctre/CANTalon$TrajectoryPoint;
         * 140: iload_3
         * 141: putfield      com/ctre/CANTalon$TrajectoryPoint.profileSlotSelect:I
         * 144: aload_1
         * 145: iload         9
         * 147: invokestatic  com/ctre/CANTalon$SetValueMotionProfile.valueOf:(I)Lcom/ctre/CANTalon$SetValueMotionProfile;
         * 150: putfield      com/ctre/CANTalon$MotionProfileStatus.outputEnable:Lcom/ctre/CANTalon$SetValueMotionProfile;
         * 153: aload_1
         * 154: getfield      com/ctre/CANTalon$MotionProfileStatus.activePoint:Lcom/ctre/CANTalon$TrajectoryPoint;
         * 157: iconst_0
         * 158: putfield      com/ctre/CANTalon$TrajectoryPoint.zeroPos:Z
         * 161: aload_1
         * 162: getfield      com/ctre/CANTalon$MotionProfileStatus.activePoint:Lcom/ctre/CANTalon$TrajectoryPoint;
         * 165: iconst_0
         * 166: putfield      com/ctre/CANTalon$TrajectoryPoint.timeDurMs:I
         * 169: return
         *  */
        // </editor-fold>
    }

    public void clearMotionProfileHasUnderrun() {
        // <editor-fold defaultstate="collapsed" desc="Compiled Code">
        /* 0: aload_0
         * 1: getstatic     com/ctre/CanTalonJNI$param_t.aJ:Lcom/ctre/CanTalonJNI$param_t;
         * 4: dconst_0
         * 5: invokevirtual com/ctre/CANTalon.setParameter:(Lcom/ctre/CanTalonJNI$param_t;D)V
         * 8: return
         *  */
        // </editor-fold>
    }

    public void setMotionMagicCruiseVelocity(double motMagicCruiseVeloc) {
        // <editor-fold defaultstate="collapsed" desc="Compiled Code">
        /* 0: aload_0
         * 1: aload_0
         * 2: getfield      com/ctre/CANTalon.d:Lcom/ctre/CANTalon$FeedbackDevice;
         * 5: dload_1
         * 6: invokespecial com/ctre/CANTalon.b:(Lcom/ctre/CANTalon$FeedbackDevice;D)I
         * 9: istore_3
         * 10: aload_0
         * 11: getstatic     com/ctre/CanTalonJNI$param_t.aN:Lcom/ctre/CanTalonJNI$param_t;
         * 14: iload_3
         * 15: i2d
         * 16: invokevirtual com/ctre/CANTalon.setParameter:(Lcom/ctre/CanTalonJNI$param_t;D)V
         * 19: return
         *  */
        // </editor-fold>
    }

    public void setMotionMagicAcceleration(double motMagicAccel) {
        // <editor-fold defaultstate="collapsed" desc="Compiled Code">
        /* 0: aload_0
         * 1: aload_0
         * 2: getfield      com/ctre/CANTalon.d:Lcom/ctre/CANTalon$FeedbackDevice;
         * 5: dload_1
         * 6: invokespecial com/ctre/CANTalon.b:(Lcom/ctre/CANTalon$FeedbackDevice;D)I
         * 9: istore_3
         * 10: aload_0
         * 11: getstatic     com/ctre/CanTalonJNI$param_t.aM:Lcom/ctre/CanTalonJNI$param_t;
         * 14: iload_3
         * 15: i2d
         * 16: invokevirtual com/ctre/CANTalon.setParameter:(Lcom/ctre/CanTalonJNI$param_t;D)V
         * 19: return
         *  */
        // </editor-fold>
    }

    public double getMotionMagicCruiseVelocity() {
        // <editor-fold defaultstate="collapsed" desc="Compiled Code">
        /* 0: aload_0
         * 1: getstatic     com/ctre/CanTalonJNI$param_t.aN:Lcom/ctre/CanTalonJNI$param_t;
         * 4: invokevirtual com/ctre/CANTalon.getParameter:(Lcom/ctre/CanTalonJNI$param_t;)D
         * 7: dstore_1
         * 8: aload_0
         * 9: aload_0
         * 10: getfield      com/ctre/CANTalon.d:Lcom/ctre/CANTalon$FeedbackDevice;
         * 13: dload_1
         * 14: d2i
         * 15: i2l
         * 16: invokespecial com/ctre/CANTalon.a:(Lcom/ctre/CANTalon$FeedbackDevice;J)D
         * 19: dreturn
         *  */
        // </editor-fold>
    }

    public double getMotionMagicAcceleration() {
        // <editor-fold defaultstate="collapsed" desc="Compiled Code">
        /* 0: aload_0
         * 1: getstatic     com/ctre/CanTalonJNI$param_t.aM:Lcom/ctre/CanTalonJNI$param_t;
         * 4: invokevirtual com/ctre/CANTalon.getParameter:(Lcom/ctre/CanTalonJNI$param_t;)D
         * 7: dstore_1
         * 8: aload_0
         * 9: aload_0
         * 10: getfield      com/ctre/CANTalon.d:Lcom/ctre/CANTalon$FeedbackDevice;
         * 13: dload_1
         * 14: d2i
         * 15: i2l
         * 16: invokespecial com/ctre/CANTalon.a:(Lcom/ctre/CANTalon$FeedbackDevice;J)D
         * 19: dreturn
         *  */
        // </editor-fold>
    }

    public double getMotionMagicActTrajVelocity() {
        // <editor-fold defaultstate="collapsed" desc="Compiled Code">
        /* 0: aload_0
         * 1: getfield      com/ctre/CANTalon.j:J
         * 4: invokestatic  com/ctre/CanTalonJNI.GetMotMagActTraj_Velocity:(J)I
         * 7: i2d
         * 8: dstore_1
         * 9: aload_0
         * 10: aload_0
         * 11: getfield      com/ctre/CANTalon.d:Lcom/ctre/CANTalon$FeedbackDevice;
         * 14: dload_1
         * 15: d2i
         * 16: i2l
         * 17: invokespecial com/ctre/CANTalon.a:(Lcom/ctre/CANTalon$FeedbackDevice;J)D
         * 20: dreturn
         *  */
        // </editor-fold>
    }

    public double getMotionMagicActTrajPosition() {
        // <editor-fold defaultstate="collapsed" desc="Compiled Code">
        /* 0: aload_0
         * 1: getfield      com/ctre/CANTalon.j:J
         * 4: invokestatic  com/ctre/CanTalonJNI.GetMotMagActTraj_Position:(J)I
         * 7: i2d
         * 8: dstore_1
         * 9: aload_0
         * 10: aload_0
         * 11: getfield      com/ctre/CANTalon.d:Lcom/ctre/CANTalon$FeedbackDevice;
         * 14: dload_1
         * 15: d2i
         * 16: invokespecial com/ctre/CANTalon.a:(Lcom/ctre/CANTalon$FeedbackDevice;I)D
         * 19: dreturn
         *  */
        // </editor-fold>
    }

    public void setCurrentLimit(int amps) {
        // <editor-fold defaultstate="collapsed" desc="Compiled Code">
        /* 0: aload_0
         * 1: getstatic     com/ctre/CanTalonJNI$param_t.aP:Lcom/ctre/CanTalonJNI$param_t;
         * 4: iload_1
         * 5: i2d
         * 6: invokevirtual com/ctre/CANTalon.setParameter:(Lcom/ctre/CanTalonJNI$param_t;D)V
         * 9: return
         *  */
        // </editor-fold>
    }

    public void EnableCurrentLimit(boolean enable) {
        // <editor-fold defaultstate="collapsed" desc="Compiled Code">
        /* 0: aload_0
         * 1: getfield      com/ctre/CANTalon.j:J
         * 4: iload_1
         * 5: invokestatic  com/ctre/CanTalonJNI.SetCurrentLimEnable:(JZ)V
         * 8: aload_0
         * 9: getstatic     com/ctre/CANTalon$UsageFlags.k:Lcom/ctre/CANTalon$UsageFlags;
         * 12: invokespecial com/ctre/CANTalon.a:(Lcom/ctre/CANTalon$UsageFlags;)V
         * 15: return
         *  */
        // </editor-fold>
    }

    public boolean isAlive() {
        // <editor-fold defaultstate="collapsed" desc="Compiled Code">
        /* 0: aload_0
         * 1: getfield      com/ctre/CANTalon.e:Ledu/wpi/first/wpilibj/MotorSafetyHelper;
         * 4: invokevirtual edu/wpi/first/wpilibj/MotorSafetyHelper.isAlive:()Z
         * 7: ireturn
         *  */
        // </editor-fold>
    }
}
