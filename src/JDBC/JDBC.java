
package JDBC;

import FileIO.ErrorDataLog;
import General.Service;
import Inventory.Manufacturer;
import Inventory.Product;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import javax.swing.table.DefaultTableModel;

/**
 * Final Assignment (Group assignment)
 * @version 2016-04-20
 * @author Haeyeon Kang, Cindy Diaz
 */

public class JDBC {
    
    //variables for connection to db
    private static String QRY;
    private static Connection conn = null;
    private static Statement stat = null;
    private static ResultSet rs = null;
    
    private static ErrorDataLog errLog;
    private static StringWriter exceptionData;
    private static ResultSetMetaData metaData;
    private static ArrayList<String> empResult;
    
    //this method is for retrieving all error data from db
    public static DefaultTableModel retrieveErrData() {
        //create new table model
        DefaultTableModel tableModel = new DefaultTableModel();
        
        try{
            //create connection
            conn = DBConnect.getConn();

            //create statement
            stat = conn.createStatement();

            //execute queries to get results
            QRY = "Select * From ErrorLog;";
            rs = stat.executeQuery(QRY);
            metaData = rs.getMetaData();
            
            //declare and initialise variables 
            int columnCount = metaData.getColumnCount();
            Object[] row ;
            
            //get all column names from the metadata and populate the table
            for(int colIndex = 1; colIndex <= columnCount; colIndex++){
                tableModel.addColumn(metaData.getColumnName(colIndex));
            }
            
            //create array of object for row data
            row = new Object[columnCount];
            
            //loop to iterate the results
            while(rs.next()){ //if there is more row next, it will return true
                for(int i=1; i <= columnCount; i++){ //index starts from 1
                    row[i-1] = rs.getObject(i);
                }//end for
                
                //add row to the table model
                tableModel.insertRow(rs.getRow() - 1,row);
            }//end while
        }
        catch(SQLException err){
            //dont save error log to database since there was a sql error and potentially generate more errors.
            err.printStackTrace(new PrintWriter(exceptionData));
            errLog.appendData(exceptionData.toString(), new GregorianCalendar().getTime());
        }
        catch(Exception err){
            errLog.errHandling(err);
            err.printStackTrace(new PrintWriter(exceptionData));
            errLog.appendData(exceptionData.toString(), new GregorianCalendar().getTime());
        }
        
        return tableModel;
    }
    
    //
    public static void insertErrData(String errMsg) throws SQLException{
        try{
            //create connection
            conn = DBConnect.getConn();
            stat = conn.createStatement();
            
            QRY =  "INSERT INTO `ErrorLog` (`errTime`, `errMsg`) VALUES (CURRENT_TIMESTAMP, '" + errMsg + "');";
            
            stat.executeUpdate(QRY);
            
            System.out.println("Err Log Succesfully inserted.");
            
        }
        catch(SQLException err){
            //when there was an error inserting error data to database, save that error log to the log file in computer
            err.printStackTrace(new PrintWriter(exceptionData));
            errLog.appendData(exceptionData.toString(), new GregorianCalendar().getTime());
        }
        catch(Exception err){
            err.printStackTrace(new PrintWriter(exceptionData));
            errLog.appendData(exceptionData.toString(), new GregorianCalendar().getTime());
        }
        finally{
            stat.close();
            conn.close();
        }
    }
    public static String displayEmpData(String selectedItem) throws SQLException{
        String selectedEmp = "********* Employee Information **********\n";
        
        try{
            //create connection
            conn = DBConnect.getConn();
            
            //create statement
            stat = conn.createStatement();
            
            //execute queries to get results
            QRY = "SELECT id as \"Employee ID\", firstName as \"First Name\", lastName as \"Last Name\", age as \"Age\", position as \"Position\", CONCAT(year, \"-\", month, \"-\", day) as \"Hire Date\", sales as \"Gross Sales\", comRate as \"Commission\" FROM Employees WHERE CONCAT(firstName, \" \", lastName) = \"" + selectedItem + "\";";
            rs = stat.executeQuery(QRY);
            
            metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();
            
            while(rs.next()){
                for(int i=1; i <= columnCount; i++){
                    selectedEmp += (metaData.getColumnLabel(i) + ": \t\t" + rs.getObject(i) + "\n");
                }
            }
            //return selectedEmp;
        }
        catch(SQLException err){
            errLog.errHandling(err);
            err.printStackTrace(new PrintWriter(exceptionData));
            errLog.appendData(exceptionData.toString(), new GregorianCalendar().getTime());
        }
        catch(Exception err){
            errLog.errHandling(err);
            err.printStackTrace(new PrintWriter(exceptionData));
            errLog.appendData(exceptionData.toString(), new GregorianCalendar().getTime());
        }
        finally{
            rs.close();
            stat.close();
            conn.close();
        }
        return selectedEmp;
    }
    public static ArrayList retrieveEmpData() throws SQLException {
        empResult = new ArrayList<>();
        
        try{
            //create connection
            conn = DBConnect.getConn();
            
            //create statement
            stat = conn.createStatement();
            
            //execute queries to get results
            QRY = "Select id, CONCAT(firstName, \" \", lastName) From Employees;";
            rs = stat.executeQuery(QRY);
            
            metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();
            
            //loop to iterate the results
            while(rs.next()){ //if there is more row next, it will return true
                empResult.add(rs.getString(2));
            }//end while
        }
        catch(SQLException err){
            errLog.errHandling(err);
            err.printStackTrace(new PrintWriter(exceptionData));
            errLog.appendData(exceptionData.toString(), new GregorianCalendar().getTime());
        }
        catch(Exception err){
            errLog.errHandling(err);
            err.printStackTrace(new PrintWriter(exceptionData));
            errLog.appendData(exceptionData.toString(), new GregorianCalendar().getTime());
        }
        finally{
            rs.close();
            stat.close();
            conn.close();
        }
         return empResult;
    }//end of retrieveData
    
