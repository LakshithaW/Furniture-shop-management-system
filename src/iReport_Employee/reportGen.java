/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iReport_Employee;

import java.awt.Container;
import java.sql.Connection;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import mycode.DBconnect;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.swing.JRViewer;


public class reportGen extends JFrame  {
    //private Object JasperFileManager;
   public reportGen(String filename){
       try {
           Connection con = DBconnect.connect();
           JasperPrint print = JasperFillManager.fillReport(filename, null, con);
           JRViewer jr = new JRViewer(print);
           Container contain = getContentPane();
           contain.add(jr);
           setVisible(true);
           setBounds(10,10,800,660);
      /* }catch(JRException e){
           JOptionPane.showMessageDialog(this,"JR Error");*/
                  /* } catch (JRException ex) {
           Logger.getLogger(reportGen.class.getName()).log(Level.SEVERE, null, ex);
       }*/
       } catch (Exception e){
       System.out.println();
       } 
   }
    
   
   public reportGen(String filename , HashMap hash){
       try {
           Connection con = DBconnect.connect();
           JasperPrint print = JasperFillManager.fillReport(filename, hash, con);
           JRViewer jr = new JRViewer(print);
           Container contain = getContentPane();
           contain.add(jr);
           setVisible(true);
           setBounds(10,10,800,660);
      /* }catch(JRException e){
           JOptionPane.showMessageDialog(this,"JR Error");*/
                  /* } catch (JRException ex) {
           Logger.getLogger(reportGen.class.getName()).log(Level.SEVERE, null, ex);
       }*/
       } catch (Exception e){
       System.out.println();
       } 
   }
    
}

    

