/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import daoimpl.BrokerDAOImpl;
import daoimpl.CompanyDAOImpl;
import daoimpl.SellDAOImpl;
import daoimpl.StoreDaoImpl;
import java.awt.Color;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableCellRenderer;
import models.BrokerModel;
import models.CompanyModel;
import models.SellModel;
import models.StoreModel;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author Lenovo
 */
public class SellFrame extends javax.swing.JFrame {

    /**
     * Creates new form SellFrame
     */
    Integer sellId = null;
    SellFrame sellFrame = null;
    public SellFrame() {
        initComponents();
        fillCompanyComboBox();
        sellFrame = this;
        fillBrokerComboBox();
        fillSellTable();
        disableButtons(1);
        builtyNoField.requestFocus();
        this.setLocationRelativeTo(null);
        sellTable.setDefaultEditor(Object.class, null);
        this.setExtendedState(SellFrame.MAXIMIZED_BOTH);
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
        jMenuItem1 = new javax.swing.JMenuItem();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        searchSaleField = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        sellTable = new javax.swing.JTable();
        addSellButton = new javax.swing.JLabel();
        updateSellButton = new javax.swing.JLabel();
        clearAllFieldsButton = new javax.swing.JLabel();
        builtyNoField = new javax.swing.JTextField();
        companyField = new javax.swing.JComboBox<>();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        brokerField = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        noteField = new javax.swing.JTextArea();
        saleDateField = new com.toedter.calendar.JDateChooser();
        sellCreditDebitButton = new javax.swing.JLabel();
        sellCreditDebitButton1 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();

        jMenuItem1.setText("Delete Row");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jPopupMenu1.add(jMenuItem1);

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
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 450, -1, -1));

        jLabel4.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Search Sell");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 130, -1, 26));

        searchSaleField.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        searchSaleField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchSaleFieldActionPerformed(evt);
            }
        });
        searchSaleField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                searchSaleFieldKeyReleased(evt);
            }
        });
        jPanel2.add(searchSaleField, new org.netbeans.lib.awtextra.AbsoluteConstraints(1100, 130, 250, -1));

        jLabel5.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Company :");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 250, -1, -1));

        jLabel7.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Broker :");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 320, -1, -1));

        jLabel8.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Sale Date :");
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 390, -1, -1));

        sellTable.setFont(new java.awt.Font("Century Gothic", 0, 17)); // NOI18N
        sellTable.setModel(new javax.swing.table.DefaultTableModel(
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
        sellTable.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        sellTable.setRowHeight(20);
        sellTable.setSelectionBackground(new java.awt.Color(11, 18, 29));
        sellTable.setSelectionForeground(new java.awt.Color(140, 198, 62));
        sellTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                sellTableMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                sellTableMouseReleased(evt);
            }
        });
        sellTable.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                sellTableKeyReleased(evt);
            }
        });
        jScrollPane2.setViewportView(sellTable);

        jPanel2.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 170, 910, 450));

        addSellButton.setBackground(new java.awt.Color(140, 198, 62));
        addSellButton.setFont(new java.awt.Font("Century Gothic", 0, 15)); // NOI18N
        addSellButton.setForeground(new java.awt.Color(255, 255, 255));
        addSellButton.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        addSellButton.setText("Add Sell");
        addSellButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        addSellButton.setOpaque(true);
        addSellButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                addSellButtonMouseClicked(evt);
            }
        });
        jPanel2.add(addSellButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 590, 180, 34));

        updateSellButton.setBackground(new java.awt.Color(140, 198, 62));
        updateSellButton.setFont(new java.awt.Font("Century Gothic", 0, 15)); // NOI18N
        updateSellButton.setForeground(new java.awt.Color(255, 255, 255));
        updateSellButton.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        updateSellButton.setText("Update Sell");
        updateSellButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        updateSellButton.setOpaque(true);
        updateSellButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                updateSellButtonMouseClicked(evt);
            }
        });
        jPanel2.add(updateSellButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 590, 190, 34));

        clearAllFieldsButton.setBackground(new java.awt.Color(140, 198, 62));
        clearAllFieldsButton.setFont(new java.awt.Font("Century Gothic", 0, 15)); // NOI18N
        clearAllFieldsButton.setForeground(new java.awt.Color(255, 255, 255));
        clearAllFieldsButton.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        clearAllFieldsButton.setText("Reset");
        clearAllFieldsButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        clearAllFieldsButton.setOpaque(true);
        clearAllFieldsButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                clearAllFieldsButtonMouseClicked(evt);
            }
        });
        jPanel2.add(clearAllFieldsButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 640, 390, 30));

        builtyNoField.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jPanel2.add(builtyNoField, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 180, 220, -1));

        companyField.setBackground(new java.awt.Color(204, 204, 255));
        companyField.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        companyField.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Company" }));
        jPanel2.add(companyField, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 250, 220, 30));

        jPanel1.setBackground(new java.awt.Color(11, 18, 29));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setFont(new java.awt.Font("Century Gothic", 0, 27)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(140, 192, 62));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Sell Panel");

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
                .addGap(568, 568, 568)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 622, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(20, 20, 20))
        );

        jPanel2.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1380, 70));

        brokerField.setBackground(new java.awt.Color(153, 153, 255));
        brokerField.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        brokerField.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Broker" }));
        jPanel2.add(brokerField, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 320, 220, 30));

        jLabel6.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jLabel6.setText("Builty No :");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, -1, -1));

        noteField.setColumns(20);
        noteField.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        noteField.setRows(5);
        jScrollPane1.setViewportView(noteField);

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 450, 220, 100));
        jPanel2.add(saleDateField, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 390, 220, 30));

        sellCreditDebitButton.setBackground(new java.awt.Color(140, 198, 62));
        sellCreditDebitButton.setFont(new java.awt.Font("Century Gothic", 0, 15)); // NOI18N
        sellCreditDebitButton.setForeground(new java.awt.Color(255, 255, 255));
        sellCreditDebitButton.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        sellCreditDebitButton.setText("View Expense");
        sellCreditDebitButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        sellCreditDebitButton.setOpaque(true);
        sellCreditDebitButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                sellCreditDebitButtonMouseClicked(evt);
            }
        });
        jPanel2.add(sellCreditDebitButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 640, 190, 40));

        sellCreditDebitButton1.setBackground(new java.awt.Color(140, 198, 62));
        sellCreditDebitButton1.setFont(new java.awt.Font("Century Gothic", 0, 15)); // NOI18N
        sellCreditDebitButton1.setForeground(new java.awt.Color(255, 255, 255));
        sellCreditDebitButton1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        sellCreditDebitButton1.setText("View Sell Credit Debit");
        sellCreditDebitButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        sellCreditDebitButton1.setOpaque(true);
        sellCreditDebitButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                sellCreditDebitButton1MouseClicked(evt);
            }
        });
        jPanel2.add(sellCreditDebitButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1170, 640, 190, 40));

        jMenuBar1.setBackground(new java.awt.Color(0, 0, 0));

        jMenu1.setText("File");

        jMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem2.setText("Add");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuItem3.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_U, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem3.setText("Update");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem3);

        jMenuItem4.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_D, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem4.setText("Delete");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem4);

        jMenuItem5.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem5.setText("Reset");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem5);

        jMenuItem6.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_B, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem6.setText("Back");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem6);

        jMenuItem7.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem7.setText("Exit");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem7);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 1370, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 718, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void searchSaleFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchSaleFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchSaleFieldActionPerformed

    private void searchSaleFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchSaleFieldKeyReleased
        
    }//GEN-LAST:event_searchSaleFieldKeyReleased

    private void sellTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sellTableMouseClicked
        if(evt.getClickCount()==2){
            sellId = (Integer) sellTable.getValueAt(sellTable.getSelectedRow(), 0);
            new SellDetailNewFrame(sellId,sellFrame).setVisible(true);
        }
        else{
            getSelectedRow();
        }
    }//GEN-LAST:event_sellTableMouseClicked

    private void sellTableMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sellTableMouseReleased
        if (evt.isPopupTrigger()) {
            jPopupMenu1.show(sellTable, evt.getX(), evt.getY());
        }        
    }//GEN-LAST:event_sellTableMouseReleased

    private void addSellButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addSellButtonMouseClicked
        if(addSellButton.isEnabled())
        {
           addSellRecord();
        }
    }//GEN-LAST:event_addSellButtonMouseClicked

    private void updateSellButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_updateSellButtonMouseClicked
        if(updateSellButton.isEnabled())
        {
           updateSellRecord();
        }
    }//GEN-LAST:event_updateSellButtonMouseClicked

    private void clearAllFieldsButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_clearAllFieldsButtonMouseClicked
       clearAllFields(); 
    }//GEN-LAST:event_clearAllFieldsButtonMouseClicked

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        this.dispose();
    }//GEN-LAST:event_jLabel2MouseClicked

    private void jPanel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jPanel2MouseClicked

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        deleteRecord();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void sellCreditDebitButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sellCreditDebitButtonMouseClicked
       if(sellTable.getSelectedRow()>-1){
            int id = (int)(sellTable.getValueAt(sellTable.getSelectedRow(), 0));
            new SellExpenses(id,this).setVisible(true);
        }
        else{
            new MessageForm("Error", "Select Sell", "error.png").setVisible(true);
        }
        
    }//GEN-LAST:event_sellCreditDebitButtonMouseClicked

    private void sellCreditDebitButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sellCreditDebitButton1MouseClicked
       
       if(sellTable.getSelectedRow()>-1)
       {
         new SellCreditDebitFrame(sellId).setVisible(true);  
       }else{
           new MessageForm("Error", "Please select any sell to view.", "error.png").setVisible(true);
       }
        
    }//GEN-LAST:event_sellCreditDebitButton1MouseClicked

    private void sellTableKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_sellTableKeyReleased
        if(evt.getKeyCode()==38 || evt.getKeyCode()==40)
        {
            getSelectedRow();
        }
        else if(evt.getKeyCode()==37)
        {
            builtyNoField.requestFocusInWindow();
        }
    }//GEN-LAST:event_sellTableKeyReleased

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        if(addSellButton.isEnabled()){
            addSellRecord();
        }
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        if(updateSellButton.isEnabled()){
            updateSellRecord();
        }
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        deleteRecord();
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        // TODO add your handling code here:
        clearAllFields();
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_jMenuItem7ActionPerformed

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
            java.util.logging.Logger.getLogger(SellFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SellFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SellFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SellFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SellFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel addSellButton;
    private javax.swing.JComboBox<String> brokerField;
    private javax.swing.JTextField builtyNoField;
    private javax.swing.JLabel clearAllFieldsButton;
    private javax.swing.JComboBox<String> companyField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
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
    private javax.swing.JTextArea noteField;
    private com.toedter.calendar.JDateChooser saleDateField;
    private javax.swing.JTextField searchSaleField;
    private javax.swing.JLabel sellCreditDebitButton;
    private javax.swing.JLabel sellCreditDebitButton1;
    private javax.swing.JTable sellTable;
    private javax.swing.JLabel updateSellButton;
    // End of variables declaration//GEN-END:variables

    private void fillCompanyComboBox() {
        ResultSet resultSet = new CompanyDAOImpl().viewAllCompanies();
        try{
            while(resultSet.next())
            {
                companyField.addItem(resultSet.getString("Company Name"));
            }
        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    private void fillBrokerComboBox() {
        ResultSet resultSet = new BrokerDAOImpl().viewAllBrokers();
        try{
            while(resultSet.next())
            {
                brokerField.addItem(resultSet.getString("Broker Name"));
            }
        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
//    private void fillStoreComboBox() {
//        ResultSet resultSet = new StoreDaoImpl().getAllStores();
//        try{
//            while(resultSet.next())
//            {
//                storeField.addItem(resultSet.getString("Store Name"));
//            }
//        }catch(Exception e)
//        {
//            e.printStackTrace();
//        }
//    }

    public void fillSellTable() {
        ResultSet resultSet = new SellDAOImpl().viewAllSellRecords();
        sellTable.setModel(DbUtils.resultSetToTableModel(resultSet));
        sellTable.getColumnModel().getColumn(0).setWidth(0);
        sellTable.getColumnModel().getColumn(0).setMinWidth(0);
        sellTable.getColumnModel().getColumn(0).setMaxWidth(0);
        
        DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer();
        headerRenderer.setBackground(new Color(11, 18, 29));
        headerRenderer.setForeground(new Color(140, 198, 62));

        for (int i = 0; i < sellTable.getModel().getColumnCount(); i++) {
            sellTable.getColumnModel().getColumn(i).setHeaderRenderer(headerRenderer);
        }
        sellTable.setShowHorizontalLines(true);
        sellTable.setShowVerticalLines(true);
        this.getContentPane().setBackground(Color.WHITE);
        jScrollPane2.getViewport().setBackground(Color.WHITE);
    }
    
    
    public void clearAllFields() {
        brokerField.setSelectedIndex(0);
        companyField.setSelectedIndex(0);
        builtyNoField.setText("");
        saleDateField.setCalendar(null);
        noteField.setText("");
//        storeField.setSelectedIndex(0);
        sellTable.clearSelection();
        disableButtons(1);
    }

    private void disableButtons(Integer i) {
        if(i==0)
        {
            addSellButton.setEnabled(false);
            updateSellButton.setEnabled(true);
            sellCreditDebitButton.setEnabled(true);
        }else if(i==1){
            addSellButton.setEnabled(true);
            updateSellButton.setEnabled(false);
            sellCreditDebitButton.setEnabled(false);
        }
    }

    
    private void addSellRecord() {
        if(builtyNoField.getText().trim().equals("") || companyField.getSelectedIndex()==0 || brokerField.getSelectedIndex()==0 )
        {
            new MessageForm("Error","Please fill all fileds to add record.","error.png").setVisible(true);
        }else{

            String brokerName = brokerField.getSelectedItem().toString();
            String companyName = companyField.getSelectedItem().toString();
            String note = noteField.getText();
            //String storeName = storeField.getSelectedItem().toString();
            String builtyNumber = builtyNoField.getText();
            //StoreModel storeModel = new StoreDaoImpl().getStoreByName(storeName);
            CompanyModel companyModel = new CompanyDAOImpl().getCompanyByName(companyName);
            BrokerModel brokerModel = new BrokerDAOImpl().getBrokerByName(brokerName);
            java.util.Date utilDate = new java.util.Date();
            Calendar cal = Calendar.getInstance();
            cal.setTime(utilDate);
            cal.set(Calendar.MILLISECOND, 0);
            SellModel sellModel = new SellModel();
            sellModel.setBuiltyNo(builtyNumber);
            sellModel.setDate(new java.sql.Timestamp(utilDate.getTime()));
            //sellModel.setStoreModel(storeModel);
            Timestamp createdAndModifiedDate = new Timestamp(System.currentTimeMillis());
            sellModel.setBrokerModel(brokerModel);
            sellModel.setCompanyModel(companyModel);
            sellModel.setCreatedDate(createdAndModifiedDate);
            sellModel.setModifiedDate(createdAndModifiedDate);
            sellModel.setNote(note);
            Integer row = new SellDAOImpl().addSellRecord(sellModel);
           if(row<0)
            {
                new MessageForm("Error", "Record could not be added try again.","error.png").setVisible(true); 
            }else{
                new MessageForm("Success", "Record has been added.","info.png").setVisible(true);
                clearAllFields();
                fillSellTable();
            }
        }
    }

    private void updateSellRecord() {
        if(sellTable.getSelectedRow()<0)
        {
            new MessageForm("Error","Please select row to update record.","error.png").setVisible(true);
            
        }else if(builtyNoField.getText().trim().equals("") || companyField.getSelectedIndex()==0 || brokerField.getSelectedIndex()==0)
        {
            new MessageForm("Error","Please fill all fileds to update record.","error.png").setVisible(true);
        }else{
            String brokerName = brokerField.getSelectedItem().toString();
            String companyName = companyField.getSelectedItem().toString();
            String builtyNumber = builtyNoField.getText();
            String note = noteField.getText();
            CompanyModel companyModel = new CompanyDAOImpl().getCompanyByName(companyName);
            BrokerModel brokerModel = new BrokerDAOImpl().getBrokerByName(brokerName);
            java.util.Date utilDate = new java.util.Date();
            Calendar cal = Calendar.getInstance();
            cal.setTime(utilDate);
            cal.set(Calendar.MILLISECOND, 0);
            SellModel sellModel = new SellModel();
            sellModel.setBuiltyNo(builtyNumber);
            sellModel.setDate(new java.sql.Timestamp(utilDate.getTime()));
            sellModel.setBrokerModel(brokerModel);
            sellModel.setCompanyModel(companyModel);
            sellModel.setNote(note);
            sellModel.setSellId(sellId);
            Integer row = new SellDAOImpl().updateSellRecord(sellModel);
           if(row<0)
            {
                new MessageForm("Error", "Record could not be updated try again.","error.png").setVisible(true); 
            }else{
                new MessageForm("Success", "Record has been updated.","info.png").setVisible(true);
                clearAllFields();
                fillSellTable();
            }
        }
    }

    private void deleteRecord() {
        if(sellTable.getSelectedRow()<0)
        {
            new MessageForm("Error","Please select row to delete record.","error.png").setVisible(true);
        }else{
            sellId = (Integer) sellTable.getValueAt(sellTable.getSelectedRow(), 0);
            SellModel sellModel = new SellModel();
            sellModel.setSellId(sellId);
            Timestamp modifiedDate = new Timestamp(System.currentTimeMillis());
            sellModel.setModifiedDate(modifiedDate);
            Integer row = new SellDAOImpl().deleteSellRecord(sellModel);
           if(row<0)
            {
                new MessageForm("Error", "Record could not be deleted try again.","error.png").setVisible(true); 
            }else{
                new MessageForm("Success", "Record has been deleted.","info.png").setVisible(true);
                clearAllFields();
                fillSellTable();
            }
        }
    }

    private void getSelectedRow() {
            disableButtons(0);
            sellId = (Integer) sellTable.getValueAt(sellTable.getSelectedRow(), 0);
            SellModel sellModel = new SellDAOImpl().getSellRecordById(sellId);
            builtyNoField.setText(sellModel.getBuiltyNo());
            companyField.setSelectedItem(sellModel.getCompanyModel().getCompanyName());
            brokerField.setSelectedItem(sellModel.getBrokerModel().getBrokerName());
            java.util.Date utilDate = new java.util.Date();
            Calendar cal = Calendar.getInstance();
            cal.setTime(utilDate);
            cal.set(Calendar.MILLISECOND, 0);
            saleDateField.setDate(sellModel.getDate());
            noteField.setText(sellModel.getNote());
    }
}