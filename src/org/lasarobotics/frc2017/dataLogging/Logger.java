package org.lasarobotics.frc2017.dataLogging;

import java.util.ArrayList;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class Logger{
    
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
    
    public void writeToFile(){
        File logFile = new File("logfile.cvs");
        if(File.createNewFile();)

        
        for(String line : lines){
            
        }
    }
    
    
    
}
