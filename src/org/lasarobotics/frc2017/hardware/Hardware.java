package org.lasarobotics.frc2017.hardware;

import com.ctre.CANTalon;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.lasarobotics.frc2017.ConstantsList;

public class Hardware implements Runnable {

    private static Hardware instance;

    // Drivetrain
    private final VictorSP leftDriveMotorA, leftDriveMotorB;
    private final VictorSP rightDriveMotorA, rightDriveMotorB;

    private final Encoder leftDriveEncoder, rightDriveEncoder;
    private double leftDriveEncoderPosition, leftDriveEncoderVelocity;
    private double rightDriveEncoderPosition, rightDriveEncoderVelocity;
    
    //private final AHRS navX;
    private volatile double navXAngle, robotAngle;
    private volatile int rotations;

    // Gear
    private final Solenoid gearSolenoid;

    //Shooter
    private final CANTalon leftShooterMotor;
    private final CANTalon rightShooterMotor;
    
    // Intake
    private final CANTalon leftIntakeMotor;
    private final CANTalon rightIntakeMotor;
    private final Solenoid intakeSolenoid;

    public static Hardware getInstance() {
        return (instance == null) ? instance = new Hardware() : instance;
    }

    public Hardware() {
        // Drivetrain
//        navX = new AHRS(SPI.Port.kMXP);

        leftDriveMotorA = new VictorSP(Ports.LEFT_DRIVE_MOTOR_A);
        leftDriveMotorB = new VictorSP(Ports.LEFT_DRIVE_MOTOR_B);
        rightDriveMotorA = new VictorSP(Ports.RIGHT_DRIVE_MOTOR_A);
        rightDriveMotorB = new VictorSP(Ports.RIGHT_DRIVE_MOTOR_B);

        rightDriveMotorA.setInverted(true);
        rightDriveMotorB.setInverted(true);

        leftDriveEncoder = new Encoder(Ports.DRIVE_ENCODER_L_A, Ports.DRIVE_ENCODER_L_B);
        rightDriveEncoder = new Encoder(Ports.DRIVE_ENCODER_R_A, Ports.DRIVE_ENCODER_R_B);

        leftDriveEncoder.setReverseDirection(true);

        // Left Shooter Controller
        leftShooterMotor = new CANTalon(Ports.SHOOTER_MOTOR_L);
        leftShooterMotor.reverseOutput(true);
        leftShooterMotor.reverseSensor(true);
        leftShooterMotor.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);
        leftShooterMotor.SetVelocityMeasurementPeriod(CANTalon.VelocityMeasurementPeriod.Period_10Ms);
        leftShooterMotor.SetVelocityMeasurementWindow(20);
        leftShooterMotor.configEncoderCodesPerRev(100);
        leftShooterMotor.configNominalOutputVoltage(0, 0);
        leftShooterMotor.configPeakOutputVoltage(12, -2);
        leftShooterMotor.changeControlMode(CANTalon.TalonControlMode.Speed);
        leftShooterMotor.set(0);

