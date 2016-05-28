/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HR;

import HR.Employee;

/**
 *
 * @author pc1
 */
public class CommissionSalesEmployee extends Employee{
    //variables
    private double commissionRate, grossSales, salesTarget;
    
    //constructors
    public CommissionSalesEmployee(){}
    public CommissionSalesEmployee(String firstName, String lastName, String position, 
                    int age, int year, int month, int day, double commissionRate, double grossSales){
        super(firstName, lastName, position, age, year, month, day);
        //setPayRate(payRate);
        //setEmpID("CS"+getEmpID());
        this.commissionRate = commissionRate;
        this.grossSales = grossSales;
    }
    public CommissionSalesEmployee(String firstName, String lastName, String position, 
                    int age, int year, int month, int day, double commissionRate, double grossSales, double salesTarget){
        super(firstName, lastName, position, age, year, month, day);
        //setPayRate(payRate);
        setEmpID("CS"+getEmpID());
        this.commissionRate = commissionRate;
        this.grossSales = grossSales;
        this.salesTarget = salesTarget;
    }
    
    //Methods
    //Accessor Methods
    public double getCommissionRate(){
        return this.commissionRate;
    }
    
    public double getGrossSales(){
        return this.grossSales;
    }
    
    public double getSalesTarget(){
        return this.salesTarget;
    }

    //Mutator Methods
    public boolean setCommissionRate(double commissionRate) {
        boolean success;
        if(commissionRate<0.0 && commissionRate>1.0)
            success = false;
        else{
            this.commissionRate = commissionRate;
            success = true;
        }
        return success;
    }

    public void setGrossSales(double grossSales) {
        this.grossSales = grossSales;
    }

    public void setSalesTarget(double salesTarget) {
        this.salesTarget = salesTarget;
    }
    
    public double calculateCommission(){
        return grossSales * commissionRate;
    }
    /*
    @Override
    public double earnings(){
        return calculateCommission();
    }
    */
    @Override
    public double getPaymentAmount(){
        return calculateCommission();
    }
    
    @Override
    public String toString(){
        return super.toString()
        + "\nCommission Rate: \t" + getCommissionRate()
        + "\nGross Sales: \t\t" + getGrossSales();
        
    }

}
