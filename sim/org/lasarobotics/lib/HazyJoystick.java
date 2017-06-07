package org.lasarobotics.lib;

import java.util.TimerTask;
import net.java.games.input.Component;
import net.java.games.input.Controller;
import net.java.games.input.ControllerEnvironment;

public class HazyJoystick {
    //This is the fake joystick class. This will replicate the normal
    //one but instead use a windows joystick library.

    double deadband;

    Controller joystick;
    Component[] components;

    public HazyJoystick(int joystickPort, double deadband) {
        Controller[] ca = ControllerEnvironment.getDefaultEnvironment().getControllers();
        joystick = ca[joystickPort];
        components = joystick.getComponents();
        this.deadband = deadband;
    }

    class Poller extends TimerTask {

        @Override
        public void run() {
            joystick.poll();
        }
    }

    private double handleDeadband(double input) {

        if (Math.abs(input) > deadband) {

            if (input > 0) {
                return (input - deadband) / (1 - deadband);
            } else {
                return (input + deadband) / (1 - deadband);
            }

        } else {
            return 0.0;
        }
    }

    public double getXAxis() {
        return handleDeadband(0);
    }

    public double getYAxis() {
        return handleDeadband(0);
    }

    public double getZAxis() {
        return handleDeadband(0);
    }

    public boolean getTrigger() {
        return false;
    }

    public boolean getTopBackButton() {
        return false;
    }

    public boolean getTopFrontButton() {
        return false;
    }

    public boolean getTopleftButton() {
        return false;
    }

    public boolean getTopRightButton() {
        return false;
    }

    public boolean getLeftFrontButton() {
        return false;
    }

    public boolean getLeftBackButton() {
        return false;
    }

    public boolean getBackLeftButton() {
        return false;
    }

    public boolean getBackRightButton() {
        return false;
    }

    public boolean getRightBackButton() {
        return false;
    }

    public boolean getRightFrontButton() {
        return false;
    }

    public boolean getRawButton(int i) {
        return false;
    }

}
