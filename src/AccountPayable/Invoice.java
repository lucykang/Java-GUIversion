/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AccountPayable;

import java.util.*;

/**
 *
 * @author pc1
 */
public class Invoice implements Payable {
    private String itemName, description;
    private double moneyOwed;
    private int year, month, day;  
    private Date dueDate;
    private Calendar calendar;
    
    public Invoice(){}
    public Invoice(String itemName, String description, double moneyOwed, int year, int month, int day){
        this.itemName = itemName;
        this.description = description;
        this.moneyOwed = moneyOwed;
        this.year = year;
        this.month = month;
        this.day = day;
        dueDate = new GregorianCalendar(year, month-1, day).getTime();
    }
    
    @Override
    public double getPaymentAmount(){
        return this.moneyOwed;
    }
    
    @Override
    public String toString(){
        return "";
    }
}
