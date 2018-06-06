/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import daoimpl.BankDaoImpl;
import daoimpl.SellCreditDebitDAOImpl;
import java.awt.Color;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.HashMap;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableRowSorter;
import models.BankModel;
import models.SellCreditDebitModel;
import models.SellModel;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author Lenovo
 */
public class SellCreditDebitFrame extends javax.swing.JFrame {

    /**
     * Creates new form SellCreditDebitFrame
     */
    Integer sellId = null;
    Integer bankId = null;
    Integer sellCreditDebitId = null;
    Timestamp currentDate = null;
    Double previousAmount;
    Integer bankTableBankId;
    public SellCreditDebitFrame() {
        initComponents();
    }
    
    public SellCreditDebitFrame(Integer sellId) {
        initComponents();
        this.sellId = sellId;
        fillBankTable();
        amountReceivedField.requestFocus();
        fillSellCreditDebitBySellId();
        fillTotalAmountTableBySellId();
        this.setLocationRelativeTo(null);
        bankTable.setDefaultEditor(Object.class, null);
        creditDebitTable.setDefaultEditor(Object.class, null);
        totalAmountTable.setDefaultEditor(Object.class, null);
        this.setExtendedState(SellCreditDebitFrame.MAXIMIZED_BOTH);
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
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        bankTable = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        creditDebitTable = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        totalAmountTable = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        amountReceivedField = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        commentsField = new javax.swing.JTextArea();
        paymentTypeField = new javax.swing.JComboBox<>();
        receivedDateField = new com.toedter.calendar.JDateChooser();
        addButton = new javax.swing.JLabel();
        clearAllFieldsButton = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        searchCreditDebitField = new javax.swing.JTextField();
        addButton1 = new javax.swing.JLabel();
        clearAllFieldsButton1 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();

        jMenuItem1.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jMenuItem1.setText("Delete Row");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jPopupMenu1.add(jMenuItem1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(11, 18, 29));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Century Gothic", 0, 27)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(140, 192, 62));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Sell Credit Debit Panel");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 20, -1, -1));

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
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1330, 20, 40, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1400, 70));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        bankTable.setFont(new java.awt.Font("Century Gothic", 0, 17)); // NOI18N
        bankTable.setModel(new javax.swing.table.DefaultTableModel(
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
        bankTable.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        bankTable.setRowHeight(20);
        bankTable.setSelectionBackground(new java.awt.Color(0, 0, 0));
        bankTable.setSelectionForeground(new java.awt.Color(140, 198, 40));
        bankTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bankTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(bankTable);

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, 570, 260));

        jScrollPane2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        creditDebitTable.setFont(new java.awt.Font("Century Gothic", 0, 17)); // NOI18N
        creditDebitTable.setModel(new javax.swing.table.DefaultTableModel(
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
        creditDebitTable.setRowHeight(20);
        creditDebitTable.setSelectionBackground(new java.awt.Color(0, 0, 0));
        creditDebitTable.setSelectionForeground(new java.awt.Color(140, 198, 62));
        creditDebitTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                creditDebitTableMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                creditDebitTableMouseReleased(evt);
            }
        });
        creditDebitTable.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                creditDebitTableKeyReleased(evt);
            }
        });
        jScrollPane2.setViewportView(creditDebitTable);

        jPanel2.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 100, 740, 420));

        totalAmountTable.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        totalAmountTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null}
            },
            new String [] {
                "Title 1"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        totalAmountTable.setCellSelectionEnabled(true);
        totalAmountTable.setRowHeight(20);
        totalAmountTable.setSelectionBackground(new java.awt.Color(0, 0, 0));
        totalAmountTable.setSelectionForeground(new java.awt.Color(140, 198, 62));
        totalAmountTable.setShowHorizontalLines(false);
        jScrollPane3.setViewportView(totalAmountTable);

        jPanel2.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 560, 740, 60));

        jLabel4.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Payment Type :");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(26, 450, 130, 26));

        jLabel5.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Summury :");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 530, -1, 26));

        jLabel7.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Bank Details");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, -1, 26));

        jLabel8.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Amount Received : ");
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 400, -1, 26));

        amountReceivedField.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        amountReceivedField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                amountReceivedFieldKeyTyped(evt);
            }
        });
        jPanel2.add(amountReceivedField, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 400, 200, 30));

        jLabel9.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Comments :");
        jPanel2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 370, 120, 26));

        jLabel10.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Received Date :");
        jPanel2.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 500, 120, 26));

        commentsField.setColumns(20);
        commentsField.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        commentsField.setRows(5);
        jScrollPane4.setViewportView(commentsField);

        jPanel2.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 400, 200, 130));

        paymentTypeField.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        paymentTypeField.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Payment Type", "Bank", "Cash", "Online" }));
        jPanel2.add(paymentTypeField, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 450, 200, 30));

        receivedDateField.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jPanel2.add(receivedDateField, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 500, 200, 30));

        addButton.setBackground(new java.awt.Color(140, 198, 62));
        addButton.setFont(new java.awt.Font("Century Gothic", 0, 15)); // NOI18N
        addButton.setForeground(new java.awt.Color(255, 255, 255));
        addButton.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        addButton.setText("Add Credit Debit");
        addButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        addButton.setOpaque(true);
        addButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                addButtonMouseClicked(evt);
            }
        });
        jPanel2.add(addButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 570, 180, 40));

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
        jPanel2.add(clearAllFieldsButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 570, 180, 40));

        jLabel11.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("Search Credit Debit");
        jPanel2.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 50, 180, 40));

        searchCreditDebitField.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        searchCreditDebitField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchCreditDebitFieldActionPerformed(evt);
            }
        });
        searchCreditDebitField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                searchCreditDebitFieldKeyReleased(evt);
            }
        });
        jPanel2.add(searchCreditDebitField, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 50, 250, 40));

        addButton1.setBackground(new java.awt.Color(140, 198, 62));
        addButton1.setFont(new java.awt.Font("Century Gothic", 0, 15)); // NOI18N
        addButton1.setForeground(new java.awt.Color(255, 255, 255));
        addButton1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        addButton1.setText("Print");
        addButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        addButton1.setOpaque(true);
        addButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                addButton1MouseClicked(evt);
            }
        });
        jPanel2.add(addButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1200, 20, 150, 30));

        clearAllFieldsButton1.setBackground(new java.awt.Color(140, 198, 62));
        clearAllFieldsButton1.setFont(new java.awt.Font("Century Gothic", 0, 15)); // NOI18N
        clearAllFieldsButton1.setForeground(new java.awt.Color(255, 255, 255));
        clearAllFieldsButton1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        clearAllFieldsButton1.setText("View");
        clearAllFieldsButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        clearAllFieldsButton1.setOpaque(true);
        clearAllFieldsButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                clearAllFieldsButton1MouseClicked(evt);
            }
        });
        jPanel2.add(clearAllFieldsButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1200, 60, 150, 30));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 67, 1390, 640));

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

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        this.dispose();
    }//GEN-LAST:event_jLabel2MouseClicked

    private void addButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addButtonMouseClicked
        addRecord();
    }//GEN-LAST:event_addButtonMouseClicked

    private void clearAllFieldsButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_clearAllFieldsButtonMouseClicked
        clearAllFields();
    }//GEN-LAST:event_clearAllFieldsButtonMouseClicked

    private void bankTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bankTableMouseClicked
        bankId = (Integer) bankTable.getValueAt(bankTable.getSelectedRow(), 0);
    }//GEN-LAST:event_bankTableMouseClicked

    private void amountReceivedFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_amountReceivedFieldKeyTyped
        char c = evt.getKeyChar();
        if(Character.isAlphabetic(c))
        {
            evt.consume();
        }
    }//GEN-LAST:event_amountReceivedFieldKeyTyped

    private void creditDebitTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_creditDebitTableMouseClicked
        getSelectedRow();
    }//GEN-LAST:event_creditDebitTableMouseClicked

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        deleteRecord();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void creditDebitTableMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_creditDebitTableMouseReleased
        if (evt.isPopupTrigger()) {
            jPopupMenu1.show(creditDebitTable, evt.getX(), evt.getY());
        }
    }//GEN-LAST:event_creditDebitTableMouseReleased

    private void searchCreditDebitFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchCreditDebitFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchCreditDebitFieldActionPerformed

    private void searchCreditDebitFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchCreditDebitFieldKeyReleased
        String txt = searchCreditDebitField.getText().toString();
        TableRowSorter trs = new TableRowSorter(creditDebitTable.getModel());
        creditDebitTable.setRowSorter(trs);
        trs.setRowFilter(RowFilter.regexFilter("(?i)"+txt));
    }//GEN-LAST:event_searchCreditDebitFieldKeyReleased

    private void creditDebitTableKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_creditDebitTableKeyReleased
        if(evt.getKeyCode()==38 || evt.getKeyCode()==40)
        {
            getSelectedRow();
        }
        else if(evt.getKeyCode()==37)
        {
            amountReceivedField.requestFocusInWindow();
        }
    }//GEN-LAST:event_creditDebitTableKeyReleased

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        if(addButton.isEnabled()){
            addRecord();
        }
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
       
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

    private void addButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addButton1MouseClicked
