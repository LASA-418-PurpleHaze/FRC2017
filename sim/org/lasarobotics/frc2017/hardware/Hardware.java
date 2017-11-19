package org.lasarobotics.frc2017.hardware;

import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.tables.ITable;
import org.lasarobotics.lib.FakeTalon;

public class Hardware {
    //This is the fake hardware class.
    //Comments are Zayan's hypothesis of what various methods will do, probably wrong
    
    public static Hardware instance;
    
    private double leftDriveSpeed, rightDriveSpeed;
    private double leftDriveVelocity, rightDriveVelocity;
    private double leftDrivePosition, rightDrivePosition;
    FakeTalon tilt;
    public Hardware(){
        prevTime = getCurrentTime();
        //Initialize all the fake hardware counters
        
        //Fake Motors
        tilt = new FakeTalon(0);
        System.out.println(tilt.m);
        tilt.setP(1);
        //Fake Sensors
    }
    
    double prevTime, dt;
    
    public void run(){
        //simulate running the robot and assign values to all the numbers
        
        double newTime = getCurrentTime();
        dt = newTime - prevTime;
        prevTime = newTime;
        
        if (dt < 0.0001) {
            //return;
        }
        //System.out.println(tilt.getOutputVoltage());
        //dt = 0.01;
        //System.out.println(dt);
        //Assume drive motor velocity is perfectly linear with requested output
        //speed. Too annoying to model it properly. Going with 14 fps top speed.
        leftDriveVelocity = leftDriveSpeed * 14 * 12;
        rightDriveVelocity = rightDriveSpeed * 14 * 12;
        
        //Ignoring the fact that the left and right side of the robot affect
        //each other...
        leftDrivePosition += leftDriveVelocity * dt;
        rightDrivePosition += rightDriveVelocity * dt;
    }
    
    public void reset(){
        //resets all sim values back to 0
    }
    
    public static Hardware getInstance(){
        //Will return an instance of the hardware sim
        return (instance == null) ? instance = new Hardware() : instance;
    }
    
    private IntakeMode intakeMode;

    public enum IntakeMode {
        intaking, shooting;
    }
    
    public void setIntakeMode(IntakeMode intakeMode){
        //sets intake mode of the sim
    }
    
    public void setIntakeOutput(double Output){
        //sets output value for the intake
    }
    
    public void setIntakeSolenoid(boolean down){
        //whether sim input arm is down or not
    }
    
    public void actuateIntake(boolean down){
        //Does the same thing as the last function, in here bc I dont wanna break stuff
    }
    
    public void setConveyorRollerSpeed(double speed){
        //sets sim conveyor to speed
    }
    
    public void setClimberSpeed(double speed){
        //sets sim climber speed
    }
    
    public void setShooterRPM(double speed){
        //sets speeds for sim shooter motors(controlled by talon PID)
    }
    
    public boolean isShooterRPMDone(){
        //returns if fake shooter is close enough to the RPM we want
        return true;
    }
    
    public void setDriveSpeeds(double left, double right){
        leftDriveSpeed = left;
        rightDriveSpeed = right;
        //uses left and right to set sim drive speeds
    }
    
    public double getLeftDriveDistance(){
        return leftDrivePosition;
        //take sim drive distance and give it back
        //originally took encoder value and multiplied by gear ratio
    }
    
    public double getRightDriveDistance(){
        return rightDrivePosition;
    }
    
    public double getLeftDriveVelocity(){
        return leftDriveVelocity;
    }
    
    public double getRightDriveVelocity(){
        return rightDriveVelocity;
    }
    
    public double getNavXAngle(){
        //returns angle the sim robot is at
        return 0.0;
    }
    
    public double getRobotAngle(){
        return 0.0;
        //returns a value called "robotangle", which is apparently different from the navx angle
    }
    
    public void actuateGear(boolean out){
        //actuates the sim gear pneumatic
    }
    
    public double getClimberCurrent(){
        return 0.0;
        //returns the current running through climber to see it's power output
    }
    
    public void setGearIntakeAngle(double degrees){
        
    }
    
    public double getGearIntakeAngle()
    {
        return 0;
    }
    
    public void setGearRollerSpeed(double speed){
        
    }
    
    public boolean hasGear(){
        return true;
    }
    
    public static double getCurrentTime(){
        return ((double) System.currentTimeMillis()) / 1000.0;
        //returns current time in seconds
    }
    
    public void pushToDashboard(){
        //pushes stuff to the dashboard, which idk if we'll implement into the sim
    }
    
    public void setGearSpeed(double speed){
        
    }
    
    public void setGearAngle(double angle){
        
    }
    
    static NetworkTable y;
    static ITable x;
    
    static {
        //Robot.setTeam(418);
        //x = Robot.getTable();
        x = NetworkTable.getTable("SmartDashboard");
    }
    
    public static void putDash(String label, double num){
        //System.out.println(label + " : " + num);
        x.putNumber(label, num);
    }
    
    public static void putDash(String label, boolean bool){
        //System.out.println(label + " : " + bool);
        x.putBoolean(label, bool);
    }
    
    public static void putDash(String label, String str){
        //System.out.println(label + " : " + str);
        x.putString(label, str);
    }
    
    public static double getDashNum(String label, double def){
        return x.getNumber(label, def);
    }
    
    public static boolean getDashBool(String label, boolean def){
        return def;
    }
    
    public static String getDashString(String label, String def){
        return def;
    }
}
