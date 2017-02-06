package org.lasarobotics.frc2017.command;

public class Shoot extends Command{

    public Shoot(String name, double timeOut) {
        super(name, timeOut);
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
        //pew pew
    }

    @Override
    public void stop() {
    }
    
}
