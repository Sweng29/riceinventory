/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;
import connection.DBConnection;
import daoimpl.BankDaoImpl;
import utility.CommonMethods;
import daoimpl.PurchaseCreditDebitDaoImpl;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import javax.swing.JOptionPane;
import models.BankModel;
import models.EmployeesModel;
import models.PurchaseCreditDebitModel;
import models.PurchaseModel;
//import net.sf.jasperreports.engine.JasperCompileManager;
//import net.sf.jasperreports.engine.JasperFillManager;
//import net.sf.jasperreports.engine.JasperPrint;
//import net.sf.jasperreports.engine.JasperPrintManager;
//import net.sf.jasperreports.engine.JasperReport;
//import net.sf.jasperreports.view.JasperViewer;
/**
 *
 * @author M.UMAIR
 */
public class PurchaseCreditDebitFrame extends javax.swing.JFrame {

    /**
     * Creates new form PurchaseCreditDebitFrame
     */
    private int purchaseId;
    private BankDaoImpl bankDaoImpl;
    private PurchaseCreditDebitDaoImpl pcdDaoImp;
    private double balance = 0.00;
    public PurchaseCreditDebitFrame(int purchaseId) {
        
        this.purchaseId = purchaseId;
        pcdDaoImp = new PurchaseCreditDebitDaoImpl();
        bankDaoImpl = new BankDaoImpl();
        initComponents();
        this.setLocationRelativeTo(null);
        datePay.setDate(new Date());
        loadAllTables();
        clearFields();
    }
    private void loadAllTables(){
        CommonMethods.fillTables(pcdDaoImp.getPurchaseCreditDebitBYPurchaseId(purchaseId), tblPcd, jScrollPane2, this);
         tblPcd.getColumnModel().getColumn(1).setWidth(0);
        tblPcd.getColumnModel().getColumn(1).setMinWidth(0);
        tblPcd.getColumnModel().getColumn(1).setMaxWidth(0);
        CommonMethods.fillTables(bankDaoImpl.getAllAccounts(), tblAccount, jScrollPane2, this);
        CommonMethods.fillTables(pcdDaoImp.getSummaryPurchaseCredit(purchaseId), tblSummary, jScrollPane1, this);
    }
    private void clearFields(){
        datePay.setDate(new Date());
        tfPrice.setText("");
        tblAccount.clearSelection();
        tblPcd.clearSelection();
        tblSummary.clearSelection();
        tfNote.setText("");
        btnAddPurchaseDetail.setEnabled(true);
        balance = 0.00;
        radioOnline.setSelected(true);
    }
    
