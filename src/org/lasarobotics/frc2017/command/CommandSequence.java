/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lasarobotics.frc2017.command;

import java.util.Iterator;
import java.util.LinkedList;
import org.lasarobotics.frc2017.hardware.Hardware;

/**
 *
 * @author Gijs Landwehr
 */
public class CommandSequence extends Command {

    private LinkedList <Command> commands = new LinkedList();
    
    public CommandSequence(String name, double t){
        super(name, t);
    }
    
    public void addCommand(Command c){
        commands.add(c);
    }
    
    @Override
    public void start() {
        startTime = Hardware.getCurrentTime();
    }

    @Override
    public boolean isDone() {
        Command c = commands.getLast();
        return c.isDone;
    }

    @Override
    public void run() {
        Command c = commands.getFirst();
        if(!c.isStarted()){
            c.start();
            c.run();
        }else if(c.isDone()){
            c.stop();
            commands.removeFirst();
        }
    }

    @Override
    public void stop() {
    }
    
}