////        try {
////            HashMap<String, Object> hm = new HashMap<String, Object>();
////            hm.put("sellId", this.sellId);
////                    
////            
////            JasperPrint jasperPrint;
////            java.sql.Connection con = DBConnection.getInstance();
////            JasperReport jasperReport = JasperCompileManager.compileReport("C:\\Users\\Rehan Ali Azeemi\\Desktop\\sell.jrxml");   
////            jasperPrint = JasperFillManager.fillReport(jasperReport, hm, con);
////            
////            JasperPrintManager.printReport(jasperPrint, true);
////            
////        } catch (Exception e) {
////            JOptionPane.showMessageDialog(null, e);
////            e.printStackTrace();
////        }
    }//GEN-LAST:event_addButton1MouseClicked

    private void clearAllFieldsButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_clearAllFieldsButton1MouseClicked
//        try {
//            HashMap<String, Object> hm = new HashMap<String, Object>();
//            hm.put("sellId", this.sellId);
//                    
//            
//            JasperPrint jasperPrint;
//            java.sql.Connection con = DBConnection.getInstance();
//            JasperReport jasperReport = JasperCompileManager.compileReport("C:\\Users\\Rehan Ali Azeemi\\Desktop\\sell.jrxml");   
//            jasperPrint = JasperFillManager.fillReport(jasperReport, hm, con);
//            
//                JasperViewer.viewReport(jasperPrint);       
//        } catch (Exception e) {
//            JOptionPane.showMessageDialog(null, e);
//            e.printStackTrace();
//        }
    }//GEN-LAST:event_clearAllFieldsButton1MouseClicked

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
            java.util.logging.Logger.getLogger(SellCreditDebitFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SellCreditDebitFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SellCreditDebitFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SellCreditDebitFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SellCreditDebitFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel addButton;
    private javax.swing.JLabel addButton1;
    private javax.swing.JTextField amountReceivedField;
    private javax.swing.JTable bankTable;
    private javax.swing.JLabel clearAllFieldsButton;
    private javax.swing.JLabel clearAllFieldsButton1;
    private javax.swing.JTextArea commentsField;
    private javax.swing.JTable creditDebitTable;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
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
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JComboBox<String> paymentTypeField;
    private com.toedter.calendar.JDateChooser receivedDateField;
    private javax.swing.JTextField searchCreditDebitField;
    private javax.swing.JTable totalAmountTable;
    // End of variables declaration//GEN-END:variables

    private void fillBankTable() {
        ResultSet resultSet = new BankDaoImpl().getAllAccounts();
        bankTable.setModel(DbUtils.resultSetToTableModel(resultSet));
        
        bankTable.getColumnModel().getColumn(0).setWidth(0);
        bankTable.getColumnModel().getColumn(0).setMinWidth(0);
        bankTable.getColumnModel().getColumn(0).setMaxWidth(0);
        
        DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer();
        headerRenderer.setBackground(new Color(11, 18, 29));
        headerRenderer.setForeground(new Color(140, 198, 62));

        for (int i = 0; i < bankTable.getModel().getColumnCount(); i++) {
            bankTable.getColumnModel().getColumn(i).setHeaderRenderer(headerRenderer);
        }
        bankTable.setShowHorizontalLines(true);
        bankTable.setShowVerticalLines(true);
        this.getContentPane().setBackground(Color.WHITE);
        jScrollPane1.getViewport().setBackground(Color.WHITE);
    }

    private void fillSellCreditDebitBySellId() {
        ResultSet resultSet = new SellCreditDebitDAOImpl().viewAllSellCreditDebitBySellId(sellId);
        creditDebitTable.setModel(DbUtils.resultSetToTableModel(resultSet));
        
        creditDebitTable.getColumnModel().getColumn(0).setWidth(0);
        creditDebitTable.getColumnModel().getColumn(0).setMinWidth(0);
        creditDebitTable.getColumnModel().getColumn(0).setMaxWidth(0);
        
        creditDebitTable.getColumnModel().getColumn(1).setWidth(0);
        creditDebitTable.getColumnModel().getColumn(1).setMinWidth(0);
        creditDebitTable.getColumnModel().getColumn(1).setMaxWidth(0);
        
        DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer();
        headerRenderer.setBackground(new Color(11, 18, 29));
        headerRenderer.setForeground(new Color(140, 198, 62));

        for (int i = 0; i < creditDebitTable.getModel().getColumnCount(); i++) {
            creditDebitTable.getColumnModel().getColumn(i).setHeaderRenderer(headerRenderer);
        }
        creditDebitTable.setShowHorizontalLines(true);
        creditDebitTable.setShowVerticalLines(true);
        this.getContentPane().setBackground(Color.WHITE);
        jScrollPane2.getViewport().setBackground(Color.WHITE);        
    }

    private void fillTotalAmountTableBySellId() {
        ResultSet resultSet = new SellCreditDebitDAOImpl().viewAllTotalAmountBySellId(sellId);
        totalAmountTable.setModel(DbUtils.resultSetToTableModel(resultSet));
                
        DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer();
        headerRenderer.setBackground(new Color(11, 18, 29));
        headerRenderer.setForeground(new Color(140, 198, 62));

        for (int i = 0; i < totalAmountTable.getModel().getColumnCount(); i++) {
            totalAmountTable.getColumnModel().getColumn(i).setHeaderRenderer(headerRenderer);
        }
        totalAmountTable.setShowHorizontalLines(true);
        totalAmountTable.setShowVerticalLines(true);
        this.getContentPane().setBackground(Color.WHITE);
        jScrollPane3.getViewport().setBackground(Color.WHITE);                
    }
    
    
    private void clearAllFields() {
        paymentTypeField.setSelectedIndex(0);
        amountReceivedField.setText("");
        receivedDateField.setCalendar(null);
        commentsField.setText("");
        bankTable.clearSelection();
        creditDebitTable.clearSelection();
        disableButtons(1);
    }

    private void disableButtons(Integer i) {
        if(i==0)
        {
            addButton.setEnabled(false);
        }else if(i==1){
            addButton.setEnabled(true);
        }
    }

    private void addRecord() {
        
        if(amountReceivedField.getText().trim().equals("") || paymentTypeField.getSelectedIndex()==0 || receivedDateField.getDate()==null)
        {
            new MessageForm("Error", "Some fields are missing,please fill to add record.","error.png").setVisible(true); 
        }else if(amountReceivedField.getText().contains("-"))
        {
           new MessageForm("Error", "Amount can not be negative.","error.png").setVisible(true); 
        }
        else{
            SellCreditDebitModel sellCreditDebitModel = new SellCreditDebitModel();
            currentDate = new Timestamp(System.currentTimeMillis());
            SellModel sellModel = new SellModel();
            sellModel.setSellId(sellId);
            BankModel bankModel = new BankModel();
            Double amountReceived = Double.valueOf(amountReceivedField.getText());
            String paymentType = paymentTypeField.getSelectedItem().toString();
            System.out.println(paymentType);
            if(paymentTypeField.getSelectedItem().toString().trim().equals("Cash"))
            {
                bankModel.setBankId(0);
            }else{
                bankModel.setBankId(bankId);
            }
            String comments = commentsField.getText();
            Timestamp paymentDate = new Timestamp(receivedDateField.getDate().getTime());
            
            sellCreditDebitModel.setBankModel(bankModel);
            sellCreditDebitModel.setSellModel(sellModel);
            sellCreditDebitModel.setAmountPaid(amountReceived);
            sellCreditDebitModel.setBankModel(bankModel);
            sellCreditDebitModel.setSellModel(sellModel);
            sellCreditDebitModel.setDate(paymentDate);
            sellCreditDebitModel.setComments(comments);
            sellCreditDebitModel.setPaymentType(paymentType);
            sellCreditDebitModel.setCreatedDate(currentDate);
            sellCreditDebitModel.setModifiedDate(currentDate);
            Integer row = new SellCreditDebitDAOImpl().addSellCreditDebit(sellCreditDebitModel);
            Integer count = 0;
            if(!(paymentType.equals("Cash")))
            {
                bankModel = new BankDaoImpl().getBankDetailsById(bankId);
                bankModel.setModifiedBy(LoginFrame.employeesModel);            
                Double totalAmount = bankModel.getBalance()+amountReceived;
                bankModel.setBalance(totalAmount);
                count = new BankDaoImpl().updateAccount(bankModel);
            }
            if(row<0)
            {
                new MessageForm("Error", "Record could not be added try again.","error.png").setVisible(true); 
            }else{
                new MessageForm("Success", "Record has been added.","info.png").setVisible(true);
                clearAllFields();
                fillSellCreditDebitBySellId();
                fillTotalAmountTableBySellId();
                fillBankTable();
            }
        }
    }

    private void deleteRecord() {
        if(creditDebitTable.getSelectedRow()<0)
        {
            new MessageForm("Error", "Please select a row to delete.","error.png").setVisible(true);
        }else{
                sellCreditDebitId = (Integer) creditDebitTable.getValueAt(creditDebitTable.getSelectedRow(), 0);
                bankId = (Integer) creditDebitTable.getValueAt(creditDebitTable.getSelectedRow(), 1);
                
                SellCreditDebitModel sellCreditDebitModel = new SellCreditDebitModel();
                currentDate = new Timestamp(System.currentTimeMillis());
                SellModel sellModel = new SellModel();
                BankModel bankModel = new BankModel(); 
                sellCreditDebitModel.setSellCreditDebitId(sellCreditDebitId);
                sellCreditDebitModel.setModifiedDate(currentDate);
                Integer row = new SellCreditDebitDAOImpl().deleteSellCreditDebit(sellCreditDebitModel);
                Integer count;
                if(!(bankId==null))
                {
                    bankModel = new BankDaoImpl().getBankDetailsById(bankId);
                    bankModel.setModifiedBy(LoginFrame.employeesModel);
                    bankModel.setModifiedDate(currentDate);
                    previousAmount = bankModel.getBalance()-previousAmount;
                    bankModel.setBalance(previousAmount);
                    count = new BankDaoImpl().updateAccount(bankModel);
                }
                if(row<0)
                {
                    new MessageForm("Error", "Record could not be deleted try again.","error.png").setVisible(true); 
                }else{
                    new MessageForm("Success", "Record has been deleted.","info.png").setVisible(true);
                    clearAllFields();
                    fillSellCreditDebitBySellId();
                    fillTotalAmountTableBySellId();
                    fillBankTable();
                }
            }
        }

    private void getSelectedRow() {
    bankId = (Integer) creditDebitTable.getValueAt(creditDebitTable.getSelectedRow(), 1);
        bankTable.clearSelection();
        sellCreditDebitId = (Integer) creditDebitTable.getValueAt(creditDebitTable.getSelectedRow(), 0);
        BigDecimal bd =  (BigDecimal) creditDebitTable.getValueAt(creditDebitTable.getSelectedRow(), 6);
        previousAmount = bd.doubleValue();
        SellCreditDebitModel sellCreditDebitModel = new SellCreditDebitDAOImpl().getSellCreditDebitById(sellCreditDebitId);
        if(bankId!=null)
        {
            for(int i=0;i<bankTable.getRowCount();i++)
            {
                bankTableBankId = (Integer) bankTable.getValueAt(i, 0);
                if(bankId.equals(bankTableBankId))
                {
                    bankTable.setRowSelectionInterval(i,i);
                    break;
                }
            }
        }
        amountReceivedField.setText(sellCreditDebitModel.getAmountPaid().toString());
        paymentTypeField.setSelectedItem(sellCreditDebitModel.getPaymentType());
        receivedDateField.setDate(sellCreditDebitModel.getDate());
        commentsField.setText(sellCreditDebitModel.getComments());
        disableButtons(0);
    }
}