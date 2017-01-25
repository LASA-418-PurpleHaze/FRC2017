package org.lasarobotics.frc2017.command;

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
