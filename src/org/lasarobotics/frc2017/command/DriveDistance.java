/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lasarobotics.frc2017.command;

/**
 *
 * @author airpendragon
 */
public class DriveDistance extends Command{
    private final double distance;
    
    public DriveDistance(String name, double t, double distance) {
        super(name, t);
        this.distance=distance;
    }
    
    public boolean isDone() {
        return drivetrain.isDistanceDone() && drivetrain.getStraightSetpoint() == distance;
    }
    
    public void start()
    {
        super.start();
        drivetrain.setStraightSetpoint(distance);
    }
    
    
}
