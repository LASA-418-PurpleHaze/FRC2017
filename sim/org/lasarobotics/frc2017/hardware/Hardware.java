package org.lasarobotics.frc2017.hardware;

import edu.wpi.first.smartdashboard.robot.Robot;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.tables.ITable;

public class Hardware {
    //This is the fake hardware class.
    //Comments are Zayan's hypothesis of what various methods will do, probably wrong
    
    public static Hardware instance;
    
    public Hardware(){
        //Initialize all the fake hardware counters
        
        //Fake Motors
        
        //Fake Sensors
    }
    
    public void run(){
        //simulate running the robot and assign values to all the numbers
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
        //uses left and right to set sim drive speeds
    }
    
    public double getLeftDriveDistance(){
        return 0;
        //take sim drive distance and give it back
        //originally took encoder value and multiplied by gear ratio
    }
    
    public double getRightDriveDistance(){
        return 0;
    }
    
    public double getLeftDriveVelocity(){
        return 0;
    }
    
    public double getRightDriveVelocity(){
        return 0;
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
    
    public static double getCurrentTime(){
        return System.currentTimeMillis() / 1000;
        //returns current time in seconds
    }
    
    public void pushToDashboard(){
        //pushes stuff to the dashboard, which idk if we'll implement into the sim
    }
    
    static NetworkTable y;
    static ITable x;
    
    static {
        Robot.setTeam(418);
        x = Robot.getTable();
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
        return def;
    }
    
    public static boolean getDashBool(String label, boolean def){
        return def;
    }
    
    public static String getDashString(String label, String def){
        return def;
    }
}
