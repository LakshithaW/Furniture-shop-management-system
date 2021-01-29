/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface_Suppliers;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.StringTokenizer;
import javax.swing.JOptionPane;
import mycode.DBconnect;
import net.proteanit.sql.DbUtils;

public class orders extends javax.swing.JFrame {
    Connection con = null;
    PreparedStatement pst = null;
    PreparedStatement pst1 = null;
    PreparedStatement pst2 = null;
    PreparedStatement pst3 = null;
    ResultSet rs = null;
    ResultSet rs1= null;
    
    /**
     * Creates new form intOrders
     */
    public orders() {
        initComponents();
        
        //connect DB 
        con = DBconnect.connect();
        
        //fill combo boxes 
        supfillcombo();
        fillcombo();
        tableload1();
        tableload2();
        
        btnTmpEdit.setEnabled(false);
        btnTmpDelete.setEnabled(false);
        btnDelete.setEnabled(false);
        btnUpdate.setEnabled(false);
        
    }

    public void supfillcombo()
    {
        String sql = "select * from supplier";
        try
        {
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            cmbSupID.removeAllItems();
            
            while(rs.next())
            {
                cmbSupID.addItem(rs.getString("supplierID"));
            }
        }
        catch(Exception e)
        {
        System.out.println(e);
        }
    }
    
