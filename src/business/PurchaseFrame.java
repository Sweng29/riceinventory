/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import daoimpl.PurchaseDaoImpl;
import java.awt.Color;
import java.awt.FlowLayout;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import models.BrokerModel;
import models.CompanyModel;
import models.EmployeesModel;
import models.PurchaseModel;
import utility.BackgroundMenuBar;
import utility.CommonMethods;

/**
 *
 * @author Intel
 */
public class PurchaseFrame extends javax.swing.JFrame {
    PurchaseDaoImpl purchaseDaoImpl ;
    /**
     * Creates new form PurchaseFrame
     */
    public PurchaseFrame() {
        getContentPane().setLayout(new FlowLayout());
        UIManager.put("Menu.selectionBackground",
             new javax.swing.plaf.ColorUIResource(Color.blue));
      UIManager.put("MenuItem.selectionBackground",
             new javax.swing.plaf.ColorUIResource(Color.green));
        initComponents();
        //jMenuBar1.setBackground(new java.awt.Color(47, 51, 58));
        

        purchaseDaoImpl = new PurchaseDaoImpl();
        //fillPurchaseTable();
        clearFields();
        CommonMethods.fillTables(purchaseDaoImpl.getAllPurchases(), tblPurchase, jScrollPane2, this);
        CommonMethods.fillCombos(purchaseDaoImpl.getAllCompaniesForCombos(), comboCompany);
        CommonMethods.fillCombos(purchaseDaoImpl.getAllBrokerNamesForCombos(), comboBroker);
        
    }
    public void fillPurchaseTable(){
        CommonMethods.fillTables(purchaseDaoImpl.getAllPurchases(), tblPurchase, jScrollPane2, this);
        
    }
    private void clearFields(){
        tfBuiltyNo.setText("");
        tfSearchPurchase.setText("");
        comboBroker.setSelectedIndex(0);
        comboCompany.setSelectedIndex(0);
        dateDelivery.setDate(new Date());
        datePayment.setDate(new Date());
        taPayemntCondition.setText("");
        tblPurchase.clearSelection();
        btnAddPurchase.setEnabled(true);
        btnUpdatePurchase.setEnabled(false);
    }
    
