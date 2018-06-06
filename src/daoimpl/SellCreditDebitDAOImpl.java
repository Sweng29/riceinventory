/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daoimpl;

import business.LoginFrame;
import connection.DBConnection;
import dao.SellCreditDebitDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import models.BankModel;
import models.BrokerModel;
import models.CompanyModel;
import models.SellCreditDebitModel;
import models.SellModel;

/**
 *
 * @author Lenovo
 */
public class SellCreditDebitDAOImpl implements SellCreditDebitDAO{

    ResultSet resultSet;
    PreparedStatement preparedStatement;
    Connection conn = DBConnection.getInstance();
    String query="";
    
    @Override
    public Integer addSellCreditDebit(SellCreditDebitModel sellCreditDebitModel) {
    Integer condition = 0;
        try{
            if(sellCreditDebitModel.getBankModel().getBankId()==0)
            {
                query = "INSERT INTO sell_credit_debit(`sell_id`,`amount_paid`,`payment_type`,`date`,`comments`,`created_by`,`created_date`,`modified_by`,`modified_date`)\n" +
                    " VALUES(?,?,?,?,?,?,?,?,?);";
                preparedStatement = conn.prepareStatement(query);
                preparedStatement.setInt(1, sellCreditDebitModel.getSellModel().getSellId());
                preparedStatement.setDouble(2, sellCreditDebitModel.getAmountPaid());
                preparedStatement.setString(3, sellCreditDebitModel.getPaymentType());
                preparedStatement.setTimestamp(4, sellCreditDebitModel.getDate());
                preparedStatement.setString(5, sellCreditDebitModel.getComments());
                preparedStatement.setInt(6, LoginFrame.employeesModel.getEmployeeId());
                preparedStatement.setTimestamp(7, sellCreditDebitModel.getCreatedDate());
                preparedStatement.setInt(8, LoginFrame.employeesModel.getEmployeeId());
                preparedStatement.setTimestamp(9, sellCreditDebitModel.getModifiedDate());                
            }else{
                query = "INSERT INTO sell_credit_debit(`bank_id`,`sell_id`,`amount_paid`,`payment_type`,`date`,`comments`,`created_by`,`created_date`,`modified_by`,`modified_date`)\n" +
                    " VALUES(?,?,?,?,?,?,?,?,?,?);";
                preparedStatement = conn.prepareStatement(query);
                preparedStatement.setInt(1, sellCreditDebitModel.getBankModel().getBankId());
                preparedStatement.setInt(2, sellCreditDebitModel.getSellModel().getSellId());
                preparedStatement.setDouble(3, sellCreditDebitModel.getAmountPaid());
                preparedStatement.setString(4, sellCreditDebitModel.getPaymentType());
                preparedStatement.setTimestamp(5, sellCreditDebitModel.getDate());
                preparedStatement.setString(6, sellCreditDebitModel.getComments());
                preparedStatement.setInt(7, LoginFrame.employeesModel.getEmployeeId());
                preparedStatement.setTimestamp(8, sellCreditDebitModel.getCreatedDate());
                preparedStatement.setInt(9, LoginFrame.employeesModel.getEmployeeId());
                preparedStatement.setTimestamp(10, sellCreditDebitModel.getModifiedDate());
            }
            condition = preparedStatement.executeUpdate();
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        return condition;
    }

    @Override
    public Integer updateSellCreditDebit(SellCreditDebitModel sellCreditDebitModel) {
    Integer condition = 0;
        try{
            query = "UPDATE sell_credit_debit SET `bank_id`=?,`sell_id`=?,`amount_paid`=?,`payment_type`=?,`date`=?,`comments`=?,`modified_by`=?,`modified_date`=? WHERE `sell_credit_debit_id`=?";
            preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, sellCreditDebitModel.getBankModel().getBankId());
            preparedStatement.setInt(2, sellCreditDebitModel.getSellModel().getSellId());
            preparedStatement.setDouble(3, sellCreditDebitModel.getAmountPaid());
            preparedStatement.setString(4, sellCreditDebitModel.getPaymentType());
            preparedStatement.setTimestamp(5, sellCreditDebitModel.getDate());
            preparedStatement.setString(6, sellCreditDebitModel.getComments());
            preparedStatement.setInt(7, LoginFrame.employeesModel.getEmployeeId());
            preparedStatement.setTimestamp(8, sellCreditDebitModel.getModifiedDate());
            preparedStatement.setInt(9, sellCreditDebitModel.getSellCreditDebitId());            
            condition = preparedStatement.executeUpdate();
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        return condition;
    }

    @Override
    public Integer deleteSellCreditDebit(SellCreditDebitModel sellCreditDebitModel) {
    Integer condition = 0;
        try{
            query = "UPDATE sell_credit_debit SET active=0,modified_by=?,modified_date=? WHERE `sell_credit_debit_id`=?";
            preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, LoginFrame.employeesModel.getEmployeeId());
            preparedStatement.setTimestamp(2, sellCreditDebitModel.getModifiedDate());
            preparedStatement.setInt(3, sellCreditDebitModel.getSellCreditDebitId());
            condition = preparedStatement.executeUpdate();
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        return condition;
    }

    @Override
    public SellCreditDebitModel getSellCreditDebitById(Integer sellCreditDebitId) {
        SellCreditDebitModel sellCreditDebitModel = null;
        try{
            query = "SELECT scd.`sell_credit_debit_id` AS 'Sell Credit Debit ID',b.`bank_id` AS 'Bank ID',b.`bank_name` AS 'Bank Name',b.`branch_name` AS 'Branch Name',\n" +
                    "b.`acc_title` AS 'Account Title',b.`acc_no` AS 'Account Number',s.`builty_no` AS 'Builty Number',s.`total_amount` AS 'Total Amount',scd.`amount_paid` AS 'Received Amount',\n" +
                    "scd.`payment_type` AS 'Payment Type',scd.`date` AS 'Payment Date',s.`date` AS 'Sell Date',scd.`comments` AS 'Comments'\n" +
                    " FROM sell_credit_debit scd LEFT JOIN bank b ON scd.`bank_id` = b.`bank_id`\n" +
                    "INNER JOIN sell s ON scd.`sell_id` = s.`sell_id`\n" +
                    "WHERE scd.`sell_credit_debit_id` = ? AND scd.`active` = 1 AND s.`active` = 1;";
            preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, sellCreditDebitId);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next())
            {
                sellCreditDebitModel = new SellCreditDebitModel();
                sellCreditDebitModel.setSellCreditDebitId(resultSet.getInt("Sell Credit Debit ID"));
                sellCreditDebitModel.setAmountPaid(resultSet.getDouble("Received Amount"));
                sellCreditDebitModel.setComments(resultSet.getString("Comments"));
                sellCreditDebitModel.setDate(resultSet.getTimestamp("Payment Date"));
                sellCreditDebitModel.setPaymentType(resultSet.getString("Payment Type"));
                BankModel bankModel = new BankModel();
                bankModel.setBankId(resultSet.getInt("Bank ID"));
                bankModel.setBankName(resultSet.getString("Bank Name"));
                bankModel.setBranchName(resultSet.getString("Branch Name"));
                bankModel.setAccountNo(resultSet.getString("Account Number"));
                bankModel.setAccountTitle(resultSet.getString("Account Title"));
                SellModel sellModel = new SellModel();
                sellModel.setTotalAmount(resultSet.getDouble("Total Amount"));
                sellModel.setBuiltyNo(resultSet.getString("Builty Number"));
                sellModel.setDate(resultSet.getTimestamp("Sell Date"));
                sellCreditDebitModel.setBankModel(bankModel);
                sellCreditDebitModel.setSellModel(sellModel);
            }
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        return sellCreditDebitModel;
    }

