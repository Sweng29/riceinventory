/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import daoimpl.PurchaseDetailDaoImpl;
import models.EmployeesModel;
import models.ProductModel;
import models.PurchaseDetailsModel;
import models.PurchaseModel;
import models.StoreModel;
import utility.CommonMethods;

/**
 *
 * @author Intel
 */
public class PurchaseDetailFrame extends javax.swing.JFrame {
    private int purchaseId;
    /**
     * Creates new form PurchaseDetail
     */
    PurchaseFrame purchaseFrame;
    PurchaseDetailDaoImpl detailsDao ;
    public PurchaseDetailFrame(int purchaseId , String builtyNo , PurchaseFrame purchaseFrame) {
        initComponents();
        this.setLocationRelativeTo(null);
        lblBuilty.setText(builtyNo);
        detailsDao = new PurchaseDetailDaoImpl();
        this.purchaseId = purchaseId;
        this.purchaseFrame = purchaseFrame;
        CommonMethods.fillTables(detailsDao.getAllPurchaseDetailByPurchaseId(purchaseId), tblPurchaseDetail, jScrollPane2, this);
        CommonMethods.fillCombos(detailsDao.getStoresForCombos(), comboStore);
        CommonMethods.fillCombos(detailsDao.getProductsForCombo(), comboProduct);
        clearFields();
    }

    PurchaseDetailFrame() {
  
    }

    

   
    private void clearFields(){
        tfNote.setText("");
        tfQuantity.setText("0");
        tfPrice.setText("0");
        tfSearchPurchase.setText("");
        comboProduct.setSelectedIndex(0);
        comboStore.setSelectedIndex(0);
        btnAddPurchaseDetail.setEnabled(true);
        btnUpdatePurchaseDetail.setEnabled(false);
    }
    private void addUpdatePurchaseDetail(String mode){
        String price = tfPrice.getText();
        String quantity = tfQuantity.getText();
        
        if(price.equals("")){
            tfPrice.setText("0");
        }
        if(quantity.equals("")){
            tfQuantity.setText("0");
        }
        EmployeesModel employeesModel = LoginFrame.employeesModel;
        PurchaseModel purchaseModel = new PurchaseModel();
        purchaseModel.setPurchaseId(this.purchaseId);
        ProductModel productModel = new ProductModel();
        productModel.setProductName(comboProduct.getSelectedItem().toString());
        StoreModel storeModel = new StoreModel();
        storeModel.setStoreName(comboStore.getSelectedItem().toString());
        PurchaseDetailsModel pDetailsModel = new PurchaseDetailsModel();
        pDetailsModel.setPrice(Double.parseDouble(tfPrice.getText()));
        pDetailsModel.setQuantity(Integer.parseInt(tfQuantity.getText()));
        pDetailsModel.setProductModel(productModel);
        pDetailsModel.setPurchaseModel(purchaseModel);
        pDetailsModel.setStoreModel(storeModel);
        int re=0;
        if(mode.equals("add")){
            pDetailsModel.setCreatedBy(employeesModel);
            re=detailsDao.addDetail(pDetailsModel);
            
        }
        else if(mode.equals("update")){
            pDetailsModel.setModifiedBy(employeesModel);
            if (tblPurchaseDetail.getSelectedRow() > -1) {
                int pdId =(int) tblPurchaseDetail.getValueAt(tblPurchaseDetail.getSelectedRow(), 0);
                pDetailsModel.setPurchaseId(pdId);
                re = detailsDao.updateDetail(pDetailsModel);
            }else {
            new MessageForm("Error", "Please select row", "error.png").setVisible(true);
            }
            
        }
        if(re>0){
                updatePriceOfPurchase();
                CommonMethods.fillTables(detailsDao.getAllPurchaseDetailByPurchaseId(purchaseId), tblPurchaseDetail, jScrollPane2, this);
                clearFields();
            }
            else{
                new MessageForm("Error","Error", "error.png");
            }
        
    }
    
