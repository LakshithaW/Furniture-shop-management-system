/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface_Cushioning;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import mycode.DBconnect;
import net.proteanit.sql.DbUtils;


public class Quoatation extends javax.swing.JFrame {
    
        Connection con =null;
        PreparedStatement pst = null;
        PreparedStatement pst2 = null;
        ResultSet rs = null;
        ResultSet rs2= null;

    
    public Quoatation() {
        initComponents();
        
        con = DBconnect.connect();
        loadDetails();
        tableLoad();
        comboLoad();
        tableLoad2(); 
        
        btnInfoDelete.setEnabled(false);
        btnInfoUpdate.setEnabled(false);
        
        btnUpdate.setEnabled(false);
        btnDelete.setEnabled(false);
        
    }
    
    
    @SuppressWarnings("unchecked")
    
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        lblMatirialType = new javax.swing.JLabel();
        lblQuantity = new javax.swing.JLabel();
        cmbmt = new javax.swing.JComboBox();
        txtQuantity = new javax.swing.JTextField();
        lblup1 = new javax.swing.JLabel();
        lblmc1 = new javax.swing.JLabel();
        lblup = new javax.swing.JLabel();
        lblmc = new javax.swing.JLabel();
        btnInfoAdd = new javax.swing.JButton();
        btnInfoDelete = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        lblTotalcost2 = new javax.swing.JLabel();
        lblExtraCost3 = new javax.swing.JLabel();
        lblTotacost3 = new javax.swing.JLabel();
        lblTotalcost7 = new javax.swing.JLabel();
        lblCoustomerID9 = new javax.swing.JLabel();
        lblQuatationID13 = new javax.swing.JLabel();
        lblCoustomerID = new javax.swing.JLabel();
        cmbCus = new javax.swing.JComboBox();
        lblExtraCost = new javax.swing.JLabel();
        txtLabourCost = new javax.swing.JTextField();
        btnInfoUpdate = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        table2 = new javax.swing.JTable();
        btnAdd = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        txtDescription = new javax.swing.JTextArea();
        jLabel3 = new javax.swing.JLabel();
        lblQuotationID = new javax.swing.JLabel();
        btnUpdate = new javax.swing.JButton();

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable2);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblMatirialType.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblMatirialType.setText("Matirial Type");

        lblQuantity.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblQuantity.setText("Quantity");

        cmbmt.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbmtItemStateChanged(evt);
            }
        });
        cmbmt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbmtActionPerformed(evt);
            }
        });

        txtQuantity.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtQuantityActionPerformed(evt);
            }
        });
        txtQuantity.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtQuantityKeyReleased(evt);
            }
        });

        lblup1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblup1.setText("Unite Price");

        lblmc1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblmc1.setText("Matirial Cost");

        lblup.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblup.setText("up");

        lblmc.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblmc.setText("mc");

        btnInfoAdd.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnInfoAdd.setText("Add");
        btnInfoAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInfoAddActionPerformed(evt);
            }
        });

        btnInfoDelete.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnInfoDelete.setText("Delete");
        btnInfoDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInfoDeleteActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "QuotationID", "MatirialDescripction", "UnitPrice", "Quantity", "TotalCost"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(jTable1);

        lblTotalcost2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        lblExtraCost3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        lblTotacost3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        lblTotalcost7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        lblCoustomerID9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        lblQuatationID13.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        lblCoustomerID.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblCoustomerID.setText("Coustomer ID");

        cmbCus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbCusActionPerformed(evt);
            }
        });

        lblExtraCost.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblExtraCost.setText("Labour cost");

        txtLabourCost.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtLabourCost.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtLabourCostActionPerformed(evt);
            }
        });

        btnInfoUpdate.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnInfoUpdate.setText("Update");
        btnInfoUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInfoUpdateActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel2.setText(" Quotation");

        table2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "QuotationID", "CustomerID", "HumanCost", "TotalCost", "Date"
            }
        ));
        table2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table2MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(table2);

        btnAdd.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnAdd.setText("Add");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnDelete.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnDelete.setText("Delete");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jButton3.setText("Back");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Description");

        txtDescription.setColumns(20);
        txtDescription.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtDescription.setRows(5);
        jScrollPane4.setViewportView(txtDescription);

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Quotation ID");

        lblQuotationID.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblQuotationID.setText("QID");

        btnUpdate.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnUpdate.setText("Update");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(jButton3)
                .addGap(221, 221, 221)
                .addComponent(jLabel2))
            .addGroup(layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addComponent(lblMatirialType)
                .addGap(35, 35, 35)
                .addComponent(cmbmt, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50)
                .addComponent(lblup1, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46)
                .addComponent(lblup, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addComponent(lblQuantity)
                .addGap(57, 57, 57)
                .addComponent(txtQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50)
                .addComponent(lblmc1)
                .addGap(58, 58, 58)
                .addComponent(lblmc, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 650, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnInfoAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnInfoUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnInfoDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGroup(layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(lblQuotationID)
                .addGap(176, 176, 176)
                .addComponent(lblExtraCost)
                .addGap(47, 47, 47)
                .addComponent(txtLabourCost, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(lblCoustomerID)
                .addGap(34, 34, 34)
                .addComponent(cmbCus, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTotacost3)
                    .addComponent(lblTotalcost7)
                    .addComponent(lblCoustomerID9)
                    .addComponent(lblExtraCost3)
                    .addComponent(lblTotalcost2)
                    .addComponent(lblQuatationID13))
                .addGap(8, 8, 8)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 640, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton3)
                    .addComponent(jLabel2))
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblMatirialType)
                    .addComponent(cmbmt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblup1)
                    .addComponent(lblup))
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblQuantity)
                    .addComponent(txtQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblmc1)
                    .addComponent(lblmc))
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnInfoAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(btnInfoUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(btnInfoDelete)))
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(lblQuotationID)
                    .addComponent(lblExtraCost)
                    .addComponent(txtLabourCost, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblCoustomerID, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbCus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblTotacost3)
                            .addComponent(lblTotalcost7)
                            .addComponent(lblCoustomerID9)
                            .addComponent(lblExtraCost3)
                            .addComponent(lblTotalcost2)
                            .addComponent(lblQuatationID13)))
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)
                        .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)
                        .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(2, 2, 2))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