    private void addUpdatePurchase(int id , String mode){
        String builtyNo = tfBuiltyNo.getText();
        String companyName = (String) comboCompany.getSelectedItem();
        String brokerName = (String) comboBroker.getSelectedItem();
        Timestamp deliveryDate = new Timestamp(dateDelivery.getDate().getTime());
        Timestamp paymentDate = new Timestamp(datePayment.getDate().getTime());
        System.out.println("PaymentDate :"+paymentDate);
        String paymentCondition = taPayemntCondition.getText();
        if(builtyNo.trim().equals("")){
            new MessageForm("Error", "Builty No Reqiured", "error.png").setVisible(true);
            return;
        }
        
        PurchaseModel purchaseModel = new PurchaseModel();
        BrokerModel brokerModel = new BrokerModel();
        CompanyModel companyModel = new CompanyModel();
        EmployeesModel emploeeModel = LoginFrame.employeesModel;
        
        brokerModel.setBrokerName(brokerName);
        companyModel.setCompanyName(companyName);
        
        purchaseModel.setBuiltyNo(builtyNo);
        purchaseModel.setPaymentCondition(paymentCondition);
        purchaseModel.setPurchaseDate(deliveryDate);
        purchaseModel.setPaymentConditionDate(paymentDate);
        purchaseModel.setCompanyModel(companyModel);
        purchaseModel.setBrokerModel(brokerModel);
        purchaseModel.setCreatedBy(emploeeModel);
        int re = 0;
        if(mode.equals("update")){
            purchaseModel.setPurchaseId(id);
            re = purchaseDaoImpl.updatePurcahse(purchaseModel);
        }
        else if(mode.equals("add")){
            re = purchaseDaoImpl.insertNewPurchase(purchaseModel);
        }
        if(re>0){
            CommonMethods.fillTables(purchaseDaoImpl.getAllPurchases(), tblPurchase, jScrollPane2, this);
            clearFields();
        }
        else{
            new MessageForm("Error", "Error", "error.png").setVisible(true);
            return;
        }
    }
    private void deletePurchase(int id){
        this.setEnabled(false);
        LoginFrame lgFrame = new LoginFrame(this);
        lgFrame.setVisible(true);
        
    }
    public void deletePurc(){
        if (tblPurchase.getSelectedRow() > -1) {
            Integer id = (Integer) tblPurchase.getValueAt(tblPurchase.getSelectedRow(), 0);
            int re = purchaseDaoImpl.deletePurchaseRecord(id);
            if(re>0){
                CommonMethods.fillTables(purchaseDaoImpl.getAllPurchases(), tblPurchase, jScrollPane2, this);
                clearFields();
            }
            else{
                new MessageForm("Error", "Error", "error.png").setVisible(true);
                return;
            }
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
        deletePurchase = new javax.swing.JMenuItem();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        tfSearchPurchase = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblPurchase = new javax.swing.JTable();
        btnAddPurchase = new javax.swing.JLabel();
        btnUpdatePurchase = new javax.swing.JLabel();
        lblResetPurchase = new javax.swing.JLabel();
        tfBuiltyNo = new javax.swing.JTextField();
        comboCompany = new javax.swing.JComboBox<>();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        comboBroker = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        taPayemntCondition = new javax.swing.JTextArea();
        btnViewPayment = new javax.swing.JLabel();
        btnViewPayment1 = new javax.swing.JLabel();
        datePayment = new com.toedter.calendar.JDateChooser();
        dateDelivery = new com.toedter.calendar.JDateChooser();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();

        deletePurchase.setFont(new java.awt.Font("Century Gothic", 0, 15)); // NOI18N
        deletePurchase.setText("Delete Purchase");
        deletePurchase.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        deletePurchase.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deletePurchaseActionPerformed(evt);
            }
        });
        jPopupMenu1.add(deletePurchase);

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
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 490, -1, -1));

        jLabel4.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Search Purchase");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 130, -1, 26));

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
        jPanel2.add(tfSearchPurchase, new org.netbeans.lib.awtextra.AbsoluteConstraints(1100, 130, 220, -1));

        jLabel5.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Company :");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 250, -1, -1));

        jLabel7.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Broker :");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 310, -1, -1));

        jLabel8.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Delivery Date :");
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 370, -1, -1));

        jLabel9.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Payment Date:");
        jPanel2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 430, -1, -1));

        tblPurchase.setFont(new java.awt.Font("Century Gothic", 0, 17)); // NOI18N
        tblPurchase.setModel(new javax.swing.table.DefaultTableModel(
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
        tblPurchase.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tblPurchase.setRowHeight(20);
        tblPurchase.setSelectionBackground(new java.awt.Color(11, 18, 29));
        tblPurchase.setSelectionForeground(new java.awt.Color(140, 198, 62));
        tblPurchase.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblPurchaseMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tblPurchaseMouseReleased(evt);
            }
        });
        jScrollPane2.setViewportView(tblPurchase);

        jPanel2.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 180, 870, 510));

        btnAddPurchase.setBackground(new java.awt.Color(140, 198, 62));
        btnAddPurchase.setFont(new java.awt.Font("Century Gothic", 0, 15)); // NOI18N
        btnAddPurchase.setForeground(new java.awt.Color(255, 255, 255));
        btnAddPurchase.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnAddPurchase.setText("Add Purchase");
        btnAddPurchase.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAddPurchase.setOpaque(true);
        btnAddPurchase.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnAddPurchaseMouseClicked(evt);
            }
        });
        jPanel2.add(btnAddPurchase, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 610, 180, 34));

        btnUpdatePurchase.setBackground(new java.awt.Color(140, 198, 62));
        btnUpdatePurchase.setFont(new java.awt.Font("Century Gothic", 0, 15)); // NOI18N
        btnUpdatePurchase.setForeground(new java.awt.Color(255, 255, 255));
        btnUpdatePurchase.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnUpdatePurchase.setText("Update Purchase");
        btnUpdatePurchase.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnUpdatePurchase.setOpaque(true);
        btnUpdatePurchase.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnUpdatePurchaseMouseClicked(evt);
            }
        });
        jPanel2.add(btnUpdatePurchase, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 610, 190, 34));

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
        jPanel2.add(lblResetPurchase, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 650, 390, 30));

        tfBuiltyNo.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jPanel2.add(tfBuiltyNo, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 180, 220, -1));

        comboCompany.setBackground(new java.awt.Color(204, 204, 255));
        comboCompany.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        comboCompany.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel2.add(comboCompany, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 250, 220, 30));

        jPanel1.setBackground(new java.awt.Color(11, 18, 29));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setFont(new java.awt.Font("Century Gothic", 1, 27)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(140, 192, 62));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Purchase Panel");

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
                .addContainerGap(561, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(561, 561, 561)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 66, Short.MAX_VALUE)
        );

        jPanel2.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1366, 70));

        comboBroker.setBackground(new java.awt.Color(153, 153, 255));
        comboBroker.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        comboBroker.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel2.add(comboBroker, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 310, 220, 30));

        jLabel6.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jLabel6.setText("Builty No :");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, -1, -1));

        taPayemntCondition.setColumns(20);
        taPayemntCondition.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        taPayemntCondition.setRows(5);
        jScrollPane1.setViewportView(taPayemntCondition);

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 490, 220, 100));

        btnViewPayment.setBackground(new java.awt.Color(140, 198, 62));
        btnViewPayment.setFont(new java.awt.Font("Century Gothic", 0, 15)); // NOI18N
        btnViewPayment.setForeground(new java.awt.Color(255, 255, 255));
        btnViewPayment.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnViewPayment.setText("View Expense");
        btnViewPayment.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnViewPayment.setOpaque(true);
        btnViewPayment.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnViewPaymentMouseClicked(evt);
            }
        });
        jPanel2.add(btnViewPayment, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 700, 250, 34));

        btnViewPayment1.setBackground(new java.awt.Color(140, 198, 62));
        btnViewPayment1.setFont(new java.awt.Font("Century Gothic", 0, 15)); // NOI18N
        btnViewPayment1.setForeground(new java.awt.Color(255, 255, 255));
        btnViewPayment1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnViewPayment1.setText("View Payment");
        btnViewPayment1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnViewPayment1.setOpaque(true);
        btnViewPayment1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnViewPayment1MouseClicked(evt);
            }
        });
        jPanel2.add(btnViewPayment1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1070, 700, 250, 34));
        jPanel2.add(datePayment, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 430, 220, 30));
        jPanel2.add(dateDelivery, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 370, 220, 30));

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

        jMenuItem7.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem7.setText("View Payment");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem7);

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
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 1366, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 768, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tfSearchPurchaseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfSearchPurchaseActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfSearchPurchaseActionPerformed

    private void tfSearchPurchaseKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfSearchPurchaseKeyReleased
                // TODO add your handling code here:
                CommonMethods.searchFromTable(tblPurchase, tfSearchPurchase);
    }//GEN-LAST:event_tfSearchPurchaseKeyReleased

    private void tblPurchaseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPurchaseMouseClicked
        if(evt.getClickCount()==2){
            int id =(int) tblPurchase.getValueAt(tblPurchase.getSelectedRow(), 0);
            System.out.println("ID :"+id);
            String builtyNo = "Builty No :"+(String)tblPurchase.getValueAt(tblPurchase.getSelectedRow(), 1);
            new PurchaseDetailFrame(id , builtyNo , this).setVisible(true);
        }
        else{
        btnAddPurchase.setEnabled(false);
        btnUpdatePurchase.setEnabled(true);
        int selectedRow = tblPurchase.getSelectedRow();

        if (selectedRow > -1) {
            try {
                String builtyNo = (String) tblPurchase.getValueAt(tblPurchase.getSelectedRow(), 1);
                String purchaseDate = ((Object) tblPurchase.getValueAt(tblPurchase.getSelectedRow(), 2)).toString();
                String company = (String) tblPurchase.getValueAt(tblPurchase.getSelectedRow(), 4);
                String broker = (String) tblPurchase.getValueAt(tblPurchase.getSelectedRow(), 5);
                String paymentDate = ((Object)tblPurchase.getValueAt(tblPurchase.getSelectedRow(), 6)).toString();
                String note = tblPurchase.getValueAt(tblPurchase.getSelectedRow(), 7).toString();
                DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                Date paydate = formatter.parse(paymentDate);
                Date purDate = formatter.parse(purchaseDate);
                
                tfBuiltyNo.setText(builtyNo);
                comboBroker.setSelectedItem(broker);
                comboCompany.setSelectedItem(company);
                taPayemntCondition.setText(note);
                dateDelivery.setDate(purDate);
                datePayment.setDate(paydate);
            } catch (ParseException ex) {
                Logger.getLogger(PurchaseFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        }
    }//GEN-LAST:event_tblPurchaseMouseClicked

    private void tblPurchaseMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPurchaseMouseReleased
        if (evt.isPopupTrigger()) {
            jPopupMenu1.show(tblPurchase, evt.getX(), evt.getY());
        }        // TODO add your handling code here:
    }//GEN-LAST:event_tblPurchaseMouseReleased

    private void btnAddPurchaseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAddPurchaseMouseClicked
        // TODO add your handling code here:
        if(btnAddPurchase.isEnabled()){
            addUpdatePurchase(0, "add");
            int id = (int)tblPurchase.getValueAt(0, 0);
            String builtyNo = "Builty No :"+(String)tblPurchase.getValueAt(0, 1);
            new PurchaseDetailFrame(id , builtyNo , this).setVisible(true);        }
        
    }//GEN-LAST:event_btnAddPurchaseMouseClicked

    private void btnUpdatePurchaseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnUpdatePurchaseMouseClicked
        Integer row = tblPurchase.getSelectedRow();

        if (row > -1) {
            Integer id = (Integer) tblPurchase.getValueAt(tblPurchase.getSelectedRow(), 0);
            addUpdatePurchase(id, "update");
        } else {
            new MessageForm("Error", "Please select row", "error.png").setVisible(true);
        }
    }//GEN-LAST:event_btnUpdatePurchaseMouseClicked

    private void lblResetPurchaseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblResetPurchaseMouseClicked
        clearFields();
    }//GEN-LAST:event_lblResetPurchaseMouseClicked

    private void jPanel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jPanel2MouseClicked

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        this.dispose();
    }//GEN-LAST:event_jLabel2MouseClicked

    private void deletePurchaseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deletePurchaseActionPerformed
        Integer row = tblPurchase.getSelectedRow();

        if (row > -1) {
            Integer id = (Integer) tblPurchase.getValueAt(tblPurchase.getSelectedRow(), 0);
            deletePurchase(id);
        } else {
            new MessageForm("Error", "Please select row", "error.png").setVisible(true);
        }
    }//GEN-LAST:event_deletePurchaseActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        // TODO add your handling code here:
        clearFields();
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        if(btnAddPurchase.isEnabled()){
            addUpdatePurchase(0, "add");
            int id = (int)tblPurchase.getValueAt(0, 0);
            String builtyNo = "Builty No :"+(String)tblPurchase.getValueAt(0, 1);
            new PurchaseDetailFrame(id , builtyNo , this).setVisible(true);
        }
        
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
        Integer row = tblPurchase.getSelectedRow();
        if(btnUpdatePurchase.isEnabled()){
        if (row > -1) {
            Integer id = (Integer) tblPurchase.getValueAt(tblPurchase.getSelectedRow(), 0);
            addUpdatePurchase(id, "update");
        } else {
            new MessageForm("Error", "Please select row", "error.png").setVisible(true);
        }
        }
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        // TODO add your handling code here:
        Integer row = tblPurchase.getSelectedRow();

        if (row > -1) {
            Integer id = (Integer) tblPurchase.getValueAt(tblPurchase.getSelectedRow(), 0);
            deletePurchase(id);
        } else {
            new MessageForm("Error", "Please select row", "error.png").setVisible(true);
        }
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void btnViewPaymentMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnViewPaymentMouseClicked
        // TODO add your handling code here:
        if(tblPurchase.getSelectedRow()>-1){
            int id = (int)(tblPurchase.getValueAt(tblPurchase.getSelectedRow(), 0));
            new PurchaseExpense(id,this).setVisible(true);
        }
        else{
            new MessageForm("Error", "Select Purchase", "error.png").setVisible(true);
        }
    }//GEN-LAST:event_btnViewPaymentMouseClicked

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        // TODO add your handling code here:
        if(tblPurchase.getSelectedRow()>-1){
            int id = (int)(tblPurchase.getValueAt(tblPurchase.getSelectedRow(), 0));
            new PurchaseCreditDebitFrame(id).setVisible(true);
        }
        else{
            new MessageForm("Error", "Select Purchase", "error.png").setVisible(true);
        }
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void btnViewPayment1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnViewPayment1MouseClicked
       if(tblPurchase.getSelectedRow()>-1){
            int id = (int)(tblPurchase.getValueAt(tblPurchase.getSelectedRow(), 0));
            new PurchaseCreditDebitFrame(id).setVisible(true);
        }
        else{
            new MessageForm("Error", "Select Purchase", "error.png").setVisible(true);
        }
    }//GEN-LAST:event_btnViewPayment1MouseClicked

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
            java.util.logging.Logger.getLogger(PurchaseFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PurchaseFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PurchaseFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PurchaseFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                PurchaseFrame frame =new PurchaseFrame();
                frame.setExtendedState(MAXIMIZED_BOTH);
                frame.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btnAddPurchase;
    private javax.swing.JLabel btnUpdatePurchase;
    private javax.swing.JLabel btnViewPayment;
    private javax.swing.JLabel btnViewPayment1;
    private javax.swing.JComboBox<String> comboBroker;
    private javax.swing.JComboBox<String> comboCompany;
    private com.toedter.calendar.JDateChooser dateDelivery;
    private com.toedter.calendar.JDateChooser datePayment;
    private javax.swing.JMenuItem deletePurchase;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblResetPurchase;
    private javax.swing.JTextArea taPayemntCondition;
    private javax.swing.JTable tblPurchase;
    private javax.swing.JTextField tfBuiltyNo;
    private javax.swing.JTextField tfSearchPurchase;
    // End of variables declaration//GEN-END:variables
}
