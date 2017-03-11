package org.lasarobotics.frc2017.dataLogging;

import java.util.ArrayList;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Logger{
    
    private File logFile;
    private String fileName;
    
    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    Date date = new Date();
    
    
    
    public void Logger(){
        Logger l = new Logger();
    }
    
    
    static ArrayList<Loggable> loggedSystems = new ArrayList();
    ArrayList<String> lines = new ArrayList(); //strings
    
    public static void addLog(Loggable l)
    {
        loggedSystems.add(l);
    }
    
    
    public void log() {
        String line = "";
        
        for(Loggable o : loggedSystems)
        {
            line.concat(o.getValues());
        }
        
        lines.add(line);
    }
    
    public void makeFile(){
        fileName = "LOG_" + dateFormat.format(date); 
        
        logFile = new File(fileName);
    }
    
    public void closeFile(){
        
    }
    
    
    
    public void writeToFile(){        
        
        
    }
    
    
    
}
