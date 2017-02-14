package org.lasarobotics.frc2017.subsystem;

//import org.lasarobotics.frc2017.hardware.Hardware;
import edu.wpi.first.wpilibj.Timer;
import org.lasarobotics.frc2017.ConstantsList;
import org.lasarobotics.lib.controlloop.HazyPID;

public class Shooter extends HazySubsystem {

    private static Shooter instance;
    private final HazyPID shooterPID;
    private static double targetRPM;
    private static double dt, prevTime;

    public static Shooter getInstance() {
        return (instance == null) ? instance = new Shooter() : instance;
    }

    public Shooter() {
        shooterPID = new HazyPID();

        setMode(Mode.OVERRIDE);
    }

    public static enum Mode {
        OVERRIDE, LOADING, SHOOTING;
    }

    static Mode mode;

    public void setMode(Mode m) {
        mode = m;
    }

    public Mode newMode;

    @Override
    public void run() {
        
        dt = Timer.getFPGATimestamp() - prevTime;
        
        if (null != mode) {
            switch (mode) {
                case OVERRIDE:
                    break;
                case LOADING:
                    //Fill out later
                    break;
                case SHOOTING:
                    speed = shooterPID.calculate(/*current RPM */, dt);
                    break;
            }
        }

        prevTime = Timer.getFPGATimestamp();
        hardware.setShooterSpeed(speed);
    }

    public void setTargetRPM(double target) {
        targetRPM = target;
        shooterPID.setTarget(targetRPM);
    }
    
    public boolean isUpToRPM(){
        return shooterPID.onTarget();
    }

    @Override
    public void initSubsystem() {
        shooterPID.setPID(ConstantsList.S_kP.getValue(), ConstantsList.S_kI.getValue(),
                ConstantsList.S_kD.getValue(), ConstantsList.S_kFF.getValue(),
                ConstantsList.S_doneBound.getValue());
    }

    @Override
    public void pushToDashboard() {
    }

}
