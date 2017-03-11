package org.lasarobotics.frc2017.dataLogging;

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
    
    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    Date date = new Date();
    
    
    
    public void Logger(){
        Logger l = new Logger();
    }
    
    
    static ArrayList<Loggable> loggedSystems = new ArrayList();
    LinkedList<String> lines = new LinkedList(); //strings
    
    public static void addLog(Loggable l)
    {
        loggedSystems.add(l);
    }
    
    Object lock = new Object();
    
    public void log() {
        String line = "";
        
        for(Loggable o : loggedSystems)
        {
            line = line.concat(o.getValues());
        }
        
        line = line.concat("\n");
        
        synchronized(lock){
            lines.addLast(line);

        }
    }
    
    public void makeFile(){
        date = new Date();
        
        fileName = "LOG_" + dateFormat.format(date); 
        
        logFile = new File(fileName);
    }
    
    public void closeFile(){
        try {
            writer.close();
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(Logger.class.getName()).log(Level.SEVERE, null, ex);
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
            java.util.logging.Logger.getLogger(Logger.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
    
}
