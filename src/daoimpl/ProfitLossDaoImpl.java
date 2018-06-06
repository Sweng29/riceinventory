/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daoimpl;

import connection.DBConnection;
import dao.ProfitLossDao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Intel
 */
public class ProfitLossDaoImpl implements ProfitLossDao{
    private Connection con = DBConnection.getInstance();
    private PreparedStatement pst;
    private ResultSet rs;
    @Override
    public ResultSet getPurchase(Timestamp startDate , Timestamp endDate) {
        try {
            pst = con.prepareStatement("select 1, ifnull(b.bank_name , 'Cash') as Bank , ifnull(b.acc_no ,'-------') as Account,  \n" +
"round(sum(amount_paid),2) as 'Paid Amount(Debit)'  \n" +
"from purchase_credit_debit pcd left join bank b using(bank_id) \n" +
"where pcd.active=1 and pcd.date between ? and ? group by bank_id;");
            pst.setTimestamp(1, startDate);
            pst.setTimestamp(2, endDate);
            rs = pst.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(ProfitLossDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }

    @Override
    public ResultSet getSell(Timestamp startDate , Timestamp endDate) {
        try {
            pst = con.prepareStatement("select 1, ifnull(b.bank_name , 'Cash') as Bank , ifnull(b.acc_no ,'-------') as Account,  \n" +
"round(sum(amount_paid),2) as 'Receive Amount(Credit)' from sell_credit_debit scd \n" +
"left join bank b using(bank_id) \n" +
"where scd.active=1 and scd.date between ? and ? group by bank_id;");
            pst.setTimestamp(1, startDate);
            pst.setTimestamp(2, endDate);
            rs = pst.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(ProfitLossDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }

    @Override
    public ResultSet getPurchaseSumary(Timestamp startDate, Timestamp endDate) {
        try {
            pst = con.prepareStatement("SELECT 'Purchase' AS 'Purchase',\n" +
"ROUND(IFNULL(SUM(p.total_amount),0),2) AS 'Grand Total' ,\n" +
"ROUND(IFNULL(SUM(amount_paid),0),2) AS 'PAID AMOUNT' , \n" +
"ROUND(IFNULL(SUM(p.total_amount),0),2)-ROUND(IFNULL(SUM(amount_paid),0),2) \n" +
"AS 'Due Amount' FROM purchase p LEFT JOIN \n" +
"purchase_credit_debit pcd ON p.purchase_id = pcd.`purchase_id` \n" +
"AND pcd.`active`=1\n" +
"WHERE  p.active=1 \n" +
"AND p.date BETWEEN ? AND ?;");
            pst.setTimestamp(1, startDate);
            pst.setTimestamp(2, endDate);
            rs = pst.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(ProfitLossDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }

    @Override
    public ResultSet getSellSumary(Timestamp startDate, Timestamp endDate) {
        try {
            pst = con.prepareStatement("SELECT 'Sell' AS 'Sell',\n" +
"ROUND(IFNULL(SUM(s.total_amount),0),2) AS 'Grand Total' ,\n" +
"ROUND(IFNULL(SUM(amount_paid),0),2) AS 'Received Amount' , \n" +
"ROUND(IFNULL(SUM(s.total_amount),0),2)-ROUND(IFNULL(SUM(amount_paid),0),2) \n" +
"AS 'Due Amount' FROM sell s LEFT JOIN \n" +
"sell_credit_debit scd ON s.sell_id = scd.`sell_id` \n" +
"AND scd.`active`=1\n" +
"WHERE  s.active=1 \n" +
"AND s.date BETWEEN ? AND ?;");
            pst.setTimestamp(1, startDate);
            pst.setTimestamp(2, endDate);
            rs = pst.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(ProfitLossDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }

    @Override
    public ResultSet getPurchaseExpense(Timestamp startDate, Timestamp endDate) {
        try {
            pst = con.prepareStatement(""
                    + "SELECT 1, IFNULL(b.bank_name , 'Cash')  AS 'Bank', \n" +
"IFNULL(b.`acc_no` , '---') AS 'Account No' , \n" +
"IFNULL(SUM(transport_expense),0) AS 'Transport Expense', \n" +
"IFNULL(SUM(labour_expense),0) AS 'Labour Expense', \n" +
"IFNULL(SUM(other) , 0) AS 'Other Expense' , \n" +
"IFNULL(SUM(broker_paid_amount),0) AS 'Paid To Broker' \n" +
"FROM purchase_sell_expense pse LEFT JOIN bank b USING(bank_id) \n" +
"INNER JOIN purchase p USING(purchase_id)\n" +
"WHERE pse.`active`=1 AND pse.`purchase_id` IS NOT NULL \n" +
"AND p.date BETWEEN ? AND ?\n" +
"GROUP BY bank_id");
            pst.setTimestamp(1, startDate);
            pst.setTimestamp(2, endDate);
            rs = pst.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(ProfitLossDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }

    @Override
    public ResultSet getSellExpense(Timestamp startDate, Timestamp endDate) {
        try {
            pst = con.prepareStatement(""
                    + "SELECT 1, IFNULL(b.bank_name , 'Cash')  AS 'Bank', \n" +
"IFNULL(b.`acc_no` , '---') AS 'Account No' , \n" +
"IFNULL(SUM(transport_expense),0) AS 'Transport Expense', \n" +
"IFNULL(SUM(labour_expense),0) AS 'Labour Expense', \n" +
"IFNULL(SUM(other) , 0) AS 'Other Expense' , \n" +
"IFNULL(SUM(broker_paid_amount),0) AS 'Paid To Broker' \n" +
"FROM purchase_sell_expense pse LEFT JOIN bank b USING(bank_id) \n" +
"INNER JOIN sell s USING(sell_id)\n" +
"WHERE pse.`active`=1 AND pse.`sell_id` IS NOT NULL \n" +
"AND s.date BETWEEN ? AND ?\n" +
"GROUP BY bank_id");
            pst.setTimestamp(1, startDate);
            pst.setTimestamp(2, endDate);
            rs = pst.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(ProfitLossDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }
}
