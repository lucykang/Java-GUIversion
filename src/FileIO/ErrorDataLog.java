
package FileIO;

import java.io.*;
import java.sql.SQLException;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Final Assignment (Group assignment)
 * @version 2016-04-20
 * @author Haeyeon Kang, Cindy Diaz
 */

public class ErrorDataLog {
    //StringWriter exceptionData;
    
    private void checkDirectory(){
        try{
            //directory path that will save error logs
            File directory = new File("c:/logs");
            
            if(!directory.exists()){
                //create directory if it doesn't exist
                if(directory.mkdir())
                    System.out.println("Directory Created!");
                else
                    System.out.println("Failed to create directory!");
            }
        }catch(SecurityException err){
            //save only to the database since there is a problem creating directory to save the log file.
            errHandling(err);
        }catch(Exception err){
            errHandling(err);
        }
    }
    
    public void appendData(String data, Date currentDateTime){ 
       
        //check if directory exists
        checkDirectory();
        
        try{
            //path for the file to save logs
            File file = new File("c:/logs/errorlog.txt");
            
            //if file doesn't exist, it will create new file.
            if(!file.exists()){
                file.createNewFile();
            }
            
            //create the file writer 
            //true: yes append. false: no you can't append and only overwrite th e file
            FileWriter fWriter = new FileWriter(file, true);
            
            BufferedWriter bWriter = new BufferedWriter(fWriter); //if you use file writer directly, it causes more overhead.
            
            bWriter.write("******************** ERROR LOG START ******************" 
                    + currentDateTime.toString()
                    + data
                    + "******************** ERROR LOG END ******************\n");
            bWriter.newLine();
            bWriter.close();
            
        }catch(IOException err){
            errHandling(err);
        }catch(Exception err){
            errHandling(err);
        }
    }//end of appendData()
    
    public void readDataBR(){ //reading by character (not by byte)
        
        BufferedReader br = null;
        
        try{
            String currentLine;
            br = new BufferedReader(new FileReader("c:/logs/errorlog.txt"));
            
            currentLine = br.readLine();
            
            while(currentLine != null){
                System.out.println(currentLine);
                currentLine = br.readLine();
            }
            
            br.close();
            
        }catch(IOException err){
            errHandling(err);
        }catch(Exception err){
            errHandling(err);
        }
    }//end of readDataBR()
    
    public void errHandling(Exception err){
        
        //saving to database
        StackTraceElement[] stack = err.getStackTrace();
        String errorStr = "Type: " + err.toString() + "\nDetail: ";
        for(StackTraceElement ste : stack){
            errorStr += ste.toString();
        }
        try{
            JDBC.JDBC.insertErrData(errorStr);
        }
        catch(SQLException sqlerr){
            //save it to error log file 
            StringWriter exceptionData = new StringWriter();
            sqlerr.printStackTrace(new PrintWriter(exceptionData));
            appendData(exceptionData.toString(), new GregorianCalendar().getTime());
        }
        //saving to log file
        StringWriter exceptionData = new StringWriter();
        err.printStackTrace(new PrintWriter(exceptionData));
        appendData(exceptionData.toString(), new GregorianCalendar().getTime());
    }//end of errHandling
}
