
package General;

import Inventory.*;
import JDBC.DBConnect;
import java.sql.*;
import java.util.ArrayList;

/**
 * Final Assignment (Group assignment)
 * @version 2016-04-20
 * @author Haeyeon Kang, Cindy Diaz
 */

public class Service {
    public static ArrayList<Manufacturer> listManufacturer = new ArrayList<>();
    public static ArrayList<Product> listProducts = new ArrayList<>();
    
    private static int empId = 10000;
    private static int productId = 10000;
    private static int manufacturerId = 10000;
    private static int invoiceNumber = 100000;
    
    public static int getEmpId(){
        return empId++;//sends 10 000 first
    }
    
    public static int getProductId(){
        return productId++;
    }
    
    public static int getManufcturerId(){
        return manufacturerId++;
    }
    public static int getInvoiceNumber(){
        return invoiceNumber++;
    }
    
    //Method to retrieve an arraylist of strings containing product name
    public static ArrayList<String> getProductsArray()
    {  
        ArrayList<String> products = new ArrayList<>();
        //loop through our list of products
        for(Product temp : listProducts ){
            //Add to aeeay listt string
            products.add(temp.getProductName());
        }
        //return the string arraylist
        return products;
    }
    //Mthod to get return a string with the product information
    public static String productInformation(String product)
    {
        String searchResultProduct = "********* Product Information **********\n";
        int countProduct = 0;
        //Loop products arraylist
        for(Product currentProduct : Service.listProducts){
            //find a match
            if(currentProduct.getProductName().equals(product))
            {
                //store product information of the match
                searchResultProduct += currentProduct.getProductInfo();//Store match product.getProductInfo 
                countProduct++;//Increase count if match was found
            }
        }
        //Return information depending there was a product found or not
        return (countProduct > 0) ? searchResultProduct : "Sorry, there is no product associated to that id";
    }
}
