
package HR;

import AccountPayable.Payable;
import General.Service;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 *
 * @author HAE YEON KANG (Lucy)
 */
public abstract class Employee implements Payable {
   
    private String empID, firstName, lastName, position;
    private int age, year, month, day;
    private Date hireDate;
    private Calendar empCalendar;
    private SimpleDateFormat dateFormat;
    
    //constructors
    public Employee(){}
    public Employee(String firstName, String lastName, String position, 
                    int age, int year, int month, int day){
        
        this.firstName = firstName.toUpperCase();
        this.lastName = lastName.toUpperCase();
        this.position = position;
        this.empID = (Service.getEmpId()+"");
        this.age = age;
        this.year = year;
        this.month = month;
        this.day = day;
        this.hireDate = new GregorianCalendar(year, month-1, day).getTime();
        dateFormat = new SimpleDateFormat("yyyy-mm-dd");
        //this.hireDate = dateFormat.parse(hireDateString);
    }
    
    //Accessor Methods
    public String getFirstName(){
        return this.firstName;
    }
    
    public String getLastName(){
        return this.lastName;
    }
    
    public int getAge() {
        return age;
    }
     
    public String getPosition(){
        return this.position;
    }
    
    public String getEmpID(){
        return this.empID;
    }
    
    public int getYear(){
        return this.year;
    }
    
    public int getMonth(){
        return this.month;
    }
    
    public int getDay(){
        return this.day;
    }
    
    public Date getHireDate(){
        return this.hireDate;
        //return dateFormat.format(hireDate);
    }
    
    //public abstract double earnings();
    
    //Mutator Methods
    public boolean setFirstName(String firstName){
        boolean success;
        if(firstName.equals(""))
            success = false;
        else{
            this.firstName = firstName.toUpperCase();
            success = true;
        }    
        return success;
    }
    
    public boolean setLastName(String lastName){
        boolean success;
        if(lastName.equals(""))
            success = false;
        else{
            this.lastName = lastName.toUpperCase();
            success = true;
        }
        return success;
    }
    
    public boolean setPosition(String position){
        boolean success;
        if(position.equals(""))
            success = false;
        else{
            this.position = position;
            success = true;
        }
        return success;
    }
    
    public boolean setAge(int age){
        boolean success;
        if(age <= 16 || age > 110)
            success = false;
        else{
            this.age = age;
            success = true;
        }
        return success;
    }
    
    public boolean setEmpID(String empID){
        this.empID = empID;
        return true;
    }
    
    public boolean setYear(int year){
        boolean success;
        
        if(year < 1922 || year > 2017)
            success = false;
        else{
            this.year = year;
            setHireDate(this.year, this.month, this.day);//reset the calendar
            success = true;
        }
        return success;
    }
    
    public boolean setMonth(int month){
        boolean success;
        
        if(month < 1 || month > 12)
            success = false;
        else{
            this.month = month;
            setHireDate(this.year, this.month, this.day);//reset the calendar
            success = true;
        }
        return success;
    }
    
    public boolean setDay(int day){
        boolean success=false;
        
        if(month==1 || month==3 || month==5 || month==7 || month==8 || month==10 || month==12){
            if(day < 1 || day > 31)
                success = false;
        }
        else if(month==4 || month==6 || month==9 || month==11){
            if(day < 1 || day > 30)
                success = false;
        }
        else if (month == 2){
            if(day < 1 || day > 29)
                success = false;
        }
        else{
            this.day = day;
            setHireDate(this.year, this.month, this.day);//reset the calendar
            success = true;
        }
        return success;
    }
    
    public void setHireDate(int year, int month, int day){
        this.empCalendar.set(year, month-1, day);
        this.hireDate = empCalendar.getTime();
    }
    
    @Override
    public String toString(){
        String employeeInfo = "";
        
        employeeInfo += "\nName: \t\t" + getFirstName() + " " + getLastName();
        employeeInfo += "\nEmployee Type: \t" + this.getClass().getSimpleName();
        //employeeInfo += "\nID: \t\t" + getEmpID();
        employeeInfo += "\nAge: \t\t" + getAge();
        employeeInfo += "\nPosition: \t" + getPosition();
        employeeInfo += "\nHire-Date: \t" + getHireDate();
        
        return employeeInfo;
        
        /*return String.format(
                "********** Employee Information **************%n"
                + "Name: %s %s%n"
                + "Employee ID: %s%n"
                + "Position: %s%n" 
                + "Age: %d%n" 
                + "Hire Date: %s%n", getFirstName(), getLastName(), getEmpID(), getPosition(), getAge(), dateFormat.format(getHireDate()));
        */
    }
    /*
    public boolean setHireDate(String hireDateString){
        this.hireDate = dateFormat.parse(hireDateString);
        return true;
    }
    */

    
}