    private void addPayment(){
        int bankId = 0;
        if(radioCheque.isSelected() || radioOnline.isSelected()){
            if(tblAccount.getSelectedRow()<0){
                new MessageForm("Error","Select Account First", "error.png").setVisible(true);
                return;
            }else{
                bankId = (int)tblAccount.getValueAt(tblAccount.getSelectedRow(), 0);
                balance =Double.parseDouble(tblAccount.getValueAt(tblAccount.getSelectedRow(), 5).toString());
            }
        }
        if(tfPrice.getText().trim().equals("")){
            new MessageForm("Error","Provide Payment Amount", "error.png").setVisible(true);
            return;
        }
        if(datePay.getDate()==null){
            new MessageForm("Error","Select Date", "error.png").setVisible(true);
            return;
        }
        double amount = Double.parseDouble(tfPrice.getText());
        Timestamp payDate = new Timestamp(datePay.getDate().getTime());
        String note  = tfNote.getText();
        models.PurchaseCreditDebitModel pcdModel = new PurchaseCreditDebitModel();
        BankModel bankModel = new BankModel();
        EmployeesModel employeesModel = LoginFrame.employeesModel;
        
        bankModel.setBankId(bankId);
        PurchaseModel model = new PurchaseModel();
        model.setPurchaseId(purchaseId);
        pcdModel.setPurchaseModel(model);
        pcdModel.setAmountPaid(amount);
        pcdModel.setComments(note);
        if(radioCheque.isSelected()){
            pcdModel.setPaymentType("Cheque");
            pcdModel.setBankModel(bankModel);
        }
        else if(radioOnline.isSelected()){
            pcdModel.setPaymentType("Online");
            pcdModel.setBankModel(bankModel);
        }
        else if(radioCash.isSelected()){
            pcdModel.setPaymentType("Cash");
        }
        pcdModel.setCreatedBy(employeesModel);
        pcdModel.setDate(payDate);
        int re = 0;
        if(radioOnline.isSelected() || radioCheque.isSelected()){
            if(balance<amount){
                new MessageForm("error", "Balance Not Available", "error.png").setVisible(true);
                return;
            }
        }
        re = pcdDaoImp.addPurchaseCreditDebit(pcdModel);
        if(re>0){
            balance = balance-amount;
            int re2 =  pcdDaoImp.changeBalance(bankId,  balance);
            new MessageForm("Success", "Success", "info.png").setVisible(true);
            loadAllTables();
            clearFields();
        }
        
    }
    private void deleteFields(){
        if(tblPcd.getSelectedRow()<0){
            new MessageForm("Error","Please Select A record First", "error.png");
            return;
        }
        int id = (int)tblPcd.getValueAt(tblPcd.getSelectedRow(), 0);
        int empId = LoginFrame.employeesModel.getEmployeeId();
        if(pcdDaoImp.deletePurchaseCreditDebit(id , empId)>0){
            String payOption = tblPcd.getValueAt(tblPcd.getSelectedRow(), 4).toString();
            if(payOption.equalsIgnoreCase("Cheque") || payOption.equalsIgnoreCase("Online")){
                int bankId = (int)tblPcd.getValueAt(tblPcd.getSelectedRow(), 1);
                double balance = Double.parseDouble(tblPcd.getValueAt(tblPcd.getSelectedRow(), 3).toString());
                int re = pcdDaoImp.changeBalanceForDelete(bankId, balance);                
            }
            clearFields();
            loadAllTables();
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
        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        tfSearchPurchase = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblPcd = new javax.swing.JTable();
        btnAddPurchaseDetail = new javax.swing.JLabel();
        lblResetPurchase = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        tfPrice = new javax.swing.JFormattedTextField();
        jLabel11 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblAccount = new javax.swing.JTable();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblSummary = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tfNote = new javax.swing.JTextArea();
        radioCash = new javax.swing.JRadioButton();
        radioOnline = new javax.swing.JRadioButton();
        radioCheque = new javax.swing.JRadioButton();
        jLabel13 = new javax.swing.JLabel();
        btnAddPurchaseDetail1 = new javax.swing.JLabel();
        btnAddPurchaseDetail2 = new javax.swing.JLabel();
        datePay = new com.toedter.calendar.JDateChooser();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
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

        jLabel4.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Select Account");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 130, -1, 26));

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
        jPanel2.add(tfSearchPurchase, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 120, 220, -1));

        tblPcd.setFont(new java.awt.Font("Century Gothic", 0, 17)); // NOI18N
        tblPcd.setModel(new javax.swing.table.DefaultTableModel(
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
        tblPcd.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tblPcd.setRowHeight(20);
        tblPcd.setSelectionBackground(new java.awt.Color(11, 18, 29));
        tblPcd.setSelectionForeground(new java.awt.Color(140, 198, 62));
        tblPcd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tblPcdMouseReleased(evt);
            }
        });
        jScrollPane2.setViewportView(tblPcd);