    public static void insertEmpData(String firstName, String lastName, int age, String position, int year, int month, int day, double sales, double comRate) throws SQLException {
        try{
            //create connection
            conn = DBConnect.getConn();
            stat = conn.createStatement();
            
            QRY =  "INSERT INTO `Employees`(`firstName`, `lastName`, `age`, `position`, `year`, `month`, `day`, `sales`, `comRate`) "
                    + "VALUES ('"+ firstName +"', '"+ lastName +"', '"+ age +"', '"+ position +"', '"
                    + year +"', '"+ month +"', '" +day+ "', '" +sales+ "', '" + comRate + "');";
            
            stat.executeUpdate(QRY);
            
            System.out.println("Data Succesfully inserted.");
            
        }
        catch(SQLException err){
            errLog.errHandling(err);
            err.printStackTrace(new PrintWriter(exceptionData));
            errLog.appendData(exceptionData.toString(), new GregorianCalendar().getTime());
        }
        catch(Exception err){
            errLog.errHandling(err);
            err.printStackTrace(new PrintWriter(exceptionData));
            errLog.appendData(exceptionData.toString(), new GregorianCalendar().getTime());
        }
        finally{
            stat.close();
            conn.close();
        }
    }
   
    //Method to insert Manufacturer objects to database, takes String manufacturerName, String address, String phoneNumber, String contact as parameters
    public static boolean insertManufacturerDB(String manufacturerName, String address, String phoneNumber, String contact) throws SQLException//Throw possible SQL Exception
    {
        //Get connection
        conn = DBConnect.getConn();        
        //Declare and create statement
        Statement stmtManufacurer = null;
        stmtManufacurer = conn.createStatement();
        //Sql query
        String sql = "INSERT INTO ManufacturersTable " + "(`manufacturerName`, `manufacturerAddress`, `manufacturerPhoneNumber`, `manufacturerContact`) " +
                     "VALUES('" + manufacturerName + "', '" + address + "', '" + phoneNumber + "', '" + contact + "');";
        //Execute statement with the query
        stmtManufacurer.executeUpdate(sql);
        //return true if no excpetion was thrown
        return true;
    }
    //Method to retrieve the id generated by the database of a manufacturer object
    //Needed to create product object, foreign key
    //Takes a manufacturer object as parameter
    public static int manufacturerIdFromDB(Manufacturer manufacturer) throws SQLException//Throw possible SQL Exception
    {
        //Get connection
        conn = DBConnect.getConn();
        //Declare statements and resultsets          
        Statement stmtManufacurerId = null;
        ResultSet rs = null;
        //Variable to store Manufacturer Id retrieved from database
        int value = 0;
        stmtManufacurerId = conn.createStatement();
        //Get manufacturerId from database of the object passed 
        String query = "SELECT manufacturerId FROM ManufacturersTable WHERE manufacturerName = '" + manufacturer.getManufacturerName() + "';";
        rs = stmtManufacurerId.executeQuery(query);
        //Loop through results, in this case is only one
        while(rs.next())
        {
            value = rs.getInt(1);//Store the result from the query in column 1
        }
        //Close result sets, statements and connections
        rs.close();
        stmtManufacurerId.close();
        conn.close();
        //Testing purposes
        System.out.println("Retrieved Manufacturer Id: " + value);
        //return the value retrieved        
        return value;
    }
    //Method to insert product into database
    public static boolean inserProductDB(String productName, String category, int manufacturerID,
                    String description, String partNum, double productCost, 
                    double productPrice, double productMarkup, int minimumInventory) throws SQLException
    {
        //Get connection
        conn = DBConnect.getConn();
        //Declaare statement
        Statement stmtProduct = null;
        stmtProduct = conn.createStatement();
        //Query to insert product object
        String sqlProduct = "INSERT INTO ProductsTable " + "(`productName`, `productCategory`, `productManufacturer`, `productDescription`,"
                              + " `productPartNum`, `productCost`, `productPrice`, `productMarkup`, `minInventory`) " +
                              "VALUES('" + productName + "', '" + category + "', '" + manufacturerID + "', '" 
                              + description + "', '" + partNum + "', '" + productCost + "', '" 
                              + productPrice + "', '" + productMarkup + "', '" + minimumInventory+ "');";            
        //Execute query
        stmtProduct.executeUpdate(sqlProduct);
        //Close connection        
        stmtProduct.close();
        conn.close();
        //return true if no excpetions were thrown
        return true;
    }
    //Method to populate the Arraylist with manufacurers, for dropdown selection
    public static void populateManufacturerArray()
    {
        try
        {
            //Start connections, statements, resultsets
           conn = DBConnect.getConn();
           Statement stmtGetManufacturers = null;
           stmtGetManufacturers = conn.createStatement();
           //Create query
           String query = "SELECT * FROM ManufacturersTable";
           ResultSet rsManufacturers = null;
           //Store results of query en resultsset
           rsManufacturers = stmtGetManufacturers.executeQuery(query);
           //loop in the results
           while(rsManufacturers.next())
           {
               //For every result create a manufacturer object to add to arraylist
               Manufacturer currentManufacturer = new Manufacturer(rsManufacturers.getInt("manufacturerId"),
                       rsManufacturers.getString("manufacturerName"), rsManufacturers.getString("manufacturerAddress"), 
                       rsManufacturers.getString("manufacturerPhoneNumber"),rsManufacturers.getString("manufacturerContact"));
                       Service.listManufacturer.add(currentManufacturer);
           }
           //Close connection, statemet, result set
           rsManufacturers.close();
           stmtGetManufacturers.close();
           conn.close();       
        }
        //catch exception
        catch(SQLException err)
        {
            errLog.errHandling(err);
            err.printStackTrace(new PrintWriter(exceptionData));
            errLog.appendData(exceptionData.toString(), new GregorianCalendar().getTime());
        }
        catch(Exception err)
        {
            errLog.errHandling(err);
            err.printStackTrace(new PrintWriter(exceptionData));
            errLog.appendData(exceptionData.toString(), new GregorianCalendar().getTime());
        }
    }
    //Mehod to populate the arraylist of products
    public static void populateProductsArray() throws SQLException
    {
        //Clear data stored to update with current one
        Service.listProducts.clear();
        try
        {
            //Start connections, statements, resultsets
            conn = DBConnect.getConn();
            Statement stmtGetManufacturers = null;
            stmtGetManufacturers = conn.createStatement();
            //SQ query
            String query = "SELECT * FROM ProductsTable";
            //Store results of query en resultsset
            ResultSet rsProducts = null;
            rsProducts = stmtGetManufacturers.executeQuery(query);
            //loop through results
            while(rsProducts.next())
            {
                //Every product is associated with a manufacturer
                //To create an arraylist of products we need a manufcturer object
                //Retrieve the manufacturer information corresponding to the foreign key located in Products table
                Statement statM = null;
                ResultSet rs = null;
                int value = 0;
                statM = conn.createStatement();
                String query2 = "SELECT * FROM ManufacturersTable WHERE manufacturerId = '" + rsProducts.getInt("productManufacturer") + "';";
                rs =statM.executeQuery(query2);
                //Temporary manufacturer object creation with result found by query
                Manufacturer tempManufacturer = null;
                while(rs.next())
                {
                    //create temp manufacturer object
                    tempManufacturer = new Manufacturer(rs.getInt("manufacturerId"), rs.getString("manufacturerName"), rs.getString("manufacturerAddress"), rs.getString("manufacturerPhoneNumber"),rs.getString("manufacturerContact"));
                }
                //Close statement, en resultset
                rs.close();
                statM.close();
                //Creade product with corresponding manufacturer object
                Product currentProduct = new Product (rsProducts.getInt("productId"),
                        rsProducts.getString("productName"), rsProducts.getString("productCategory"), 
                        tempManufacturer,rsProducts.getString("productDescription"),
                        rsProducts.getString("productPartNum"),rsProducts.getDouble("productCost"),
                        rsProducts.getDouble("productPrice"), rsProducts.getDouble("productMarkup"), rsProducts.getInt("minInventory"));
                //Add the current product to arraylist
                Service.listProducts.add(currentProduct);
            }
            //Close connection, statement, en resultset
            rsProducts.close();
            stmtGetManufacturers.close();
            conn.close();        
        }
        catch(SQLException err)
        {
            errLog.errHandling(err);
            err.printStackTrace(new PrintWriter(exceptionData));
            errLog.appendData(exceptionData.toString(), new GregorianCalendar().getTime());
        }
        catch(Exception err)
        {
            errLog.errHandling(err);
            err.printStackTrace(new PrintWriter(exceptionData));
            errLog.appendData(exceptionData.toString(), new GregorianCalendar().getTime());
        }
    }
}
