package org.lasarobotics.frc2017.dataLogging;

import edu.wpi.first.wpilibj.Timer;
import java.util.ArrayList;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.logging.Level;

public class Logger{
    
    private static File logFile;
    private static String fileName;
    private static FileWriter writer;
    private static double startTime;
    
    static Object lock = new Object();
    
    static DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    static Date date = new Date();
    
    static ArrayList<Loggable> loggedSystems;
    static LinkedList<String> lines; //strings
    
    
    
    public static void init(){
        lines = new LinkedList();
        loggedSystems = new ArrayList();
    }
    
    public static void addLog(Loggable l){
        loggedSystems.add(l);
    }
    
    public static void log() {
        String line = Timer.getFPGATimestamp() - startTime +"";
        
        if(startTime == Double.MAX_VALUE){
            startTime = Timer.getFPGATimestamp();
        }
        
        for(Loggable o : loggedSystems){
            line = line.concat(", ");
            line = line.concat(o.getValues());
        }
        
        line = line.concat("\n");
        
        synchronized(lock){
            lines.addLast(line);

        }
    }
    
    public static void makeFile(){
        date = new Date();
        startTime = Double.MAX_VALUE;
        
        fileName = "LOG_" + dateFormat.format(date); 
        logFile = new File(fileName);
        
        String line ="Time";
       
        try {
            writer = new FileWriter(logFile);
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(Logger.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        for(Loggable o : loggedSystems){
            line = line.concat(", ");
            line = line.concat(o.getNames());
        }        
        
        
    }
    
    public static void closeFile(){
        try {
            writer.close();
        } catch (IOException ex) {
            System.out.println("Botched closing log file.");
        }
    }
    
    
    
    public static void writeToFile(){ 
        String lineToWrite;
        synchronized(lock){
            lineToWrite = lines.removeFirst();
        }
        
        try {
            writer.write(lineToWrite);
        } catch (IOException ex) {
            System.out.println("Botched writing to log file.");
        }      
    }
}
