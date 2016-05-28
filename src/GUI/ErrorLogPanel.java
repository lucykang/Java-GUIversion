
package GUI;

import FileIO.ErrorDataLog;
import static JDBC.JDBC.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.sql.*;
import java.util.GregorianCalendar;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 * Final Assignment (Group assignment)
 * @version 2016-04-20
 * @author Haeyeon Kang, Cindy Diaz
 */

public class ErrorLogPanel extends JPanel {
    //variables
    private static String QRY;
    private static Connection conn = null;
    private static Statement stat = null;
    private static ResultSet rs = null;
    
    private static ErrorDataLog errLog;
    private StringWriter exceptionData;
    private static ResultSetMetaData metaData;
    
    //components
    //panels
    private JPanel firstInnerPanel = new JPanel();
    private JPanel refreshBtnPanel = new JPanel();
    
    //table
    private JTable errTable = new JTable();
    private DefaultTableModel tableModel;
    
    //button
    private JButton refreshBtn = new JButton("Refresh");
    
    public ErrorLogPanel(){
        //initialise variables
        errLog = new ErrorDataLog();
        exceptionData = new StringWriter();
        
        setLayout(new BorderLayout());
        refreshBtnPanel.setLayout(new FlowLayout());
        refreshBtnPanel.add(refreshBtn);
        add(refreshBtnPanel, BorderLayout.NORTH);
        add(firstInnerPanel, BorderLayout.CENTER);
        
        displayTable();
        eventHandler();
    }
    
    private void eventHandler(){
        refreshBtn.addActionListener(new refreshButtonHandler());
    }
    
    private class refreshButtonHandler implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            try{
                tableModel.setRowCount(0);
                
                //when refresh button is clicked, the employee and product list will be refreshed
                errTable.setModel(retrieveErrData());
            }
            catch(Exception err){
                errLog.errHandling(err);
                err.printStackTrace(new PrintWriter(exceptionData));
                errLog.appendData(exceptionData.toString(), new GregorianCalendar().getTime());
            }
        }
    }
    
    //method to display table in textarea
    public void displayTable(){
        firstInnerPanel.setLayout(new BorderLayout());
        
        //set the table to the table model by using a method in JDBC class
        tableModel = retrieveErrData();
        errTable.setModel(tableModel);

        //set border
        firstInnerPanel.setBorder(BorderFactory.createTitledBorder("Error Log Lookup"));

        //add components
        firstInnerPanel.add(errTable, BorderLayout.CENTER);
        firstInnerPanel.add(errTable.getTableHeader(), BorderLayout.NORTH);
    }//end displayTable()
}//end class
