package org.lasarobotics.frc2017.command;

import java.util.Iterator;
import java.util.LinkedList;
import org.lasarobotics.frc2017.hardware.Hardware;

public class CommandGroup extends Command {

    private LinkedList<Command> commands = new LinkedList<>();

    public CommandGroup(String name, double timeOut) {
        super(name, timeOut);
    }

    public void addCommand(Command c) {
        commands.add(c);
    }

    @Override
    public void start() {
        startTime = Hardware.getCurrentTime();
        for (Command c : commands) {
            c.startTime = Hardware.getCurrentTime();
            c.start();
        }
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
                temp.run();
            }
        }
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
    public void stop() {
    }
}
