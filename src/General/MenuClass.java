/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package General;

/**
 *
 * @author pc1
 */
public class MenuClass {
    
    //Menu messages
    public static void mainMessage(){
        System.out.println("Please choose an option from the menu." + 
                    "\n1 - Employees" +
                    "\n2 - Products" +
                    "\n0 - Exit");
    }
    
    public static void empMessage(){
        System.out.println("Please choose an option from the menu." + 
                    "\n1 - Create new Employee" +
                    "\n2 - Work with existing Employee" +
                    "\n0 - Exit");
    }
    
    public static void existingEmpMessage(){
        System.out.println("Please choose an option from the menu."
                + "\n1 - Edit employee information"
                + "\n2 - Delete employee"
                + "\n0 - Cancel");
    }
    
    public static void whichEmpInfo(){
        System.out.println("\nPlease choose an option from the menu."
                + "\n1 - First name"
                + "\n2 - Last name"
                + "\n3 - Position"
                + "\n4 - Age"
                + "\n5 - Hire year"
                + "\n6 - Hire month"
                + "\n7 - Hire day"
                + "\n8 - Commission rate"
                + "\n0 - exit");
    }
    
    public static void prodMessage(){
        System.out.println("Please choose an option from the menu." + 
                    "\n1 - Create new Product" +
                    "\n2 - Work with existing Product" +
                    "\n0 - Exit");
    }
    
    public static void existingProdMessage(){
        System.out.println("Please choose an option from the menu."
                + "\n1 - Edit Product information"
                + "\n2 - Delete Product"
                + "\n0 - Cancel");
    }
    
    public static void whichProdInfo(){
        System.out.println("\nPlease choose an option from the menu."
                + "\n1 - Product Name"
                + "\n2 - Product Category"
                + "\n3 - Product Description"
                + "\n4 - Product Part#"
                + "\n5 - Product Manufacturer"
                + "\n6 - Product Cost"
                + "\n7 - Product Price"
                + "\n8 - Product Markup"
                + "\n0 - exit");
    }
    
    //general messages
    public static void changeSuccess(){
        System.out.println("The change was successful!");
    }
    
    //Error messages
    public static String errMessage(String fieldName){
        //System.out.println("Invalid input!");
        return "Invalid input in " + fieldName + " field." ;
    }
    
    public static String noLetters(String fieldName){
        //System.out.println("Invalid input. Letters cannot be accepted. Please enter number.");
        return "Invalid input in " + fieldName + " field. Letters cannot be accepted. Please enter number.";
    }
    
}
