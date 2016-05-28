
package General;


/**
 *
 * @author HAE YEON KANG (Lucy)
 */

import AccountPayable.Invoice;
import AccountPayable.Payable;
import HR.*;
import Inventory.*;
import java.util.*;

public class RetailCompany {
/*
    
    previous menu driven cli replaced by GUI interface
    
    
    public static void main(String[] args) {
        //util variables
        boolean keepGoing = true;
        Scanner read = new Scanner(System.in);
        Scanner empRead = new Scanner(System.in);
        Scanner prodRead = new Scanner(System.in);
        
        //var to read user selection
        int input;
        
        //object to hold data
        ArrayList<Employee> employees = new ArrayList<>();
        ArrayList<Product> products = new ArrayList<>();
        
        System.out.println("WELCOME TO JAVA R US!");
        
        //menu driven do while loop
        do{
            //display main message
            MenuClass.mainMessage();
           
            input = read.nextInt();
            
            if(input == 1){ // emp option
                //local variables for Employee
                int empInput;
                boolean empKeepGoing = true;
                
                //emp decision nested loop
                do{
                    
                    //display emp message
                    MenuClass.empMessage();
                    
                    //read input
                    empInput = empRead.nextInt();
                    empRead.nextLine();
                    if(empInput == 1){ //new employee
                        //temp variables for emp object creation
                        String firstName, lastName, position;
                        int age, year, month, day;
                        double commissionRate, grossSales, salesTarget;
                        CommissionSalesEmployee comEmp;
                        
                        boolean empFlag = true;
                        
                        do{
                            try{
                                //ask for user input
                                System.out.print("Enter first name: ");
                                firstName = empRead.nextLine();
                                if(firstName.equals(""))
                                    throw new Exception();
                                empFlag = false;
                            }
                            catch(Exception e){
                                MenuClass.errMessage();
                                e.printStackTrace();
                                firstName="";
                                empFlag = true;
                            }
                        }while(empFlag);
                        
                        do{
                            try{
                                System.out.print("Enter last name: ");
                                lastName = empRead.nextLine();
                                if(lastName.equals(""))
                                    throw new Exception();
                                empFlag = false;
                            }
                            catch(Exception e){
                                MenuClass.errMessage();
                                e.printStackTrace();
                                lastName="";
                                empFlag = true;
                            }
                        }while(empFlag);
                        
                        do{
                            try{
                                System.out.print("Enter position: ");
                                position = empRead.nextLine();
                                if(position.equals(""))
                                    throw new Exception();
                                empFlag = false;
                            }
                            catch(Exception e){
                                MenuClass.errMessage();
                                e.printStackTrace();
                                position="";
                                empFlag = true;
                            }
                        }while(empFlag);                        
                        
                        do{
                            try{
                                System.out.print("Enter age: ");
                                age = empRead.nextInt();
                                if(age <= 16 || age > 110)
                                    throw new IllegalArgumentException();
                                empFlag = false;
                            }
                            catch(InputMismatchException ime){
                                MenuClass.noLetters();
                                ime.printStackTrace();
                                age=0; //reset the value so it won't keep looping by itself.
                                empFlag = true;
                                empRead.nextLine();//prevent the nextInt passing the value by itself when going back to try block.
                            }
                            catch(IllegalArgumentException iae){
                                System.out.println("Invalid Number. The age should be >= 16 and < 110");
                                iae.printStackTrace();
                                age=0;
                                empFlag = true;
                                empRead.nextLine();
                            }
                            catch(Exception e){
                                MenuClass.errMessage();
                                e.printStackTrace();
                                age=0;
                                empFlag = true;
                                empRead.nextLine();
                            }
                        }while(empFlag);
                        
                        do{
                            try{
                                System.out.print("Enter hire year: ");
                                year = empRead.nextInt();
                                if(year < 1922 || year > 2017)
                                    throw new IllegalArgumentException();
                                empFlag = false;
                            }
                            catch(InputMismatchException ime){
                                MenuClass.noLetters();
                                ime.printStackTrace();
                                year=0; //reset the value so it won't keep looping by itself.
                                empFlag = true;
                                empRead.nextLine();//prevent the nextInt passing the value by itself when going back to try block.
                            }
                            catch(IllegalArgumentException iae){
                                System.out.println("Invalid Number. The year should be between 1923 and 2016 inclusive.");
                                iae.printStackTrace();
                                year=0;
                                empFlag = true;
                                empRead.nextLine();
                            }
                            catch(Exception e){
                                MenuClass.errMessage();
                                e.printStackTrace();
                                year=0;
                                empFlag = true;
                                empRead.nextLine();
                            }
                        }while(empFlag);
                        
                        do{
                            try{
                                System.out.print("Enter hire month: ");
                                month = empRead.nextInt();
                                if(month < 1 || month > 12)
                                    throw new IllegalArgumentException();                   
                                empFlag = false;
                            }
                            catch(InputMismatchException ime){
                                MenuClass.noLetters();
                                ime.printStackTrace();
                                month=0; //reset the value so it won't keep looping by itself.
                                empFlag = true;
                                empRead.nextLine();//prevent the nextInt passing the value by itself when coming back from catch.
                            }
                            catch(IllegalArgumentException iae){
                                System.out.println("Invalid Number. The month should be between 1 and 12 inclusive.");
                                iae.printStackTrace();
                                month=0;
                                empFlag = true;
                                empRead.nextLine();
                            }
                            catch(Exception e){
                                MenuClass.errMessage();
                                e.printStackTrace();
                                month=0;
                                empFlag = true;
                                empRead.nextLine();
                            }
                        }while(empFlag);
                        
                        do{
                            try{
                                System.out.print("Enter hire day: ");
                                day = empRead.nextInt();
                                
                                //validate the value depending on which month it was entered.
                                if(month==1 || month==3 || month==5 || month==7 || month==8 || month==10 || month==12){
                                    if(day < 1 || day > 31)
                                        throw new IllegalArgumentException();                   
                                    empFlag = false;
                                }
                                else if(month==4 || month==6 || month==9 || month==11){
                                    if(day < 1 || day > 30)
                                        throw new IllegalArgumentException();                   
                                    empFlag = false;
                                }
                                else{
                                    if(day < 1 || day > 29)
                                        throw new IllegalArgumentException();
                                    empFlag = false;
                                }
                            }
                            catch(InputMismatchException ime){
                                MenuClass.noLetters();
                                ime.printStackTrace();
                                day=0; //reset the value so it won't keep looping by itself.
                                empFlag = true;
                                empRead.nextLine();//prevent the nextInt passing the value by itself when coming back from catch.
                            }
                            catch(IllegalArgumentException iae){
                                System.out.println("Invalid Number."
                                        + "\nDays of the month(Jan, Mar, May, Jul, Aug, Oct, Dec): \tfrom 1 to 31"
                                        + "\nDays of the month(Apr, Jun, Sep, Nov): \t\t\tfrom 1 to 30"
                                        + "\nDays of the month(Feb): \t\t\t\tfrom 1 to 28 or 29");
                                iae.printStackTrace();
                                day=0;
                                empFlag = true;
                                empRead.nextLine();
                            }
                            catch(Exception e){
                                MenuClass.errMessage();
                                e.printStackTrace();
                                day=0;
                                empFlag = true;
                                empRead.nextLine();
                            }
                        }while(empFlag);
                        
                        do{
                            try{
                                System.out.println("Enter commission rate: ");
                                commissionRate = empRead.nextDouble();
                                if(commissionRate < 0 || commissionRate > 1)
                                    throw new IllegalArgumentException();                   
                                empFlag = false;
                            }
                            catch(InputMismatchException ime){
                                MenuClass.noLetters();
                                ime.printStackTrace();
                                commissionRate=0.0; //reset the value so it won't keep looping by itself.
                                empFlag = true;
                                empRead.nextLine();//prevent the nextInt passing the value by itself when coming back from catch.
                            }
                            catch(IllegalArgumentException iae){
                                System.out.println("Invalid Number. The commission rate is between 0.0 and 1.0. Ex) 10% is 0.1");
                                iae.printStackTrace();
                                commissionRate=0.0;
                                empFlag = true;
                                empRead.nextLine();
                            }
                            catch(Exception e){
                                MenuClass.errMessage();
                                e.printStackTrace();
                                commissionRate=0.0;
                                empFlag = true;
                                empRead.nextLine();
                            }
                        }while(empFlag);
                        
                        //create a new object of commission sales employee
                        comEmp = new CommissionSalesEmployee(firstName, lastName, position, age, year, month, day, commissionRate, 0.0, 0.0);
                        employees.add(comEmp);
                        System.out.println("Employee Created Successfully.");
                        
                        //print new emp info
                        System.out.print(comEmp.toString()+"\n");
                        System.out.println("Back to Employee menu.");
                    }
                    else if(empInput == 2){ //existing employee 
                        //temp variables for emp object
                        String firstName, lastName, position;
                        int age, year, month, day;
                        double commissionRate, grossSales, salesTarget;
                        
                        boolean empFlag = true;
                        
                        //emp decision nested loop
                        do{
                            // (menu) ask user what they want to do (edit, delete, cancel)
                            MenuClass.existingEmpMessage();

                            int existingEmpInput = empRead.nextInt();
                            empRead.nextLine();
                            String empID = null;//reset the value in case the user wants to delete after editting an employee and vice versa.
                            
                            switch(existingEmpInput){
                                case 1://edit employee info
                                    //asking employee ID to search
                                    System.out.print("\nPlease enter Employee ID: ");
                                    
                                    empID = empRead.nextLine();
                                    CommissionSalesEmployee searchedEmployee = null;
                                    
                                    //search the employee from the arraylist
                                    for(Employee employee : employees){
                                        if(employee.getEmpID().equalsIgnoreCase(empID)){
                                            searchedEmployee = (CommissionSalesEmployee) employee;
                                            //print the employee information
                                            System.out.print(searchedEmployee.toString());
                                        }
                                    }
                                    //if employee not found, print message and leave the current menu.
                                    if(searchedEmployee==null){
                                        System.out.print("\nSorry, couldn't find the employee ID "+ empID.toUpperCase() + "\n");
                                        System.out.println("Back to Existing Employee Menu.");
                                        break;
                                    }
                                    
                                    //allow user to change multiple info for the searched employee
                                    boolean empInfoChanging = true;
                                    do{
                                        // (menu) ask user which information they want to change.
                                        MenuClass.whichEmpInfo();
                                        int whichEmpInfo = empRead.nextInt();
                                        empRead.nextLine();
                                        
                                        if(whichEmpInfo == 1){ //first name
                                            boolean changeLoop = true;
                                            
                                            do{
                                                System.out.print("Enter employee's new first name");
                                                String changedFirstName = empRead.nextLine();
                                                String originalFirstName = searchedEmployee.getFirstName();
                                                
                                                try{
                                                    //if the setFirstName returns false, throw exception
                                                    if(!searchedEmployee.setFirstName(changedFirstName))
                                                        throw new Exception();
                                                    
                                                    MenuClass.changeSuccess();
                                                    changeLoop = false;    
                                                }
                                                catch(Exception e){
                                                    MenuClass.errMessage();
                                                    e.printStackTrace();
                                                    //reassign the value to make sure the original data is kept.
                                                    searchedEmployee.setFirstName(originalFirstName);
                                                    System.out.print("The employee's name will be back to "+ originalFirstName);
                                                    changeLoop = true;
                                                }
                                            }while(changeLoop);
                                        }//end of if for firstname option
                                        

                                        else if(whichEmpInfo == 2){ //last name
                                            boolean changeLoop = true;
                                            
                                            do{
                                                System.out.print("Enter employee's new last name");
                                                String originalLastName = searchedEmployee.getLastName();
                                                String changedLastName = empRead.nextLine();
                                                 
                                                try{
                                                    if(!searchedEmployee.setLastName(changedLastName))
                                                        throw new Exception();
                                                    
                                                    MenuClass.changeSuccess();
                                                    changeLoop = false;
                                                }
                                                catch(Exception e){
                                                    MenuClass.errMessage();
                                                    e.printStackTrace();
                                                    searchedEmployee.setLastName(originalLastName);
                                                    System.out.print("The employee's name will be back to "+ originalLastName);
                                                    changeLoop = true;
                                                }
                                            }while(changeLoop);
                                        }
                                        else if(whichEmpInfo == 3){ //position
                                            boolean changeLoop = true;
                                            
                                            do{
                                                System.out.print("Enter employee's new position");
                                                String originalPosition = searchedEmployee.getPosition();
                                                String changedPosition = empRead.nextLine();
                                                 
                                                try{
                                                    if(!searchedEmployee.setPosition(changedPosition))
                                                        throw new Exception();
                                                    else{
                                                        MenuClass.changeSuccess();
                                                        changeLoop = false;
                                                    }
                                                }
                                                catch(Exception e){
                                                    MenuClass.errMessage();
                                                    e.printStackTrace();
                                                    searchedEmployee.setPosition(originalPosition);
                                                    System.out.print("The employee's position will be back to "+ originalPosition);
                                                    changeLoop = true;
                                                }
                                            }while(changeLoop);
                                        }
                                        
                                        else if(whichEmpInfo == 4){ //age
                                            boolean changeLoop = true;
                                            
                                            do{
                                                System.out.print("\nEnter employee's age");
                                                int originalAge = searchedEmployee.getAge();
                                                int changedAge = empRead.nextInt();
                                                 
                                                try{
                                                    if(!searchedEmployee.setAge(changedAge))
                                                        throw new IllegalArgumentException();
                                                    else{
                                                        MenuClass.changeSuccess();
                                                        changeLoop = false;
                                                    }
                                                }
                                                catch(InputMismatchException ime){
                                                    MenuClass.noLetters();
                                                    ime.printStackTrace();
                                                    searchedEmployee.setAge(originalAge); //reset the value so it won't keep looping by itself.
                                                    System.out.print("The employee's age will be back to "+ originalAge);
                                                    changeLoop = true;
                                                    empRead.nextLine();
                                                }
                                                catch(IllegalArgumentException iae){
                                                    System.out.println("Invalid Number. The age should be >= 16 and < 110");
                                                    iae.printStackTrace();
                                                    searchedEmployee.setAge(originalAge);
                                                    System.out.print("The employee's age will be back to "+ originalAge);
                                                    changeLoop = true;
                                                    empRead.nextLine();
                                                }
                                                catch(Exception e){
                                                    MenuClass.errMessage();
                                                    e.printStackTrace();
                                                    searchedEmployee.setAge(originalAge);
                                                    System.out.print("The employee's age will be back to "+ originalAge);
                                                    changeLoop = true;
                                                    empRead.nextLine();
                                                }
                                            }while(changeLoop);
                                        }
                                        else if(whichEmpInfo == 5){ //hire year
                                            boolean changeLoop = true;
                                            
                                            do{
                                                System.out.print("Enter employee's hire year");
                                                int originalYear = searchedEmployee.getYear();
                                                int changedYear = empRead.nextInt();
                                                 
                                                try{
                                                    if(!searchedEmployee.setYear(changedYear))
                                                        throw new IllegalArgumentException();
                                                    else{
                                                        MenuClass.changeSuccess();
                                                        changeLoop = false;
                                                    }
                                                }
                                                catch(InputMismatchException ime){
                                                    MenuClass.noLetters();
                                                    ime.printStackTrace();
                                                    searchedEmployee.setYear(originalYear); //reset the value so it won't keep looping by itself.
                                                    System.out.print("The employee's hire year will be back to "+ originalYear);
                                                    changeLoop = true;
                                                    empRead.nextLine();
                                                }
                                                catch(IllegalArgumentException iae){
                                                    System.out.println("Invalid Number. The year should be between 1923 and 2016 inclusive.");
                                                    iae.printStackTrace();
                                                    searchedEmployee.setYear(originalYear);
                                                    System.out.print("The employee's hire year will be back to "+ originalYear);
                                                    changeLoop = true;
                                                    empRead.nextLine();
                                                }
                                                catch(Exception e){
                                                    MenuClass.errMessage();
                                                    e.printStackTrace();
                                                    searchedEmployee.setYear(originalYear);
                                                    System.out.print("The employee's hire year will be back to "+ originalYear);
                                                    changeLoop = true;
                                                    empRead.nextLine();
                                                }
                                            }while(changeLoop);
                                        }
                                        else if(whichEmpInfo == 6){ //hire month
                                            boolean changeLoop = true;
                                            
                                            do{
                                                System.out.print("Enter employee's hire month");
                                                int originalMonth = searchedEmployee.getMonth();
                                                int changedMonth = empRead.nextInt();
                                                 
                                                try{
                                                    if(!searchedEmployee.setMonth(changedMonth))
                                                        throw new IllegalArgumentException();
                                                    else{
                                                        MenuClass.changeSuccess();
                                                        changeLoop = false;
                                                    }
                                                }
                                                catch(InputMismatchException ime){
                                                    MenuClass.noLetters();
                                                    ime.printStackTrace();
                                                    searchedEmployee.setMonth(originalMonth); //reset the value so it won't keep looping by itself.
                                                    System.out.print("The employee's hire month will be back to "+ originalMonth);
                                                    changeLoop = true;
                                                    empRead.nextLine();
                                                }
                                                catch(NullPointerException npe){ //the program totally stops...
                                                    System.out.print("The day may not match to the days of the months. Please check the day and change it first and come back to change the month.");
                                                    npe.printStackTrace();
                                                    searchedEmployee.setMonth(originalMonth);
                                                    System.out.print("The employee's hire month will be back to "+ originalMonth);
                                                    changeLoop = false;//let the user get out from month change and let them change the day first.
                                                    empRead.nextLine();
                                                }
                                                catch(IllegalArgumentException iae){
                                                    System.out.println("Invalid Number. The month should be between 1 and 12 inclusive.");
                                                    iae.printStackTrace();
                                                    searchedEmployee.setMonth(originalMonth);
                                                    System.out.print("The employee's hire month will be back to "+ originalMonth);
                                                    changeLoop = true;
                                                    empRead.nextLine();
                                                }
                                                catch(Exception e){
                                                    MenuClass.errMessage();
                                                    e.printStackTrace();
                                                    searchedEmployee.setMonth(originalMonth);
                                                    System.out.print("The employee's hire month will be back to "+ originalMonth);
                                                    changeLoop = true;
                                                    empRead.nextLine();
                                                }
                                            }while(changeLoop);
                                        }
                                        else if(whichEmpInfo == 7){ //hire day
                                            boolean changeLoop = true;
                                            
                                            do{
                                                System.out.print("Enter employee's hire day");
                                                int originalDay = searchedEmployee.getDay();
                                                int changedDay = empRead.nextInt();
                                                 
                                                try{
                                                    if(!searchedEmployee.setDay(changedDay))
                                                        throw new IllegalArgumentException();
                                                    else{
                                                        MenuClass.changeSuccess();
                                                        changeLoop = false;
                                                    }
                                                }
                                                catch(InputMismatchException ime){
                                                    MenuClass.noLetters();
                                                    ime.printStackTrace();
                                                    searchedEmployee.setDay(originalDay); //reset the value so it won't keep looping by itself.
                                                    System.out.print("The employee's hire day will be back to "+ originalDay);
                                                    changeLoop = true;
                                                    empRead.nextLine();
                                                }
                                                catch(IllegalArgumentException iae){
                                                    System.out.println("Invalid Number."
                                                            + "\nDays of the month(Jan, Mar, May, Jul, Aug, Oct, Dec): \tfrom 1 to 31"
                                                            + "\nDays of the month(Apr, Jun, Sep, Nov): \t\t\tfrom 1 to 30"
                                                            + "\nDays of the month(Feb): \t\t\t\tfrom 1 to 28 or 29");
                                                    iae.printStackTrace();
                                                    searchedEmployee.setDay(originalDay);
                                                    System.out.print("The employee's hire day will be back to "+ originalDay);
                                                    changeLoop = true;
                                                    empRead.nextLine();
                                                }
                                                catch(Exception e){
                                                    MenuClass.errMessage();
                                                    e.printStackTrace();
                                                    searchedEmployee.setDay(originalDay);
                                                    System.out.print("The employee's hire day will be back to "+ originalDay);
                                                    changeLoop = true;
                                                    empRead.nextLine();
                                                }
                                            }while(changeLoop);
                                        }
                                        else if(whichEmpInfo == 8){ //commission rate
                                            boolean changeLoop = true;
                                            
                                            do{
                                                System.out.print("Enter employee's new commission rate.");
                                                double originalComm = searchedEmployee.getCommissionRate();
                                                double changedComm = empRead.nextDouble();
                                                 
                                                try{
                                                    if(!searchedEmployee.setCommissionRate(changedComm))
                                                        throw new IllegalArgumentException();
                                                    else{
                                                        MenuClass.changeSuccess();
                                                        changeLoop = false;
                                                    }
                                                }
                                                catch(InputMismatchException ime){
                                                    MenuClass.noLetters();
                                                    ime.printStackTrace();
                                                    searchedEmployee.setCommissionRate(originalComm); //reset the value so it won't keep looping by itself.
                                                    System.out.print("The employee's commission rate will be back to "+ originalComm);
                                                    changeLoop = true;
                                                    empRead.nextLine();
                                                }
                                                catch(IllegalArgumentException iae){
                                                    System.out.println("Invalid Number. The commission rate is between 0.0 and 1.0. Ex) 10% is 0.1");
                                                    iae.printStackTrace();
                                                    searchedEmployee.setCommissionRate(originalComm);
                                                    System.out.print("The employee's commission rate will be back to "+ originalComm);
                                                    changeLoop = true;
                                                    empRead.nextLine();
                                                }
                                                catch(Exception e){
                                                    MenuClass.errMessage();
                                                    e.printStackTrace();
                                                    searchedEmployee.setCommissionRate(originalComm);
                                                    System.out.print("The employee's commission rate will be back to "+ originalComm);
                                                    changeLoop = true;
                                                    empRead.nextLine();
                                                }
                                            }while(changeLoop);
                                        }
                                        else if(whichEmpInfo == 0){
                                            empInfoChanging = false;
                                            System.out.println("Back to Existing Employee Menu.");
                                        }
                                        else{
                                            MenuClass.errMessage();
                                            empInfoChanging = false;
                                        }
                                    }while(empInfoChanging);
                                    break;
                                    
                                case 2://delete employee
                                    //asking employee ID to search
                                    System.out.print("\nPlease enter Employee ID: ");
                                    
                                    empID = empRead.nextLine();
                                    searchedEmployee = null;
                                    
                                    //search the employee from the arraylist
                                    for(Employee employee : employees){
                                        if(employee.getEmpID().equalsIgnoreCase(empID)){
                                            searchedEmployee = (CommissionSalesEmployee) employee;
                                            //print the employee information
                                            System.out.print(searchedEmployee.toString());
                                        }
                                    }
                                    //if employee not found, print message and leave the current menu.
                                    if(searchedEmployee==null){
                                        System.out.print("\nSorry, couldn't find the employee ID "+ empID.toUpperCase() + "\n");
                                        System.out.println("Back to Existing Employee Menu.");
                                        break;
                                    }
                                    
                                    //reconfirm this operation
                                    System.out.println("\nDo you really want to delete this employee? 1. Yes 2. Cancel");
                                    int deleteConfirm = empRead.nextInt();
                                    if(deleteConfirm == 1){
                                       //delete and print message saying deletion was successful.
                                        employees.remove(searchedEmployee);
                                        System.out.println("Employee is deleted successfully.");

                                        System.out.println("Back to Existing Employee Menu.");
                                        break; 
                                    }
                                    else{
                                        System.out.println("Back to Existing Employee Menu.");
                                        break;
                                    }
                                case 0://cancel
                                    System.out.println("Back to Employee Menu.");
                                    empFlag = false;
                                    break;
                                    
                                default://invalid input
                                    //error message
                                    MenuClass.errMessage();
                                    empFlag = true;
                                    break;
                            }//end of switch
                        }while(empFlag);
                    }//end of existing employee option
                    else if(empInput == 0){ //exit to main menu
                        System.out.println("Back to Main Menu.");
                        empKeepGoing = false;
                    }
                    else{
                        MenuClass.errMessage();
                        empKeepGoing = true;
                    }
                    
                }while(empKeepGoing);//end of emp do-while
                
            }//end of emp
            
            
            
            
            
            
            
            
            // Below is product
            
            
            
            
            else if(input == 2){ //work with product
                //local variables for Product
                int prodInput;
                boolean prodKeepGoing = true;
                
                //prod decision nested loop
                do{
                    
                    //display prod message
                    MenuClass.prodMessage();
                    
                    //read input
                    prodInput = prodRead.nextInt();
                    prodRead.nextLine();
                    if(prodInput == 1){ //new product
                        //temp variables for prod object creation
                        String productID, productName, category, description, partNum, manufacturer;
                        double productPrice, productCost, productMarkup;
                        Product product;
                        
                        boolean prodFlag = true;
                        
                        do{
                            try{
                                //ask for user input
                                System.out.print("Enter product name: ");
                                productName = prodRead.nextLine();
                                if(productName.equals(""))
                                    throw new Exception();
                                prodFlag = false;
                            }
                            catch(Exception e){
                                MenuClass.errMessage();
                                e.printStackTrace();
                                productName="";
                                prodFlag = true;
                            }
                        }while(prodFlag);
                        
                        do{
                            try{
                                System.out.print("Enter category: ");
                                category = prodRead.nextLine();
                                if(category.equals(""))
                                    throw new Exception();
                                prodFlag = false;
                            }
                            catch(Exception e){
                                MenuClass.errMessage();
                                e.printStackTrace();
                                category="";
                                prodFlag = true;
                            }
                        }while(prodFlag);
                        
                        do{
                            try{
                                System.out.print("Enter product description: ");
                                description = prodRead.nextLine();
                                if(description.equals(""))
                                    throw new Exception();
                                prodFlag = false;
                            }
                            catch(Exception e){
                                MenuClass.errMessage();
                                e.printStackTrace();
                                description="";
                                prodFlag = true;
                            }
                        }while(prodFlag);                        
                        
                        do{
                            try{
                                System.out.print("Enter product part#: ");
                                partNum = prodRead.nextLine();
                                if(partNum.equals(""))
                                    throw new Exception();
                                prodFlag = false;
                            }
                            catch(Exception e){
                                MenuClass.errMessage();
                                e.printStackTrace();
                                partNum="";
                                prodFlag = true;
                            }
                        }while(prodFlag);     
                        
                        do{
                            try{
                                System.out.print("Enter product manufacturer: ");
                                manufacturer = prodRead.nextLine();
                                if(manufacturer.equals(""))
                                    throw new Exception();
                                prodFlag = false;
                            }
                            catch(Exception e){
                                MenuClass.errMessage();
                                e.printStackTrace();
                                manufacturer="";
                                prodFlag = true;
                            }
                        }while(prodFlag);     
                        
                        do{
                            try{
                                System.out.println("Enter product cost: ");
                                productCost = prodRead.nextDouble();
                                if(productCost <= 0.0)
                                    throw new IllegalArgumentException();                   
                                prodFlag = false;
                            }
                            catch(InputMismatchException ime){
                                MenuClass.noLetters();
                                ime.printStackTrace();
                                productCost=0.0; //reset the value so it won't keep looping by itself.
                                prodFlag = true;
                                prodRead.nextLine();//prevent the nextInt passing the value by itself when coming back from catch.
                            }
                            catch(IllegalArgumentException iae){
                                System.out.println("Invalid Number. The product cost must be more than $0.0");
                                iae.printStackTrace();
                                productCost=0.0;
                                prodFlag = true;
                                prodRead.nextLine();
                            }
                            catch(Exception e){
                                MenuClass.errMessage();
                                e.printStackTrace();
                                productCost=0.0;
                                prodFlag = true;
                                prodRead.nextLine();
                            }
                        }while(prodFlag);
                        
                        do{
                            try{
                                System.out.println("Enter product price: ");
                                productPrice = prodRead.nextDouble();
                                if(productPrice <= 0.0 || productPrice <= productCost)
                                    throw new IllegalArgumentException();                   
                                prodFlag = false;
                            }
                            catch(InputMismatchException ime){
                                MenuClass.noLetters();
                                ime.printStackTrace();
                                productPrice=0.0; //reset the value so it won't keep looping by itself.
                                prodFlag = true;
                                prodRead.nextLine();//prevent the nextInt passing the value by itself when coming back from catch.
                            }
                            catch(IllegalArgumentException iae){
                                System.out.println("Invalid Number. The product price must be over $0.0 and more than its cost.");
                                iae.printStackTrace();
                                productPrice=0.0;
                                prodFlag = true;
                                prodRead.nextLine();
                            }
                            catch(Exception e){
                                MenuClass.errMessage();
                                e.printStackTrace();
                                productPrice=0.0;
                                prodFlag = true;
                                prodRead.nextLine();
                            }
                        }while(prodFlag);
                        
                        do{
                            try{
                                System.out.println("Enter product markup: ");
                                productMarkup = prodRead.nextDouble();
                                if(productMarkup <= 0.0 || productMarkup > 1.0)
                                    throw new IllegalArgumentException();                   
                                prodFlag = false;
                            }
                            catch(InputMismatchException ime){
                                MenuClass.noLetters();
                                ime.printStackTrace();
                                productMarkup=0.0; //reset the value so it won't keep looping by itself.
                                prodFlag = true;
                                prodRead.nextLine();//prevent the nextInt passing the value by itself when coming back from catch.
                            }
                            catch(IllegalArgumentException iae){
                                System.out.println("Invalid Number. The product markup must be over 0 but less than 1.");
                                iae.printStackTrace();
                                productMarkup=0.0;
                                prodFlag = true;
                                prodRead.nextLine();
                            }
                            catch(Exception e){
                                MenuClass.errMessage();
                                e.printStackTrace();
                                productMarkup=0.0;
                                prodFlag = true;
                                prodRead.nextLine();
                            }
                        }while(prodFlag);
                        
                        //create a new object of product
                        product = new Product(productName, category, description, partNum, manufacturer, productCost, productPrice, productMarkup);
                        products.add(product);
                        System.out.println("Product Created Successfully.");
                        
                        //print new emp info
                        System.out.print(product.toString()+"\n");
                        System.out.println("Back to Product menu.");
                    }
                    else if(prodInput == 2){ //existing product
                        //temp variables for prod object
                        String productName, category, description, partNum, manufacturer;
                        double productPrice, productCost, productMarkup;
                        
                        boolean prodFlag = true;
                        
                        //prod decision nested loop
                        do{
                            // (menu) ask user what they want to do (edit, delete, cancel)
                            MenuClass.existingProdMessage();

                            int existingProdInput = prodRead.nextInt();
                            prodRead.nextLine();
                            String prodID = null;//reset the value in case the user wants to delete after editting an prod and vice versa.
                            
                            switch(existingProdInput){
                                case 1://edit product info
                                    //asking product ID to search
                                    System.out.print("\nPlease enter Product ID: ");
                                    
                                    prodID = prodRead.nextLine();
                                    Product searchedProduct = null;
                                    
                                    //search the product from the arraylist
                                    for(Product product : products){
                                        if(product.getProductID().equalsIgnoreCase(prodID)){
                                            searchedProduct = product;
                                            //print the product information
                                            System.out.print(searchedProduct.toString());
                                        }
                                    }
                                    //if the product not found, print message and leave the current menu.
                                    if(searchedProduct==null){
                                        System.out.print("\nSorry, couldn't find the product ID "+ prodID.toUpperCase() + "\n");
                                        System.out.println("Back to Existing Product Menu.");
                                        break;
                                    }
                                    
                                    //allow user to change multiple info for the searched product
                                    boolean prodInfoChanging = true;
                                    do{
                                        // (menu) ask user which information they want to change.
                                        MenuClass.whichProdInfo();
                                        int whichProdInfo = prodRead.nextInt();
                                        prodRead.nextLine();
                                        
                                        if(whichProdInfo == 1){ //product name
                                            boolean changeLoop = true;
                                            
                                            do{
                                                System.out.print("Enter new product name");
                                                String changedProductName = prodRead.nextLine();
                                                String originalProductName = searchedProduct.getProductName();
                                                
                                                try{
                                                    //if the setProductName returns false, throw exception
                                                    if(!searchedProduct.setProductName(changedProductName))
                                                        throw new Exception();
                                                    
                                                    MenuClass.changeSuccess();
                                                    changeLoop = false;    
                                                }
                                                catch(Exception e){
                                                    MenuClass.errMessage();
                                                    e.printStackTrace();
                                                    //reassign the value to make sure the original data is kept.
                                                    searchedProduct.setProductName(originalProductName);
                                                    System.out.print("The product name will be back to "+ originalProductName);
                                                    changeLoop = true;
                                                }
                                            }while(changeLoop);
                                        }
                                        else if(whichProdInfo == 2){ //category
                                            boolean changeLoop = true;
                                            
                                            do{
                                                System.out.print("Enter product category: ");
                                                String originalCategory = searchedProduct.getCategory();
                                                String changedCategory = prodRead.nextLine();
                                                 
                                                try{
                                                    if(!searchedProduct.setCategory(changedCategory))
                                                        throw new Exception();
                                                    
                                                    MenuClass.changeSuccess();
                                                    changeLoop = false;
                                                }
                                                catch(Exception e){
                                                    MenuClass.errMessage();
                                                    e.printStackTrace();
                                                    searchedProduct.setCategory(originalCategory);
                                                    System.out.print("The product category will be back to "+ originalCategory);
                                                    changeLoop = true;
                                                }
                                            }while(changeLoop);
                                        }
                                        else if(whichProdInfo == 3){ //description
                                            boolean changeLoop = true;
                                            
                                            do{
                                                System.out.print("Enter product description: ");
                                                String originalDescription = searchedProduct.getDescription();
                                                String changedDescription = prodRead.nextLine();
                                                 
                                                try{
                                                    if(!searchedProduct.setDescription(changedDescription))
                                                        throw new Exception();
                                                    else{
                                                        MenuClass.changeSuccess();
                                                        changeLoop = false;
                                                    }
                                                }
                                                catch(Exception e){
                                                    MenuClass.errMessage();
                                                    e.printStackTrace();
                                                    searchedProduct.setDescription(originalDescription);
                                                    System.out.print("The product description will be back to "+ originalDescription);
                                                    changeLoop = true;
                                                }
                                            }while(changeLoop);
                                        }
                                        else if(whichProdInfo == 4){ //partNum
                                            boolean changeLoop = true;
                                            
                                            do{
                                                System.out.print("Enter product part#: ");
                                                String originalPartNum = searchedProduct.getPartNum();
                                                String changedPartNum = prodRead.nextLine();
                                                 
                                                try{
                                                    if(!searchedProduct.setPartNum(changedPartNum))
                                                        throw new Exception();
                                                    else{
                                                        MenuClass.changeSuccess();
                                                        changeLoop = false;
                                                    }
                                                }
                                                catch(Exception e){
                                                    MenuClass.errMessage();
                                                    e.printStackTrace();
                                                    searchedProduct.setPartNum(originalPartNum);
                                                    System.out.print("The product part number will be back to "+ originalPartNum);
                                                    changeLoop = true;
                                                }
                                            }while(changeLoop);
                                        }
                                        else if(whichProdInfo == 5){ //manufacturer
                                            boolean changeLoop = true;
                                            
                                            do{
                                                System.out.print("Enter product manufacturer: ");
                                                String originalManufacturer = searchedProduct.getManufacturer();
                                                String changedManufacturer = prodRead.nextLine();
                                                 
                                                try{
                                                    if(!searchedProduct.setManufacturer(changedManufacturer))
                                                        throw new Exception();
                                                    else{
                                                        MenuClass.changeSuccess();
                                                        changeLoop = false;
                                                    }
                                                }
                                                catch(Exception e){
                                                    MenuClass.errMessage();
                                                    e.printStackTrace();
                                                    searchedProduct.setManufacturer(originalManufacturer);
                                                    System.out.print("The product manufacturer will be back to "+ originalManufacturer);
                                                    changeLoop = true;
                                                }
                                            }while(changeLoop);
                                        }
                                        else if(whichProdInfo == 6){ //cost
                                            boolean changeLoop = true;
                                            
                                            do{
                                                System.out.print("\nEnter new product cost");
                                                double originalCost = searchedProduct.getProductCost();
                                                double changedCost = prodRead.nextDouble();
                                                 
                                                try{
                                                    if(!searchedProduct.setProductCost(changedCost))
                                                        throw new IllegalArgumentException();
                                                    else{
                                                        MenuClass.changeSuccess();
                                                        changeLoop = false;
                                                    }
                                                }
                                                catch(InputMismatchException ime){
                                                    MenuClass.noLetters();
                                                    ime.printStackTrace();
                                                    searchedProduct.setProductCost(originalCost); //reset the value so it won't keep looping by itself.
                                                    System.out.print("The product cost will be back to "+ originalCost);
                                                    changeLoop = true;
                                                    prodRead.nextLine();
                                                }
                                                catch(IllegalArgumentException iae){
                                                    System.out.println("Invalid Number. The cost must be over $0.0");
                                                    iae.printStackTrace();
                                                    searchedProduct.setProductCost(originalCost);
                                                    System.out.print("The product cost will be back to "+ originalCost);
                                                    changeLoop = true;
                                                    prodRead.nextLine();
                                                }
                                                catch(Exception e){
                                                    MenuClass.errMessage();
                                                    e.printStackTrace();
                                                    searchedProduct.setProductCost(originalCost);
                                                    System.out.print("The product cost will be back to "+ originalCost);
                                                    changeLoop = true;
                                                    prodRead.nextLine();
                                                }
                                            }while(changeLoop);
                                        }
                                        else if(whichProdInfo == 7){ //price
                                            boolean changeLoop = true;
                                            
                                            do{
                                                System.out.print("\nEnter new product price");
                                                double originalPrice = searchedProduct.getProductPrice();
                                                double changedPrice = prodRead.nextDouble();
                                                 
                                                try{
                                                    if(!searchedProduct.setProductPrice(changedPrice))
                                                        throw new IllegalArgumentException();
                                                    else{
                                                        MenuClass.changeSuccess();
                                                        changeLoop = false;
                                                    }
                                                }
                                                catch(InputMismatchException ime){
                                                    MenuClass.noLetters();
                                                    ime.printStackTrace();
                                                    searchedProduct.setProductPrice(originalPrice); //reset the value so it won't keep looping by itself.
                                                    System.out.print("The product price will be back to "+ originalPrice);
                                                    changeLoop = true;
                                                    prodRead.nextLine();
                                                }
                                                catch(IllegalArgumentException iae){
                                                    System.out.println("Invalid Number. The price must be over $0.0 and more than its cost.");
                                                    iae.printStackTrace();
                                                    searchedProduct.setProductPrice(originalPrice);
                                                    System.out.print("The product price will be back to "+ originalPrice);
                                                    changeLoop = true;
                                                    prodRead.nextLine();
                                                }
                                                catch(Exception e){
                                                    MenuClass.errMessage();
                                                    e.printStackTrace();
                                                    searchedProduct.setProductPrice(originalPrice);
                                                    System.out.print("The product price will be back to "+ originalPrice);
                                                    changeLoop = true;
                                                    prodRead.nextLine();
                                                }
                                            }while(changeLoop);
                                        }
                                        else if(whichProdInfo == 8){ //markup
                                            boolean changeLoop = true;
                                            
                                            do{
                                                System.out.print("\nEnter new product markup");
                                                double originalMarkup = searchedProduct.getProductMarkup();
                                                double changedMarkup = prodRead.nextDouble();
                                                 
                                                try{
                                                    if(!searchedProduct.setProductMarkup(changedMarkup))
                                                        throw new IllegalArgumentException();
                                                    else{
                                                        MenuClass.changeSuccess();
                                                        changeLoop = false;
                                                    }
                                                }
                                                catch(InputMismatchException ime){
                                                    MenuClass.noLetters();
                                                    ime.printStackTrace();
                                                    searchedProduct.setProductMarkup(originalMarkup); //reset the value so it won't keep looping by itself.
                                                    System.out.print("The product markup will be back to "+ originalMarkup);
                                                    changeLoop = true;
                                                    prodRead.nextLine();
                                                }
                                                catch(IllegalArgumentException iae){
                                                    System.out.println("Invalid Number. The markup must be between 0 and 1.");
                                                    iae.printStackTrace();
                                                    searchedProduct.setProductMarkup(originalMarkup);
                                                    System.out.print("The product markup will be back to "+ originalMarkup);
                                                    changeLoop = true;
                                                    prodRead.nextLine();
                                                }
                                                catch(Exception e){
                                                    MenuClass.errMessage();
                                                    e.printStackTrace();
                                                    searchedProduct.setProductMarkup(originalMarkup);
                                                    System.out.print("The product markup will be back to "+ originalMarkup);
                                                    changeLoop = true;
                                                    prodRead.nextLine();
                                                }
                                            }while(changeLoop);
                                        }
                                        else if(whichProdInfo == 0){
                                            prodInfoChanging = false;
                                            System.out.println("Back to Existing Product Menu.");
                                        }
                                        else{
                                            MenuClass.errMessage();
                                            prodInfoChanging = false;
                                        }
                                    }while(prodInfoChanging);
                                    break;
                                    
                                case 2://delete product
                                    //asking product ID to search
                                    System.out.print("\nPlease enter Product ID: ");
                                    
                                    prodID = empRead.nextLine();
                                    searchedProduct = null;
                                    
                                    //search the product from the arraylist
                                    for(Product product : products){
                                        if(product.getProductID().equalsIgnoreCase(prodID)){
                                            searchedProduct = product;
                                            //print the product information
                                            System.out.print(searchedProduct.toString()+"\n");
                                        }
                                    }
                                    //if product not found, print message and leave the current menu.
                                    if(searchedProduct==null){
                                        System.out.print("\nSorry, couldn't find the product ID "+ prodID.toUpperCase() + "\n");
                                        System.out.println("Back to Existing Product Menu.");
                                        break;
                                    }
                                    
                                    //reconfirm this operation
                                    System.out.println("\nDo you really want to delete this product? 1. Yes 2. Cancel");
                                    int deleteConfirm = prodRead.nextInt();
                                    if(deleteConfirm == 1){
                                       //delete and print message saying deletion was successful.
                                        products.remove(searchedProduct);
                                        System.out.println("Product is deleted successfully.");

                                        System.out.println("Back to Existing Product Menu.");
                                        break; 
                                    }
                                    else{
                                        System.out.println("You have cancelled the job.\nBack to Existing Product Menu.");
                                        break;
                                    }
                                case 0://cancel
                                    System.out.println("Back to Product Menu.");
                                    prodFlag = false;
                                    break;
                                    
                                default://invalid input
                                    //error message
                                    MenuClass.errMessage();
                                    prodFlag = true;
                                    break;
                            }//end of switch
                        }while(prodFlag);
                    }//end of existing product option
                    else if(prodInput == 0){ //exit to main menu
                        System.out.println("Back to Main Menu.");
                        prodKeepGoing = false;
                    }
                    else{
                        MenuClass.errMessage();
                        prodKeepGoing = true;
                    }
                }while(prodKeepGoing);//end of prod do-while
            }
            else if(input == 0){ //exit the loop
                //change the flag to false
                keepGoing = false;
            }
            else{//invalid input block
                MenuClass.errMessage();
                keepGoing = true;
            }
        }while(keepGoing);
        System.out.println("Good Bye.");
        
    }//end of main
    
    
    /* In class example
    private static int readInput() throws InputMismatchException {
        Scanner read = new Scanner(System.in);
        int input = Integer.parseInt(read.nextLine());
        
        return input;
    }
    */
    
