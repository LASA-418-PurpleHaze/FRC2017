package org.lasarobotics.frc2017.hardware;

import com.ctre.CANTalon;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.lasarobotics.frc2017.ConstantsList;

public class Hardware implements Runnable {

    private static Hardware instance;

    // Drivetrain
    private final VictorSP leftDriveMotorA, leftDriveMotorB;
    private final VictorSP rightDriveMotorA, rightDriveMotorB;
    private final VictorSP climberMotorA, climberMotorB;
    private final VictorSP conveyorRoller;

    private final Encoder leftDriveEncoder, rightDriveEncoder;
    private volatile double leftDriveEncoderPosition, leftDriveEncoderVelocity;
    private volatile double rightDriveEncoderPosition, rightDriveEncoderVelocity;

    private PowerDistributionPanel pdp;

    private final AHRS navX;
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
        navX = new AHRS(SPI.Port.kMXP);

        pdp = new PowerDistributionPanel();

        leftDriveMotorA = new VictorSP(Ports.LEFT_DRIVE_MOTOR_A);
        leftDriveMotorB = new VictorSP(Ports.LEFT_DRIVE_MOTOR_B);
        rightDriveMotorA = new VictorSP(Ports.RIGHT_DRIVE_MOTOR_A);
        rightDriveMotorB = new VictorSP(Ports.RIGHT_DRIVE_MOTOR_B);

        rightDriveMotorA.setInverted(true);
        rightDriveMotorB.setInverted(true);

        leftDriveEncoder = new Encoder(Ports.DRIVE_ENCODER_L_A, Ports.DRIVE_ENCODER_L_B);
        rightDriveEncoder = new Encoder(Ports.DRIVE_ENCODER_R_A, Ports.DRIVE_ENCODER_R_B);

        rightDriveEncoder.setReverseDirection(true);