    @Override
    public ResultSet viewAllSellCreditDebit() {
    try{
            query = "SELECT scd.`sell_credit_debit_id` AS 'Sell Credit Debit ID',b.`bank_name` AS 'Bank Name',b.`branch_name` AS 'Branch Name',\n" +
                    "b.`acc_no` AS 'Account Number',s.`total_amount` AS 'Total Payment',scd.`amount_paid` AS 'Received Amount',\n" +
                    "scd.`payment_type` AS 'Payment Type',scd.`date` AS 'Payment Date',scd.`comments` AS 'Commnets'\n" +
                    " FROM sell_credit_debit scd INNER JOIN bank b ON scd.`bank_id` = b.`bank_id`\n" +
                    "INNER JOIN sell s ON scd.`sell_id` = s.`sell_id`\n" +
                    "WHERE scd.`active` = 1 AND s.`active` = 1 AND b.`active` = 1;";
            preparedStatement = conn.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        return resultSet;
    }

    @Override
    public ResultSet viewAllSellCreditDebitBySellId(Integer sellId) {
       try{
            query = "SELECT scd.`sell_credit_debit_id` AS 'Sell Credit Debit ID',scd.`bank_id` AS 'Bank ID',\n" +
                    "IFNULL(b.`bank_name`,'Cash') AS 'Bank Name',IFNULL(b.`branch_name`,'-') AS 'Branch Name',\n" +
                    "IFNULL(b.`acc_no`,'-') AS 'Account Number',s.`total_amount` AS 'Total Payment',scd.`amount_paid` AS 'Amount Paid',\n" +
                    "scd.`payment_type` AS 'Payment Type',scd.`date` AS 'Payment Date',scd.`comments` AS 'Commnets'\n" +
                    "FROM sell s INNER JOIN sell_credit_debit scd USING(sell_id) LEFT JOIN bank b ON b.`bank_id` = scd.`bank_id`\n" +
                    "WHERE scd.`sell_id` = ? AND scd.`active` = 1 AND s.`active` = 1 ;";
            preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, sellId);
            resultSet = preparedStatement.executeQuery();
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        return resultSet;
    }

    @Override
    public ResultSet viewAllTotalAmountBySellId(Integer sellId) {
               try{
            query = "SELECT s.`total_amount` AS 'Total Payment',SUM(scd.`amount_paid`) AS 'Received Amount' ,(s.`total_amount`-SUM(scd.`amount_paid`)) AS 'Remaining'\n" +
                    "FROM sell_credit_debit scd LEFT JOIN bank b ON scd.`bank_id` = b.`bank_id`\n" +
                    "INNER JOIN sell s ON scd.`sell_id` = s.`sell_id`\n" +
                    "WHERE scd.`sell_id` = ? AND scd.`active` = 1 AND s.`active` = 1 GROUP BY s.`sell_id`;";
            preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, sellId);
            resultSet = preparedStatement.executeQuery();
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        return resultSet;
    }

    @Override
    public SellCreditDebitModel checkAvailabilty(SellCreditDebitModel sellCreditDebitModelOne) {
        SellCreditDebitModel sellCreditDebitModel = null;
        try{
            query = "SELECT scd.`sell_credit_debit_id` AS 'Sell Credit Debit ID',b.`bank_id` AS 'Bank ID',b.`bank_name` AS 'Bank Name',b.`branch_name` AS 'Branch Name',\n" +
                    "b.`acc_title` AS 'Account Title',b.`acc_no` AS 'Account Number',s.`builty_no` AS 'Builty Number',s.`total_amount` AS 'Total Amount',scd.`amount_paid` AS 'Received Amount',\n" +
                    "scd.`payment_type` AS 'Payment Type',scd.`date` AS 'Payment Date',s.`date` AS 'Sell Date',scd.`comments` AS 'Comments'\n" +
                    " FROM sell_credit_debit scd INNER JOIN bank b ON scd.`bank_id` = b.`bank_id`\n" +
                    "INNER JOIN sell s ON scd.`sell_id` = s.`sell_id`\n" +
                    "WHERE scd.`sell_id` = ? AND scd.`bank_id` = ? AND scd.`active` = 1 AND s.`active` = 1 AND b.`active` = 1;";
            preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, sellCreditDebitModelOne.getSellModel().getSellId());
            preparedStatement.setInt(2, sellCreditDebitModelOne.getBankModel().getBankId());
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next())
            {
                sellCreditDebitModel = new SellCreditDebitModel();
                sellCreditDebitModel.setSellCreditDebitId(resultSet.getInt("Sell Credit Debit ID"));
                sellCreditDebitModel.setAmountPaid(resultSet.getDouble("Received Amount"));
                sellCreditDebitModel.setComments(resultSet.getString("Comments"));
                sellCreditDebitModel.setDate(resultSet.getTimestamp("Payment Date"));
                sellCreditDebitModel.setPaymentType(resultSet.getString("Payment Type"));
                BankModel bankModel = new BankModel();
                bankModel.setBankId(resultSet.getInt("Bank ID"));
                bankModel.setBankName(resultSet.getString("Bank Name"));
                bankModel.setBranchName(resultSet.getString("Branch Name"));
                bankModel.setAccountNo(resultSet.getString("Account Number"));
                bankModel.setAccountTitle(resultSet.getString("Account Title"));
                SellModel sellModel = new SellModel();
                sellModel.setTotalAmount(resultSet.getDouble("Total Amount"));
                sellModel.setBuiltyNo(resultSet.getString("Builty Number"));
                sellModel.setDate(resultSet.getTimestamp("Sell Date"));
                sellCreditDebitModel.setBankModel(bankModel);
                sellCreditDebitModel.setSellModel(sellModel);
            }
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        return sellCreditDebitModel;
    }
    
}