    public void fillcombo()
    {
        
        String sql = "select distinct product from furniture";
        try
        {
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            //cmbProduct.removeAllItems();
            //cmbModel.removeAllItems();
            
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
    
    public void tableload1()
    {
        
        try
        {
            String no = "0";
            String sql = "SELECT material, unitPrice, quantity, totalPrice FROM orderinfo where orderNo = '"+ no +"' ";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            
            tblOrderInfo.setModel(DbUtils.resultSetToTableModel(rs));
        }
        catch (Exception e)
        {
            System.out.println("tableload1");
            System.out.println(e);
        }    
    }
    
    public void tableload2()
    {
        try
        {
            String sql = "SELECT orderNo, supplierID, date, totalPrice from orders";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            
            tblOrder.setModel(DbUtils.resultSetToTableModel(rs));
        }
        catch(Exception e)
        {
            
        }
    }
    
    public void reset()
    {
        btnTempAdd.setEnabled(true);
        btnTmpDelete.setEnabled(false);
        btnTmpEdit.setEnabled(false);
        
        btnAdd.setEnabled(true);
        btnDelete.setEnabled(false);
        btnUpdate.setEnabled(false);
       
        txtQuantity.setText("");
        txtPrice.setText("");
        
        lblInvoiceNo.setText(null);
        orderDate.setCalendar(null);
    }
    
 
    
    
    public void insertOrder()
    {
        String supid = cmbSupID.getSelectedItem().toString();
        try
        {   
            String no = "0";
            String sql = "SELECT SUM(totalPrice) from orderinfo WHERE orderNo = '"+ no +"' ";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            
            if(rs.next())
            {
                String sum = rs.getString("SUM(totalPrice)");
                
                insert(supid, sum); 
                updateOrderInfo();
            }
            
        }
        catch(Exception e)
        {
            System.out.println(e);
            System.out.println("a");
        }
        
    }

    public void insert(String supID, String totalPrice)
    {
        
        try 
        {
            java.util.Date d = orderDate.getDate();
            String odate = DateFormat.getDateInstance().format(d);
            String sql1;
            sql1 = "INSERT INTO orders (totalPrice,supplierID,date) VALUES ('"+ totalPrice +"','"+ supID +"', '"+odate+"') ";
            pst = con.prepareStatement(sql1);
            pst.execute();
        } 
        catch (Exception e) 
        {  
            System.out.println(e);
            System.out.println("b");
        }
    }
    
    
    public void updateOrderInfo()
    {
        try
        {
            //String orderno;
            String no = "0";
            String sql = "select MAX(orderNo) from orders";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            
            while(rs.next())
            {
                String orderNo = rs.getString("MAX(orderNo)");
                String sql1 = " UPDATE orderinfo SET orderNo = '"+ orderNo +"' where orderNo = '"+ no +"' ";
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

    public boolean validator()
    {
        if(txtQuantity.getText().equals(""))
        {
            JOptionPane.showMessageDialog(null,"Quantity Field is Empty ");
            return false;
        }
        else if(txtPrice.getText().equals(""))
        {
            JOptionPane.showMessageDialog(null,"Price Field is Empty ");
            return false;
        }
        return true;
    }
           
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnTmpEdit = new javax.swing.JButton();
        btnTmpDelete = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblOrder = new javax.swing.JTable();
        btnAdd = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnReset = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        txtQuantity = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        cmbSupID = new javax.swing.JComboBox();
        cmbProduct = new javax.swing.JComboBox();
        cmbMaterial = new javax.swing.JComboBox();
        cmbModel = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblOrderInfo = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        btnSearch = new javax.swing.JButton();
        txtSearch = new javax.swing.JTextField();
        txtPrice = new javax.swing.JTextField();
        btnTempAdd = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        lblInvoiceNo = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        orderDate = new com.toedter.calendar.JDateChooser();
        jLabel11 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnTmpEdit.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        btnTmpEdit.setText("Edit");
        btnTmpEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTmpEditActionPerformed(evt);
            }
        });

        btnTmpDelete.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        btnTmpDelete.setText("Delete");
        btnTmpDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTmpDeleteActionPerformed(evt);
            }
        });

        tblOrder.setModel(new javax.swing.table.DefaultTableModel(
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
                {null, null, null, null},
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
                "Invoice Number", "Date ", "Supplier ID", "Total Price"
            }
        ));
        tblOrder.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblOrderMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblOrder);

        btnAdd.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        btnAdd.setText("Place Order");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
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

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("Quantity");

        txtQuantity.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtQuantityKeyReleased(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setText("Product");

        cmbProduct.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbProductItemStateChanged(evt);
            }
        });
        cmbProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbProductActionPerformed(evt);
            }
        });

        cmbMaterial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbMaterialActionPerformed(evt);
            }
        });

        cmbModel.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbModelItemStateChanged(evt);
            }
        });

        tblOrderInfo.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tblOrderInfo.setModel(new javax.swing.table.DefaultTableModel(
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
                {null, null, null, null},
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
                "Material ", "Price", "Quantity", "Total price"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblOrderInfo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblOrderInfoMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblOrderInfo);

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setText("Price");

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel1.setText("Orders");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Supplier ID ");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Model");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Material Name");

        btnSearch.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        btnSearch.setText("Search");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });
        btnSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnSearchKeyPressed(evt);
            }
        });

        txtSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtSearchKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchKeyReleased(evt);
            }
        });

        txtPrice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPriceActionPerformed(evt);
            }
        });
        txtPrice.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPriceKeyReleased(evt);
            }
        });

        btnTempAdd.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        btnTempAdd.setText("Add");
        btnTempAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTempAddActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel8.setText("Order No");

        jButton1.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jButton1.setText("Back");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel9.setText("Invoice No");

        lblInvoiceNo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblInvoiceNo.setText("No");

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel10.setText("Date");

        orderDate.setDateFormatString("MM-dd-yyyy");

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel11.setText("OR");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addGap(171, 171, 171)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(80, 80, 80)
                                .addComponent(jLabel8)))
                        .addGap(34, 34, 34)
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(btnSearch))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(71, 71, 71)
                        .addComponent(cmbProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40)
                        .addComponent(jLabel3)
                        .addGap(45, 45, 45)
                        .addComponent(cmbModel, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(36, 36, 36)
                        .addComponent(cmbMaterial, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(67, 67, 67)
                        .addComponent(txtQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40)
                        .addComponent(jLabel7)
                        .addGap(51, 51, 51)
                        .addComponent(txtPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 480, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnTempAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnTmpEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnTmpDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(50, 50, 50)
                        .addComponent(lblInvoiceNo, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(50, 50, 50)
                        .addComponent(cmbSupID, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(60, 60, 60)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(orderDate, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnAdd)
                        .addGap(61, 61, 61)
                        .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(51, 51, 51)
                        .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(51, 51, 51)
                        .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 640, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(jButton1))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(jLabel8))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addComponent(jLabel11))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(btnSearch)))
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel6))
                    .addComponent(cmbProduct, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(cmbModel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(cmbMaterial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(txtQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))))
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnTempAdd)
                        .addGap(9, 9, 9)
                        .addComponent(btnTmpEdit)
                        .addGap(9, 9, 9)
                        .addComponent(btnTmpDelete)))
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addComponent(lblInvoiceNo))
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cmbSupID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(orderDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAdd)
                    .addComponent(btnUpdate)
                    .addComponent(btnDelete)
                    .addComponent(btnReset))
                .addGap(9, 9, 9)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnTmpEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTmpEditActionPerformed
        // TODO add your handling code here:

        int x = JOptionPane.showConfirmDialog(null, "Do you want to update?");
        int r = tblOrderInfo.getSelectedRow();

        if(x == 0)
        {
            String orderNo = "0";
            String material = tblOrderInfo.getValueAt(r, 0).toString();
            String qty = txtQuantity.getText();
            String price = txtPrice.getText();

            double q = Double.parseDouble(qty);
            double p = Double.parseDouble(price);
            double tp = q*p;
            String total = Double.toString(tp);

            String sql = "UPDATE orderinfo SET price = '"+ price +"' , quantity = '"+ qty +"' , totalPrice = '"+ total +"' where orderNo = '"+ orderNo +"' AND material = '"+ material +"' ";

            try
            {
                pst = con.prepareStatement(sql);
                pst.execute();

                tableload1();
                reset();
                //supfillcombo();
                //fillcombo();
            }
            catch(Exception e)
            {

            }
        }
    }//GEN-LAST:event_btnTmpEditActionPerformed

    private void btnTmpDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTmpDeleteActionPerformed
        // TODO add your handling code here:

        int x = JOptionPane.showConfirmDialog(null, "Do you want to delete?");
        int r = tblOrderInfo.getSelectedRow();

        if(x == 0)
        {
            String material = tblOrderInfo.getValueAt(r, 0).toString();
            String orderNo = "0";

            String sql = "DELETE from orderinfo where material = '"+ material +"' AND orderNo = '"+ orderNo +"' ";
            try
            {
                pst = con.prepareStatement(sql);
                pst.execute();

                tableload1();
                reset();
                //supfillcombo();
                fillcombo();
            }
            catch(Exception e)
            {

            }

        }
    }//GEN-LAST:event_btnTmpDeleteActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed

            insertOrder();
            tableload2();
            tableload1();
        
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        // TODO add your handling code here:
        int x = JOptionPane.showConfirmDialog(null, "Do you really want to update?");
                
        if(x==0)
        {
            
            String no = lblInvoiceNo.getText();
            String[] a = no.split("R"); 
            String orderno = a[1];
              
            String supId = cmbSupID.getSelectedItem().toString();
            
            java.util.Date d = orderDate.getDate();
            String odate = DateFormat.getDateInstance().format(d);
            try
            {
                String sql = "UPDATE orders SET supplierID = '"+ supId +"', date = '"+ odate +"' where orderNo = '"+ orderno +"' ";
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

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        // TODO add your handling code here:
        supfillcombo();
        //fillcombo();
        reset();
    }//GEN-LAST:event_btnResetActionPerformed

    private void cmbProductItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbProductItemStateChanged

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

        }
    }//GEN-LAST:event_cmbProductItemStateChanged

    private void cmbProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbProductActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbProductActionPerformed

    private void cmbMaterialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbMaterialActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbMaterialActionPerformed

    private void cmbModelItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbModelItemStateChanged
        // TODO add your handling code here:

        String p = cmbProduct.getSelectedItem().toString();
        String m = cmbModel.getSelectedItem().toString();
        String sql = "SELECT material from materialratio where product = '"+ p +"' AND model = '"+ m +"' ";
        try {
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            cmbMaterial.removeAllItems();
            while(rs.next())
            {
                cmbMaterial.addItem(rs.getString("material"));
            }

        }
        catch (Exception e)
        {

        }
    }//GEN-LAST:event_cmbModelItemStateChanged

    private void tblOrderInfoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblOrderInfoMouseClicked
        // TODO add your handling code here:

        btnTempAdd.setEnabled(false);
        btnTmpEdit.setEnabled(true);
        btnTmpDelete.setEnabled(true);
        
        int r = tblOrderInfo.getSelectedRow();

        //String material = tblOrderInfo.getValueAt(r, 0).toString();
        String price = tblOrderInfo.getValueAt(r, 1).toString();
        String qty = tblOrderInfo.getValueAt(r, 2).toString();
        //String total = tblOrderInfo.getValueAt(r, 3).toString();

        //cmbMaterial.setSelectedItem(material);
        txtQuantity.setText(qty);
        txtPrice.setText(price);
    }//GEN-LAST:event_tblOrderInfoMouseClicked

    private void txtPriceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPriceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPriceActionPerformed

    private void btnTempAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTempAddActionPerformed
        // TODO add your handling code here:
        boolean check = validator();
        if(check==true)
        {
            String orderno = "0";
            String material = cmbMaterial.getSelectedItem().toString();
            String quantity = txtQuantity.getText();
            String price = txtPrice.getText();

            double qty = Double.parseDouble(quantity);
            double p = Double.parseDouble(price);
            double tp = qty*p;
            String total = Double.toString(tp);

            try
            {
                String q = "INSERT INTO orderinfo (orderNo,material,unitPrice,quantity,totalPrice) values ( '"+ orderno +"', '"+ material +"', '"+ price +"', '"+ quantity +"', '"+ total +"')";
                pst = con.prepareStatement(q);
                pst.execute();

                tableload1();
                reset();

            }
            catch (Exception e)
            {
                System.out.println("table 1 add");
                System.out.println(e);
            }
        }
    }//GEN-LAST:event_btnTempAddActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        mainSuppliers x = new mainSuppliers();
        x.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtQuantityKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtQuantityKeyReleased
        // TODO add your handling code here:
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

    private void txtPriceKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPriceKeyReleased
        // TODO add your handling code here:
        
        String x =txtPrice.getText();
         
        for(int i =0; i<x.length(); i++){
        
        char c = x.charAt(i);
        
        if(Character.isDigit(c))
        {
            
        }
        else if(Character.isLetter(c))
        {
            JOptionPane.showMessageDialog(null, "This field cannot have letters", "Error",0);
            txtPrice.setText(" ");
 
            break;
        }
        }
        
    }//GEN-LAST:event_txtPriceKeyReleased

    private void tblOrderMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblOrderMouseClicked
        // TODO add your handling code here:
        
        btnAdd.setEnabled(false);
        btnUpdate.setEnabled(true);
        btnDelete.setEnabled(true);
        
        int r = tblOrder.getSelectedRow();
        
        String invoiceNo = tblOrder.getValueAt(r, 0).toString();
        String supid = tblOrder.getValueAt(r, 1).toString();
        String date = tblOrder.getValueAt(r, 2).toString();
        
        lblInvoiceNo.setText("OR"+invoiceNo); 
        cmbSupID.setSelectedItem(supid);
        
        SimpleDateFormat formatter = new SimpleDateFormat("MMM dd, yyyy");
        try 
        {
                java.util.Date d = formatter.parse(date);
		orderDate.setDate(d);
	} 
        catch (Exception e) 
        {
		System.out.println(e);
	}
        
    }//GEN-LAST:event_tblOrderMouseClicked

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        // TODO add your handling code here:
      
        String no = lblInvoiceNo.getText();
        String[] a = no.split("R");  
        String orderNo = a[1];
              int x = JOptionPane.showConfirmDialog(null, "Do you really want to Delete");
                      
              if(x==0){
                  
                  try
                  {
                    String sql = "DELETE from orders where orderNo = '"+ orderNo +"'";
                    String sql2 = "DELETE from orderinfo where orderNo = '"+ orderNo +"'";
                    pst=con.prepareStatement(sql);
                    pst.execute();
                      
                    pst2=con.prepareStatement(sql2);
                    pst2.execute();
                      
                    //lode table
                    tableload2();
                    reset();
                  }
                  catch(Exception e)
                  {
                      System.out.println(e);
                      System.out.println("a");
                  }
              }
              
                 
        
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnSearchKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnSearchKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSearchKeyPressed

    private void txtSearchKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSearchKeyPressed

    private void txtSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyReleased
        // TODO add your handling code here:
        
    }//GEN-LAST:event_txtSearchKeyReleased

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        // TODO add your handling code here:
        
        String search = txtSearch.getText();
        
        try 
        {
            String sql = "SELECT orderNo, supplierID, date, totalPrice from orders where orderNo like '%"+ search +"%' ";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            
            tblOrder.setModel(DbUtils.resultSetToTableModel(rs));
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        txtSearch.setText("");
    }//GEN-LAST:event_btnSearchActionPerformed

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
            java.util.logging.Logger.getLogger(orders.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(orders.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(orders.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(orders.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new orders().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnTempAdd;
    private javax.swing.JButton btnTmpDelete;
    private javax.swing.JButton btnTmpEdit;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JComboBox cmbMaterial;
    private javax.swing.JComboBox cmbModel;
    private javax.swing.JComboBox cmbProduct;
    private javax.swing.JComboBox cmbSupID;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lblInvoiceNo;
    private com.toedter.calendar.JDateChooser orderDate;
    private javax.swing.JTable tblOrder;
    private javax.swing.JTable tblOrderInfo;
    private javax.swing.JTextField txtPrice;
    private javax.swing.JTextField txtQuantity;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables
}
