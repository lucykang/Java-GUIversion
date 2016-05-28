
package GUI;

import FileIO.ErrorDataLog;
import General.Service;
import JDBC.JDBC;
import static JDBC.JDBC.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.sql.SQLException;
import java.util.*;
import javax.swing.*;

/**
 * Final Assignment (Group assignment)
 * @version 2016-04-20
 * @author Haeyeon Kang, Cindy Diaz
 */

public class SearchPanel extends JPanel{
    //components
    //panels
    private JPanel upperMainPanel = new JPanel();
    private JPanel firstInnerPanel = new JPanel();
    private JPanel secondInnerPanel = new JPanel();
    private JPanel refreshButtonPanel = new JPanel();
    
    //labels
    private JLabel searchEmpLabel, searchProdLabel;
    
    //combobox (emp or product)
    private JComboBox<String> searchEmpDD;
    private JComboBox<String> searchProdDD;
    private JComboBox searchProductsDD;
    private DefaultComboBoxModel empModel = new DefaultComboBoxModel();
    private DefaultComboBoxModel productModel = new DefaultComboBoxModel();
    
    //button
    private JButton refreshBtn;
    
    //JTextArea
    private JTextArea resultBox;
    
    //objects to handle errors
    private ErrorDataLog errLog;
    private StringWriter exceptionData;
    
    public SearchPanel(){
        errLog = new ErrorDataLog();
        exceptionData = new StringWriter();
        
        //setLayout(new GridLayout(1,1));
        setLayout(new BorderLayout());
        
        add(upperMainPanel, BorderLayout.NORTH);
        //add(firstInnerPanel, BorderLayout.NORTH);
        add(secondInnerPanel, BorderLayout.CENTER);
        
        mainPanel();
        resultPanel();
        eventHandlers();
    }
    
    private void resultPanel(){
        secondInnerPanel.setLayout(new GridLayout(0,1));
        
        //set border
        secondInnerPanel.setBorder(BorderFactory.createTitledBorder("Search Result"));
        
        resultBox = new JTextArea("");
        resultBox.setEditable(false);
        resultBox.setLineWrap(true);
        
        secondInnerPanel.add(resultBox);
    }
    
    //main panel
    private void mainPanel(){
        upperMainPanel.setLayout(new BorderLayout());
        firstInnerPanel.setLayout(new GridLayout(5,2));
        refreshButtonPanel.setLayout(new FlowLayout());
        
        //create components
        searchEmpLabel = new JLabel("Search Employee: ");
        searchProdLabel = new JLabel("Search Product: ");
        
        refreshBtn = new JButton("Refresh Lists");
        
        searchEmpDD = new JComboBox();
        searchProductsDD = new JComboBox();
        
        //set border
        upperMainPanel.setBorder(BorderFactory.createTitledBorder("Search Employee or Product"));
        
        //add components
        refreshButtonPanel.add(refreshBtn);
        
        firstInnerPanel.add(searchEmpLabel);
        firstInnerPanel.add(searchEmpDD);
        firstInnerPanel.add(searchProdLabel);
        firstInnerPanel.add(searchProductsDD);
        
        upperMainPanel.add(firstInnerPanel, BorderLayout.CENTER);
        upperMainPanel.add(refreshButtonPanel, BorderLayout.SOUTH);
        
        empItemUpdate();
        productItemUpdate();
    }
    
    //method to update employee combobox
    public void empItemUpdate(){
        try{
            //reset the model
            empModel.removeAllElements();
            
            //retrieve data and store them in the arraylist
            ArrayList<String> empData = retrieveEmpData();
            
            //store the data into the model
            for(String emp : empData){
                empModel.addElement(emp);
            }
            
            //set combobox to the model
            searchEmpDD.setModel(empModel);
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
    }//end empItemUpdate()
    
    //method to update product combobox
    public void productItemUpdate(){
        try{
            //reset the model
            productModel.removeAllElements();
            
            Service.listProducts.clear();
            JDBC.populateProductsArray();
            ArrayList<String> prodData =  Service.getProductsArray();
            //store the data into the model
            for(String product : prodData){
                productModel.addElement(product);
            }
            
            //set combobox to the model
            searchProductsDD.setModel(productModel);
        }
        catch(SQLException err){
            errLog.errHandling(err);
        }
        catch(Exception err){
            errLog.errHandling(err);
        }
            
    }//end productItemUpdate()
    
    private void eventHandlers(){
        //add action listener
        searchEmpDD.addActionListener(new DropBoxHandler());
        searchProductsDD.addActionListener(new ProductsDropDownHandler());
        refreshBtn.addActionListener(new refreshButtonHandler());
    }
    
    private class refreshButtonHandler implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            try{
                //when refresh button is clicked, the employee and product list will be refreshed
                empItemUpdate();
                productItemUpdate();
            }
            catch(Exception err){
                errLog.errHandling(err);
                err.printStackTrace(new PrintWriter(exceptionData));
                errLog.appendData(exceptionData.toString(), new GregorianCalendar().getTime());
            }
        }
    }
    
    //employee dropbox handler
    private class DropBoxHandler implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            try{
                //if there's one selected from the dropbox, display the data in the textarea
                String selection = (String) searchEmpDD.getSelectedItem();
                resultBox.setText(displayEmpData(selection));
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
        }
    }
    
    private class ProductsDropDownHandler implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            try{
                //if there's one selected from the dropbox, display the data in the textarea
                String selection = (String) searchProductsDD.getSelectedItem();
                resultBox.setText(Service.productInformation(selection));
            }
            catch(Exception err){
                errLog.errHandling(err);
                err.printStackTrace(new PrintWriter(exceptionData));
                errLog.appendData(exceptionData.toString(), new GregorianCalendar().getTime());
            }
        }
    }
}