//public void saveTable(){
//    for(int i=0; i<jTable1.getRowCount();i++){
//        int total = 0;
//        int amount =0;
//        try{
//        String s = jTable1.getValueAt(i,4).toString();
//        amount =Integer.parseInt(s);
//        total = amount+total;
//        JOptionPane.showMessageDialog(null,Integer.toString(total));
//        }
//        catch(Exception e){
//            System.out.println(e);
//        }
//        //System.out.println(total);
//    }                
//}
    
public void insertQuotation()
    {
        String cusid = cmbCus.getSelectedItem().toString();
        String lcost = txtLabourCost.getText();
        String description = txtDescription.getText();
        try
        {   
            String no = "0";
            String sql = "SELECT SUM(totalCost) from quotationinfo1 WHERE quotationID = '"+ no +"' ";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            //while(rs.next()){
            if(rs.next())
            {
                String sum = rs.getString("SUM(totalCost)");
                double t = Double.parseDouble(sum);
                double l = Double.parseDouble(lcost);
                double tot = t+l;
                String total = Double.toString(tot);
                
                insert(total, cusid, lcost, description); 
                updateQuotationInfo();
           
            }
            
        }
        catch(Exception e)
        {
            System.out.println(e);
            System.out.println("a");
        }
        
    }

public void insert(String total, String cusid, String lcost, String description)
    {
        
        try 
        {
            String sql1= "INSERT INTO quotation (totalCost,customerID,humanCost,description) VALUES ('"+ total +"','"+ cusid +"', '"+ lcost +"', '"+ description +"' ) ";
            pst = con.prepareStatement(sql1);
            pst.execute();
        } 
        catch (Exception e) 
        {  
            System.out.println(e);
            System.out.println("b");
        }
    }
    
    
    public void updateQuotationInfo()
    {
        try
        {
            //String orderno;
            String no = "0";
            String sql = "select MAX(quotationID) from quotation";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            
            while(rs.next())
            {
                String qno = rs.getString("MAX(quotationID)");
                String sql1 = " UPDATE quotationinfo1 SET quotationID = '"+ qno +"' where quotationID = '"+ no +"' ";
                pst2 = con.prepareStatement(sql1);
                pst2.execute();
            }       
        }
        catch(Exception e)
        {
            System.out.println(e);
            System.out.println("c");
        }   
    } 

    public void loadDetails(){
        try{
        
        String sql ="SELECT * FROM customer";
        pst = con.prepareStatement(sql);
        rs = pst.executeQuery();
        cmbCus.removeAllItems();
            while(rs.next()){
                cmbCus.addItem(rs.getString("customerID"));
            }
        }
       
        catch (Exception e){
        System.out.println(e);
        }
    }
    
    
    public void tableLoad(){
        try
        {
            String qno = "0";
            String sql="SELECT * FROM quotationinfo1 where quotationID = '"+ qno +"' ";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            
            jTable1.setModel(DbUtils.resultSetToTableModel(rs));
        }
        catch(Exception e){
            System.out.println(e);
        }
            
    }


