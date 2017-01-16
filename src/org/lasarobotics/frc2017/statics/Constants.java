package org.lasarobotics.frc2017.statics;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.LinkedList;

public class Constants {

    public static LinkedList<Constant> ConstantsList = new LinkedList<>();
    private static File constantsFile;

    public static void init() {
        constantsFile = new File("/home/admin/params.txt");

        try {
            constantsFile.createNewFile();
            SmartDashboard.putNumber("yes", 1);
        } catch (Exception e) {

        }
    }

    public static void load() {
        try (BufferedReader br = new BufferedReader(new FileReader(constantsFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                int pos = line.indexOf(" ");
                if (pos != -1) {
                    for (Constant c : ConstantsList) {
                        SmartDashboard.putNumber("key", c.value);
                        if (c.getName().equals(line.substring(0, pos))) {
                            c.value = Double.parseDouble(line.substring(pos));

                            break;
                        }
                    }
                } else {
                    System.out.println("Invalid line!");
                }
            }
        } catch (Exception e) {
            System.out.println("Messed up readin paramters");
        }
    }

    public static class Constant {

        private double value;
        private String name;

        public Constant(String nm, double val) {
            this.name = nm;
            this.value = val;
            ConstantsList.add(this);
        }

        public double getValue() {
            return value;
        }

        public String getName() {
            return name;
        }

        public void setValue(double value) {
            this.value = value;
        }
    }
}
