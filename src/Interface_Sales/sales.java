/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface_Sales;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import mycode.DBconnect;
import net.proteanit.sql.DbUtils;


public class sales extends javax.swing.JFrame {

    Connection con = null;
    PreparedStatement ps = null;
    PreparedStatement ps1 = null;
    PreparedStatement ps2 = null;
    ResultSet rs = null;
    ResultSet rs1 = null;
    ResultSet rs2 = null;
    
    
    public sales() {
        initComponents();
        
        //connet to db
        con = DBconnect.connect();
        
        
        fillcombo();
        tableload();
        tableload2();
        cuscombo();
        
        btn_tblupdate.setEnabled(false);
        btn_tblDlt.setEnabled(false);
        btn_Update.setEnabled(false);
        btn_Delete.setEnabled(false);
        
    }
    
    public void cuscombo()
    {
        String sql = "select customerID from customer";
        try
        {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while(rs.next()){
                cmbCusID.addItem(rs.getString("customerID"));
            }
        }
        catch(Exception e)
        {
            System.out.println("cuscombo");
            System.out.println(e);
        }
    }
    
    public void fillcombo()
    {
        cbox_prdct.removeAllItems();
        String sql = "select distinct product from furniture";
        try
        {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while(rs.next())
            {
                cbox_prdct.addItem(rs.getString("product"));
            }
        }
        catch(Exception e)
        {
        
        }
    }
    
    public boolean validator()
    {
        if(txt_qty.getText().equals(""))
        {
            JOptionPane.showMessageDialog(null, "Quantity field is empty!", "Error", 0);
            return false;
        }
        else if(totalPrice.getText().equals(""))
        {
            JOptionPane.showMessageDialog(null, "Calculate the total price!", "Error", 0);
            return false;
        }
        return true;
    }
    public void tableload()
    {
        try
        {
            String id = "0";
            String sql = "SELECT product,model,quantity,totalPrice FROM salesinfo where receiptNo='"+ id +"' ";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
        
            tbl_SalesInfo.setModel(DbUtils.resultSetToTableModel(rs));
        
        }
            catch(Exception e)
        {
            System.out.println(e);
            System.out.println("tableload");
        }
    }
    
    public void tableload2()
    {
        try
        {
            String sql = "Select receiptNo,customerID,date,invoicnePrice,warranty from sale";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            
            tbl_Sales.setModel(DbUtils.resultSetToTableModel(rs));
        }
        catch(Exception e)
        {
        }
    }
    
    public void reset()
    {
        btn_Add.setEnabled(true);
        btn_tblupdate.setEnabled(false);
        btn_tblDlt.setEnabled(false);
        
        btn_process.setEnabled(true);
        btn_Update.setEnabled(false);
        btn_Delete.setEnabled(false);
        
        //fillcombo();
        
        totalPrice.setText("");
        txt_qty.setText("");
        
        lblReceiptNo.setText("");
        saleDate.setCalendar(null);
        txtWarranty.setText("");
        txtSearch.setText("");
        tableload();
        tableload2();
    }
     
