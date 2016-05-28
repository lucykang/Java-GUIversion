/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HR;

/**
 *
 * @author pc1
 */
public class Volunteer extends Employee {
    //variables
    public double hours;
    
    public Volunteer(){}
    public Volunteer(String firstName, String lastName, String position, 
                    int age, int year, int month, int day, double hours){
        super(firstName, lastName, position, age, year, month, day);
        setEmpID("V"+getEmpID());
        this.hours = hours;
    }
    
    public double getHours(){
        return this.hours;
    }
    
    public void setHours(double hours){
        this.hours = hours;
    }
    /*
    @Override
    public double earnings(){
        return 0;
    } 
    */
    
    @Override
    public double getPaymentAmount(){
        return 0;
    }
    
    @Override
    public String toString(){
        return super.toString()
        + "\nNumber of hours worked: \t" + getHours();
    }
}
