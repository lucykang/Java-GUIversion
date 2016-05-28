
package GUI;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Final Assignment (Group assignment)
 * @version 2016-04-20
 * @author Haeyeon Kang, Cindy Diaz
 */
public class RetailCompanyGUI extends JFrame{
    
    //components
    private JTabbedPane mainTabPane;
    private JButton exitBtn;
    private JPanel northPanel, southPanel;
    
    //constructor
    public RetailCompanyGUI(){
        //set the title
        super("Lucindy Company Management System");
        
        //set layout
        setLayout(new BorderLayout());
        
        //build panels
        buildNorthPanel();
        buildCenterPanel();
        buildSouthPanel();
        
        windowFeatures();        
    } //end of constructor
    
    //window features method
    private void windowFeatures(){
        //pack the content
        pack();
        
        //set location
        setLocationRelativeTo(null);
        
        //set size of the window
        setSize(600,800);
        
        //set close operations
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //make the frame visible
        setVisible(true);
    }//end of windowFeatures()
    
    //method that builds north panel
    private void buildNorthPanel(){
        //create and add the north panel
        northPanel = new TopPanel();
        add(northPanel, BorderLayout.NORTH);
    }
    
    //method that builds center panel
    private void buildCenterPanel(){
        buildTabs();
        add(mainTabPane, BorderLayout.CENTER);
    }
    
    //method that builds south panel
    private void buildSouthPanel(){
        southPanel = new JPanel();
        exitBtn = new JButton("Exit");
        
        //add action listener
        exitBtn.addActionListener(new ExitButtonHandler());
        
        //add btn to the panel
        southPanel.add(exitBtn);
        //southPanel.add(errorMessage); text area to show error message
        
        add(southPanel, BorderLayout.SOUTH);
    }//end buildSouthPanel()
    
    //method for tabs
    private void buildTabs(){
        //initialise tab interface
        mainTabPane = new JTabbedPane();
        
        //add tabs
        mainTabPane.addTab("Employee", null, new EmployeePanel(), "Register Employee");
        mainTabPane.addTab("Product", null, new ProductPanel(), "Register Product");
        mainTabPane.addTab("Search", null, new SearchPanel(), "Search Employee or Product");
        mainTabPane.addTab("Error Log", null, new ErrorLogPanel(), "Error Log Lookup");
    }//end buildTabs()
    
    //class to handle action for exit button
    private class ExitButtonHandler implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            System.exit(0);
        }
    }
    
    public static void main(String[]args){
        new RetailCompanyGUI();
    }
    
}
