package org.lasarobotics.frc2017.subsystem;

public class Drivetrain extends HazySubsystem {

    private double leftSpeed, rightSpeed;

    public Drivetrain() {

    }

    private static Drivetrain instance;

    public static Drivetrain getInstance() {
        return (instance == null) ? instance = new Drivetrain() : instance;
    }

    @Override
    public void run() {
    }

    public void setDriveSpeeds(double l, double r) {
        leftSpeed = l;
        rightSpeed = r;
    }

    @Override
    public void initSubsystem() {
    }

    @Override
    public void pushToDashboard() {
    }

}
