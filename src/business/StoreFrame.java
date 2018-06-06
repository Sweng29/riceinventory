/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import daoimpl.ShopDaoImpl;
import daoimpl.StoreDaoImpl;
import java.awt.Color;
import java.sql.ResultSet;
import java.sql.Timestamp;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import models.ShopModel;
import models.StoreModel;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author Asif Ali
 */
public class StoreFrame extends javax.swing.JFrame {

    /**
     * Creates new form ShopFrame
     */
    public StoreFrame() {
        initComponents();
        fillStoreTable();
        this.setLocationRelativeTo(null);
        jstoretable.setDefaultEditor(Object.class, null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jpopup = new javax.swing.JPopupMenu();
        Delete = new javax.swing.JMenuItem();
        jScrollPane1 = new javax.swing.JScrollPane();
        jstoretable = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jstoresearch = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jstorename = new javax.swing.JTextField();
        jstoreaddress = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        addstore = new javax.swing.JLabel();
        updatestore = new javax.swing.JLabel();
        resetstore = new javax.swing.JLabel();

        Delete.setBackground(new java.awt.Color(140, 198, 62));
        Delete.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        Delete.setForeground(new java.awt.Color(255, 255, 255));
        Delete.setText("Delete");
        Delete.setOpaque(true);
        Delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeleteActionPerformed(evt);
            }
        });
        jpopup.add(Delete);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jstoretable.setFont(new java.awt.Font("Century Gothic", 0, 17)); // NOI18N
        jstoretable.setForeground(new java.awt.Color(11, 18, 29));
        jstoretable.setModel(new javax.swing.table.DefaultTableModel(
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
        jstoretable.setRowHeight(20);
        jstoretable.setSelectionBackground(new java.awt.Color(11, 18, 29));
        jstoretable.setSelectionForeground(new java.awt.Color(140, 198, 62));
        jstoretable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jstoretableMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jstoretableMouseReleased(evt);
            }
        });
        jScrollPane1.setViewportView(jstoretable);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(357, 143, 500, 330));

        jLabel1.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel1.setText("Name");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 160, 50, -1));

        jPanel1.setBackground(new java.awt.Color(11, 18, 29));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel5.setFont(new java.awt.Font("Century Gothic", 0, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(140, 198, 62));
        jLabel5.setText("Store Frame");

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("X");
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(376, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addGap(281, 281, 281)
                .addComponent(jLabel3)
                .addGap(57, 57, 57))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(16, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel3))
                .addGap(23, 23, 23))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 870, -1));

        jLabel6.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel6.setText("Search");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 90, 50, 30));

        jstoresearch.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jstoresearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jstoresearchKeyReleased(evt);
            }
        });
        getContentPane().add(jstoresearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 90, 200, 30));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jstorename.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jstoreaddress.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        jLabel2.setText("Address");

        addstore.setBackground(new java.awt.Color(140, 198, 62));
        addstore.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        addstore.setForeground(new java.awt.Color(255, 255, 255));
        addstore.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        addstore.setText("Add Shop");
        addstore.setOpaque(true);
        addstore.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                addstoreMouseClicked(evt);
            }
        });

        updatestore.setBackground(new java.awt.Color(140, 198, 62));
        updatestore.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        updatestore.setForeground(new java.awt.Color(255, 255, 255));
        updatestore.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        updatestore.setText("Update Shop");
        updatestore.setOpaque(true);
        updatestore.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                updatestoreMouseClicked(evt);
            }
        });

        resetstore.setBackground(new java.awt.Color(140, 198, 62));
        resetstore.setFont(new java.awt.Font("Century Gothic", 0, 15)); // NOI18N
        resetstore.setForeground(new java.awt.Color(255, 255, 255));
        resetstore.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        resetstore.setText("Reset");
        resetstore.setOpaque(true);
        resetstore.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                resetstoreMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(addstore, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(updatestore, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jstoreaddress, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jstorename, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(resetstore, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(590, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(86, 86, 86)
                .addComponent(jstorename, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jstoreaddress, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(48, 48, 48)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addstore, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(updatestore, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(resetstore, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(154, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void addstoreMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addstoreMouseClicked
        
        if(jstorename.getText().equals("")||jstoreaddress.getText().equals("")){
        
       new MessageForm("UnSuccuss","All Fields Are Required To Fill","error.png").setVisible(true);     
            
        }else{
        String storeName = jstorename.getText();
        String storeAddress = jstoreaddress.getText();
        Timestamp currentTime = new Timestamp(System.currentTimeMillis());
        StoreModel storeModel  = new StoreModel();
        storeModel.setStoreName(storeName);
        storeModel.setStoreAddress(storeAddress);
        storeModel.setCreatedDate(currentTime);
        storeModel.setModifiedDate(currentTime);
        storeModel.setModifiedBy(LoginFrame.employeesModel);
        storeModel.setCreatedBy(LoginFrame.employeesModel);
        
        StoreDaoImpl storeDaoImpl = new StoreDaoImpl();
        int row = storeDaoImpl.insertStoreDetail(storeModel);
        if(row>0){
       new MessageForm("Succuss","Inserted","info.png").setVisible(true);
        fillStoreTable();
        }else{
              new MessageForm("UnSuccess","Not Inserted","error.png").setVisible(true);
    
        }
        }
        
        
    }//GEN-LAST:event_addstoreMouseClicked

    private void updatestoreMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_updatestoreMouseClicked
        if(jstorename.getText().equals("")||jstoreaddress.getText().equals("")){
        
       new MessageForm("UnSuccuss","All Fields Are Required To Fill","error.png").setVisible(true);     
            
        }else{
        int id = Integer.parseInt(jstoretable.getValueAt(jstoretable.getSelectedRow(), 0).toString());
        
        String storeName = jstorename.getText();
        String storeAddress = jstoreaddress.getText();
        Timestamp currentTime = new Timestamp(System.currentTimeMillis());
        StoreModel storeModel  = new StoreModel();
        storeModel.setStoreName(storeName);
        storeModel.setStoreAddress(storeAddress);
        storeModel.setModifiedDate(currentTime);
        storeModel.setModifiedBy(LoginFrame.employeesModel);
        storeModel.setStoreId(id);
        StoreDaoImpl storeDaoImpl = new StoreDaoImpl();
        int row = storeDaoImpl.updateStoreDetail(storeModel);
        if(row>0){
         new MessageForm("Succuss","Updated","info.png").setVisible(true);
       fillStoreTable();
        }else{
         new MessageForm("UnSuccuss","Not Updated","error.png").setVisible(true);
       
        }
        }
    }//GEN-LAST:event_updatestoreMouseClicked

    private void jstoretableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jstoretableMouseClicked
        jstorename.setText(jstoretable.getValueAt(jstoretable.getSelectedRow(), 1).toString());
        jstoreaddress.setText(jstoretable.getValueAt(jstoretable.getSelectedRow(), 2).toString());
        Integer storeId = (Integer) jstoretable.getValueAt(jstoretable.getSelectedRow(), 0);
        if(evt.getClickCount()==2)
        {
            new StoreDetailFrame(storeId).setVisible(true);
        }
    }//GEN-LAST:event_jstoretableMouseClicked

    private void jstoresearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jstoresearchKeyReleased
      String jshopSearch = jstoresearch.getText();
        TableRowSorter tableRowSorter = new TableRowSorter(jstoretable.getModel());
        jstoretable.setRowSorter(tableRowSorter);
        tableRowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + jshopSearch));
    }//GEN-LAST:event_jstoresearchKeyReleased

    private void DeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeleteActionPerformed
      int id = Integer.parseInt(jstoretable.getValueAt(jstoretable.getSelectedRow(),0 ).toString());
        StoreModel storeModel = new StoreModel();
        storeModel.setStoreId(id);
      if(jstoretable.getSelectedRow()>-1){
      int row = new StoreDaoImpl().deleteStoreDetail(storeModel);
      if(row>0){
      fillStoreTable();
         new MessageForm("Success","Deleted","info.png").setVisible(true);
      
      }else{
  new MessageForm("UnSuccess","Not Deleted","error.png").setVisible(true);
          }
      }else{
     new MessageForm("Please Select Row!!!","UnSuccess","error.png").setVisible(true);
          }
      
    }//GEN-LAST:event_DeleteActionPerformed

    private void jstoretableMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jstoretableMouseReleased
       
        if (evt.isPopupTrigger()) {
        if(jstoretable.getSelectedRow()>-1){
            jpopup.show(jstoretable, evt.getX(), evt.getY());
        }
        }
    }//GEN-LAST:event_jstoretableMouseReleased

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
     this.dispose();   // TODO add your handling code here:
    }//GEN-LAST:event_jLabel3MouseClicked

    private void resetstoreMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_resetstoreMouseClicked
        jstorename.setText("");
        jstoreaddress.setText("");
    }//GEN-LAST:event_resetstoreMouseClicked

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
            java.util.logging.Logger.getLogger(StoreFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(StoreFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(StoreFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(StoreFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new StoreFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem Delete;
    private javax.swing.JLabel addstore;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPopupMenu jpopup;
    private javax.swing.JTextField jstoreaddress;
    private javax.swing.JTextField jstorename;
    private javax.swing.JTextField jstoresearch;
    private javax.swing.JTable jstoretable;
    private javax.swing.JLabel resetstore;
    private javax.swing.JLabel updatestore;
    // End of variables declaration//GEN-END:variables

    private void fillStoreTable() {
        StoreDaoImpl storeDaoImpl = new StoreDaoImpl();
        ResultSet resultSet = storeDaoImpl.getAllStores();
        jstoretable.setModel(DbUtils.resultSetToTableModel(resultSet));
        jstoretable.getColumnModel().getColumn(0).setWidth(0);
        jstoretable.getColumnModel().getColumn(0).setMinWidth(0);
        jstoretable.getColumnModel().getColumn(0).setMaxWidth(0);

        DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer();
        headerRenderer.setBackground(new Color(11, 18, 29));
        headerRenderer.setForeground(new Color(140, 198, 62));

        for (int i = 0; i < jstoretable.getModel().getColumnCount(); i++) {
            jstoretable.getColumnModel().getColumn(i).setHeaderRenderer(headerRenderer);
        }
        jstoretable.setShowHorizontalLines(true);
        jstoretable.setShowVerticalLines(true);
        this.getContentPane().setBackground(Color.WHITE);
      //  jScrollPane2.getViewport().setBackground(Color.WHITE);

    }
}