    private void deletDetail(){
        Integer row = tblPurchaseDetail.getSelectedRow();

        if (row > -1) {
            Integer id = (Integer) tblPurchaseDetail.getValueAt(tblPurchaseDetail.getSelectedRow(), 0);
            int re = detailsDao.deleteDetail(id);
            if(re>0){
                updatePriceOfPurchase();
                CommonMethods.fillTables(detailsDao.getAllPurchaseDetailByPurchaseId(purchaseId), tblPurchaseDetail, jScrollPane2, this);
                clearFields();
            }
            else{
                new MessageForm("Error","Error", "error.png");
            }
        
        } else {
            new MessageForm("Error", "Please select row", "error.png").setVisible(true);
        }
    }
    
    private void updatePriceOfPurchase(){
        int re = detailsDao.updateTotalAmountOfPurchase(purchaseId);
        if(re>0){
            purchaseFrame.fillPurchaseTable();
        }
        else{
            new MessageForm("Error", "Error While Updating Purhase Table Amount", "error.png");
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        deletePurchaseDetail = new javax.swing.JMenuItem();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        tfSearchPurchase = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblPurchaseDetail = new javax.swing.JTable();
        btnAddPurchaseDetail = new javax.swing.JLabel();
        btnUpdatePurchaseDetail = new javax.swing.JLabel();
        lblResetPurchase = new javax.swing.JLabel();
        comboProduct = new javax.swing.JComboBox<>();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        comboStore = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tfNote = new javax.swing.JTextArea();
        jLabel10 = new javax.swing.JLabel();
        tfQuantity = new javax.swing.JFormattedTextField();
        tfPrice = new javax.swing.JFormattedTextField();
        lblBuilty = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();

        deletePurchaseDetail.setFont(new java.awt.Font("Century Gothic", 0, 15)); // NOI18N
        deletePurchaseDetail.setText("Delete Account");
        deletePurchaseDetail.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        deletePurchaseDetail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deletePurchaseDetailActionPerformed(evt);
            }
        });
        jPopupMenu1.add(deletePurchaseDetail);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel2MouseClicked(evt);
            }
        });
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jLabel3.setText("Note :");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 420, -1, -1));

        jLabel4.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Search Purchase");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 120, -1, 26));

        tfSearchPurchase.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        tfSearchPurchase.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfSearchPurchaseActionPerformed(evt);
            }
        });
        tfSearchPurchase.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfSearchPurchaseKeyReleased(evt);
            }
        });
        jPanel2.add(tfSearchPurchase, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 120, 220, -1));

        jLabel5.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Product :");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 180, -1, -1));

        jLabel7.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Store/Shop :");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 240, -1, -1));

        tblPurchaseDetail.setFont(new java.awt.Font("Century Gothic", 0, 17)); // NOI18N
        tblPurchaseDetail.setModel(new javax.swing.table.DefaultTableModel(
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
        tblPurchaseDetail.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tblPurchaseDetail.setRowHeight(20);
        tblPurchaseDetail.setSelectionBackground(new java.awt.Color(11, 18, 29));
        tblPurchaseDetail.setSelectionForeground(new java.awt.Color(140, 198, 62));
        tblPurchaseDetail.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblPurchaseDetailMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tblPurchaseDetailMouseReleased(evt);
            }
        });
        jScrollPane2.setViewportView(tblPurchaseDetail);

        jPanel2.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 170, 800, 440));

        btnAddPurchaseDetail.setBackground(new java.awt.Color(140, 198, 62));
        btnAddPurchaseDetail.setFont(new java.awt.Font("Century Gothic", 0, 15)); // NOI18N
        btnAddPurchaseDetail.setForeground(new java.awt.Color(255, 255, 255));
        btnAddPurchaseDetail.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnAddPurchaseDetail.setText("Add Account");
        btnAddPurchaseDetail.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAddPurchaseDetail.setOpaque(true);
        btnAddPurchaseDetail.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnAddPurchaseDetailMouseClicked(evt);
            }
        });
        jPanel2.add(btnAddPurchaseDetail, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 540, 180, 34));

        btnUpdatePurchaseDetail.setBackground(new java.awt.Color(140, 198, 62));
        btnUpdatePurchaseDetail.setFont(new java.awt.Font("Century Gothic", 0, 15)); // NOI18N
        btnUpdatePurchaseDetail.setForeground(new java.awt.Color(255, 255, 255));
        btnUpdatePurchaseDetail.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnUpdatePurchaseDetail.setText("Update Account");
        btnUpdatePurchaseDetail.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnUpdatePurchaseDetail.setOpaque(true);
        btnUpdatePurchaseDetail.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnUpdatePurchaseDetailMouseClicked(evt);
            }
        });
        jPanel2.add(btnUpdatePurchaseDetail, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 540, 190, 34));

        lblResetPurchase.setBackground(new java.awt.Color(140, 198, 62));
        lblResetPurchase.setFont(new java.awt.Font("Century Gothic", 0, 15)); // NOI18N
        lblResetPurchase.setForeground(new java.awt.Color(255, 255, 255));
        lblResetPurchase.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblResetPurchase.setText("Reset");
        lblResetPurchase.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblResetPurchase.setOpaque(true);
        lblResetPurchase.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblResetPurchaseMouseClicked(evt);
            }
        });
        jPanel2.add(lblResetPurchase, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 580, 390, 30));

        comboProduct.setBackground(new java.awt.Color(204, 204, 255));
        comboProduct.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        comboProduct.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel2.add(comboProduct, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 180, 220, 30));

        jPanel1.setBackground(new java.awt.Color(11, 18, 29));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setFont(new java.awt.Font("Century Gothic", 1, 27)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(140, 192, 62));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Purchase Detail");

        jLabel2.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("X");
        jLabel2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(482, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(561, 561, 561)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 66, Short.MAX_VALUE)
        );

        jPanel2.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1300, 70));

        comboStore.setBackground(new java.awt.Color(153, 153, 255));
        comboStore.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        comboStore.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel2.add(comboStore, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 240, 220, 30));

        jLabel6.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jLabel6.setText("Quantity :");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 300, -1, -1));

        tfNote.setColumns(20);
        tfNote.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        tfNote.setRows(5);
        tfNote.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfNoteKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tfNote);

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 420, 220, 100));

        jLabel10.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jLabel10.setText("Price :");
        jPanel2.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 360, -1, -1));

        tfQuantity.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        tfQuantity.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jPanel2.add(tfQuantity, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 300, 220, 30));

        tfPrice.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        tfPrice.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jPanel2.add(tfPrice, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 360, 220, 30));

        lblBuilty.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblBuilty.setText("jLabel8");
        jPanel2.add(lblBuilty, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, 220, 30));

        jMenuBar1.setBackground(new java.awt.Color(0, 0, 0));

        jMenu1.setText("File");

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem1.setText("Add");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_U, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem2.setText("Update");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuItem3.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_D, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem3.setText("Delete");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem3);

        jMenuItem4.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem4.setText("Reset");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem4);

        jMenuItem5.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_B, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem5.setText("Back");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem5);

        jMenuItem6.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem6.setText("Exit");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem6);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 1296, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 638, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 1, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tfSearchPurchaseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfSearchPurchaseActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfSearchPurchaseActionPerformed

    private void tfSearchPurchaseKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfSearchPurchaseKeyReleased
        // TODO add your handling code here:
        CommonMethods.searchFromTable(tblPurchaseDetail, tfSearchPurchase);
    }//GEN-LAST:event_tfSearchPurchaseKeyReleased

    private void tblPurchaseDetailMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPurchaseDetailMouseClicked
            btnAddPurchaseDetail.setEnabled(false);
            btnUpdatePurchaseDetail.setEnabled(true);
            int selectedRow = tblPurchaseDetail.getSelectedRow();

            if (selectedRow > -1) {
                String product =(String)tblPurchaseDetail.getValueAt(tblPurchaseDetail.getSelectedRow(), 1);
                String shop =(String)tblPurchaseDetail.getValueAt(tblPurchaseDetail.getSelectedRow(), 2);
                int quantity =(int)tblPurchaseDetail.getValueAt(tblPurchaseDetail.getSelectedRow(), 3);
                String price =tblPurchaseDetail.getValueAt(tblPurchaseDetail.getSelectedRow(), 4).toString();
                String note =(String)tblPurchaseDetail.getValueAt(tblPurchaseDetail.getSelectedRow(), 6);
                
                comboProduct.setSelectedItem(product);
                comboStore.setSelectedItem(shop);
                tfPrice.setText(price);
                tfQuantity.setText(""+quantity);
                tfNote.setText(note);
            }

        
    }//GEN-LAST:event_tblPurchaseDetailMouseClicked

    private void tblPurchaseDetailMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPurchaseDetailMouseReleased
        if (evt.isPopupTrigger()) {
            jPopupMenu1.show(tblPurchaseDetail, evt.getX(), evt.getY());
        }        // TODO add your handling code here:
    }//GEN-LAST:event_tblPurchaseDetailMouseReleased

    private void btnUpdatePurchaseDetailMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnUpdatePurchaseDetailMouseClicked
        addUpdatePurchaseDetail("update");
    }//GEN-LAST:event_btnUpdatePurchaseDetailMouseClicked

    private void lblResetPurchaseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblResetPurchaseMouseClicked
        clearFields();
    }//GEN-LAST:event_lblResetPurchaseMouseClicked

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        this.dispose();
    }//GEN-LAST:event_jLabel2MouseClicked

    private void tfNoteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfNoteKeyReleased
        // TODO add your handling code here:
        if(evt.getKeyCode()==10){
            if(btnAddPurchaseDetail.isEnabled()){
                addUpdatePurchaseDetail("add");
            }
            else if(btnUpdatePurchaseDetail.isEnabled()){
                addUpdatePurchaseDetail("update");
            }
        }
    }//GEN-LAST:event_tfNoteKeyReleased

    private void jPanel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jPanel2MouseClicked

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        if(btnAddPurchaseDetail.isEnabled()){
            addUpdatePurchaseDetail("add");
        }
        
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
        if(btnUpdatePurchaseDetail.isEnabled()){
            addUpdatePurchaseDetail("update");
        }

    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        // TODO add your handling code here:
        deletDetail();
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        // TODO add your handling code here:
        clearFields();
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void deletePurchaseDetailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deletePurchaseDetailActionPerformed
        deletDetail();
    }//GEN-LAST:event_deletePurchaseDetailActionPerformed

    private void btnAddPurchaseDetailMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAddPurchaseDetailMouseClicked
        // TODO add your handling code here:
        addUpdatePurchaseDetail("add");
    }//GEN-LAST:event_btnAddPurchaseDetailMouseClicked

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
            java.util.logging.Logger.getLogger(PurchaseDetailFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PurchaseDetailFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PurchaseDetailFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PurchaseDetailFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                //new PurchaseDetailFrame(1, "Builty No :2011").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btnAddPurchaseDetail;
    private javax.swing.JLabel btnUpdatePurchaseDetail;
    private javax.swing.JComboBox<String> comboProduct;
    private javax.swing.JComboBox<String> comboStore;
    private javax.swing.JMenuItem deletePurchaseDetail;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblBuilty;
    private javax.swing.JLabel lblResetPurchase;
    private javax.swing.JTable tblPurchaseDetail;
    private javax.swing.JTextArea tfNote;
    private javax.swing.JFormattedTextField tfPrice;
    private javax.swing.JFormattedTextField tfQuantity;
    private javax.swing.JTextField tfSearchPurchase;
    // End of variables declaration//GEN-END:variables
}