public void comboLoad(){
        try
        {
        String sql = "SELECT * FROM materialratio";
        pst = con.prepareStatement(sql);
        rs = pst.executeQuery();
        cmbmt.removeAllItems();
            while(rs.next())
            {
                    cmbmt.addItem(rs.getString("material"));
            }
        }
        catch(Exception e)
        {
            System.out.println(e); 
            
        }
    }

public void cuscomboload()
{
    try{
        String sql = "SELECT * FROM customer";
        pst = con.prepareStatement(sql);
        rs = pst.executeQuery();
        cmbCus.removeAllItems();
            while(rs.next())
            {
                cmbCus.addItem(rs.getString("customerID"));
            }
        }
        catch(Exception e)
        {
            System.out.println(e);   
        }
}

 public void tableLoad2()
 {
        try
        {
            String sql="SELECT * FROM quotation";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            
            table2.setModel(DbUtils.resultSetToTableModel(rs));
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
            
    }

 public void reset()
 {
     btnAdd.setEnabled(true);
     btnInfoUpdate.setEnabled(false);
     btnInfoDelete.setEnabled(false);
     btnAdd.setEnabled(true);
     btnUpdate.setEnabled(false);
     btnDelete.setEnabled(false);
     
     
    //cmbmt.setSelectedItem("Select matirial type");
    //comboLoad();
    txtQuantity.setText("");
    lblup.setText("");
    lblmc.setText("");
 
 }
 
 public void reset2()
 {
        txtDescription.setText("");
        txtLabourCost.setText("");
 }

    private void cmbmtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbmtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbmtActionPerformed

    private void txtQuantityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtQuantityActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtQuantityActionPerformed

    private void btnInfoAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInfoAddActionPerformed
        //temp add
        String qty = txtQuantity.getText();
        String unitPrice = lblup.getText();
        String material = cmbmt.getSelectedItem().toString();
        String id = "0";
        tableLoad();

        if(!qty.isEmpty()){
            
            Double cost =  (Integer.parseInt(qty) * Double.parseDouble(unitPrice));
            String total = cost.toString();
            lblmc.setText(total);
            try{
                
                String sql = "INSERT INTO quotationinfo1 (quotationID,materilalDescription,unitPrice,quantity,totalCost) values('"+ id +"', '"+ material +"', '"+ unitPrice +"','"+ qty +"', '"+ total +"')";
                pst = con.prepareStatement(sql);
                pst.execute();
                
                tableLoad();
                reset();

            }
            catch(Exception e)
            {
                System.out.println("ABC");
                System.out.println(e);
            }

        }
        
        else
        {
            JOptionPane.showMessageDialog(null," Please Enter Quantity");
        }

    }//GEN-LAST:event_btnInfoAddActionPerformed

    private void cmbCusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbCusActionPerformed

          
        
    }//GEN-LAST:event_cmbCusActionPerformed

    private void txtLabourCostActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtLabourCostActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtLabourCostActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked

        btnInfoAdd.setEnabled(false);
        btnInfoUpdate.setEnabled(true);
        btnInfoDelete.setEnabled(true);
        
        int r = jTable1.getSelectedRow();
        
        String id = jTable1.getValueAt(r, 0).toString();
        String mtype = jTable1.getValueAt(r, 1).toString();
        String uprice = jTable1.getValueAt(r, 2).toString();
        String qu = jTable1.getValueAt(r, 3).toString();
        String tcost = jTable1.getValueAt(r, 4).toString();
         
        cmbmt.setSelectedItem(mtype); 
        txtQuantity.setText(qu);
        lblup.setText(uprice);
        lblmc.setText(tcost);
        //lblMID.setText(id);
        
        
         
                
        
        
    }//GEN-LAST:event_jTable1MouseClicked

    private void btnInfoDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInfoDeleteActionPerformed

        String ID ="0";
        String Mtype =cmbmt.getSelectedItem().toString();
        String Qun = txtQuantity.getText();
        String up =lblup.getText(); 
            
        if(Qun.isEmpty())
        {
            JOptionPane.showMessageDialog(null,"Please Select What You Want To Delete");
        }
        else
        {
            int x = JOptionPane.showConfirmDialog(null, "Do you really want to Delete");
         
            if(x==0){
         
            //String Mtype =cmbmt.getSelectedItem().toString();
            String sql="DELETE from quotationinfo1 where materilalDescription = '"+Mtype+"'";
         
                try
                {
                    pst=con.prepareStatement(sql);
                    pst.execute();
                    //lode table
                    tableLoad();  
                    reset();
                }
                catch(Exception e)
                {
             
                }
            }       
        
        }   
    }//GEN-LAST:event_btnInfoDeleteActionPerformed

    private void btnInfoUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInfoUpdateActionPerformed

            String id ="0";
            String Mtype =cmbmt.getSelectedItem().toString();
            String Qun = txtQuantity.getText();
            String up =lblup.getText();
            
            if(Qun.isEmpty())
            {
                JOptionPane.showMessageDialog(null,"Please Select What You Want To Update?");
            }
            else
            {
                int x = JOptionPane.showConfirmDialog(null, "Do you really want to update");
              
                if(x==0)
                {
                
//            String ID ="0";
//            String Mtype =cmbmt.getSelectedItem().toString();
//            String Qun = txtQuantity.getText();
//            String up =lblup.getText();
            
           
                double q = Double.parseDouble(Qun);
                double p = Double.parseDouble(up);
            
                double t = q*p;
                String mc = Double.toString(t);
                lblmc.setText(mc);
                //lblmc.setText(mc);
            
                String sql = "UPDATE quotationinfo1 SET  quantity= '"+Qun+"' , unitPrice= '"+up+"' , totalCost='"+mc+"' where materilalDescription ='"+Mtype+"' AND quotationID = '"+ id +"' ";
            
             
                    try{
                        pst=con.prepareStatement(sql);
                        pst.execute();
                        
                        //lode table
                       tableLoad();
//                       reset();
                    }
                    catch(Exception e)
                    {
                        System.out.println("update");
                        System.out.println(e);
                    }
                    
                }
            }
         
    
    }//GEN-LAST:event_btnInfoUpdateActionPerformed

    private void cmbmtItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbmtItemStateChanged
        // TODO add your handling code here:
        
        String value = cmbmt.getSelectedItem().toString();
        //int val=Integer.parseInt(value);
        //System.out.println(val);

        try{
            String sql2 = "SELECT unitPrice from orderinfo where material = '"+ value +"' ";

            pst2 = con.prepareStatement(sql2);
            rs2 = pst2.executeQuery();
            while(rs2.next())
            {
                String unit1=rs2.getString("unitPrice");
                lblup.setText(unit1);
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        } 
        
    }//GEN-LAST:event_cmbmtItemStateChanged

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        insertQuotation();
        tableLoad2();
        tableLoad();
        reset2();
       
    }//GEN-LAST:event_btnAddActionPerformed

    private void txtQuantityKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtQuantityKeyReleased
        // TODO add your handling code here:
        
                String faccos =txtQuantity.getText();
         
        for(int i =0; i<faccos.length(); i++){
        
        char c = faccos.charAt(i);
        
        if(Character.isDigit(c))
        {
            
        }
        else if(Character.isLetter(c))
        {
         JOptionPane.showMessageDialog(null, "This field cannot have letters", "Error",0);
            txtQuantity.setText(" ");
 
            break;
        }
        } 
    }//GEN-LAST:event_txtQuantityKeyReleased

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        
        mainCushioning x = new mainCushioning();
        x.setVisible(true);
        this.dispose();
        
    }//GEN-LAST:event_jButton3ActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        // TODO add your handling code here:
        String ID = lblQuotationID.getText();
        String lcost = txtLabourCost.getText(); 
            
        if(lcost.isEmpty())
        {
            JOptionPane.showMessageDialog(null,"Please Select What You Want To Delete");
        }
        else
        {
            int x = JOptionPane.showConfirmDialog(null, "Do you really want to Delete");
         
            if(x==0){
         
            String sql = "DELETE from quotation where quotationID = '"+ ID +"'";
            String sql2 = "DELETE from quotationinfo1 where quotationID = '"+ ID +"'";
         
                try
                {
                    pst=con.prepareStatement(sql);
                    pst.execute();
                    
                    pst2=con.prepareStatement(sql2);
                    pst2.execute();
                    
                    //lode table
                    tableLoad2();  
                    reset2();
                }
                catch(Exception e)
                {
                    System.out.println(e);
                    System.out.println("a");
                }
            }       
        
        }
        
        
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void table2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table2MouseClicked
        
        btnAdd.setEnabled(false);
        btnUpdate.setEnabled(true);
        btnDelete.setEnabled(true);
        
        int r = table2.getSelectedRow();
        
        String id = table2.getValueAt(r, 0).toString();
        String lcost = table2.getValueAt(r, 2).toString();
        String cusID = table2.getValueAt(r, 4).toString();
        String description = table2.getValueAt(r, 5).toString();
        
        lblQuotationID.setText(id); 
        txtLabourCost.setText(lcost);
        cmbCus.setSelectedItem(cusID);
        txtDescription.setText(description);
        //lblMID.setText(id);        
        
        
    }//GEN-LAST:event_table2MouseClicked

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        // TODO add your handling code here:
        
            String id = lblQuotationID.getText();
            String cusID = cmbCus.getSelectedItem().toString();
            String newlcost = txtLabourCost.getText();
            String description = txtDescription.getText();
            
            if(newlcost.isEmpty())
            {
                JOptionPane.showMessageDialog(null,"Please Select What You Want To Update?");
            }
            else
            {
                int x = JOptionPane.showConfirmDialog(null, "Do you really want to update");
              
                if(x==0)
                {   
                    try{
                        
                        String sql1 = "select humanCost,totalCost from quotation where quotationID = '"+ id +"'";
                        pst = con.prepareStatement(sql1);
                        rs = pst.executeQuery();
                        
                        if(rs.next())
                        {
                            String l = rs.getString("humanCost");
                            String t = rs.getString("totalCost");
                            double olcost = Double.parseDouble(l);
                            double otcost = Double.parseDouble(t);
                            double nlcost = Double.parseDouble(newlcost);
                            
                            double newTot = (otcost-olcost)+nlcost ;
                            String total = Double.toString(newTot);
                      
                            String sql = "UPDATE quotation SET  humanCost = '"+ newlcost +"' , totalCost = '"+ total +"' , customerID='"+ cusID +"', description = '"+ description +"' where quotationID ='"+ id +"' ";
                            pst2=con.prepareStatement(sql);
                            pst2.execute();
                        
                            //lode table
                            tableLoad2();
                            reset2();
                            
                        }
                        
                        
                    }
                    catch(Exception e)
                    {
                        System.out.println("update");
                        System.out.println(e);
                    }
                    
                }
            }        
        
    }//GEN-LAST:event_btnUpdateActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Quoatation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Quoatation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Quoatation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Quoatation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Quoatation().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnInfoAdd;
    private javax.swing.JButton btnInfoDelete;
    private javax.swing.JButton btnInfoUpdate;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JComboBox cmbCus;
    private javax.swing.JComboBox cmbmt;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JLabel lblCoustomerID;
    private javax.swing.JLabel lblCoustomerID9;
    private javax.swing.JLabel lblExtraCost;
    private javax.swing.JLabel lblExtraCost3;
    private javax.swing.JLabel lblMatirialType;
    private javax.swing.JLabel lblQuantity;
    private javax.swing.JLabel lblQuatationID13;
    private javax.swing.JLabel lblQuotationID;
    private javax.swing.JLabel lblTotacost3;
    private javax.swing.JLabel lblTotalcost2;
    private javax.swing.JLabel lblTotalcost7;
    private javax.swing.JLabel lblmc;
    private javax.swing.JLabel lblmc1;
    private javax.swing.JLabel lblup;
    private javax.swing.JLabel lblup1;
    private javax.swing.JTable table2;
    private javax.swing.JTextArea txtDescription;
    private javax.swing.JTextField txtLabourCost;
    private javax.swing.JTextField txtQuantity;
    // End of variables declaration//GEN-END:variables

    //private void tableLoad() {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    //}
}
