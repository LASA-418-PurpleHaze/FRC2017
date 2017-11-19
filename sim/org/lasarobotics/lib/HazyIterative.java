package org.lasarobotics.lib;

import java.util.Timer;
import java.util.TimerTask;

/*----------------------------------------------------------------------------*/
 /* Copyright (c) FIRST 2008-2016. All Rights Reserved.                        */
 /* Open Source Software - may be modified and shared by FRC teams. The code   */
 /* must be accompanied by the FIRST BSD license file in the root directory of */
 /* the project.                                                               */
 /*----------------------------------------------------------------------------*/

/**
 * IterativeRobot implements a specific type of Robot Program framework,
 * extending the RobotBase class.
 *
 * The IterativeRobot class is intended to be subclassed by a user creating a
 * robot program.
 *
 * This class is intended to implement the "old style" default code, by
 * providing the following functions which are called by the main loop,
 * startCompetition(), at the appropriate times:
 *
 * robotInit() -- provide for initialization at robot power-on
 *
 * init() functions -- each of the following functions is called once when the
 * appropriate mode is entered: - DisabledInit() -- called only when first
 * disabled - AutonomousInit() -- called each and every time autonomous is
 * entered from another mode - TeleopInit() -- called each and every time teleop
 * is entered from another mode - TestInit() -- called each and every time test
 * mode is entered from anothermode
 *
 * Periodic() functions -- each of these functions is called iteratively at the
 * appropriate periodic rate (aka the "slow loop"). The period of the iterative
 * robot is synced to the driver station control packets, giving a periodic
 * frequency of about 50Hz (50 times per second). - disabledPeriodic() -
 * autonomousPeriodic() - teleopPeriodic() - testPeriodoc()
 *
 */
public class HazyIterative {

    private volatile boolean m_disabledInitialized;
    private volatile boolean m_autonomousInitialized;
    private volatile boolean m_teleopInitialized;
    private volatile boolean m_testInitialized;

    public boolean isDisabled = true;
    public boolean isTest = false;
    public boolean isAutonomous = false;
    
    /**
     * Constructor for RobotIterativeBase
     *
     * The constructor initializes the instance variables for the robot to
     * indicate the status of initialization for disabled, autonomous, and
     * teleop code.
     */
    public HazyIterative() {
        // set status for initialization of disabled, autonomous, and teleop code.
        m_disabledInitialized = false;
        m_autonomousInitialized = false;
        m_teleopInitialized = false;
        m_testInitialized = false;
    }

    /**
     * Provide an alternate "main loop" via startCompetition().
     *
     */
    final double continuousPeriod = 0.01;

    public void startCompetition() {
        robotInit();

        Timer t = new Timer();
        t.scheduleAtFixedRate(new ContinuousRunner(), 0, 10);

        // loop forever, calling the appropriate mode-dependent function
        while (true) {
            try {
                Thread.sleep(20);
            } catch (InterruptedException ex) {
            }
            
            // Call the appropriate function depending upon the current robot mode
            if (isDisabled) {
                // call DisabledInit() if we are now just entering disabled mode from
                // either a different mode or from power-on
                if (!m_disabledInitialized) {
                    disabledInit();
                    m_teleopInitialized = false;
                    m_testInitialized = false;
                    m_autonomousInitialized = false;
                    m_disabledInitialized = true;
                }
                disabledPeriodic();
            } else if (isTest) {
                // call TestInit() if we are now just entering test mode from either
                // a different mode or from power-on
                if (!m_testInitialized) {
                    testInit();
                    m_testInitialized = true;
                    m_autonomousInitialized = false;
                    m_teleopInitialized = false;
                    m_disabledInitialized = false;
                }
                testPeriodic();
            } else if (isAutonomous) {
                // call Autonomous_Init() if this is the first time
                // we've entered autonomous_mode
                if (!m_autonomousInitialized) {
                    // KBS NOTE: old code reset all PWMs and relays to "safe values"
                    // whenever entering autonomous mode, before calling
                    // "Autonomous_Init()"
                    autonomousInit();
                    m_autonomousInitialized = true;
                    m_testInitialized = false;
                    m_teleopInitialized = false;
                    m_disabledInitialized = false;
                }
                autonomousPeriodic();
            } else {
                // call Teleop_Init() if this is the first time
                // we've entered teleop_mode
                if (!m_teleopInitialized) {
                    teleopInit();
                    m_disabledInitialized = false;
                    // reset the initialization flags for the other modes
                    m_autonomousInitialized = false;
                    m_teleopInitialized = true;
                    m_testInitialized = false;
                }
                teleopPeriodic();
            }
        }
    }

    class ContinuousRunner extends TimerTask {

