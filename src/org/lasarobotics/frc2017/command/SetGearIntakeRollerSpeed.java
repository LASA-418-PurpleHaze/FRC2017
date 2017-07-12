/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lasarobotics.frc2017.command;

/**
 *
 * @author Gijs Landwehr
 */
public class SetGearIntakeRollerSpeed extends Command {

    double speed;
    
    public SetGearIntakeRollerSpeed(double speed)
    {
        super("bla", 5);
        this.speed = speed;
    }
    
    @Override
    public void start() {
        gearIntake.setRollerSpeed(speed);
    }

    @Override
    public boolean isDone() {
        return true;
    }

    @Override
    public void run() {
    }

    @Override
    public void stop() {
    }
    
}