        jPanel2.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 170, 600, 440));

        btnAddPurchaseDetail.setBackground(new java.awt.Color(140, 198, 62));
        btnAddPurchaseDetail.setFont(new java.awt.Font("Century Gothic", 0, 15)); // NOI18N
        btnAddPurchaseDetail.setForeground(new java.awt.Color(255, 255, 255));
        btnAddPurchaseDetail.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnAddPurchaseDetail.setText("View");
        btnAddPurchaseDetail.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAddPurchaseDetail.setOpaque(true);
        btnAddPurchaseDetail.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnAddPurchaseDetailMouseClicked(evt);
            }
        });
        btnAddPurchaseDetail.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnAddPurchaseDetailKeyPressed(evt);
            }
        });
        jPanel2.add(btnAddPurchaseDetail, new org.netbeans.lib.awtextra.AbsoluteConstraints(1130, 120, 120, 30));

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
        jPanel2.add(lblResetPurchase, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 670, 390, 30));

        jPanel1.setBackground(new java.awt.Color(11, 18, 29));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setFont(new java.awt.Font("Century Gothic", 1, 27)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(140, 192, 62));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Purchase Credit Debit Details");

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
                .addContainerGap(411, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 408, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(477, 477, 477)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 66, Short.MAX_VALUE)
        );

        jPanel2.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1340, 70));

        jLabel10.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jLabel10.setText("Date :");
        jPanel2.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 560, -1, -1));

        tfPrice.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        tfPrice.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jPanel2.add(tfPrice, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 510, 190, 30));

        jLabel11.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jLabel11.setText("Note:");
        jPanel2.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 510, -1, -1));

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
        jScrollPane3.setViewportView(tblAccount);

        jPanel2.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 170, 580, 260));

        tblSummary.setFont(new java.awt.Font("Century Gothic", 0, 17)); // NOI18N
        tblSummary.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tblSummary);
        tblSummary.getAccessibleContext().setAccessibleParent(jScrollPane2);

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(822, 640, 430, 60));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setText("Summary :");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 660, 120, 30));

        jLabel5.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Search Purchase");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 120, -1, 26));

        jLabel12.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jLabel12.setText("Payment Options :");
        jPanel2.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 460, 180, -1));

        tfNote.setColumns(20);
        tfNote.setRows(5);
        jScrollPane4.setViewportView(tfNote);

        jPanel2.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 510, 220, 90));

        buttonGroup1.add(radioCash);
        radioCash.setFont(new java.awt.Font("Century Gothic", 0, 15)); // NOI18N
        radioCash.setText("Cash");
        radioCash.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioCashActionPerformed(evt);
            }
        });
        jPanel2.add(radioCash, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 460, -1, -1));

        buttonGroup1.add(radioOnline);
        radioOnline.setFont(new java.awt.Font("Century Gothic", 0, 15)); // NOI18N
        radioOnline.setSelected(true);
        radioOnline.setText("Online");
        jPanel2.add(radioOnline, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 460, -1, -1));

        buttonGroup1.add(radioCheque);
        radioCheque.setFont(new java.awt.Font("Century Gothic", 0, 15)); // NOI18N
        radioCheque.setText("Cheque");
        jPanel2.add(radioCheque, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 460, -1, -1));

        jLabel13.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jLabel13.setText("Amount :");
        jPanel2.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 510, 90, -1));

        btnAddPurchaseDetail1.setBackground(new java.awt.Color(140, 198, 62));
        btnAddPurchaseDetail1.setFont(new java.awt.Font("Century Gothic", 0, 15)); // NOI18N
        btnAddPurchaseDetail1.setForeground(new java.awt.Color(255, 255, 255));
        btnAddPurchaseDetail1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnAddPurchaseDetail1.setText("Add Purchase Credit");
        btnAddPurchaseDetail1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAddPurchaseDetail1.setOpaque(true);
        btnAddPurchaseDetail1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnAddPurchaseDetail1MouseClicked(evt);
            }
        });
        btnAddPurchaseDetail1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnAddPurchaseDetail1KeyPressed(evt);
            }
        });
        jPanel2.add(btnAddPurchaseDetail1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 630, 390, 34));

        btnAddPurchaseDetail2.setBackground(new java.awt.Color(140, 198, 62));
        btnAddPurchaseDetail2.setFont(new java.awt.Font("Century Gothic", 0, 15)); // NOI18N
        btnAddPurchaseDetail2.setForeground(new java.awt.Color(255, 255, 255));
        btnAddPurchaseDetail2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnAddPurchaseDetail2.setText("Print");
        btnAddPurchaseDetail2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAddPurchaseDetail2.setOpaque(true);
        btnAddPurchaseDetail2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnAddPurchaseDetail2MouseClicked(evt);
            }
        });
        btnAddPurchaseDetail2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnAddPurchaseDetail2KeyPressed(evt);
            }
        });
        jPanel2.add(btnAddPurchaseDetail2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1130, 80, 120, 30));
        jPanel2.add(datePay, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 560, 190, 30));

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
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 1340, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 750, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tfSearchPurchaseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfSearchPurchaseActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfSearchPurchaseActionPerformed

    private void tfSearchPurchaseKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfSearchPurchaseKeyReleased
        // TODO add your handling code here:
        CommonMethods.searchFromTable(tblPcd, tfSearchPurchase);
    }//GEN-LAST:event_tfSearchPurchaseKeyReleased

    private void tblPcdMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPcdMouseReleased
        if (evt.isPopupTrigger()) {
            jPopupMenu1.show(tblPcd, evt.getX(), evt.getY());
        }        // TODO add your handling code here:
    }//GEN-LAST:event_tblPcdMouseReleased

    private void lblResetPurchaseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblResetPurchaseMouseClicked
        clearFields();
    }//GEN-LAST:event_lblResetPurchaseMouseClicked

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        this.dispose();
    }//GEN-LAST:event_jLabel2MouseClicked

    private void jPanel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jPanel2MouseClicked

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        if(btnAddPurchaseDetail.isEnabled()){
            addPayment();
        }

    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        // TODO add your handling code here:
       deleteFields();
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
        deleteFields();
    }//GEN-LAST:event_deletePurchaseDetailActionPerformed

    private void tblAccountMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblAccountMouseReleased
        if (evt.isPopupTrigger()) {
            jPopupMenu1.show(tblAccount, evt.getX(), evt.getY());
        }        // TODO add your handling code here:
    }//GEN-LAST:event_tblAccountMouseReleased

    private void tblAccountMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblAccountMouseClicked
        // TODO add your handling code here:
        int selectedRow = tblAccount.getSelectedRow();
        if(selectedRow>-1){
             balance = Double.parseDouble(tblAccount.getValueAt(tblAccount.getSelectedRow(), 5).toString());
             System.out.println("Balance "+balance);
        }
    }//GEN-LAST:event_tblAccountMouseClicked

    private void btnAddPurchaseDetailKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnAddPurchaseDetailKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAddPurchaseDetailKeyPressed

    private void btnAddPurchaseDetailMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAddPurchaseDetailMouseClicked
             try {
            HashMap<String, Object> hm = new HashMap<String, Object>();
            hm.put("purchaseId", this.purchaseId);
                    
            
          /*  JasperPrint jasperPrint;
            java.sql.Connection con = DBConnection.getInstance();
            JasperReport jasperReport = JasperCompileManager.compileReport("C:\\Users\\Rehan Ali Azeemi\\Desktop\\RiceInventory.jrxml");   
            jasperPrint = JasperFillManager.fillReport(jasperReport, hm, con);
            
                JasperViewer.viewReport(jasperPrint);       */
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnAddPurchaseDetailMouseClicked

    private void radioCashActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioCashActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_radioCashActionPerformed

    private void btnAddPurchaseDetail1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAddPurchaseDetail1MouseClicked
        addPayment();
    }//GEN-LAST:event_btnAddPurchaseDetail1MouseClicked

    private void btnAddPurchaseDetail1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnAddPurchaseDetail1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAddPurchaseDetail1KeyPressed

    private void btnAddPurchaseDetail2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAddPurchaseDetail2MouseClicked
         try {
            HashMap<String, Object> hm = new HashMap<String, Object>();
            hm.put("purchaseId", this.purchaseId);
                    
            
            /*JasperPrint jasperPrint;
            java.sql.Connection con = DBConnection.getInstance();
            JasperReport jasperReport = JasperCompileManager.compileReport("C:\\Users\\Rehan Ali Azeemi\\Desktop\\RiceInventory.jrxml");   
            jasperPrint = JasperFillManager.fillReport(jasperReport, hm, con);
            
                
           
                JasperPrintManager.printReport(jasperPrint, true);
            
            */
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            e.printStackTrace();
        } 
    }//GEN-LAST:event_btnAddPurchaseDetail2MouseClicked

    private void btnAddPurchaseDetail2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnAddPurchaseDetail2KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAddPurchaseDetail2KeyPressed

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
            java.util.logging.Logger.getLogger(PurchaseCreditDebitFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PurchaseCreditDebitFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PurchaseCreditDebitFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PurchaseCreditDebitFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PurchaseCreditDebitFrame(1).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btnAddPurchaseDetail;
    private javax.swing.JLabel btnAddPurchaseDetail1;
    private javax.swing.JLabel btnAddPurchaseDetail2;
    private javax.swing.ButtonGroup buttonGroup1;
    private com.toedter.calendar.JDateChooser datePay;
    private javax.swing.JMenuItem deletePurchaseDetail;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JLabel lblResetPurchase;
    private javax.swing.JRadioButton radioCash;
    private javax.swing.JRadioButton radioCheque;
    private javax.swing.JRadioButton radioOnline;
    private javax.swing.JTable tblAccount;
    private javax.swing.JTable tblPcd;
    private javax.swing.JTable tblSummary;
    private javax.swing.JTextArea tfNote;
    private javax.swing.JFormattedTextField tfPrice;
    private javax.swing.JTextField tfSearchPurchase;
    // End of variables declaration//GEN-END:variables
}