        @Override
        public void run() {
            if (isTest) {
                if (m_testInitialized) {
                    testContinuous();
                }
            } else if (isDisabled) {
                if (m_disabledInitialized) {
                    teleopContinuous();
                }
            } else if (isAutonomous) {
                if (m_autonomousInitialized) {
                    autonomousContinuous();
                }
            } else {
                teleopContinuous();
            }
        }
    }
    
    boolean disabledContinuousFirstRun = true;
    double runnableDelay = 0.001;
    public void disabledContinuous() {
        if (disabledContinuousFirstRun) {
            System.out.println("Default LasaIterative.disabledContinuous() method... Overload me!");
            disabledContinuousFirstRun = false;
        }
    }

    boolean autonomousContinuousFirstRun = true;

    public void autonomousContinuous() {
        if (autonomousContinuousFirstRun) {
            System.out.println("Default LasaIterative.autonomousContinuous() method... Overload me!");
            autonomousContinuousFirstRun = false;
        }
    }

    boolean teleopContinuousFirstRun = true;

    public void teleopContinuous() {
        if (teleopContinuousFirstRun) {
            System.out.println("Default LasaIterative.teleopContinuous() method... Overload me!");
            teleopContinuousFirstRun = false;
        }
    }

    boolean testContinuousFirstRun = true;

    public void testContinuous() {
        if (testContinuousFirstRun) {
            System.out.println("Default LasaIterative.testContinuous() method... Overload me!");
            testContinuousFirstRun = false;
        }
    }

    /* ----------- Overridable initialization code ----------------- */
    /**
     * Robot-wide initialization code should go here.
     *
     * Users should override this method for default Robot-wide initialization
     * which will be called when the robot is first powered on. It will be
     * called exactly one time.
     *
     * Warning: the Driver Station "Robot Code" light and FMS "Robot Ready"
     * indicators will be off until RobotInit() exits. Code in RobotInit() that
     * waits for enable will cause the robot to never indicate that the code is
     * ready, causing the robot to be bypassed in a match.
     */
    public void robotInit() {
        System.out.println("Default IterativeRobot.robotInit() method... Overload me!");
    }

    /**
     * Initialization code for disabled mode should go here.
     *
     * Users should override this method for initialization code which will be
     * called each time the robot enters disabled mode.
     */
    public void disabledInit() {
        System.out.println("Default IterativeRobot.disabledInit() method... Overload me!");
    }

    /**
     * Initialization code for autonomous mode should go here.
     *
     * Users should override this method for initialization code which will be
     * called each time the robot enters autonomous mode.
     */
    public void autonomousInit() {
        System.out.println("Default IterativeRobot.autonomousInit() method... Overload me!");
    }

    /**
     * Initialization code for teleop mode should go here.
     *
     * Users should override this method for initialization code which will be
     * called each time the robot enters teleop mode.
     */
    public void teleopInit() {
        System.out.println("Default IterativeRobot.teleopInit() method... Overload me!");
    }

    /**
     * Initialization code for test mode should go here.
     *
     * Users should override this method for initialization code which will be
     * called each time the robot enters test mode.
     */
    public void testInit() {
        System.out.println("Default IterativeRobot.testInit() method... Overload me!");
    }

    /* ----------- Overridable periodic code ----------------- */
    private boolean dpFirstRun = true;

    /**
     * Periodic code for disabled mode should go here.
     *
     * Users should override this method for code which will be called
     * periodically at a regular rate while the robot is in disabled mode.
     */
    public void disabledPeriodic() {
        if (dpFirstRun) {
            System.out.println("Default IterativeRobot.disabledPeriodic() method... Overload me!");
            dpFirstRun = false;
        }
    }

    private boolean apFirstRun = true;

    /**
     * Periodic code for autonomous mode should go here.
     *
     * Users should override this method for code which will be called
     * periodically at a regular rate while the robot is in autonomous mode.
     */
    public void autonomousPeriodic() {
        if (apFirstRun) {
            System.out.println("Default IterativeRobot.autonomousPeriodic() method... Overload me!");
            apFirstRun = false;
        }
    }

    private boolean tpFirstRun = true;

    /**
     * Periodic code for teleop mode should go here.
     *
     * Users should override this method for code which will be called
     * periodically at a regular rate while the robot is in teleop mode.
     */
    public void teleopPeriodic() {
        if (tpFirstRun) {
            System.out.println("Default IterativeRobot.teleopPeriodic() method... Overload me!");
            tpFirstRun = false;
        }
    }

    private boolean tmpFirstRun = true;

    /**
     * Periodic code for test mode should go here
     *
     * Users should override this method for code which will be called
     * periodically at a regular rate while the robot is in test mode.
     */
    public void testPeriodic() {
        if (tmpFirstRun) {
            System.out.println("Default IterativeRobot.testPeriodic() method... Overload me!");
            tmpFirstRun = false;
        }
    }
}
