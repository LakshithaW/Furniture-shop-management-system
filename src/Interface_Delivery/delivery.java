/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface_Delivery;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import mycode.DBconnect;
import net.proteanit.sql.DbUtils;


public class delivery extends javax.swing.JFrame {

    Connection con = null; 
    PreparedStatement pst = null; 
    PreparedStatement pst1 = null; 
    ResultSet rs= null;
    
    /**
     * Creates new form intDelivery
     */
    public delivery() {
        initComponents();
         //connect to DB
        con = DBconnect.connect();
        
        //upper buttons
        btnInfoDelete.setEnabled(false);
        btnInfoEdit.setEnabled(false);
        //lower buttons
        btnDelete.setEnabled(false);
        btnUpdate.setEnabled(false);
        btnFindVehicle.setEnabled(false);
        fillcombo();

        tableload();
        tableload2();
    }
    
    public void fillcombo()//product and model combo box load method
    {
        String sql = "select distinct product from furniture";
        try
        {
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            cmbProduct.removeAllItems();
            
            while(rs.next())
            {
                cmbProduct.addItem(rs.getString("product"));
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
            System.out.println("fillcombo");
        }
    }     
    
    public void tableload()//upper table load (deliveryInfo table)
    {
        String no = "0";
        try
        {
            String s ="select product, model, quantity from deliveryinfo where deliveryNo = '"+ no +"' ";
            pst = con.prepareStatement(s);
            rs = pst.executeQuery();
    
            tblDeliveryInfo.setModel(DbUtils.resultSetToTableModel(rs));
        }
        catch (Exception e)
        {
            System.out.println(e);
            System.out.println("table load 1");
        }
    }
    
    public void tableload2() //lower table load method (delivery table)
    {
        try
        {
            String s = "select deliveryNo, date, vehicleNo from delivery";
            pst = con.prepareStatement(s);
            rs = pst.executeQuery();
            
            tblDelivery.setModel(DbUtils.resultSetToTableModel(rs));
        }
        catch(Exception e)
        {
            System.out.println(e);
            System.out.println("table load 2");
        }
    }
    
    public void reset()//reset fields
    {
        //upper buttons
        btnInfoAdd.setEnabled(true);
        btnInfoEdit.setEnabled(false);
        btnInfoDelete.setEnabled(false);

        //lowe buttons
        btnInsert.setEnabled(true);
        btnUpdate.setEnabled(false);
        btnDelete.setEnabled(false);
        btnFindVehicle.setEnabled(false);
        
        //product and model combo box
        //fillcombo();
        
        //upper fields
        txtQuantity.setText("");
        
        //lower fields
        cmbVehicleNo.setSelectedItem("");
        deliveryDate.setCalendar(null);
        lblDescription.setText("");
        lblDeliveryNo.setText("");
        lblDescription.setText("");
    }
    
    public void vehicleCombo()//vehicle combo box load method
    {
        java.util.Date d = deliveryDate.getDate();
        String ddate = DateFormat.getDateInstance().format(d);
            
        String sql = "select distinct e.vehicleNo from vehicle e where e.vehicleNo not in (select vehicle.vehicleNo from delivery d LEFT JOIN vehicle on d.vehicleNo = vehicle.vehicleNo where d.date= '"+ ddate +"' ) ";
        try
        {
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            cmbVehicleNo.removeAllItems();
            while(rs.next())
            {
                cmbVehicleNo.addItem(rs.getString("e.vehicleNo"));
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
            System.out.println("AAA");
        }
    }
 
    public void updateDeliveryInfo()
    {
        try
        {
            //String orderno;
            String no = "0";
            String sql = "select MAX(deliveryNo) from delivery";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            
            while(rs.next())
            {
                String delNo = rs.getString("MAX(deliveryNo)");
                String sql1 = " UPDATE deliveryinfo SET deliveryNo = '"+ delNo +"' where deliveryNo = '"+ no +"' ";
                pst1 = con.prepareStatement(sql1);
                pst1.execute();
            }       
        }
        catch(Exception e)
        {
            System.out.println(e);
            System.out.println("c");
        }   
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel4 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtQuantity = new javax.swing.JTextField();
        btnInfoEdit = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        btnInfoDelete = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        txtSearch = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDeliveryInfo = new javax.swing.JTable();
        cmbProduct = new javax.swing.JComboBox();
        cmbModel = new javax.swing.JComboBox();
        cmbVehicleNo = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        btnInfoAdd = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        deliveryDate = new com.toedter.calendar.JDateChooser();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        lblDescription = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblDelivery = new javax.swing.JTable();
        btnInsert = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnReset = new javax.swing.JButton();
        btnFindVehicle = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        lblDeliveryNo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Quantity");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Product");

        txtQuantity.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtQuantity.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtQuantityKeyReleased(evt);
            }
        });

        btnInfoEdit.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        btnInfoEdit.setText("Edit");
        btnInfoEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInfoEditActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Model");

        btnInfoDelete.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        btnInfoDelete.setText("Delete");
        btnInfoDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInfoDeleteActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jButton3.setText("Search");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        txtSearch.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setText("Vehicle No ");

        tblDeliveryInfo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tblDeliveryInfo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Product", "Model", "Quantity"
            }
        ));
        tblDeliveryInfo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDeliveryInfoMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblDeliveryInfo);

        cmbProduct.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cmbProduct.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbProductItemStateChanged(evt);
            }
        });

        cmbModel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        cmbVehicleNo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cmbVehicleNo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbVehicleNoItemStateChanged(evt);
            }
        });
        cmbVehicleNo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbVehicleNoActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel1.setText("Delivery");

        btnInfoAdd.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        btnInfoAdd.setText("Add");
        btnInfoAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInfoAddActionPerformed(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jButton1.setText("Back");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        deliveryDate.addContainerListener(new java.awt.event.ContainerAdapter() {
            public void componentAdded(java.awt.event.ContainerEvent evt) {
                deliveryDateComponentAdded(evt);
            }
        });
        deliveryDate.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                deliveryDatePropertyChange(evt);
            }
        });
        deliveryDate.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                deliveryDateKeyReleased(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setText("Date");

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel8.setText("Vehicle Description");

        lblDescription.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblDescription.setText("Description");

        tblDelivery.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Delivery ID ", "Vehicle No", "Supplier ID ", "Date"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblDelivery.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDeliveryMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblDelivery);
        if (tblDelivery.getColumnModel().getColumnCount() > 0) {
            tblDelivery.getColumnModel().getColumn(0).setResizable(false);
            tblDelivery.getColumnModel().getColumn(1).setResizable(false);
            tblDelivery.getColumnModel().getColumn(2).setResizable(false);
            tblDelivery.getColumnModel().getColumn(3).setResizable(false);
        }

        btnInsert.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        btnInsert.setText("Insert");
        btnInsert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInsertActionPerformed(evt);
            }
        });

        btnUpdate.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        btnUpdate.setText("Update");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnDelete.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        btnDelete.setText("Delete");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        btnReset.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        btnReset.setText("Reset");
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });

        btnFindVehicle.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        btnFindVehicle.setText("Find Free vehicles");
        btnFindVehicle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFindVehicleActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("Delivery No");

        lblDeliveryNo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblDeliveryNo.setText("no");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addGap(186, 186, 186)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(75, 75, 75)
                                .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(19, 19, 19)
                        .addComponent(jButton3))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(61, 61, 61)
                        .addComponent(cmbProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(81, 81, 81)
                        .addComponent(jLabel3)
                        .addGap(65, 65, 65)
                        .addComponent(cmbModel, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(57, 57, 57)
                        .addComponent(txtQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 460, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnInfoAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnInfoEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnInfoDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(lblDeliveryNo, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(71, 71, 71)
                        .addComponent(deliveryDate, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(50, 50, 50)
                        .addComponent(btnFindVehicle, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 590, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnInsert, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(80, 80, 80)
                                .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(34, 34, 34)
                                .addComponent(cmbVehicleNo, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(48, 48, 48)
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(60, 60, 60)
                                .addComponent(lblDescription, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(80, 80, 80)
                                .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jButton1))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(11, 11, 11)
                        .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(jButton3)))
                .addGap(9, 9, 9)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(cmbProduct, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(cmbModel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(txtQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnInfoAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(btnInfoEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(btnInfoDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(lblDeliveryNo))
                .addGap(13, 13, 13)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnFindVehicle, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(deliveryDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(cmbVehicleNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(lblDescription, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnInsert)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnUpdate)
                        .addComponent(btnDelete)
                        .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(9, 9, 9)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnInfoEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInfoEditActionPerformed
        // TODO add your handling code here:
        int x = JOptionPane.showConfirmDialog(null, "Do you want to update?");

        if(x==0)
        {
            String product = cmbProduct.getSelectedItem().toString();
            String model = cmbModel.getSelectedItem().toString();
            String qty = txtQuantity.getText();
            String no = "0";

            String sql = "UPDATE deliveryinfo SET quantity = '"+ qty +"' where product = '"+ product +"' AND model = '"+ model +"' AND deliveryNo='"+no+"'";
            try
            {
                pst = con.prepareStatement(sql);
                pst.execute();

                tableload();
                reset();
                //fillcombo();
            }
            catch(Exception e)
            {
                System.out.println(e);
            }

        }
    }//GEN-LAST:event_btnInfoEditActionPerformed

    private void btnInfoDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInfoDeleteActionPerformed
        // TODO add your handling code here:

        int x = JOptionPane.showConfirmDialog(null, "Do you want to update?");

        if(x==0)
        {
            String product = cmbProduct.getSelectedItem().toString();
            String model = cmbModel.getSelectedItem().toString();
            String no = "0";
            
            String sql = "DELETE from deliveryinfo WHERE product = '"+ product +"' AND model = '"+ model +"' AND deliveryNo = '"+ no +"' ";
            try
            {
                pst = con.prepareStatement(sql);
                pst.execute();

                tableload();
                reset();
                //fillcombo();
            }
            catch(Exception e)
            {
                System.out.println(e);
            }

        }
    }//GEN-LAST:event_btnInfoDeleteActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void tblDeliveryInfoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDeliveryInfoMouseClicked
        
        btnInfoAdd.setEnabled(false);
        btnInfoDelete.setEnabled(true);
        btnInfoEdit.setEnabled(true);

        int r = tblDeliveryInfo.getSelectedRow();

        String product = tblDeliveryInfo.getValueAt(r, 0).toString();
        String model = tblDeliveryInfo.getValueAt(r, 1).toString();
        String quantity = tblDeliveryInfo.getValueAt(r, 2).toString();

        cmbProduct.setSelectedItem(product);
        cmbModel.setSelectedItem(model);
        txtQuantity.setText(quantity);
    }//GEN-LAST:event_tblDeliveryInfoMouseClicked

    private void cmbProductItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbProductItemStateChanged
        // TODO add your handling code here:

        String s = cmbProduct.getSelectedItem().toString();
        String sql = "SELECT * from furniture where product = '"+ s +"' ";
        try {
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            cmbModel.removeAllItems();
            while(rs.next())
            {
                cmbModel.addItem(rs.getString("model"));
            }

        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }//GEN-LAST:event_cmbProductItemStateChanged

    private void btnInfoAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInfoAddActionPerformed
        // TODO add your handling code here:

        String product = cmbProduct.getSelectedItem().toString();
        String model = cmbModel.getSelectedItem().toString();
        String qty = txtQuantity.getText();
        String no = "0";

        String sql = "INSERT INTO deliveryinfo (deliveryNo,product,model,quantity) values ( '"+ no +"', '"+ product +"' ,'"+ model +"' ,'"+ qty +"' )";
        try
        {
            pst = con.prepareStatement(sql);
            pst.execute();

            tableload();
            reset();
            //fillcombo();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }//GEN-LAST:event_btnInfoAddActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        mainDelivery x = new mainDelivery();
        x.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void cmbVehicleNoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbVehicleNoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbVehicleNoActionPerformed

    private void txtQuantityKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtQuantityKeyReleased

        String x =txtQuantity.getText();
         
        for(int i =0; i<x.length(); i++)
        {
            char c = x.charAt(i);
        
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

    private void cmbVehicleNoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbVehicleNoItemStateChanged
        if(cmbVehicleNo.getItemCount()>0){
            String no = cmbVehicleNo.getSelectedItem().toString();
            String sql = "SELECT * from vehicle where vehicleNo = '"+ no +"' ";
        
            try 
            {
                PreparedStatement pst = con.prepareStatement(sql);
                ResultSet rs = pst.executeQuery();
                lblDescription.removeAll();
                while(rs.next())
                {
                    lblDescription.setText(rs.getString("vehicleDescription"));
                }

            }
            catch (Exception e)
            {
                System.out.println(e);
            }    
        }    
    }//GEN-LAST:event_cmbVehicleNoItemStateChanged

    private void tblDeliveryMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDeliveryMouseClicked
        
        btnInsert.setEnabled(false);
        btnDelete.setEnabled(true);
        btnUpdate.setEnabled(true);
        
        int r = tblDelivery.getSelectedRow();

        String dno = tblDelivery.getValueAt(r, 0).toString();
        String date = tblDelivery.getValueAt(r, 1).toString();
        String vno = tblDelivery.getValueAt(r, 2).toString();
        
        cmbVehicleNo.setSelectedItem(vno);
        lblDeliveryNo.setText("D"+dno);
        
        SimpleDateFormat formatter = new SimpleDateFormat("MMM dd, yyyy");
        try {
            java.util.Date d = formatter.parse(date);
            deliveryDate.setDate(d);
	} 
        catch (Exception e) 
        {
		System.out.println(e);
	}
    }//GEN-LAST:event_tblDeliveryMouseClicked

    private void btnInsertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInsertActionPerformed

        java.util.Date d = deliveryDate.getDate();
        String ddate = DateFormat.getDateInstance().format(d);
        String vno = cmbVehicleNo.getSelectedItem().toString();
        
        try
        {
            String sql = "INSERT INTO delivery (date,vehicleNo) values ('"+ ddate +"', '"+ vno +"')";
            pst = con.prepareStatement(sql);
            pst.execute();
            
            updateDeliveryInfo();
            
            tableload();
            tableload2();//lower table load method call (delivery table)
            reset();//reset fields
            
            
        }
        catch(Exception e)
        {
            System.out.println(e);
            System.out.println("Insert 2");
        }
    }//GEN-LAST:event_btnInsertActionPerformed

    private void deliveryDatePropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_deliveryDatePropertyChange
        
             btnFindVehicle.setEnabled(true);
        
    }//GEN-LAST:event_deliveryDatePropertyChange

    private void btnFindVehicleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFindVehicleActionPerformed
        
//        java.util.Date d = deliveryDate.getDate();
//        String ddate = DateFormat.getDateInstance().format(d);
//        if(ddate.equals(null))
//        {
//            JOptionPane.showMessageDialog(null, "Please select the date!");
//        }
//        else
//        {
            vehicleCombo();
//        }
//        
    }//GEN-LAST:event_btnFindVehicleActionPerformed

    private void deliveryDateKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_deliveryDateKeyReleased

//        btnFindVehicle
        
    }//GEN-LAST:event_deliveryDateKeyReleased

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        
        reset();
    }//GEN-LAST:event_btnResetActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed

        int x = JOptionPane.showConfirmDialog(null, "Do you want  to update?");
        
        if(x==0)
        {
            java.util.Date d = deliveryDate.getDate();
            String ddate = DateFormat.getDateInstance().format(d);
//            String vno = cmbVehicleNo.getSelectedItem().toString();
            
            String dno = lblDeliveryNo.getText();
            String[] a = dno.split("D");
            String deliveryno = a[1];
        
            try
            {
                String sql= "UPDATE delivery SET date = '"+ ddate +"' where deliveryNo='"+deliveryno+"'";
                pst = con.prepareStatement(sql);
                pst.execute();
            
                tableload2();
                reset();
            }
            catch(Exception e)
            {
                System.out.println(e);
                System.out.println("Update 2");
            }
        }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        
        int x = JOptionPane.showConfirmDialog(null, "Do you want to delete?");
        
        if(x==0)
        {
            String dno = lblDeliveryNo.getText();
            String[] a = dno.split("D");
            String deliveryno = a[1];
            
            try
            {
                String sql = "delete from delivery where deliveryNo = '"+ deliveryno +"' ";
                String sql1 = "delete from deliveryinfo where deliveryNo = '"+ deliveryno +"' ";
                
                pst = con.prepareStatement(sql);
                pst.execute();
                pst1 = con.prepareStatement(sql1);
                pst1.execute();
                
                tableload2();
                reset();
            }
            catch(Exception e)
            {
                System.out.println(e);
                System.out.println("Delete 2");
            }
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void deliveryDateComponentAdded(java.awt.event.ContainerEvent evt) {//GEN-FIRST:event_deliveryDateComponentAdded
        // TODO add your handling code here:
         
    }//GEN-LAST:event_deliveryDateComponentAdded

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
            java.util.logging.Logger.getLogger(delivery.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(delivery.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(delivery.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(delivery.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new delivery().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnFindVehicle;
    private javax.swing.JButton btnInfoAdd;
    private javax.swing.JButton btnInfoDelete;
    private javax.swing.JButton btnInfoEdit;
    private javax.swing.JButton btnInsert;
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JComboBox cmbModel;
    private javax.swing.JComboBox cmbProduct;
    private javax.swing.JComboBox cmbVehicleNo;
    private com.toedter.calendar.JDateChooser deliveryDate;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblDeliveryNo;
    private javax.swing.JLabel lblDescription;
    private javax.swing.JTable tblDelivery;
    private javax.swing.JTable tblDeliveryInfo;
    private javax.swing.JTextField txtQuantity;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables
}
