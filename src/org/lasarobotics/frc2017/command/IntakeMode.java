package org.lasarobotics.frc2017.command;

import org.lasarobotics.frc2017.subsystem.Intake.Mode;

public class IntakeMode extends Command {

    Mode mode;

    public IntakeMode(String name, double timeOut, Mode m) {
        super(name, timeOut);
        this.mode = m;
    }

    @Override
    public void start() {
        intake.setMode(mode);
    }

    @Override
    public boolean isDone() {
        //sketchy?
        return (intake.getMode() == mode);
    }

    @Override
    public void run() {
    }

    @Override
    public void stop() {
    }

}
