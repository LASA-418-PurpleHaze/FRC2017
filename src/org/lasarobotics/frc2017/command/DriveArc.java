/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lasarobotics.frc2017.command;

import org.lasarobotics.frc2017.subsystem.Drivetrain;

/**
 *
 * @author zayanvohra
 */
public class DriveArc extends Command{
    
    private final double angle;
    private final double radius;

    public DriveArc(String name, double timeOut, double angle, double radius) {
        super(name, timeOut);
        this.angle = angle;
        this.radius = radius;
    }

    @Override
    public void start() {
        drivetrain.setMode(Drivetrain.Mode.ARCING);
        drivetrain.setArcSetpoint(radius, angle);
        
    }

    @Override
    public boolean isDone() {
        return drivetrain.isArcDone();
    }

    @Override
    public void run() {
        //nah
    }

    @Override
    public void stop() {
        drivetrain.setMode(Drivetrain.Mode.OVERRIDE);
        hardware.reset();
        drivetrain.setArcSetpoint(0.0, 0.0);
        drivetrain.setDriveSpeeds(0.0, 0.0);
        
    }
    
}
