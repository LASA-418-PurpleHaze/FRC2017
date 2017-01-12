package org.lasarobotics.frc2017.subsystem;

public class Drivetrain extends HazySubsystem {

    public Drivetrain() {

    }
    
    private static Drivetrain instance;
    
    public static Drivetrain getInstance() {
        return (instance == null) ? instance = new Drivetrain() : instance;
    }

    @Override
    public void run() {
    }

    @Override
    public void initSubsystem() {
    }

    @Override
    public void pushToDashboard() {
    }

}
