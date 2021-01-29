/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package iReport_Finance;

import mycode.DBconnect;
import java.awt.Container;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.swing.JRViewer;


public class genarateReport extends JFrame {
    public genarateReport(String filename )
    {
    try{
    
        Connection con =DBconnect.connect();
        JasperPrint print = JasperFillManager.fillReport(filename,null,con);
        JRViewer jr= new JRViewer(print);
        Container contain = getContentPane();
         contain.add(jr);
         setVisible(true); 
         setBounds(10,10,800,660);
    
    }
    catch(JRException e) 
    {
      JOptionPane.showMessageDialog(this, "JRE error");
    }
    
    
    
    
    }
    
    
      public genarateReport(String filename,HashMap hash )
    {
    try{
    
        Connection con =DBconnect.connect();
        JasperPrint print = JasperFillManager.fillReport(filename,hash,con);
        JRViewer jr= new JRViewer(print);
        Container contain = getContentPane();
         contain.add(jr);
         setVisible(true); 
         setBounds(10,10,800,660);
    
    }
    catch(JRException e) 
    {
      JOptionPane.showMessageDialog(this, "JRE error");
    }
    
   
    
    
    
    }
}



