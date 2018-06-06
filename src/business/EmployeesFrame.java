/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import daoimpl.EmployeesDaoImpl;
import daoimpl.RoleDaoImpl;
import java.awt.Color;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import models.EmployeesModel;
import models.RoleModel;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author Rehan Ali Azeemi
 */
public class EmployeesFrame extends javax.swing.JFrame {

    EmployeesDaoImpl employeesDaoImpl = null;
    RoleDaoImpl roleDaoImpl = null;

    /**
     * Creates new form EmployeesFrame
     */
    public EmployeesFrame() {
        initComponents();
        employeesDaoImpl = new EmployeesDaoImpl();
        roleDaoImpl = new RoleDaoImpl();
        this.setLocationRelativeTo(null);
        fillDataIntoEmployeesTable();
        fillRoleCombo();
        updateEmployee.setEnabled(false);
    }

    private void fillRoleCombo() {
        ResultSet rs = roleDaoImpl.getAllRoles();
        try {
            while (rs.next()) {
                employeeRoleCombo.addItem(rs.getString(2));
            }
        } catch (SQLException ex) {
            Logger.getLogger(EmployeesFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void fillDataIntoEmployeesTable() {
        DefaultTableModel dtm = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; //To change body of generated methods, choose Tools | Templates.
            }

        };
        
        ResultSet rs = employeesDaoImpl.getAllEmployees();
        if(rs != null){
            
        
        dtm = (DefaultTableModel) DbUtils.resultSetToTableModel(rs);

        employeeTable.setModel(dtm);

        employeeTable.getColumnModel().getColumn(0).setWidth(0);
        employeeTable.getColumnModel().getColumn(0).setMinWidth(0);
        employeeTable.getColumnModel().getColumn(0).setMaxWidth(0);

        DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer();
        headerRenderer.setBackground(new Color(11, 18, 29));
        headerRenderer.setForeground(new Color(140, 198, 62));

        for (int i = 0; i < employeeTable.getModel().getColumnCount(); i++) {
            employeeTable.getColumnModel().getColumn(i).setHeaderRenderer(headerRenderer);
        }
        employeeTable.setShowHorizontalLines(true);
        employeeTable.setShowVerticalLines(true);
        this.getContentPane().setBackground(Color.WHITE);
        jScrollPane2.getViewport().setBackground(Color.WHITE);
        }
        else{
            new MessageForm("Information", "No record found", "info.png").setVisible(true);
        }
    }

    private void addEmployee() {
        if (!employeeName.getText().equals("") && !employeeUsername.getText().equals("") && !employeePassword.getText().equals("") && !employeeContact.getText().equals("") && !employeeAddress.getText().equals("")) {
            String name = employeeName.getText().trim();
            String username = employeeUsername.getText().trim();
            String password = employeePassword.getText().trim();
            String contact = employeeContact.getText().trim();
            String address = employeeAddress.getText().trim();
            String role = (String) employeeRoleCombo.getSelectedItem();

            EmployeesModel employeeModel = new EmployeesModel();
            employeeModel.setName(name);
            employeeModel.setUsername(username);
            employeeModel.setPassword(password);
            employeeModel.setContact(contact);
            employeeModel.setAddress(address);
            RoleModel roleModel = new RoleModel();
            roleModel.setRole(role);
            employeeModel.setRoleModel(roleModel);
            employeeModel.setCreatedBy(LoginFrame.employeesModel);
            employeeModel.setModifiedBy(LoginFrame.employeesModel);
            employeeModel.setCreatedDate(new Timestamp(System.currentTimeMillis()));
            employeeModel.setModifiedDate(new Timestamp(System.currentTimeMillis()));

            int status = employeesDaoImpl.addEmployee(employeeModel);

            if (status == 1) {
                fillDataIntoEmployeesTable();
                new MessageForm("Information", "Record Added", "info.png").setVisible(true);
                clearFields();
            } else {
                new MessageForm("Error", "Error", "error.png").setVisible(true);
            }
        }
    }

    private void updateEmployee(Integer id) {
        if (!employeeName.getText().equals("") && !employeeUsername.getText().equals("") && !employeeContact.getText().equals("") && !employeeAddress.getText().equals("")) {
            String name = employeeName.getText().trim();
            String username = employeeUsername.getText().trim();
            String contact = employeeContact.getText().trim();
            String address = employeeAddress.getText().trim();
            String role = (String) employeeRoleCombo.getSelectedItem();

            EmployeesModel employeeModel = new EmployeesModel();
            employeeModel.setEmployeeId(id);
            employeeModel.setName(name);
            employeeModel.setUsername(username);
            employeeModel.setContact(contact);
            employeeModel.setAddress(address);
            RoleModel roleModel = new RoleModel();
            roleModel.setRole(role);
            employeeModel.setRoleModel(roleModel);
            employeeModel.setModifiedBy(LoginFrame.employeesModel);
            employeeModel.setModifiedDate(new Timestamp(System.currentTimeMillis()));

            int status = employeesDaoImpl.updateEmployee(employeeModel);

            if (status == 1) {
                fillDataIntoEmployeesTable();
                new MessageForm("Information", "Record Updated", "info.png").setVisible(true);
                clearFields();
            } else {
                new MessageForm("Error", "Error", "error.png").setVisible(true);
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
        deleteEmployee = new javax.swing.JMenuItem();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        employeeSearch = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        employeeRoleCombo = new javax.swing.JComboBox<>();
        employeeName = new javax.swing.JTextField();
        employeeUsername = new javax.swing.JTextField();
        employeeContact = new javax.swing.JFormattedTextField();
        employeePassword = new javax.swing.JPasswordField();
        jScrollPane1 = new javax.swing.JScrollPane();
        employeeAddress = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        employeeTable = new javax.swing.JTable();
        addEmployee = new javax.swing.JLabel();
        updateEmployee = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();

        deleteEmployee.setFont(new java.awt.Font("Century Gothic", 0, 15)); // NOI18N
        deleteEmployee.setText("Delete Employee");
        deleteEmployee.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        deleteEmployee.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteEmployeeActionPerformed(evt);
            }
        });
        jPopupMenu1.add(deleteEmployee);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(11, 18, 29));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setFont(new java.awt.Font("Century Gothic", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(140, 192, 62));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Employees Panel");

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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 357, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 66, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 980, 70));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel2MouseClicked(evt);
            }
        });
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Century Gothic", 0, 15)); // NOI18N
        jLabel3.setText("Name");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(45, 78, -1, -1));

        jLabel4.setFont(new java.awt.Font("Century Gothic", 0, 15)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Search Employees");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 30, -1, 26));

        employeeSearch.setFont(new java.awt.Font("Century Gothic", 0, 15)); // NOI18N
        employeeSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                employeeSearchActionPerformed(evt);
            }
        });
        employeeSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                employeeSearchKeyReleased(evt);
            }
        });
        jPanel2.add(employeeSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 30, 220, -1));

        jLabel5.setFont(new java.awt.Font("Century Gothic", 0, 15)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Username");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(45, 122, -1, -1));

        jLabel6.setFont(new java.awt.Font("Century Gothic", 0, 15)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Role");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, 40, 30));

        jLabel7.setFont(new java.awt.Font("Century Gothic", 0, 15)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Password");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(45, 172, -1, -1));

        jLabel8.setFont(new java.awt.Font("Century Gothic", 0, 15)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Contact");
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(45, 216, -1, -1));

        jLabel9.setFont(new java.awt.Font("Century Gothic", 0, 15)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Address");
        jPanel2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(45, 257, -1, -1));

        employeeRoleCombo.setFont(new java.awt.Font("Century Gothic", 0, 15)); // NOI18N
        jPanel2.add(employeeRoleCombo, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 30, 188, -1));

        employeeName.setFont(new java.awt.Font("Century Gothic", 0, 15)); // NOI18N
        jPanel2.add(employeeName, new org.netbeans.lib.awtextra.AbsoluteConstraints(159, 75, 188, -1));

        employeeUsername.setFont(new java.awt.Font("Century Gothic", 0, 15)); // NOI18N
        jPanel2.add(employeeUsername, new org.netbeans.lib.awtextra.AbsoluteConstraints(159, 119, 188, -1));

        try {
            employeeContact.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###########")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        employeeContact.setFont(new java.awt.Font("Century Gothic", 0, 15)); // NOI18N
        jPanel2.add(employeeContact, new org.netbeans.lib.awtextra.AbsoluteConstraints(159, 213, 188, -1));

        employeePassword.setFont(new java.awt.Font("Century Gothic", 0, 15)); // NOI18N
        jPanel2.add(employeePassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(159, 169, 188, -1));

        jScrollPane1.setWheelScrollingEnabled(false);

        employeeAddress.setColumns(20);
        employeeAddress.setFont(new java.awt.Font("Century Gothic", 0, 15)); // NOI18N
        employeeAddress.setRows(5);
        jScrollPane1.setViewportView(employeeAddress);

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(159, 257, 188, -1));

        employeeTable.setFont(new java.awt.Font("Century Gothic", 0, 17)); // NOI18N
        employeeTable.setModel(new javax.swing.table.DefaultTableModel(
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
        employeeTable.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        employeeTable.setRowHeight(20);
        employeeTable.setSelectionBackground(new java.awt.Color(11, 18, 29));
        employeeTable.setSelectionForeground(new java.awt.Color(140, 198, 62));
        employeeTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                employeeTableMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                employeeTableMouseReleased(evt);
            }
        });
        jScrollPane2.setViewportView(employeeTable);

        jPanel2.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(365, 75, 603, 390));

        addEmployee.setBackground(new java.awt.Color(140, 198, 62));
        addEmployee.setFont(new java.awt.Font("Century Gothic", 0, 15)); // NOI18N
        addEmployee.setForeground(new java.awt.Color(255, 255, 255));
        addEmployee.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        addEmployee.setText("Add Employee");
        addEmployee.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        addEmployee.setOpaque(true);
        addEmployee.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                addEmployeeMouseClicked(evt);
            }
        });
        jPanel2.add(addEmployee, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 390, 138, 34));

        updateEmployee.setBackground(new java.awt.Color(140, 198, 62));
        updateEmployee.setFont(new java.awt.Font("Century Gothic", 0, 15)); // NOI18N
        updateEmployee.setForeground(new java.awt.Color(255, 255, 255));
        updateEmployee.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        updateEmployee.setText("Update Employee");
        updateEmployee.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        updateEmployee.setOpaque(true);
        updateEmployee.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                updateEmployeeMouseClicked(evt);
            }
        });
        jPanel2.add(updateEmployee, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 390, 152, 34));

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

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, 980, 480));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        this.dispose();
    }//GEN-LAST:event_jLabel2MouseClicked

    private void employeeSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_employeeSearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_employeeSearchActionPerformed

    private void clearFields() {
        employeeName.setText("");
        employeeUsername.setText("");
        employeePassword.setText("");
        employeeContact.setText("");
        employeeAddress.setText("");

    }

    private void employeeSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_employeeSearchKeyReleased
        String employeeNameSearch = employeeSearch.getText();
        TableRowSorter tableRowSorter = new TableRowSorter(employeeTable.getModel());
        employeeTable.setRowSorter(tableRowSorter);
        tableRowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + employeeNameSearch));        // TODO add your handling code here:
    }//GEN-LAST:event_employeeSearchKeyReleased

    private void jPanel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel2MouseClicked
        employeeTable.clearSelection();
        addEmployee.setEnabled(true);
        updateEmployee.setEnabled(false);
        clearFields();
    }//GEN-LAST:event_jPanel2MouseClicked

    private void addEmployeeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addEmployeeMouseClicked
        String username = employeeUsername.getText();
        if(!username.equals("") && !employeesDaoImpl.checkUsernameAvailbility(username)){
             addEmployee();
        }
        else{
            new MessageForm("Error","Username should be unique","error.png").setVisible(true);
        }
    }//GEN-LAST:event_addEmployeeMouseClicked

    private void employeeTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_employeeTableMouseClicked
        addEmployee.setEnabled(false);
        updateEmployee.setEnabled(true);
        int selectedRow = employeeTable.getSelectedRow();

        if (selectedRow > -1) {
            String name = (String) employeeTable.getValueAt(employeeTable.getSelectedRow(), 1);
            String contact = (String) employeeTable.getValueAt(employeeTable.getSelectedRow(), 2);
            String address = (String) employeeTable.getValueAt(employeeTable.getSelectedRow(), 3);
            String username = (String) employeeTable.getValueAt(employeeTable.getSelectedRow(), 4);
            String role = (String) employeeTable.getValueAt(employeeTable.getSelectedRow(), 5);

            employeeName.setText(name);
            employeeUsername.setText(username);
            employeePassword.setText("");
            employeeContact.setText(contact);
            employeeAddress.setText(address);
            employeeRoleCombo.setSelectedItem(role);
        }


    }//GEN-LAST:event_employeeTableMouseClicked

    private void updateEmployeeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_updateEmployeeMouseClicked
        Integer id = (Integer) employeeTable.getValueAt(employeeTable.getSelectedRow(), 0);

        if (id > -1) {

            updateEmployee(id);
        } else {
            new MessageForm("Error", "Please select row", "error.png").setVisible(true);
        }
    }//GEN-LAST:event_updateEmployeeMouseClicked

    private void jLabel10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel10MouseClicked
        clearFields();
        employeeTable.clearSelection();
        addEmployee.setEnabled(true);
        updateEmployee.setEnabled(false);
    }//GEN-LAST:event_jLabel10MouseClicked

    private void deleteEmployeeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteEmployeeActionPerformed
        Integer row = employeeTable.getSelectedRow();

        if (row > -1) {
            Integer id = (Integer) employeeTable.getValueAt(employeeTable.getSelectedRow(), 0);
               if(id == LoginFrame.employeesModel.getEmployeeId()){
                   int option = JOptionPane.showConfirmDialog(null,"If you delete yourself you will be logged out");
                   if(option == JOptionPane.YES_OPTION){
                       deleteEmployee(id);
                       System.exit(0);
                       new LoginFrame().setVisible(true);
         
                   }
               }
               else{
                   deleteEmployee(id);
               }
        } else {
            new MessageForm("Error", "Please select row", "error.png").setVisible(true);
        }
    }//GEN-LAST:event_deleteEmployeeActionPerformed

    private void employeeTableMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_employeeTableMouseReleased
        if (evt.isPopupTrigger()) {
            jPopupMenu1.show(employeeTable, evt.getX(), evt.getY());
        }        // TODO add your handling code here:
    }//GEN-LAST:event_employeeTableMouseReleased

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        // TODO add your handling code here:
    }//GEN-LAST:event_formWindowActivated

    private void deleteEmployee(Integer id) {
        int status = employeesDaoImpl.deleteEmployee(id);

        if (status == 1) {
            fillDataIntoEmployeesTable();
            new MessageForm("Information", "Record Deleted", "info.png").setVisible(true);
            clearFields();
        } else {
            new MessageForm("Error", "Error", "error.png").setVisible(true);
        }
    }

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
            java.util.logging.Logger.getLogger(EmployeesFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EmployeesFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EmployeesFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EmployeesFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EmployeesFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel addEmployee;
    private javax.swing.JMenuItem deleteEmployee;
    private javax.swing.JTextArea employeeAddress;
    private javax.swing.JFormattedTextField employeeContact;
    private javax.swing.JTextField employeeName;
    private javax.swing.JPasswordField employeePassword;
    private javax.swing.JComboBox<String> employeeRoleCombo;
    private javax.swing.JTextField employeeSearch;
    private javax.swing.JTable employeeTable;
    private javax.swing.JTextField employeeUsername;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel updateEmployee;
    // End of variables declaration//GEN-END:variables
}
