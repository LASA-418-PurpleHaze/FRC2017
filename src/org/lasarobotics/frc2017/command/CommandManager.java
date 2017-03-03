package org.lasarobotics.frc2017.command;

import edu.wpi.first.wpilibj.Timer;
import java.util.LinkedList;

public class CommandManager {

    private static LinkedList<Command> list = new LinkedList<>();

    public static void addCommand(Command c) {
        System.out.println("Add command to list: " + c.name);
        list.add(c);
    }

    public static void run() {
        if (!list.isEmpty()) {
            Command command = list.getFirst();

            if (!command.isStarted()) {
                command.startTime = Timer.getFPGATimestamp();
                command.start();
                command.run();
                System.out.println("Starting command: " + command.name);
            } else if (command.isDone() || command.isTimedOut()) {
                System.out.println("Stopping command: " + command.name);
                double dt = Timer.getFPGATimestamp() - command.startTime;
                System.out.println("dt: " + dt);
                command.stop();
                list.remove(command);
            } else {
                command.run();
            }
        }
    }

    public static void cancelAll() {
        System.out.println("Clearing out command list.");
        list.clear();
    }
}
