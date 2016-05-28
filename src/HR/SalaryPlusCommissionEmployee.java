/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HR;

import HR.CommissionSalesEmployee;

/**
 *
 * @author pc1
 */
public class SalaryPlusCommissionEmployee extends CommissionSalesEmployee {
    //variables
    private double baseSalary;
    
    //constructors
    public SalaryPlusCommissionEmployee(){}
    public SalaryPlusCommissionEmployee(String firstName, String lastName, String position, 
                    int age, int year, int month, int day, double commissionRate, 
                    double grossSales, double salesTarget, double baseSalary){
        
        super(firstName, lastName, position, age, year, month, day, commissionRate, grossSales, salesTarget);
        this.baseSalary = baseSalary;
    }
    
    //methods
    public double getBaseSalary(){
        return this.baseSalary;
    }
    
    public void setBaseSalary(double baseSalary){
        this.baseSalary = baseSalary;
    }
    /*
    @Override
    public double earnings(){
        return baseSalary + calculateCommission();
    }
    */
    
    @Override
    public double getPaymentAmount(){
        return baseSalary + calculateCommission();
    }
    
    @Override
    public String toString(){
        
        return super.toString()+
        "\nBase Salary: \t\t" + getBaseSalary();
        
    }
}
