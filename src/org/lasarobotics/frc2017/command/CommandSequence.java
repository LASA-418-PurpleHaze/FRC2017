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
        boolean done = true;
        for (Command c : commands) {
            done &= c.isDone();
        }

        return done;
    }

    @Override
    public void run() {
        Iterator it = commands.iterator();
        while (it.hasNext()) {
            Command temp = (Command) it.next();
            if (temp.isDone()) {
                temp.stop();
                it.remove();
            } else {
                temp.start();
                temp.run();
            }
        }
    }

    @Override
    public void stop() {
    }
    
}
