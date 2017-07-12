/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lasarobotics.frc2017.command;

import org.lasarobotics.frc2017.subsystem.GearIntake;

/**
 *
 * @author Gijs Landwehr
 */
public class SetGearIntakePosition extends Command {

    double targetAngle;
    
    public SetGearIntakePosition(double angle) {
        super("bla", 5);
        this.targetAngle = angle;
    }

    @Override
    public void start() {
        gearIntake.setAngle(targetAngle);
    }

    @Override
    public boolean isDone() {
        return true;
    }

    @Override
    public void run() {
        //do nothing
    }

    @Override
    public void stop() {
        //do nothing
    }

}
