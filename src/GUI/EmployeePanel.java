
package GUI;

import FileIO.ErrorDataLog;
import General.MenuClass;
import HR.CommissionSalesEmployee;
import java.awt.*;
import java.awt.event.*; //subpackage. you have to specifically import it.
import java.io.*;
import java.sql.SQLException;
import javax.swing.*;
import java.util.*;
import static javax.swing.JOptionPane.*;

/**
 * Final Assignment (Group assignment)
 * @version 2016-04-20
 * @author Haeyeon Kang, Cindy Diaz
 */

public class EmployeePanel extends JPanel{
    
    //components
    //panels
    private JPanel firstInnerPanel = new JPanel();  //main panel
    private JPanel secondInnerPanel = new JPanel(); //panel for hiredate
    private JPanel btnPanel = new JPanel();         //panel for buttons
    
    //labels
    private JLabel firstNameLabel, lastNameLabel, ageLabel, positionLabel, hiredateLabel, 
            commissionRateLabel, grossSalesLabel;
    
    //textfields
    private JTextField firstNameField, lastNameField, ageField, positionField, yearField, 
            monthField, dayField, commissionRateField, grossSalesField;
    
    //buttons
    private JButton submitBtn, clearAllBtn;
    
    //for writing error log to a file
    private ErrorDataLog errLog;
    private StringWriter exceptionData;
    
    public EmployeePanel() {
        errLog = new ErrorDataLog();
        exceptionData = new StringWriter();
        
        setLayout(new BorderLayout());
        
        add(firstInnerPanel, BorderLayout.CENTER);
        add(btnPanel, BorderLayout.SOUTH);
        
        mainPanel();
        hireDatePanel();
        btnPanel();
        eventHandlers();
    }
    
    private void hireDatePanel(){
        secondInnerPanel.setLayout(new GridLayout(1,3));
        
        //create components
        yearField = new JTextField("yyyy",4);
        monthField = new JTextField("mm", 2);
        dayField = new JTextField("dd", 2);
        
        //add to the panel
        secondInnerPanel.add(yearField);
        secondInnerPanel.add(monthField);
        secondInnerPanel.add(dayField);
        
    }
    
    private void mainPanel(){
        //set layout
        firstInnerPanel.setLayout(new GridLayout(13,2));
        
        //create labels
        firstNameLabel = new JLabel("First Name: ");
        lastNameLabel = new JLabel("Last Name: ");
        ageLabel = new JLabel("Age: ");
        positionLabel = new JLabel("Position: ");
        hiredateLabel = new JLabel("Hire Date: ");
        commissionRateLabel = new JLabel("Commission Rate: ");
        grossSalesLabel = new JLabel("Gross Sales: ");
        
        //create textfields
        firstNameField = new JTextField(20);
        lastNameField = new JTextField(30);
        ageField = new JTextField(2);
        positionField = new JTextField(30);
        
        commissionRateField = new JTextField(3); 
        grossSalesField = new JTextField(5);
        
        //set border
        setBorder(BorderFactory.createTitledBorder("Employee Information"));
        
        //add components
        firstInnerPanel.add(firstNameLabel);
        firstInnerPanel.add(firstNameField);
        firstInnerPanel.add(lastNameLabel);
        firstInnerPanel.add(lastNameField);
        firstInnerPanel.add(ageLabel);
        firstInnerPanel.add(ageField);
        firstInnerPanel.add(positionLabel);
        firstInnerPanel.add(positionField);
        firstInnerPanel.add(commissionRateLabel);
        firstInnerPanel.add(commissionRateField);
        firstInnerPanel.add(grossSalesLabel);
        firstInnerPanel.add(grossSalesField);
        firstInnerPanel.add(hiredateLabel);
        firstInnerPanel.add(secondInnerPanel);
    }
    
    private void btnPanel(){
        btnPanel.setLayout(new FlowLayout());
        
        //create buttons
        submitBtn = new JButton("Submit");
        clearAllBtn = new JButton("Clear All");
        
        btnPanel.add(submitBtn);
        btnPanel.add(clearAllBtn);
    }
    
    private void eventHandlers(){
        //add action listener
        submitBtn.addActionListener(new SubmitHandler());
        clearAllBtn.addActionListener(new ClearAllButtonHandler()); 
        
    }
    
    private void clearAllFields(){
        firstNameField.setText("");
        lastNameField.setText("");
        ageField.setText("");
        positionField.setText("");
        yearField.setText("yyyy");
        monthField.setText("mm");
        dayField.setText("dd");
        commissionRateField.setText("");
        grossSalesField.setText("");
    }
    
