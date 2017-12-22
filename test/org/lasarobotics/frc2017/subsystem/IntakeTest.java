/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lasarobotics.frc2017.subsystem;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.lasarobotics.frc2017.ConstantsList;

/**
 *
 * @author Gijs Landwehr
 */
public class IntakeTest {
    
    public IntakeTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getMode method, of class Intake.
     */
    @Test
    public void testGetMode() {
        System.out.println("getMode");
        Intake instance = Intake.getInstance();
        instance.setMode(Intake.Mode.OFF);
        assertEquals(Intake.Mode.OFF, instance.getMode());
        instance.setMode(Intake.Mode.FEEDING);
        assertEquals(Intake.Mode.FEEDING, instance.getMode());
        instance.setMode(Intake.Mode.INTAKING);
        assertEquals(Intake.Mode.INTAKING, instance.getMode());
        instance.setMode(Intake.Mode.OUTTAKING);
        assertEquals(Intake.Mode.OUTTAKING, instance.getMode());
    }

    /**
     * Test of getInstance method, of class Intake.
     */
    @Test
    public void testGetInstance() {
        System.out.println("getInstance");
        Intake intake1 = Intake.getInstance();
        Intake intake2 = Intake.getInstance();
        
        assertNotNull(intake1);
        assertNotNull(intake2);
        assertSame(intake1, intake2);
    }

    /**
     * Test of run method, of class Intake.
     */
    @Test
    public void testRun() {
        System.out.println("run");
        Intake instance = Intake.getInstance();
        instance.setMode(Intake.Mode.FEEDING);
        instance.run();
        assertEquals(instance.hardware.getGearIntakeAngle(), ConstantsList.G_intake_angle.getValue(), 0.001);
    }
    
}
