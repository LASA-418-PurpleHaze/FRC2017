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
    
    private File logFile;
    private String fileName;
    private FileWriter writer;
    private double startTime;
    
    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    Date date = new Date();
    
    
    
    public static void init(){
        lines = new LinkedList();
        loggedSystems = new ArrayList();
    }
    
    
    static ArrayList<Loggable> loggedSystems;
    static LinkedList<String> lines; //strings
    
    public static void addLog(Loggable l)
    {
        loggedSystems.add(l);
    }
    
    Object lock = new Object();
    
    public void log() {
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
    
    public void makeFile(){
        date = new Date();
        startTime = Double.MAX_VALUE;
        
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
        
        
        fileName = "LOG_" + dateFormat.format(date); 
        
        logFile = new File(fileName);
    }
    
    public void closeFile(){
        try {
            writer.close();
        } catch (IOException ex) {
            System.out.println("Botched closing log file.");
        }
    }
    
    
    
    public void writeToFile(){ 
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