    public void insertSale()
    {
        String cusid = cmbCusID.getSelectedItem().toString();
        try
        {   
            String no = "0";
            String sql = "SELECT SUM(totalPrice) from salesinfo WHERE receiptNo = '"+ no +"' ";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            
            if(rs.next())
            {
                String sum = rs.getString("SUM(totalPrice)");
                
                insert(cusid, sum); 
                updateSaleInfo();
            }
            
        }
        catch(Exception e)
        {
            System.out.println(e);
            System.out.println("a");
        }
        
    }

public void insert(String cusID, String totalPrice)
    {
        
        try 
        {
            java.util.Date d = saleDate.getDate();
            String sdate = DateFormat.getDateInstance().format(d);
            String warranty = txtWarranty.getText();
            String sql1;
            sql1 = "INSERT INTO sale (invoicnePrice,customerID,date,warranty) VALUES ('"+ totalPrice +"','"+ cusID +"', '"+sdate+"', '"+ warranty +"' ) ";
            ps = con.prepareStatement(sql1);
            ps.execute();
        } 
        catch (Exception e) 
        {  
            System.out.println(e);
            System.out.println("b");
        }
    }
    
    
    public void updateSaleInfo()
    {
        try
        {
            String no = "0";
            String sql = "select MAX(receiptNo) from sale";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while(rs.next())
            {
                String receiptNo = rs.getString("MAX(receiptNo)");
                String sql1 = " UPDATE salesinfo SET receiptNo = '"+ receiptNo +"' where receiptNo = '"+ no +"' ";
                ps1 = con.prepareStatement(sql1);
                ps1.execute();
                updatestock(receiptNo);
            }
            
        }
        catch(Exception e)
        {
            System.out.println(e);
            System.out.println("c");
        }   
    }
    public void updatestock(String receiptNo)
    {
        try
        {
            String sql = "select * from salesinfo where receiptNo = '"+ receiptNo +"' ";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while(rs.next())
            {
                String product = rs.getString("product");
                String model = rs.getString("model");
                int quantity = Integer.parseInt(rs.getString("quantity"));
                try
                {
                    String sql1 = "select quantity from furniturestock where product = '"+ product +"' AND model = '"+ model +"' ";
                    ps1 = con.prepareStatement(sql1);
                    rs1 = ps1.executeQuery();
                    while(rs1.next())
                    {
                        int qty = Integer.parseInt(rs1.getString("quantity"));
                        qty=qty-quantity;
                        try
                        {
                            String sql2 = "UPDATE furniturestock set quantity = '"+ qty +"' where product = '"+ product +"' AND model = '"+ model +"' ";
                            ps2 = con.prepareStatement(sql2);
                            ps2.execute();
                        }
                        catch(Exception exy)
                        {
                            System.out.println(exy);
                            System.out.println(" update stock try 3");
                        }
                    }
                }
                catch(Exception e)
                {
                    System.out.println(e);
                    System.out.println("update stock try 2");
                }
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
            System.out.println("Update stock");
        }
    }
     
    public boolean checkstock(String product, String model, String quantity)
    {
        try
        {
            String sql = "select count(product),quantity from furniturestock where product = '"+ product +"' AND model = '"+ model +"'  ";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            
            int qty1 = Integer.parseInt(quantity);//get amount
            
            while(rs.next())
            {   
                int count =0;
                count = Integer.parseInt(rs.getString("count(product)"));
                
                if(count==0)
                {
                    return false;
                }
                else if (count==1)
                {
                    int qty = Integer.parseInt(rs.getString("quantity"));//stock amount
                    if(qty<qty1)
                    {
                        return false;
                    }
                }
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
            System.out.println("try 1");
        }
        return true;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbl_Product = new javax.swing.JLabel();
        lbl_pMdl = new javax.swing.JLabel();
        lbl_qty = new javax.swing.JLabel();
        lbl_Price = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbl_SalesInfo = new javax.swing.JTable();
        btn_Delete = new javax.swing.JButton();
        btn_Update = new javax.swing.JButton();
        btn_process = new javax.swing.JButton();
        btn_calcTcost = new javax.swing.JButton();
        lbl_price = new javax.swing.JLabel();
        lbl_tPrice = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tbl_Sales = new javax.swing.JTable();
        btn_Add = new javax.swing.JButton();
        btn_tblupdate = new javax.swing.JButton();
        btn_tblDlt = new javax.swing.JButton();
        cbox_prdct = new javax.swing.JComboBox();
        cbox_prdctMdl = new javax.swing.JComboBox();
        txt_qty = new javax.swing.JTextField();
        btn_Invoice = new javax.swing.JButton();
        lbl_Sales = new javax.swing.JLabel();
        lbl_tprice = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        cmbCusID = new javax.swing.JComboBox();
        saleDate = new com.toedter.calendar.JDateChooser();
        jLabel2 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        txtSearch = new javax.swing.JTextField();
        btnSearch = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lblReceiptNo = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtWarranty = new javax.swing.JTextField();
        totalPrice = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lbl_Product.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbl_Product.setText("Product : ");

        lbl_pMdl.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbl_pMdl.setText("Model : ");

        lbl_qty.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbl_qty.setText("Quantity : ");

        lbl_Price.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbl_Price.setText("Price : ");

        tbl_SalesInfo.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tbl_SalesInfo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Product", "Model", "Warranty", "Quantity", "Total Price"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_SalesInfo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_SalesInfoMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tbl_SalesInfo);

        btn_Delete.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        btn_Delete.setText("Delete");
        btn_Delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_DeleteActionPerformed(evt);
            }
        });

