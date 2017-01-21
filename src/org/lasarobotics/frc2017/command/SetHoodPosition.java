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
public class SetHoodPosition extends Command{
    
    //These final static constants are for 
    private static final double CLOSE_POSITION = 0.0;
    private static final double FAR_POSITION = 1.0;
    
    private final double position;
    
    public SetHoodPosition(String name, double timeOut, double position){
        super(name, timeOut);
        this.position = position;
    }
    @Override
    public void start() {
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
