/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.lasarobotics.lib;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 *
 * @author Gijs Landwehr
 */
public class TorqueToggleTest {
    
    public TorqueToggleTest() {
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
     * Test of calc method, of class TorqueToggle.
     */
    @Test
    public void testCalc() {
        System.out.println("calc");
        TorqueToggle instance = null;//new TorqueToggle();
        instance.calc(true);
        assertEquals(instance.get(), true);
        instance.calc(false);
        instance.calc(true);
        assertEquals(instance.get(), false);
    }

    /**
     * Test of set method, of class TorqueToggle.
     */
    @Test
    public void testSet() {
        System.out.println("set");
        TorqueToggle instance = new TorqueToggle();
        instance.set(true);
        assertEquals(instance.get(), false);
        instance.set(false);
        assertEquals(instance.get(), false);
    }

    @Test
    @Ignore("because")
    public void testConstructor() {
        System.out.println("constructor");
        TorqueToggle instance = new TorqueToggle();
        assertEquals(instance.get(), false);
        instance = new TorqueToggle(true);
        assertEquals(instance.get(), true);
        instance = new TorqueToggle(false);
        assertEquals(instance.get(), false);
    }
    
}
