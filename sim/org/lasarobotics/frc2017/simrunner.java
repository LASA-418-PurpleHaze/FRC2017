/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lasarobotics.frc2017;

import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.tables.ITable;
import static org.lasarobotics.frc2017.fake_ds.runGUI;

/**
 *
 * @author Gijs Landwehr
 */
public class simrunner {
    static Robot r;
    
    public static void main(String ... args)
    {
        NetworkTable.setServerMode();
        NetworkTable.setTeam(418);
        NetworkTable.setPort(1735);
        NetworkTable.setIPAddress("10.4.18.2");
        //NetworkTable.setUpdateRate(0.1);
        NetworkTable.initialize();
        //NetworkTable poop = NetworkTable.getTable("Root");
        
        r = new Robot();
        runGUI();
        r.startCompetition();

        
    }
    
    public static void setEnabled(boolean enable)
    {
        r.isDisabled = !enable;
    }
    
    public static void setTeleop()
    {
        r.isTest = false;
        r.isAutonomous = false;
    }
    
    public static void setAutonomous()
    {
        r.isTest = false;
        r.isAutonomous = true;
    }
    
    public static void setTest()
    {
        r.isTest = true;
        r.isAutonomous = false;
    }
}