    private class ClearAllButtonHandler implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            clearAllFields();
        }
    }
    
    private class SubmitHandler implements ActionListener{
        
        @Override
        public void actionPerformed(ActionEvent e){
            //initialise variables to store the value from textfields
            String firstName = "", lastName ="", position="";
            int age=0, year=0, month=0, day=0;
            double commissionRate=0.0, grossSales=0.0;
            CommissionSalesEmployee ce;
            GregorianCalendar calendar = new GregorianCalendar();
            
            boolean canSubmit = false;
            
            //validation fields
            //first name
            try{
            firstName = firstNameField.getText();
            
            if(firstName.equals(""))
                throw new IllegalArgumentException();
            
            canSubmit = true;
            }
            catch(IllegalArgumentException err){
                JOptionPane.showMessageDialog(null, "The first name field is empty.");
                
                //error handling
                //save log to database
                errLog.errHandling(err); 
                
                //save to file
                err.printStackTrace(new PrintWriter(exceptionData));
                errLog.appendData(exceptionData.toString(), new GregorianCalendar().getTime());
                
                //move focus to the field
                firstNameField.requestFocusInWindow();
                
                //it won't create object
                canSubmit = false;
            }
            catch(Exception err){
                JOptionPane.showMessageDialog(null, MenuClass.errMessage("First Name"));
                errLog.errHandling(err);
                err.printStackTrace(new PrintWriter(exceptionData));
                errLog.appendData(exceptionData.toString(), new GregorianCalendar().getTime());
                firstNameField.requestFocusInWindow();
                canSubmit = false;
            }
            
            //last name
            //only validate the next input if the first input was entered right to avoid multiple messages.
            if(canSubmit == true){
                try{
                    lastName = lastNameField.getText();
                    
                    if(lastName.equals(""))
                        throw new IllegalArgumentException();
            
                    canSubmit = true;
                }
                catch(IllegalArgumentException err){
                    JOptionPane.showMessageDialog(null, "The last name field is empty.");
                    errLog.errHandling(err);
                    err.printStackTrace(new PrintWriter(exceptionData));
                    errLog.appendData(exceptionData.toString(), new GregorianCalendar().getTime());
                    lastNameField.requestFocusInWindow();
                    canSubmit = false;
                }
                catch(Exception err){
                    JOptionPane.showMessageDialog(null, MenuClass.errMessage("Last Name"));
                    errLog.errHandling(err);
                    err.printStackTrace(new PrintWriter(exceptionData));
                    errLog.appendData(exceptionData.toString(), new GregorianCalendar().getTime());
                    lastNameField.requestFocusInWindow();
                    canSubmit = false;
                }
            }
            
            //position
            if(canSubmit == true){
                try{
                    position = positionField.getText();
                    
                    if(position.equals(""))
                        throw new IllegalArgumentException();
                }
                catch(IllegalArgumentException err){
                    JOptionPane.showMessageDialog(null, "The Position field is empty.");
                    errLog.errHandling(err);
                    err.printStackTrace(new PrintWriter(exceptionData));
                    errLog.appendData(exceptionData.toString(), new GregorianCalendar().getTime());
                    positionField.requestFocusInWindow();
                    canSubmit = false;
                }
                catch(Exception err){
                    JOptionPane.showMessageDialog(null, MenuClass.errMessage("Position"));
                    errLog.errHandling(err);
                    err.printStackTrace(new PrintWriter(exceptionData));
                    errLog.appendData(exceptionData.toString(), new GregorianCalendar().getTime());
                    positionField.requestFocusInWindow();
                    canSubmit = false;
                }
            }
            
            //age
            if(canSubmit == true){
                try{
                    age = Integer.parseInt(ageField.getText());
                
                    if(age <= 16 || age > 99)
                        throw new IllegalArgumentException();

                    canSubmit = true;
                }
                catch(InputMismatchException err){
                    JOptionPane.showMessageDialog(null, MenuClass.noLetters("Age"));
                    errLog.errHandling(err);
                    err.printStackTrace(new PrintWriter(exceptionData));
                    errLog.appendData(exceptionData.toString(), new GregorianCalendar().getTime());
                    ageField.requestFocusInWindow();
                    canSubmit = false;
                }
                catch(IllegalArgumentException err){
                    JOptionPane.showMessageDialog(null, "Invalid Number. The age should be >= 16 and < 99");
                    errLog.errHandling(err);
                    err.printStackTrace(new PrintWriter(exceptionData));
                    errLog.appendData(exceptionData.toString(), new GregorianCalendar().getTime());
                    ageField.requestFocusInWindow();
                    canSubmit = false;
                }
                catch(Exception err){
                    JOptionPane.showMessageDialog(null, MenuClass.errMessage("Age"));
                    errLog.errHandling(err);
                    err.printStackTrace(new PrintWriter(exceptionData));
                    errLog.appendData(exceptionData.toString(), new GregorianCalendar().getTime());
                    ageField.requestFocusInWindow();
                    canSubmit = false;
                }
            }
            
            //year
            if(canSubmit == true){
                try{
                    year = Integer.parseInt(yearField.getText());
                    
                    if(year < 1922 || year > calendar.get(Calendar.YEAR))
                        throw new IllegalArgumentException();

                    canSubmit = true;
                }
                catch(InputMismatchException err){
                    JOptionPane.showMessageDialog(null, MenuClass.noLetters("Year"));
                    errLog.errHandling(err);
                    err.printStackTrace(new PrintWriter(exceptionData));
                    errLog.appendData(exceptionData.toString(), new GregorianCalendar().getTime());
                    yearField.requestFocusInWindow();
                    canSubmit = false;//
                }
                catch(IllegalArgumentException err){
                    JOptionPane.showMessageDialog(null,"Invalid Number. The year should be between 1923 and current year inclusive.");
                    errLog.errHandling(err);
                    err.printStackTrace(new PrintWriter(exceptionData));
                    errLog.appendData(exceptionData.toString(), new GregorianCalendar().getTime());
                    yearField.requestFocusInWindow();
                    canSubmit = false;
                }
                catch(Exception err){
                    JOptionPane.showMessageDialog(null, MenuClass.errMessage("Year"));
                    errLog.errHandling(err);
                    err.printStackTrace(new PrintWriter(exceptionData));
                    errLog.appendData(exceptionData.toString(), new GregorianCalendar().getTime());
                    yearField.requestFocusInWindow();
                    canSubmit = false;
                }
            }
            
            //month
            if(canSubmit == true){
                try{
                    month = Integer.parseInt(monthField.getText());
                    
                    if(month < 1 || month > 12)
                        throw new IllegalArgumentException(); 
                    
                    canSubmit = true;
                }
                catch(InputMismatchException err){
                    JOptionPane.showMessageDialog(null, MenuClass.noLetters("Month"));
                    errLog.errHandling(err);
                    err.printStackTrace(new PrintWriter(exceptionData));
                    errLog.appendData(exceptionData.toString(), new GregorianCalendar().getTime());
                    monthField.requestFocusInWindow();
                    canSubmit = false;
                }
                catch(IllegalArgumentException err){
                    JOptionPane.showMessageDialog(null, "Invalid Number. The month should be between 1 and 12 inclusive.");
                    errLog.errHandling(err);
                    err.printStackTrace(new PrintWriter(exceptionData));
                    errLog.appendData(exceptionData.toString(), new GregorianCalendar().getTime());
                    monthField.requestFocusInWindow();
                    canSubmit = false;
                }
                catch(Exception err){
                    JOptionPane.showMessageDialog(null, MenuClass.errMessage("Month"));
                    errLog.errHandling(err);
                    err.printStackTrace(new PrintWriter(exceptionData));
                    errLog.appendData(exceptionData.toString(), new GregorianCalendar().getTime());
                    monthField.requestFocusInWindow();
                    canSubmit = false;
                }
            }
            
            //day
            if(canSubmit == true){
                try{
                    day = Integer.parseInt(dayField.getText());
                    
                    //validate the value depending on which month it was entered.
                    if(month==1 || month==3 || month==5 || month==7 || month==8 || month==10 || month==12){
                        if(day < 1 || day > 31)
                            throw new IllegalArgumentException();                   
                        canSubmit = true;
                    }
                    else if(month==4 || month==6 || month==9 || month==11){
                        if(day < 1 || day > 30)
                            throw new IllegalArgumentException();                   
                        canSubmit = true;
                    }
                    else{
                        if(day < 1 || day > 29)
                            throw new IllegalArgumentException();
                        canSubmit = true;
                    }
                    
                }
                catch(InputMismatchException err){
                    JOptionPane.showMessageDialog(null, MenuClass.noLetters("Day"));
                    errLog.errHandling(err);
                    err.printStackTrace(new PrintWriter(exceptionData));
                    errLog.appendData(exceptionData.toString(), new GregorianCalendar().getTime());
                    dayField.requestFocusInWindow();
                    canSubmit = false;
                }
                catch(IllegalArgumentException err){
                    JOptionPane.showMessageDialog(null, "Invalid Number."
                                        + "\nDays of the month(Jan, Mar, May, Jul, Aug, Oct, Dec): \tfrom 1 to 31"
                                        + "\nDays of the month(Apr, Jun, Sep, Nov): \t\t\tfrom 1 to 30"
                                        + "\nDays of the month(Feb): \t\t\t\tfrom 1 to 28 or 29");
                    errLog.errHandling(err);
                    err.printStackTrace(new PrintWriter(exceptionData));
                    errLog.appendData(exceptionData.toString(), new GregorianCalendar().getTime());
                    dayField.requestFocusInWindow();
                    canSubmit = false;
                }
                catch(Exception err){
                    JOptionPane.showMessageDialog(null, MenuClass.errMessage("Day"));
                    errLog.errHandling(err);
                    err.printStackTrace(new PrintWriter(exceptionData));
                    errLog.appendData(exceptionData.toString(), new GregorianCalendar().getTime());
                    dayField.requestFocusInWindow();
                    canSubmit = false;
                }
            }
            
            //commission rate
            if(canSubmit == true){
                try{
                    commissionRate = Double.parseDouble(commissionRateField.getText());
                    if(commissionRate < 0 || commissionRate > 1)
                        throw new IllegalArgumentException();
                    canSubmit = true;
                }
                catch(InputMismatchException err){
                    JOptionPane.showMessageDialog(null, MenuClass.noLetters("Commission Rate"));
                    errLog.errHandling(err);
                    err.printStackTrace(new PrintWriter(exceptionData));
                    errLog.appendData(exceptionData.toString(), new GregorianCalendar().getTime());
                    commissionRateField.requestFocusInWindow();
                    canSubmit = false;
                }
                catch(IllegalArgumentException err){
                    JOptionPane.showMessageDialog(null, "Invalid Number. The commission rate is between 0.0 and 1.0. Ex) 10% is 0.1");
                    errLog.errHandling(err);
                    err.printStackTrace(new PrintWriter(exceptionData));
                    errLog.appendData(exceptionData.toString(), new GregorianCalendar().getTime());
                    commissionRateField.requestFocusInWindow();
                    canSubmit = false;
                }
                catch(Exception err){
                    JOptionPane.showMessageDialog(null, MenuClass.errMessage("Commission Rate"));
                    errLog.errHandling(err);
                    err.printStackTrace(new PrintWriter(exceptionData));
                    errLog.appendData(exceptionData.toString(), new GregorianCalendar().getTime());
                    commissionRateField.requestFocusInWindow();
                    canSubmit = false;
                }
            }
            
            //gross sales
            if(canSubmit == true){
                try{
                    grossSales = Double.parseDouble(grossSalesField.getText());
                    if(grossSales < 0)
                        throw new IllegalArgumentException();
                    
                    canSubmit = true;
                }
                catch(InputMismatchException err){
                    JOptionPane.showMessageDialog(null, MenuClass.noLetters("Gross Sales"));
                    errLog.errHandling(err);
                    err.printStackTrace(new PrintWriter(exceptionData));
                    errLog.appendData(exceptionData.toString(), new GregorianCalendar().getTime());
                    grossSalesField.requestFocusInWindow();
                    canSubmit = false;
                }
                catch(IllegalArgumentException err){
                    JOptionPane.showMessageDialog(null, "Invalid Number. The Gross Sales cannot be negative value.");
                    errLog.errHandling(err);
                    err.printStackTrace(new PrintWriter(exceptionData));
                    errLog.appendData(exceptionData.toString(), new GregorianCalendar().getTime());
                    grossSalesField.requestFocusInWindow();
                    canSubmit = false;
                }
                catch(Exception err){
                    JOptionPane.showMessageDialog(null, MenuClass.errMessage("Gross Sales"));
                    errLog.errHandling(err);
                    err.printStackTrace(new PrintWriter(exceptionData));
                    errLog.appendData(exceptionData.toString(), new GregorianCalendar().getTime());
                    grossSalesField.requestFocusInWindow();
                    canSubmit = false;
                }
            }
            
            //save to database when all the fields are set
            if(canSubmit == true){
                ce = new CommissionSalesEmployee(firstName, lastName, position, age, year, month, day, commissionRate, grossSales);
                int choice = JOptionPane.showConfirmDialog(null, ce.toString() + "\nDo you really want to save this employee information?", "Registration Confirmation", YES_NO_OPTION, WARNING_MESSAGE);
                if(choice == JOptionPane.YES_OPTION){
                    try{
                    JDBC.JDBC.insertEmpData(firstName, lastName, age, position, year, month, day, grossSales, commissionRate);
                    JOptionPane.showMessageDialog(null, "Employee registration was successful.");
                    
                    //clear all the fields
                    clearAllFields();
                    }
                    catch(SQLException err){
                        errLog.errHandling(err);
                        err.printStackTrace(new PrintWriter(exceptionData));
                        errLog.appendData(exceptionData.toString(), new GregorianCalendar().getTime());
                    }
                }
                else{
                    JOptionPane.showMessageDialog(null, "The registration was cancelled.");
                }
            }
            else
                JOptionPane.showMessageDialog(null, "Employee registration was not successful.");
        }
    }
}