        // Left Shooter Controller
        leftShooterMotor = new CANTalon(Ports.SHOOTER_MOTOR_L);
        leftShooterMotor.reverseOutput(true);
        leftShooterMotor.reverseSensor(false);
        leftShooterMotor.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);
        leftShooterMotor.SetVelocityMeasurementPeriod(CANTalon.VelocityMeasurementPeriod.Period_10Ms);
        leftShooterMotor.SetVelocityMeasurementWindow(20);
        leftShooterMotor.configEncoderCodesPerRev(100);
        leftShooterMotor.configNominalOutputVoltage(0, 0);
        leftShooterMotor.configPeakOutputVoltage(2, -12);
        leftShooterMotor.changeControlMode(CANTalon.TalonControlMode.Speed);
        leftShooterMotor.set(0);

        // Right Shooter Controller
        rightShooterMotor = new CANTalon(Ports.SHOOTER_MOTOR_R);
        rightShooterMotor.reverseOutput(false);
        rightShooterMotor.reverseSensor(true);
        rightShooterMotor.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);
        rightShooterMotor.SetVelocityMeasurementPeriod(CANTalon.VelocityMeasurementPeriod.Period_10Ms);
        rightShooterMotor.SetVelocityMeasurementWindow(20);
        rightShooterMotor.configEncoderCodesPerRev(100);
        rightShooterMotor.configNominalOutputVoltage(0, 0);
        rightShooterMotor.configPeakOutputVoltage(12, -2);
        rightShooterMotor.changeControlMode(CANTalon.TalonControlMode.Speed);
        rightShooterMotor.set(0);

        // Left Intake Controller
        leftIntakeMotor = new CANTalon(Ports.INTAKE_MOTOR_L);
        leftIntakeMotor.reverseOutput(false);
        leftIntakeMotor.configNominalOutputVoltage(0, 0);
        leftIntakeMotor.configPeakOutputVoltage(12, -12);
        leftIntakeMotor.setCurrentLimit(30);
        leftIntakeMotor.changeControlMode(CANTalon.TalonControlMode.Current);
        leftIntakeMotor.setVoltageCompensationRampRate(24.0);
        leftIntakeMotor.set(0);

        // Right Intake Controller
        rightIntakeMotor = new CANTalon(Ports.INTAKE_MOTOR_R);
        rightIntakeMotor.reverseOutput(true);
        rightIntakeMotor.setCurrentLimit(30);
        rightIntakeMotor.changeControlMode(CANTalon.TalonControlMode.Follower);
        rightIntakeMotor.set(leftIntakeMotor.getDeviceID());

        intakeSolenoid = new Solenoid(Ports.INTAKE_SOLENOID);
        conveyorRoller = new VictorSP(Ports.CONVEYOR_ROLLER);

        // Gear
        gearSolenoid = new Solenoid(Ports.GEARSOLENOID);

        // Climber
        climberMotorA = new VictorSP(Ports.CLIMBER_MOTOR_A);
        climberMotorB = new VictorSP(Ports.CLIMBER_MOTOR_B);
        climberMotorB.setInverted(true);

    }

    @Override
    public void run() {
        double newAngle = navX.getFusedHeading();

        if (Math.abs(navXAngle - newAngle) > 180.0) {
            if (newAngle < 180.0) {
                rotations++;
            } else {
                rotations--;
            }
        }

        navXAngle = navX.getFusedHeading();
        robotAngle = navXAngle + rotations * 360.0;

        leftDriveEncoderPosition = leftDriveEncoder.get();
        rightDriveEncoderPosition = rightDriveEncoder.get();
        leftDriveEncoderVelocity = leftDriveEncoder.getRate();
        rightDriveEncoderVelocity = rightDriveEncoder.getRate();
    }

    /**
     * Reset all sensor values to 0 and reload the PID gains from the file.
     */
    public void reset() {
        navX.reset();
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
     * Two modes are needed to control the intake. Current control is used when
     * grabbing balls to prevent excess power usage, and voltage control is used
     * when shooting in order to feed balls into the shooter consistently.
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
        intakeMode = mode;
    }

    /**
     * If the intake is set to intaking mode then output is in Amps If the
     * intake is set to shooting mode then output is in Volts
     *
     * @param output
     */
    public void setIntakeOutput(double output) {
        leftIntakeMotor.set(output);
    }

    public void setIntakeSolenoid(boolean down) {
        intakeSolenoid.set(down);
    }

    /**
     * Move the intake arm up or down.
     *
     * @param down
     */
    public void actuateIntake(boolean down) {
        intakeSolenoid.set(down);
    }

    public void setConveyorRollerSpeed(double speed) {
        conveyorRoller.set(speed);
    }

    public void setClimberSpeed(double speed) {
        climberMotorA.set(-speed);
        climberMotorB.set(-speed);
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
     * @return True if the shooter is spinning within an acceptable range of the
     * desired RPM.
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
     * encoderpos / 250 * 3.5 * pi * (36/48)
     */
    public double getLeftDriveDistance() {
        return leftDriveEncoderPosition * 0.032987;
    }

    /**
     * @return The position of the right side of the drivetrain in inches.
     */
    public double getRightDriveDistance() {
        return rightDriveEncoderPosition * 0.032987;
    }

    /**
     * @return The velocity of the left side of the drivetrain in inches/sec.
     */
    public double getLeftDriveVelocity() {
        return leftDriveEncoderVelocity * 0.032987;
    }

    /**
     * @return The velocity of the right side of the drivetrain in inches/sec.
     */
    public double getRightDriveVelocity() {
        return rightDriveEncoderVelocity * 0.032987;
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
     * so this value will continuously increase if the robot is turned in one
     * direction.
     */
    public double getRobotAngle() {
        return robotAngle;
    }

    public void actuateGear(boolean out) {
        gearSolenoid.set(out);
    }

    public double getClimberCurrent() {
        return (pdp.getCurrent(Ports.CLIMBER_POWER_A) + pdp.getCurrent(Ports.CLIMBER_POWER_B)) * 0.5;
    }
    
    public static double getCurrentTime(){
        return Timer.getFPGATimestamp();
    }
   
    public void pushToDashboard() {
        putDash("H_n_angle", navXAngle);
        putDash("H_r_angle", robotAngle);
        putDash("S_l_rpm", leftShooterMotor.getSpeed());
        putDash("S_r_rpm", rightShooterMotor.getSpeed());
        putDash("S_l_voltage", -leftShooterMotor.getOutputVoltage());
        putDash("S_r_voltage", rightShooterMotor.getOutputVoltage());
        putDash("I_current", leftIntakeMotor.getOutputCurrent());
        putDash("D_test", leftDriveEncoderPosition);
    }
    
    public static void putDash(String label, double num){
        SmartDashboard.putNumber(label, num);
    }
    
    public static void putDash(String label, boolean bool){
        SmartDashboard.putBoolean(label, bool);
    }
    
    public static double getDashNum(String label, double def){
        //returns "label" from smartdashboard, and if it can't find it it returns default "def"
        return SmartDashboard.getNumber(label, def);
    }
    
    public static boolean getDashBool(String label, boolean def){
        return SmartDashboard.getBoolean(label, def);
    }

}