        // Right Shooter Controller
        rightShooterMotor = new CANTalon(Ports.SHOOTER_MOTOR_R);
        rightShooterMotor.reverseOutput(false);
        rightShooterMotor.reverseSensor(false);
        rightShooterMotor.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);
        rightShooterMotor.SetVelocityMeasurementPeriod(CANTalon.VelocityMeasurementPeriod.Period_10Ms);
        rightShooterMotor.SetVelocityMeasurementWindow(20);
        leftShooterMotor.configEncoderCodesPerRev(100);
        rightShooterMotor.configNominalOutputVoltage(0, 0);
        rightShooterMotor.configPeakOutputVoltage(12, -2);
        rightShooterMotor.changeControlMode(CANTalon.TalonControlMode.Speed);
        rightShooterMotor.set(0);

        // Left Intake Controller
        leftIntakeMotor = new CANTalon(Ports.INTAKE_MOTOR_L);
        leftIntakeMotor.reverseOutput(false);
        leftIntakeMotor.setCurrentLimit(30);
        leftIntakeMotor.changeControlMode(CANTalon.TalonControlMode.Current);
        leftIntakeMotor.set(0);

        // Right Intake Controller
        rightIntakeMotor = new CANTalon(Ports.INTAKE_MOTOR_R);
        rightIntakeMotor.reverseOutput(true);
        leftIntakeMotor.setCurrentLimit(30);
        rightIntakeMotor.changeControlMode(CANTalon.TalonControlMode.Follower);
        leftIntakeMotor.set(leftIntakeMotor.getDeviceID());

        intakeSolenoid = new Solenoid(Ports.INTAKE_SOLENOID);
        
        // Gear
        gearSolenoid = new Solenoid(Ports.GEARSOLENOID);
    }

    @Override
    public void run() {
/*        double newAngle = navX.getFusedHeading();

        if (Math.abs(navXAngle - newAngle) > 180.0) {
            if (newAngle < 180.0) {
                rotations++;
            } else {
                rotations--;
            }
        }

        navXAngle = navX.getAngle();
        robotAngle = navXAngle + rotations * 360.0;
*/
        leftDriveEncoderPosition = leftDriveEncoder.get();
        rightDriveEncoderPosition = rightDriveEncoder.get();
        leftDriveEncoderVelocity = leftDriveEncoder.getRate();
        rightDriveEncoderVelocity = rightDriveEncoder.getRate();
    }

    /** 
     * Reset all sensor values to 0 and reload the PID gains from the file.
     */
    public void reset() {
//        navX.reset();
        navXAngle = 0;
        robotAngle = 0;
        rotations = 0;
        
        leftShooterMotor.setPID(ConstantsList.S_kP.getValue(), ConstantsList.S_kI.getValue(),
                ConstantsList.S_kD.getValue());
        leftShooterMotor.setF(ConstantsList.S_kFF.getValue());
        rightShooterMotor.setPID(ConstantsList.S_kP.getValue(), ConstantsList.S_kI.getValue(),
                ConstantsList.S_kD.getValue());
        rightShooterMotor.setF(ConstantsList.S_kFF.getValue());
        
        leftDriveEncoder.reset();
        rightDriveEncoder.reset();
        
        leftDriveEncoderPosition = 0;
        rightDriveEncoderPosition = 0;
        leftDriveEncoderVelocity = 0;
        rightDriveEncoderVelocity = 0;
    }
    
    /**
     * Two modes are needed to control the intake. Current control is used
     * when grabbing balls to prevent excess power usage, and voltage control
     * is used when shooting in order to feed balls into the shooter consistently.
     */
    private IntakeMode intakeMode;

    public enum IntakeMode {
        intaking, shooting;
    }

    public void setIntakeMode(IntakeMode mode) {
        if (mode != intakeMode && mode == IntakeMode.intaking) {
            leftIntakeMotor.changeControlMode(CANTalon.TalonControlMode.Current);
            leftIntakeMotor.set(0);
        } else if (mode != intakeMode && mode == IntakeMode.shooting) {
            leftIntakeMotor.changeControlMode(CANTalon.TalonControlMode.Voltage);
            leftIntakeMotor.set(0);
        }
    }
    
    /**
     * If the intake is set to intaking mode then output is in Amps
     * If the intake is set to shooting mode then output is in Volts
     * 
     * @param output 
     */
    public void setIntakeOutput(double output) {
        leftIntakeMotor.set(output);
    }
    
    /**
     * Move the intake arm up or down.
     * 
     * @param down 
     */
    public void actuateIntake(boolean down) {
        intakeSolenoid.set(down);
    }
    
    /**
     * Sets the RPM to run the shooter at. This is then controlled by the
     * Talon's PID algorithm.
     * 
     * @param rpm 
     */
    public void setShooterRPM(double rpm) {
        leftShooterMotor.set(rpm);
        rightShooterMotor.set(rpm);
    }

    /**
     * @return True if the shooter is spinning within an acceptable range
     * of the desired RPM.
     */
    public boolean isShooterRPMDone() {
        return leftShooterMotor.getClosedLoopError() < ConstantsList.S_doneBound.getValue();
    }

    /**
     * Set the raw power for the two sides of the drivetrain.
     * 
     * @param left 
     * @param right 
     */
    public void setDriveSpeeds(double left, double right) {
        leftDriveMotorA.set(left);
        leftDriveMotorB.set(left);
        rightDriveMotorA.set(right);
        rightDriveMotorB.set(right);
    }

    /**
     * @return The position of the left side of the drivetrain in inches.
     */
    public double getLeftDriveDistance() {
        return leftDriveEncoderPosition / 250 * 3.5 * Math.PI * (36 / 48);
    }

    /**
     * @return The position of the right side of the drivetrain in inches.
     */
    public double getRightDriveDistance() {
        return rightDriveEncoderPosition / 250 * 3.5 * Math.PI * (36 / 48);
    }

    /**
     * @return The velocity of the left side of the drivetrain in inches/sec.
     */
    public double getLeftDriveVelocity() {
        return leftDriveEncoderVelocity / 250 * 3.5 * Math.PI * (36 / 48);
    }

    /**
     * @return The velocity of the right side of the drivetrain in inches/sec.
     */
    public double getRightDriveVelocity() {
        return rightDriveEncoderVelocity / 250 * 3.5 * Math.PI * (36 / 48);
    }

    /**
     * @return The angle (in degrees) being reported by the gyro. This resets
     * every rotation.
     */
    public double getNavXAngle() {
        return navXAngle;
    }

    /**
     * @return The angle (in degrees) of the robot. Rollover is accounted for,
     * so this value will continuously increase if the robot is turned in one direction.
     */
    public double getRobotAngle() {
        return robotAngle;
    }
   

    public void actuateGear(boolean out) {
        gearSolenoid.set(out);
    }
    
    public void pushToDashboard() {
        SmartDashboard.putNumber("NavX Angle", navXAngle);
        SmartDashboard.putNumber("Robot Angle", robotAngle);
        SmartDashboard.putNumber("Left Drive Position", getLeftDriveDistance());
        SmartDashboard.putNumber("Left Drive Velocity", getLeftDriveVelocity());
        SmartDashboard.putNumber("Right Drive Position", getRightDriveDistance());
        SmartDashboard.putNumber("Right Drive Velocity", getRightDriveVelocity());
        SmartDashboard.putNumber("Left Shooter RPM", leftShooterMotor.getSpeed());
        SmartDashboard.putNumber("Right Shooter RPM", rightShooterMotor.getSpeed());
    }

}