        btn_Update.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        btn_Update.setText("Update");
        btn_Update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_UpdateActionPerformed(evt);
            }
        });

        btn_process.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        btn_process.setText("Process");
        btn_process.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_processActionPerformed(evt);
            }
        });

        btn_calcTcost.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        btn_calcTcost.setText("Total Cost");
        btn_calcTcost.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_calcTcostActionPerformed(evt);
            }
        });

        lbl_price.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbl_price.setText("2000");

        lbl_tPrice.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        tbl_Sales.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tbl_Sales.setModel(new javax.swing.table.DefaultTableModel(
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
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Receipt Number", "Customer ID ", "Date", "Invoice Price"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_Sales.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_SalesMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tbl_Sales);

        btn_Add.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        btn_Add.setText("Add");
        btn_Add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_AddActionPerformed(evt);
            }
        });

        btn_tblupdate.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        btn_tblupdate.setText("Update");
        btn_tblupdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_tblupdateActionPerformed(evt);
            }
        });

        btn_tblDlt.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        btn_tblDlt.setText("Delete");
        btn_tblDlt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_tblDltActionPerformed(evt);
            }
        });

        cbox_prdct.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cbox_prdct.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbox_prdctItemStateChanged(evt);
            }
        });
        cbox_prdct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbox_prdctActionPerformed(evt);
            }
        });

        cbox_prdctMdl.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cbox_prdctMdl.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbox_prdctMdlItemStateChanged(evt);
            }
        });
        cbox_prdctMdl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbox_prdctMdlActionPerformed(evt);
            }
        });

        txt_qty.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txt_qty.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                txt_qtyPropertyChange(evt);
            }
        });
        txt_qty.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_qtyKeyReleased(evt);
            }
        });

        btn_Invoice.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        btn_Invoice.setText("Invoice");
        btn_Invoice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_InvoiceActionPerformed(evt);
            }
        });

        lbl_Sales.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        lbl_Sales.setText("Sales");

        lbl_tprice.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbl_tprice.setText("Total Price : ");

        jButton1.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jButton1.setText("Reset");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Customer ID");

        cmbCusID.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Date");

        jButton2.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jButton2.setText("Back");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        btnSearch.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        btnSearch.setText("Search");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Invoice No");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Receipt No ");

        lblReceiptNo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("Warranty");

        txtWarranty.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtWarrantyKeyReleased(evt);
            }
        });

        totalPrice.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        totalPrice.setText("tp");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(jButton2)
                .addGap(171, 171, 171)
                .addComponent(lbl_Sales))
            .addGroup(layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(lbl_Product)
                .addGap(18, 18, 18)
                .addComponent(cbox_prdct, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(120, 120, 120)
                .addComponent(lbl_pMdl)
                .addGap(52, 52, 52)
                .addComponent(cbox_prdctMdl, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(lbl_qty)
                .addGap(14, 14, 14)
                .addComponent(txt_qty, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(120, 120, 120)
                .addComponent(lbl_Price)
                .addGap(68, 68, 68)
                .addComponent(lbl_price))
            .addGroup(layout.createSequentialGroup()
                .addGap(350, 350, 350)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(70, 70, 70)
                .addComponent(btnSearch))
            .addGroup(layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(lblReceiptNo, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(100, 100, 100)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(txtWarranty, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(jLabel1)
                .addGap(32, 32, 32)
                .addComponent(cmbCusID, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(61, 61, 61)
                .addComponent(jLabel2)
                .addGap(71, 71, 71)
                .addComponent(saleDate, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(btn_process, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(btn_Update, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(btn_Delete, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(60, 60, 60)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(60, 60, 60)
                .addComponent(btn_Invoice, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 566, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btn_calcTcost, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(120, 120, 120)
                        .addComponent(lbl_tprice)
                        .addGap(18, 18, 18)
                        .addComponent(totalPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 530, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(70, 70, 70)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btn_Add, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_tblupdate)
                            .addComponent(btn_tblDlt, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbl_tPrice))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jButton2))
                    .addComponent(lbl_Sales))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbl_Product)
                            .addComponent(cbox_prdct, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbl_pMdl)
                            .addComponent(cbox_prdctMdl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(19, 19, 19)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbl_qty)
                            .addComponent(txt_qty, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbl_Price)
                            .addComponent(lbl_price))
                        .addGap(7, 7, 7)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(11, 11, 11)
                                .addComponent(btn_calcTcost, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(lbl_tprice))
                            .addComponent(lbl_tPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(15, 15, 15)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btn_Add, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(14, 14, 14)
                                .addComponent(btn_tblupdate, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(btn_tblDlt, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(24, 24, 24)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSearch))
                        .addGap(9, 9, 9)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(lblReceiptNo, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtWarranty, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(cmbCusID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)
                            .addComponent(saleDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(17, 17, 17)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btn_process)
                            .addComponent(btn_Update)
                            .addComponent(btn_Delete)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_Invoice))
                        .addGap(19, 19, 19)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(112, 112, 112)
                        .addComponent(totalPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void tbl_SalesInfoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_SalesInfoMouseClicked

        btn_Add.setEnabled(false);
        btn_tblupdate.setEnabled(true);
        btn_tblDlt.setEnabled(true);
        
        int r = tbl_SalesInfo.getSelectedRow();

        String product = tbl_SalesInfo.getValueAt(r,0).toString();
        String model = tbl_SalesInfo.getValueAt(r,1).toString();
        String quantity = tbl_SalesInfo.getValueAt(r,2).toString();

        cbox_prdct.setSelectedItem(product);
        txt_qty.setText(quantity);
        cbox_prdctMdl.setSelectedItem(model);
        
    }//GEN-LAST:event_tbl_SalesInfoMouseClicked

    private void btn_DeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_DeleteActionPerformed

        int d = JOptionPane.showConfirmDialog(null, "Do you really want to Delete");

        if(d==0)
        {
            String no = lblReceiptNo.getText();
            String[] a = no.split("N");
            String receiptNo = a[1];

            String sql = "DELETE from salesinfo where receiptNo = '"+ receiptNo +"'";
            String sql1 = "DELETE from sale where receiptNo = ''"+ receiptNo +" ";
            try
            {
                ps = con.prepareStatement(sql);
                ps.execute();
                
                ps1 = con.prepareStatement(sql1);
                ps1.execute();

            }
            catch (Exception e)
            {
                System.out.println(e);
                System.out.println("Delete");
            }
            tableload();
        }

        // TODO add your handling code here:
    }//GEN-LAST:event_btn_DeleteActionPerformed

    private void btn_UpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_UpdateActionPerformed

        int x = JOptionPane.showConfirmDialog(null, "Do you really want to update");

        if(x==0)
        {
            String no = lblReceiptNo.getText();
            String[] a = no.split("N");
            String receiptNo = a[1];

            String pr = cbox_prdct.getSelectedItem().toString();
            String qu = txt_qty.getText();
            String mo = cbox_prdctMdl.getSelectedItem().toString();
            String tpr = totalPrice.getText();
            String warranty = txtWarranty.getText();

            try
            {
                String sql = " UPDATE salesinfo SET product = '"+ pr +"', model = '"+ mo +"' , quantity = '"+ qu +"' , totalPrice = '"+ tpr +"',  warranty = '"+ warranty +"' WHERE receiptNo = '"+ receiptNo +"'";

                ps = con.prepareStatement(sql);
                ps.execute();
                tableload();
            }
            catch (Exception e)
            {
                System.out.println(e);
            }

        }

        // TODO add your handling code here:
    }//GEN-LAST:event_btn_UpdateActionPerformed

    private void btn_processActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_processActionPerformed

        
        //updatestock(id);
        insertSale();
        tableload();
        tableload2();
        reset();
    }//GEN-LAST:event_btn_processActionPerformed

    private void btn_calcTcostActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_calcTcostActionPerformed

        String price = lbl_price.getText();
        String qty = txt_qty.getText();

        if(!qty.isEmpty())
        {
            Double cost = Integer.parseInt(qty)* Double.parseDouble(price);
            String f = cost.toString();
            //totalPrice
            totalPrice.setText(f);
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Quantity field is empty!", "Error", 0);
        }

        
    }//GEN-LAST:event_btn_calcTcostActionPerformed

    private void tbl_SalesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_SalesMouseClicked

        btn_process.setEnabled(false);
        btn_Update.setEnabled(true);
        btn_Delete.setEnabled(true);
        
        int r = tbl_Sales.getSelectedRow();
        
        String receiptNo = tbl_Sales.getValueAt(r, 0).toString();
        String cusid = tbl_Sales.getValueAt(r, 1).toString();
        String date = tbl_Sales.getValueAt(r, 2).toString();
        String warranty = tbl_Sales.getValueAt(r, 4).toString();
        
        lblReceiptNo.setText("RN"+receiptNo); 
        cmbCusID.setSelectedItem(cusid);
        txtWarranty.setText(warranty);
        
        SimpleDateFormat formatter = new SimpleDateFormat("MMM dd, yyyy"); 
        try {
                java.util.Date d = formatter.parse(date);
		saleDate.setDate(d);
		

	} catch (Exception e) {
		System.out.println(e);
	}
    }//GEN-LAST:event_tbl_SalesMouseClicked

    private void btn_AddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_AddActionPerformed

        String receiptNo = "0";
        String product = cbox_prdct.getSelectedItem().toString();
        String model = cbox_prdctMdl.getSelectedItem().toString();
        String quantity = txt_qty.getText();
        String price = lbl_price.getText();
        String total = totalPrice.getText();
        
        boolean check = validator();
        
        if(check==true)
        {
            boolean check1 = checkstock(product,model,quantity);
            if(check1 == false)
            {
                JOptionPane.showMessageDialog(null, "Not enough quantity in stock", "Error", 0);
                reset();
            }
            else if(check1==true)
            {
                try
                {
                    String q = "INSERT INTO salesinfo (receiptNo,product,model,quantity,totalPrice)values ( '"+ receiptNo +"', '"+ product +"', '"+ model +"','"+ quantity +"','"+ total +"')";
                    ps = con.prepareStatement(q);
                    ps.execute();

                    tableload();
                    reset();
                    //fillcombo();
                }
                catch (Exception e)
                {
                    System.out.println(e);
                    System.out.println("Add info");
                }
            }
        }
        
        
        
    }//GEN-LAST:event_btn_AddActionPerformed

    private void btn_tblupdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_tblupdateActionPerformed

        
            String qty = txt_qty.getText();
            String total = lbl_tprice.getText();
            String product = cbox_prdct.getSelectedItem().toString();
            String mdl = cbox_prdctMdl.getSelectedItem().toString();

            //"UPDATE salesinfo SET  warranty ='"+ warranty +"' , totalPrice = '"+ total +"', quantity = '"+ qty +"' where receiptNo = '"+ no +"' AND product = '"+ product +"' AND model = '"+ mdl +"' "
            boolean check = validator();
        
            if(check==true)
            {
                boolean check1 = checkstock(product,mdl,qty);
                if(check1 == false)
                {
                    JOptionPane.showMessageDialog(null, "Not enough quantity in stock");
                    reset();
                }
                else if(check1==true)
                {
                    int x = JOptionPane.showConfirmDialog(null, "Do you want to update?");

                    if(x == 0)
                    {
                        try
                        {
                            String no = "0";
                            String price = totalPrice.getText();
                            String sql = "UPDATE salesinfo SET quantity = '"+ qty +"' ,totalPrice ='"+ price +"' where receiptNo = '"+ no +"' AND product = '"+ product +"' AND model = '"+ mdl +"' " ;
                            ps = con.prepareStatement(sql);
                            ps.execute();

                            tableload();
                            reset();

                            fillcombo();
                        }
                        catch(Exception e)
                        {
                            System.out.println(e);
                            System.out.println("ipdate");
                        }
                    }
                }   
            }
        
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_tblupdateActionPerformed

    private void btn_tblDltActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_tblDltActionPerformed

        int x = JOptionPane.showConfirmDialog(null, "Do you want to delete?");

        if(x == 0)
        {
            
            try
            {
                String RN = "0";
                String product = cbox_prdct.getSelectedItem().toString();
                String mdl = cbox_prdctMdl.getSelectedItem().toString();

                String sql = "DELETE from salesinfo where receiptNo = '"+ RN +"' AND product ='"+ product +"' AND model='"+ mdl +"'";
                ps = con.prepareStatement(sql);
                ps.execute();

                tableload();
                reset();
                //fillcombo();
            }
            catch(Exception e)
            {
                System.out.println(e);
                System.out.println("delete info");
            }

        }

    }//GEN-LAST:event_btn_tblDltActionPerformed

    private void cbox_prdctItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbox_prdctItemStateChanged
        
        //item change in product combo box
        if(cbox_prdct.getItemCount()>0)
        {
        String s = cbox_prdct.getSelectedItem().toString();
        String sql = "SELECT model FROM furniture WHERE product = '"+ s +"'";
        try
        {
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            cbox_prdctMdl.removeAllItems();
            while(rs.next())
            {
                cbox_prdctMdl.addItem(rs.getString("model"));
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        }

        // TODO add your handling code here:
    }//GEN-LAST:event_cbox_prdctItemStateChanged

    private void cbox_prdctActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbox_prdctActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbox_prdctActionPerformed

    private void cbox_prdctMdlItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbox_prdctMdlItemStateChanged
        if(cbox_prdctMdl.getItemCount()>0)
        {
            String mdl = cbox_prdctMdl.getSelectedItem().toString();
            String sql = "SELECT sellingPrice FROM furniturecost WHERE model='"+ mdl +"'";
            try
            {
                PreparedStatement pst = con.prepareStatement(sql);
                ResultSet rs = pst.executeQuery();
                while(rs.next())
                {
                    lbl_price.setText(rs.getString("sellingPrice"));
                }
            }
            catch(Exception e)
            {
                System.out.println(e);
                System.out.println("price");
            }
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_cbox_prdctMdlItemStateChanged

    private void cbox_prdctMdlActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbox_prdctMdlActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbox_prdctMdlActionPerformed

    private void btn_InvoiceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_InvoiceActionPerformed

        //        salesinvoice invoice = new salesinvoice();
        //        Calendar now = Calendar.getInstance();
        //        String cc=(new SimpleDateFormat("YYYY-MM-dd").format(now.getTime()));
        //
        //        invoice.setLabelText(lbl_RN.getText(),lbl_tPrice.getText(),cc);
        //        invoice.setVisible(true);
        //        this.dispose();
        //

        // TODO add your handling code here:
    }//GEN-LAST:event_btn_InvoiceActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        mainCustomers x = new mainCustomers();
        x.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void txt_qtyKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_qtyKeyReleased
        // TODO add your handling code here:
        String x =txt_qty.getText();
         
        for(int i =0; i<x.length(); i++)
        { 
            char c = x.charAt(i);
        
            if(Character.isDigit(c))
            {
            
            }
            else if(Character.isLetter(c))
            {
                JOptionPane.showMessageDialog(null, "This field cannot have letters", "Error",0);
                txt_qty.setText(" ");
 
                break;
            }
        }
        
    }//GEN-LAST:event_txt_qtyKeyReleased

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        reset();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txt_qtyPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_txt_qtyPropertyChange
        
    }//GEN-LAST:event_txt_qtyPropertyChange

    private void txtWarrantyKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtWarrantyKeyReleased
        
        String x =txtWarranty.getText();
         
        for(int i =0; i<x.length(); i++){
        
        char c = x.charAt(i);
        
        if(Character.isDigit(c))
        {
            
        }
        else if(Character.isLetter(c))
        {
            JOptionPane.showMessageDialog(null, "This field cannot have letters", "Error",0);
            txtWarranty.setText(" ");
 
            break;
        }
        }
        
    }//GEN-LAST:event_txtWarrantyKeyReleased

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        
        String search = txtSearch.getText();

        String sql = "Select receiptNo,customerID,date,invoicePrice,warranty from sale where receiptNo LIKE '"+ search +"' ";

        try
        {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            tbl_Sales.setModel(DbUtils.resultSetToTableModel(rs));
            
            //reset();
        }
        catch(Exception e)
        {
            System.out.println(e);
            System.out.println("Search");
        }
    }//GEN-LAST:event_btnSearchActionPerformed

    
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
            java.util.logging.Logger.getLogger(sales.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(sales.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(sales.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(sales.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new sales().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btn_Add;
    private javax.swing.JButton btn_Delete;
    private javax.swing.JButton btn_Invoice;
    private javax.swing.JButton btn_Update;
    private javax.swing.JButton btn_calcTcost;
    private javax.swing.JButton btn_process;
    private javax.swing.JButton btn_tblDlt;
    private javax.swing.JButton btn_tblupdate;
    private javax.swing.JComboBox cbox_prdct;
    private javax.swing.JComboBox cbox_prdctMdl;
    private javax.swing.JComboBox cmbCusID;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JLabel lblReceiptNo;
    private javax.swing.JLabel lbl_Price;
    private javax.swing.JLabel lbl_Product;
    private javax.swing.JLabel lbl_Sales;
    private javax.swing.JLabel lbl_pMdl;
    private javax.swing.JLabel lbl_price;
    private javax.swing.JLabel lbl_qty;
    private javax.swing.JLabel lbl_tPrice;
    private javax.swing.JLabel lbl_tprice;
    private com.toedter.calendar.JDateChooser saleDate;
    private javax.swing.JTable tbl_Sales;
    private javax.swing.JTable tbl_SalesInfo;
    private javax.swing.JLabel totalPrice;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JTextField txtWarranty;
    private javax.swing.JTextField txt_qty;
    // End of variables declaration//GEN-END:variables
}
