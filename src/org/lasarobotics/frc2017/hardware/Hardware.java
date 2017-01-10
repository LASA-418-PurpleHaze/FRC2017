package org.lasarobotics.frc2017.hardware;

public class Hardware implements Runnable {
    
    private static Hardware instance;

    public static Hardware getInstance() {
        return (instance == null) ? instance = new Hardware() : instance;
    }

    @Override
    public void run() {
    }
}