    /* previously in Main
        //generic collection that will store Employees
        ArrayList<Employee> myList = new ArrayList<>();
        
        //String firstName, String lastName, String position, 
               //int age, int year, int month, int day, double commissionRate, double grossSales, double salesTarget
        myList.add(new CommissionSalesEmployee("CindyA", "Diaz", "Sales Associate", 20, 2015, 3, 20, 0.15, 5000, 3500));
        myList.add(new CommissionSalesEmployee("LucyA", "Kang", "Sales Associate", 27, 2015, 1, 20, 0.15, 4500, 3500));
        myList.add(new CommissionSalesEmployee("YujiA", "Fujiyama", "Sales Associate", 23, 2014, 5, 15, 0.15, 5500, 4000));
        
        myList.add(new SalaryPlusCommissionEmployee("CindyB", "Diaz", "Sales Associate", 20, 2015, 3, 20, 0.15, 5000, 3500, 1000));
        myList.add(new SalaryPlusCommissionEmployee("LucyB", "Kang", "Sales Associate", 27, 2015, 1, 20, 0.15, 4500, 3500, 1000));
        myList.add(new SalaryPlusCommissionEmployee("YujiB", "Fujiyama", "Sales Associate", 23, 2014, 5, 15, 0.15, 5500, 4000, 1500));
        
        myList.add(new Volunteer("CindyC", "Diaz", "Sales Associate", 20, 2015, 3, 20, 40));
        myList.add(new Volunteer("LucyC", "Kang", "Sales Associate", 27, 2015, 1, 20, 35));
        myList.add(new Volunteer("YujiC", "Fujiyama", "Sales Associate", 23, 2014, 5, 15, 38));
        //Add 10 different types of employees     
        //Employee mySalaryPlusEmp = new SalaryPlusCommissionEmployee("Tony", "Kang", "Manager", 30, 2016, 3, 20, 0.15, 0.00, 100.00, 1000.00);

        /*
        for(Employee e : myList){
            System.out.println(e.toString());
        }
        */
        /*
        //print earnings for each employee
        for(Employee e : myList){
            //System.out.println("Name: " + e.getFirstName() + "\nEmployee Type: " + e.getClass().getSimpleName() + "\nEarnings" + e.earnings());
            String simpleEmpType = e.getClass().getSimpleName();
            String readableEmpType = "";
            
            for(int i=0; i<simpleEmpType.length(); i++){
                Character ch = simpleEmpType.charAt(i);
                if(Character.isUpperCase(ch)){
                    readableEmpType += " " + ch;
                }
                else{
                    readableEmpType += ch;
                }
            }
            
            System.out.println("Name: " + e.getFirstName() + "\nEmployee Type: " + readableEmpType + "\nEarnings" + e.earnings());
            
        }
        
        
        ArrayList<Payable> accPayable = new ArrayList<>();
        accPayable.add(new CommissionSalesEmployee("CindyA", "Diaz", "Sales Associate", 20, 2015, 3, 20, 0.15, 5000, 3500));
        accPayable.add(new SalaryPlusCommissionEmployee("LucyB", "Kang", "Sales Associate", 27, 2015, 1, 20, 0.15, 4500, 3500, 1000));
        accPayable.add(new Invoice("ProductA","It's Product A", 2000, 2016, 2, 28));
        
        for (Payable ap : accPayable){
            if(ap.getClass().getSuperclass().getSimpleName() == "Employee"){//doesn't work.
                
            }
            else{
                
            }
            System.out.println("Employment : Type" + ap.getClass().getSimpleName() + "\nEarnings :" + ap.getPaymentAmount());
        }
        */
}//end of retailcompany
