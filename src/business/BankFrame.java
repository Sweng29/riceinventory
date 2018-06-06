/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import daoimpl.BankDaoImpl;
import java.awt.Color;
import java.awt.event.KeyEvent;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import models.BankModel;
import net.proteanit.sql.DbUtils;
/**
 *
 * @author Intel
 */
public class BankFrame extends javax.swing.JFrame {
    BankDaoImpl bankDaoImpl = null;
    /**
     * Creates new form BankFrame
     */
    public BankFrame() {
        bankDaoImpl = new BankDaoImpl();
        initComponents();
        this.setLocationRelativeTo(null);
        fillAccountTable();
        clearFields();
    }
    
    private void fillAccountTable(){
        DefaultTableModel dtm = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; //To change body of generated methods, choose Tools | Templates.
            }

        };

        dtm = (DefaultTableModel) DbUtils.resultSetToTableModel(bankDaoImpl.getAllAccounts());

        tblAccount.setModel(dtm);

        tblAccount.getColumnModel().getColumn(0).setWidth(0);
        tblAccount.getColumnModel().getColumn(0).setMinWidth(0);
        tblAccount.getColumnModel().getColumn(0).setMaxWidth(0);

        DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer();
        headerRenderer.setBackground(new Color(11, 18, 29));
        headerRenderer.setForeground(new Color(140, 198, 62));

        for (int i = 0; i < tblAccount.getModel().getColumnCount(); i++) {
            tblAccount.getColumnModel().getColumn(i).setHeaderRenderer(headerRenderer);
        }
        tblAccount.setShowHorizontalLines(true);
        tblAccount.setShowVerticalLines(true);
        this.getContentPane().setBackground(Color.WHITE);
        jScrollPane2.getViewport().setBackground(Color.WHITE);
    }
    
    private void addUpdateAccount(String mode , int id){
        int re=0;
        String accTitle = tfAccTitle.getText();
        String acNo = tfAccNo.getText();
        String bankName = tfBankName.getText();
        String branchName = tfBranchName.getText();
        String balance  = tfBalance.getText();
        double bal = 0;
        if(accTitle.trim().equals("")){
            new MessageForm("Error", "Account Title Reqiured", "error.png").setVisible(true);
            return;
        }
        else if(acNo.trim().equals("")){
            new MessageForm("Error", "Account Number Reqiured", "error.png").setVisible(true);
            return;
        }
        else if(bankName.trim().equals("")){
            new MessageForm("Error", "Bank Name Reqiured", "error.png").setVisible(true);
            return;
        }
        else if(branchName.trim().equals("")){
            new MessageForm("Error", "Branch Name Reqiured", "error.png").setVisible(true);
            return;
        }
        if(balance.trim().equals("")){
            tfBalance.setText("0.00");
        }
        else{
            bal= Double.parseDouble(balance);
        }
        if(mode.equals("update") && acNo.equals((String) tblAccount.getValueAt(tblAccount.getSelectedRow(), 2))){
            
        }
        else if(bankDaoImpl.checkAccountNo(acNo)){
            new MessageForm("Error", "Account No Already Exist", "error.png").setVisible(true);
            return;
        }
        BankModel bankModel = new BankModel();
        bankModel.setAccountNo(acNo);
        bankModel.setAccountTitle(accTitle);
        bankModel.setBankName(bankName);
        bankModel.setBranchName(branchName);
        bankModel.setBalance(bal);
        if(mode.equals("add")){
            bankModel.setCreatedBy(LoginFrame.employeesModel);
            re = bankDaoImpl.insertNewAccount(bankModel);
            if(re>0){
                new MessageForm("Success", "Account Added", "info.png").setVisible(true);
                fillAccountTable();
                clearFields();
            }
            else{
                new MessageForm("Error", "Error", "error.png").setVisible(true);
            
            }   
        }
        else if(mode.equals("update")){
            bankModel.setModifiedBy(LoginFrame.employeesModel);
            bankModel.setBankId(id);
            re = bankDaoImpl.updateAccount(bankModel);
            if(re>0){
                new MessageForm("Success", "Account Updated", "info.png").setVisible(true);
                fillAccountTable();
                clearFields();
            }
            else{
                new MessageForm("Error", "Error", "error.png").setVisible(true);
            
            }
        }
        
    }
    
    private void deleteAccount(int id){
        int re = bankDaoImpl.deleteAccount(id);
            if(re>0){
                new MessageForm("Success", "Account Deleted", "info.png").setVisible(true);
                fillAccountTable();
                clearFields();
            }
            else{
                new MessageForm("Error", "Error", "error.png").setVisible(true);
            
            }
    }
    
    private void clearFields(){
        tfAccNo.setText("");
        tfAccTitle.setText("");
        tfBalance.setText("0.00");
        tfBankName.setText("");
        tfBranchName.setText("");
        tblAccount.clearSelection();
        btnAddAcc.setEnabled(true);
        btnUpdateAcc.setEnabled(false);
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
        deleteBank = new javax.swing.JMenuItem();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        tfSearchAcc = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        tfAccNo = new javax.swing.JTextField();
        tfBranchName = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblAccount = new javax.swing.JTable();
        btnAddAcc = new javax.swing.JLabel();
        btnUpdateAcc = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        tfBalance = new javax.swing.JFormattedTextField();
        tfAccTitle = new javax.swing.JTextField();
        tfBankName = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        deleteBank.setFont(new java.awt.Font("Century Gothic", 0, 15)); // NOI18N
        deleteBank.setText("Delete Account");
        deleteBank.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        deleteBank.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteBankActionPerformed(evt);
            }
        });
        jPopupMenu1.add(deleteBank);

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

        jLabel3.setFont(new java.awt.Font("Century Gothic", 0, 15)); // NOI18N
        jLabel3.setText("Account Title :");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(45, 78, -1, -1));

        jLabel4.setFont(new java.awt.Font("Century Gothic", 0, 15)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Search Employees");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 30, -1, 26));

        tfSearchAcc.setFont(new java.awt.Font("Century Gothic", 0, 15)); // NOI18N
        tfSearchAcc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfSearchAccActionPerformed(evt);
            }
        });
        tfSearchAcc.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfSearchAccKeyReleased(evt);
            }
        });
        jPanel2.add(tfSearchAcc, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 30, 220, -1));

        jLabel5.setFont(new java.awt.Font("Century Gothic", 0, 15)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Account No :");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(45, 122, -1, -1));

        jLabel7.setFont(new java.awt.Font("Century Gothic", 0, 15)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Bank Name:");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(45, 172, -1, -1));

        jLabel8.setFont(new java.awt.Font("Century Gothic", 0, 15)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Branch Name:");
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(45, 216, -1, -1));

        jLabel9.setFont(new java.awt.Font("Century Gothic", 0, 15)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Balance :");
        jPanel2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(45, 257, -1, -1));

        tfAccNo.setFont(new java.awt.Font("Century Gothic", 0, 15)); // NOI18N
        tfAccNo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tfAccNoKeyTyped(evt);
            }
        });
        jPanel2.add(tfAccNo, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 120, 188, -1));

        tfBranchName.setFont(new java.awt.Font("Century Gothic", 0, 15)); // NOI18N
        jPanel2.add(tfBranchName, new org.netbeans.lib.awtextra.AbsoluteConstraints(159, 213, 188, -1));

        tblAccount.setFont(new java.awt.Font("Century Gothic", 0, 17)); // NOI18N
        tblAccount.setModel(new javax.swing.table.DefaultTableModel(
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
        tblAccount.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tblAccount.setRowHeight(20);
        tblAccount.setSelectionBackground(new java.awt.Color(11, 18, 29));
        tblAccount.setSelectionForeground(new java.awt.Color(140, 198, 62));
        tblAccount.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblAccountMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tblAccountMouseReleased(evt);
            }
        });
        jScrollPane2.setViewportView(tblAccount);

        jPanel2.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(365, 75, 603, 390));

        btnAddAcc.setBackground(new java.awt.Color(140, 198, 62));
        btnAddAcc.setFont(new java.awt.Font("Century Gothic", 0, 15)); // NOI18N
        btnAddAcc.setForeground(new java.awt.Color(255, 255, 255));
        btnAddAcc.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnAddAcc.setText("Add Account");
        btnAddAcc.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAddAcc.setOpaque(true);
        btnAddAcc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnAddAccMouseClicked(evt);
            }
        });
        jPanel2.add(btnAddAcc, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 390, 138, 34));

        btnUpdateAcc.setBackground(new java.awt.Color(140, 198, 62));
        btnUpdateAcc.setFont(new java.awt.Font("Century Gothic", 0, 15)); // NOI18N
        btnUpdateAcc.setForeground(new java.awt.Color(255, 255, 255));
        btnUpdateAcc.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnUpdateAcc.setText("Update Account");
        btnUpdateAcc.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnUpdateAcc.setOpaque(true);
        btnUpdateAcc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnUpdateAccMouseClicked(evt);
            }
        });
        jPanel2.add(btnUpdateAcc, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 390, 152, 34));

        jLabel10.setBackground(new java.awt.Color(140, 198, 62));
        jLabel10.setFont(new java.awt.Font("Century Gothic", 0, 15)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Reset");
        jLabel10.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel10.setOpaque(true);
        jLabel10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel10MouseClicked(evt);
            }
        });
        jPanel2.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 434, 300, 30));

        tfBalance.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        tfBalance.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfBalanceKeyReleased(evt);
            }
        });
        jPanel2.add(tfBalance, new org.netbeans.lib.awtextra.AbsoluteConstraints(159, 260, 188, 30));

        tfAccTitle.setFont(new java.awt.Font("Century Gothic", 0, 15)); // NOI18N
        jPanel2.add(tfAccTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(159, 75, 188, -1));

        tfBankName.setFont(new java.awt.Font("Century Gothic", 0, 15)); // NOI18N
        jPanel2.add(tfBankName, new org.netbeans.lib.awtextra.AbsoluteConstraints(159, 169, 188, -1));

        jPanel1.setBackground(new java.awt.Color(11, 18, 29));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setFont(new java.awt.Font("Century Gothic", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(140, 192, 62));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Accounts Panel");

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
                .addGap(375, 375, 375)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 66, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 980, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 980, Short.MAX_VALUE)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 550, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, 0)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 480, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tfSearchAccActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfSearchAccActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfSearchAccActionPerformed

    private void tfSearchAccKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfSearchAccKeyReleased
        String employeeNameSearch = tfSearchAcc.getText();
        TableRowSorter tableRowSorter = new TableRowSorter(tblAccount.getModel());
        tblAccount.setRowSorter(tableRowSorter);
        tableRowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + employeeNameSearch));        // TODO add your handling code here:
    }//GEN-LAST:event_tfSearchAccKeyReleased

    private void tblAccountMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblAccountMouseClicked
        btnAddAcc.setEnabled(false);
        btnUpdateAcc.setEnabled(true);
        int selectedRow = tblAccount.getSelectedRow();

        if (selectedRow > -1) {
            String title = (String) tblAccount.getValueAt(tblAccount.getSelectedRow(), 1);
            String acNo = (String) tblAccount.getValueAt(tblAccount.getSelectedRow(), 2);
            String bankName = (String) tblAccount.getValueAt(tblAccount.getSelectedRow(), 3);
            String branchName = (String) tblAccount.getValueAt(tblAccount.getSelectedRow(), 4);
            String balance = tblAccount.getValueAt(tblAccount.getSelectedRow(), 5).toString();
            
            tfAccTitle.setText(title);
            tfAccNo.setText(acNo);
            tfBankName.setText(bankName);
            tfBranchName.setText(branchName);
            tfBalance.setText(balance);
            
        }

    }//GEN-LAST:event_tblAccountMouseClicked

    private void tblAccountMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblAccountMouseReleased
        if (evt.isPopupTrigger()) {
            jPopupMenu1.show(tblAccount, evt.getX(), evt.getY());
        }        // TODO add your handling code here:
    }//GEN-LAST:event_tblAccountMouseReleased

    private void btnAddAccMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAddAccMouseClicked
                // TODO add your handling code here:
                addUpdateAccount("add" ,0);
    }//GEN-LAST:event_btnAddAccMouseClicked

    private void btnUpdateAccMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnUpdateAccMouseClicked
        Integer row = tblAccount.getSelectedRow();

        if (row > -1) {
            Integer id = (Integer) tblAccount.getValueAt(tblAccount.getSelectedRow(), 0);
            addUpdateAccount("update",id);
        } else {
            new MessageForm("Error", "Please select row", "error.png").setVisible(true);
        }
    }//GEN-LAST:event_btnUpdateAccMouseClicked

    private void jLabel10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel10MouseClicked
        clearFields();
    }//GEN-LAST:event_jLabel10MouseClicked

    private void jPanel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel2MouseClicked
        clearFields();
        // TODO add your handling code here:
    }//GEN-LAST:event_jPanel2MouseClicked

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        this.dispose();
    }//GEN-LAST:event_jLabel2MouseClicked

    private void deleteBankActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteBankActionPerformed
        Integer row = tblAccount.getSelectedRow();

        if (row > -1) {
            Integer id = (Integer) tblAccount.getValueAt(tblAccount.getSelectedRow(), 0);
            deleteAccount(id);
        } else {
            new MessageForm("Error", "Please select row", "error.png").setVisible(true);
        }
    }//GEN-LAST:event_deleteBankActionPerformed

    private void tfAccNoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfAccNoKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();

        if (!(Character.isDigit(c)) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE)) {

            evt.consume();
        }
        
    }//GEN-LAST:event_tfAccNoKeyTyped

    private void tfBalanceKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfBalanceKeyReleased
        // TODO add your handling code here:
        if(evt.getKeyCode()==10){
            if(btnUpdateAcc.isEnabled()){
                Integer row = tblAccount.getSelectedRow();

                if (row > -1) {
                    Integer id = (Integer) tblAccount.getValueAt(tblAccount.getSelectedRow(), 0);
                    addUpdateAccount("update",id);
                } else {
                    new MessageForm("Error", "Please select row", "error.png").setVisible(true);
                }
            }
            else if(btnAddAcc.isEnabled()){
                addUpdateAccount("add", 0);
            }
        }
    }//GEN-LAST:event_tfBalanceKeyReleased

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
            java.util.logging.Logger.getLogger(BankFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BankFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BankFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BankFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new BankFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btnAddAcc;
    private javax.swing.JLabel btnUpdateAcc;
    private javax.swing.JMenuItem deleteBank;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tblAccount;
    private javax.swing.JTextField tfAccNo;
    private javax.swing.JTextField tfAccTitle;
    private javax.swing.JFormattedTextField tfBalance;
    private javax.swing.JTextField tfBankName;
    private javax.swing.JTextField tfBranchName;
    private javax.swing.JTextField tfSearchAcc;
    // End of variables declaration//GEN-END:variables
}
