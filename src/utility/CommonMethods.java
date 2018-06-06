/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utility;


import java.awt.Color;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author Intel
 */
public class CommonMethods {
    
    public static void fillCombos(List<String> values , javax.swing.JComboBox comboBox){
        comboBox.removeAllItems();
        for(String value : values){
            comboBox.addItem(value);
        }
    }
    
    public static void fillTables(ResultSet rs , JTable tableName , JScrollPane jScrollPane , JFrame frame){
        DefaultTableModel dtm = new DefaultTableModel() ;
        
        tableName.setDefaultEditor(Object.class, null);

        dtm = (DefaultTableModel) DbUtils.resultSetToTableModel(rs);

        tableName.setModel(dtm);

        tableName.getColumnModel().getColumn(0).setWidth(0);
        tableName.getColumnModel().getColumn(0).setMinWidth(0);
        tableName.getColumnModel().getColumn(0).setMaxWidth(0);

        DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer();
        headerRenderer.setBackground(new Color(11, 18, 29));
        headerRenderer.setForeground(new Color(140, 198, 62));

        for (int i = 0; i < tableName.getModel().getColumnCount(); i++) {
            tableName.getColumnModel().getColumn(i).setHeaderRenderer(headerRenderer);
        }
        tableName.setShowHorizontalLines(true);
        tableName.setShowVerticalLines(true);
        frame.getContentPane().setBackground(Color.WHITE);
        jScrollPane.getViewport().setBackground(Color.WHITE);
    }
    
    public static void searchFromTable(JTable tableName , JTextField textFieldName){
        String nameSearch = textFieldName.getText();
        TableRowSorter tableRowSorter = new TableRowSorter(tableName.getModel());
        tableName.setRowSorter(tableRowSorter);
        tableRowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + nameSearch));
    }
}
