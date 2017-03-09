package org.lasarobotics.frc2017.subsystem;

import org.lasarobotics.frc2017.hardware.Hardware;

public abstract class HazySubsystem {

    protected Hardware hardware;

    protected String errorMsg;
    protected final String fileName = "logger.csv";

    protected HazySubsystem() {
        hardware = Hardware.getInstance();
    }

    public abstract void run();

    public abstract void initSubsystem();

    public abstract void pushToDashboard();
}
