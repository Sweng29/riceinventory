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
import java.sql.Timestamp;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import models.RoleModel;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author Rehan Ali Azeemi
 */
public class RoleFrame extends javax.swing.JFrame {
    
    RoleDaoImpl roleDaoImpl = null;

    /**
     * Creates new form RoleFrame
     */
    public RoleFrame() {
        initComponents();
        this.setLocationRelativeTo(null);
        roleDaoImpl = new RoleDaoImpl();
        addRole.setEnabled(true);
        updateRole.setEnabled(false);
        fillDataIntoRoleTable();
    }
    
    private void clearFields() {
        roleName.setText("");
    }
    
    private void fillDataIntoRoleTable() {
        DefaultTableModel dtm = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; //To change body of generated methods, choose Tools | Templates.
            }
            
        };
        
        ResultSet rs = roleDaoImpl.getAllRoles();
        
        if (rs != null) {
            
            dtm = (DefaultTableModel) DbUtils.resultSetToTableModel(rs);
            
            roleTable.setModel(dtm);
            
            roleTable.getColumnModel().getColumn(0).setWidth(0);
            roleTable.getColumnModel().getColumn(0).setMinWidth(0);
            roleTable.getColumnModel().getColumn(0).setMaxWidth(0);
            
            DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer();
            headerRenderer.setBackground(new Color(11, 18, 29));
            headerRenderer.setForeground(new Color(140, 198, 62));
            
            for (int i = 0; i < roleTable.getModel().getColumnCount(); i++) {
                roleTable.getColumnModel().getColumn(i).setHeaderRenderer(headerRenderer);
            }
            roleTable.setShowHorizontalLines(true);
            roleTable.setShowVerticalLines(true);
            this.getContentPane().setBackground(Color.WHITE);
            jScrollPane1.getViewport().setBackground(Color.WHITE);
        } else {
            new MessageForm("Information", "No record found", "info.png").setVisible(true);
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
        deleteRole = new javax.swing.JMenuItem();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        roleTable = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        roleSearch = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        roleName = new javax.swing.JTextField();
        addRole = new javax.swing.JLabel();
        updateRole = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();

        deleteRole.setFont(new java.awt.Font("Century Gothic", 0, 17)); // NOI18N
        deleteRole.setText("Delete Role");
        deleteRole.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteRoleActionPerformed(evt);
            }
        });
        jPopupMenu1.add(deleteRole);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(11, 18, 29));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.setPreferredSize(new java.awt.Dimension(990, 70));

        jLabel1.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("X");
        jLabel1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Century Gothic", 0, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(140, 198, 62));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Role Panel");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(385, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(351, 351, 351)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 66, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 990, 70));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel2MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel2MouseEntered(evt);
            }
        });

        roleTable.setFont(new java.awt.Font("Century Gothic", 0, 17)); // NOI18N
        roleTable.setModel(new javax.swing.table.DefaultTableModel(
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
        roleTable.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        roleTable.setRowHeight(20);
        roleTable.setSelectionBackground(new java.awt.Color(11, 18, 29));
        roleTable.setSelectionForeground(new java.awt.Color(140, 198, 62));
        roleTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                roleTableMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                roleTableMouseReleased(evt);
            }
        });
        jScrollPane1.setViewportView(roleTable);

        jLabel3.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jLabel3.setText("Search Role");

        roleSearch.setFont(new java.awt.Font("Century Gothic", 0, 15)); // NOI18N
        roleSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                roleSearchKeyReleased(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Century Gothic", 0, 15)); // NOI18N
        jLabel4.setText("Role");

        roleName.setFont(new java.awt.Font("Century Gothic", 0, 15)); // NOI18N

        addRole.setBackground(new java.awt.Color(140, 198, 62));
        addRole.setFont(new java.awt.Font("Century Gothic", 0, 15)); // NOI18N
        addRole.setForeground(new java.awt.Color(255, 255, 255));
        addRole.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        addRole.setText("Add Role");
        addRole.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        addRole.setOpaque(true);
        addRole.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                addRoleMouseClicked(evt);
            }
        });

        updateRole.setBackground(new java.awt.Color(140, 198, 62));
        updateRole.setFont(new java.awt.Font("Century Gothic", 0, 15)); // NOI18N
        updateRole.setForeground(new java.awt.Color(255, 255, 255));
        updateRole.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        updateRole.setText("Update Role");
        updateRole.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        updateRole.setOpaque(true);
        updateRole.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                updateRoleMouseClicked(evt);
            }
        });

        jLabel7.setBackground(new java.awt.Color(140, 198, 62));
        jLabel7.setFont(new java.awt.Font("Century Gothic", 0, 15)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Reset");
        jLabel7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel7.setOpaque(true);
        jLabel7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel7MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(35, 35, 35)
                                .addComponent(roleName, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addGap(37, 37, 37)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(addRole, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(updateRole, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(34, 34, 34)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 669, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(roleSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(50, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(roleSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 379, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(roleName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(addRole, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(updateRole, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 61, 990, 490));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        this.dispose();
               // TODO add your handling code here:
    }//GEN-LAST:event_jLabel1MouseClicked

    private void jLabel7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MouseClicked
        clearFields();
        roleTable.clearSelection();
        addRole.setEnabled(true);
        updateRole.setEnabled(false);
    }//GEN-LAST:event_jLabel7MouseClicked

    private void roleTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_roleTableMouseClicked
        addRole.setEnabled(false);
        updateRole.setEnabled(true);
        
        int row = roleTable.getSelectedRow();
        
        if (row > -1) {
            String roleName = (String) roleTable.getValueAt(roleTable.getSelectedRow(), 1);
            this.roleName.setText(roleName);
        }

    }//GEN-LAST:event_roleTableMouseClicked

    private void jPanel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel2MouseClicked
        clearFields();
        roleTable.clearSelection();
        addRole.setEnabled(true);
        updateRole.setEnabled(false);
    }//GEN-LAST:event_jPanel2MouseClicked

    private void addRoleMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addRoleMouseClicked
        addRole();
    }//GEN-LAST:event_addRoleMouseClicked
    
    private void addRole() {
        if (!roleName.getText().equals("")) {
            RoleModel roleModel = new RoleModel();
            roleModel.setRole(roleName.getText());
            roleModel.setCreatedBy(LoginFrame.employeesModel);
            roleModel.setModifiedBy(LoginFrame.employeesModel);
            roleModel.setCreatedDate(new Timestamp(System.currentTimeMillis()));
            roleModel.setModifiedDate(new Timestamp(System.currentTimeMillis()));
            
            Integer status = roleDaoImpl.addRole(roleModel);
            
            if (status == 1) {
                fillDataIntoRoleTable();
                new MessageForm("Information", "Record Added", "info.png").setVisible(true);
                clearFields();
            } else {
                new MessageForm("Error", "Error", "error.png").setVisible(true);
            }
        } else {
            new MessageForm("Error", "Please fill all fields", "error.png").setVisible(true);
        }
    }
    private void jPanel2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel2MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_jPanel2MouseEntered

    private void updateRoleMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_updateRoleMouseClicked
        Integer id = (Integer) roleTable.getValueAt(roleTable.getSelectedRow(), 0);
        
        if (id > -1) {
            updateRole(id);
        } else {
            new MessageForm("Error", "Please select row", "error.png").setVisible(true);
        }
    }//GEN-LAST:event_updateRoleMouseClicked

    private void deleteRoleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteRoleActionPerformed
        Integer id = (Integer) roleTable.getValueAt(roleTable.getSelectedRow(), 0);
        
        if (id > -1) {
            int option = JOptionPane.showConfirmDialog(null, "If you delete this record all child records will deleted");
            if (option == JOptionPane.YES_OPTION) {
                
                if (id == LoginFrame.employeesModel.getRoleModel().getRole_id()) {
                    int option2 = JOptionPane.showConfirmDialog(null, "If you delete this role you will be logged out");
                    if (option2 == JOptionPane.YES_OPTION) {
                        deleteRole(id);
                        new EmployeesDaoImpl().deleteEmployee(id, "role");
                        new LoginFrame().setVisible(true);
                        System.exit(0);
                        
                    }
                    
                } else {
                    deleteRole(id);
                    new EmployeesDaoImpl().deleteEmployee(id, "role");
                }
            }
            
        } else {
            new MessageForm("Error", "Please select row", "error.png").setVisible(true);
        }
    }//GEN-LAST:event_deleteRoleActionPerformed

    private void roleTableMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_roleTableMouseReleased
        if (evt.isPopupTrigger()) {
            jPopupMenu1.show(roleTable, evt.getX(), evt.getY());
        }
    }//GEN-LAST:event_roleTableMouseReleased

    private void roleSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_roleSearchKeyReleased
           String employeeNameSearch = roleSearch.getText();
        TableRowSorter tableRowSorter = new TableRowSorter(roleTable.getModel());
        roleTable.setRowSorter(tableRowSorter);
        tableRowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + employeeNameSearch));  // TODO add your handling code here:
    }//GEN-LAST:event_roleSearchKeyReleased
    
    private void deleteRole(Integer id) {
        Integer status = roleDaoImpl.deleteRole(id);
        
        if (status == 1) {
            fillDataIntoRoleTable();
            new MessageForm("Information", "Record Update", "info.png").setVisible(true);
            clearFields();
        } else {
            new MessageForm("Error", "Error", "error.png").setVisible(true);
        }
    }
    
    private void updateRole(Integer id) {
        if (!roleName.getText().equals("")) {
            RoleModel roleModel = new RoleModel();
            roleModel.setRole_id(id);
            roleModel.setRole(roleName.getText());
            roleModel.setModifiedBy(LoginFrame.employeesModel);
            roleModel.setModifiedDate(new Timestamp(System.currentTimeMillis()));
            
            Integer status = roleDaoImpl.updateRole(roleModel);
            
            if (status == 1) {
                fillDataIntoRoleTable();
                new MessageForm("Information", "Record Update", "info.png").setVisible(true);
                clearFields();
            } else {
                new MessageForm("Error", "Error", "error.png").setVisible(true);
            }
        } else {
            new MessageForm("Error", "Please fill all fields", "error.png").setVisible(true);
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
            java.util.logging.Logger.getLogger(RoleFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RoleFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RoleFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RoleFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RoleFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel addRole;
    private javax.swing.JMenuItem deleteRole;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField roleName;
    private javax.swing.JTextField roleSearch;
    private javax.swing.JTable roleTable;
    private javax.swing.JLabel updateRole;
    // End of variables declaration//GEN-END:variables
}
