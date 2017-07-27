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
public class GrabGear extends Command {

    private boolean gotGear;
    private boolean lowered;

    private CommandGroup lower;
    private CommandGroup raise;

    public GrabGear() {
        super("afddsaf", 4323);

        lower.addCommand(new SetGearIntakePosition(0, "Lowering Intake", 5));
        lower.addCommand(new SetGearIntakeRollerSpeed(1, "Intaking Speed", 5));
        
        raise.addCommand(new SetGearIntakePosition(90, "Raising Intake", 5));
        raise.addCommand(new SetGearIntakeRollerSpeed(0, "Turning Off Intake", 5));
    }

    @Override
    public void start() {
    }

    @Override
    public boolean isDone() {
        return raise.isDone();
    }

    @Override
    public void run() {
        if (!lower.isDone()) {
            lower.run();
        } else if (!hardware.hasGear()) {
            //do nothing
        } else if (!raise.isDone()) {
            raise.run();
        }
    }

    @Override
    public void stop() {
    }

}
