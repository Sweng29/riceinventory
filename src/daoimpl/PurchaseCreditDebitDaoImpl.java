/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daoimpl;

import connection.DBConnection;
import dao.PurchaseCreditDebitDao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.PurchaseCreditDebitModel;

/**
 *
 * @author M.UMAIR
 */
public class PurchaseCreditDebitDaoImpl implements PurchaseCreditDebitDao{

    private Connection con = DBConnection.getInstance();
    private PreparedStatement pst;
    private ResultSet rs;
    
    @Override
    public ResultSet getPurchaseCreditDebitBYPurchaseId(int purchaseId) {
        try {
            pst = con.prepareStatement("SELECT purchase_credit_debit_id ,b.bank_id, "
                    + "b.bank_name AS 'Bank' , ROUND(amount_paid,2) AS 'Paid Amount' "
                    + ",payment_type as 'Payment Via', `date` AS 'Date' , comments AS 'Comments' FROM \n" +
"purchase_credit_debit pcd left JOIN bank b USING(bank_id) WHERE pcd.purchase_id = ? AND pcd.active = 1;");
            pst.setInt(1,purchaseId);
            rs = pst.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(PurchaseCreditDebitDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return rs;
    }

    @Override
    public int addPurchaseCreditDebit(PurchaseCreditDebitModel creditDebitModel) {
        try {
            pst = con.prepareStatement("INSERT INTO purchase_credit_debit (purchase_id , "
                    + "bank_id , "
                    + "amount_paid , `date` , comments , created_by , created_date ,"
                    + " payment_type)\n" +
                       "values( ? , ? , ? , ? ,? , ? , NOW() , ? );");
            pst.setInt(1, creditDebitModel.getPurchaseModel().getPurchaseId());
            if(creditDebitModel.getBankModel()!=null){
                pst.setInt(2, creditDebitModel.getBankModel().getBankId());
            }else{
                pst.setNull(2, 2);
            }
            pst.setDouble(3, creditDebitModel.getAmountPaid());
            pst.setTimestamp(4, creditDebitModel.getDate());
            pst.setString(5, creditDebitModel.getComments());
            
            pst.setInt(6, creditDebitModel.getCreatedBy().getEmployeeId());
            pst.setString(7,creditDebitModel.getPaymentType());
            return pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(PurchaseCreditDebitDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    @Override
    public int updatePurchaseCreditDebit(PurchaseCreditDebitModel creditDebitModel) {
        try {
            pst = con.prepareStatement("UPDATE purchase_credit_debit pcd JOIN  employees e "
                    + "SET pcd.bank_id = ? , pcd.amount_paid = ? ,\n" +
"pcd.date = ? , pcd.comments = ? , pcd.modified_by = e.emp_id , pcd.modified_date = NOW() \n" +
"WHERE  e.name = ? ");
            pst.setInt(1 , creditDebitModel.getBankModel().getBankId());
            pst.setDouble(2 , creditDebitModel.getAmountPaid());
            pst.setTimestamp(3,creditDebitModel.getDate());
            pst.setString(4, creditDebitModel.getComments());
            pst.setString(5,creditDebitModel.getModifiedBy().getUsername());
            
            return pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(PurchaseCreditDebitDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    @Override
    public int deletePurchaseCreditDebit(int pcdId , int empId) {
        try {
            pst = con.prepareStatement("update purchase_credit_debit set active = 0 "
                    + ", modified_by = ? where purchase_credit_debit_id = ?");
            pst.setInt(1, empId);
            pst.setInt(2, pcdId);
            
            return pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(PurchaseCreditDebitDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    @Override
    public ResultSet getSummaryPurchaseCredit(int purchaseId) {
        
        try {
            pst = con.prepareStatement("SELECT 'Purchase' AS 'Purchase',\n" +
"ROUND(IFNULL(p.total_amount,0),2) AS 'TOTAL AMOUNT' ,\n" +
"ROUND(IFNULL(SUM(amount_paid),0),2) AS 'PAID AMOUNT' , \n" +
"ROUND(IFNULL(p.total_amount,0),2)-ROUND(IFNULL(SUM(amount_paid),0),2) \n" +
"AS 'REMAINING' FROM purchase p LEFT JOIN \n" +
"purchase_credit_debit pcd ON p.purchase_id = pcd.`purchase_id` \n" +
"AND pcd.`active`=1\n" +
"WHERE  p.active=1 AND p.purchase_id = ?;");
            pst.setInt(1, purchaseId);
            rs = pst.executeQuery();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return rs;
    }
    @Override
    public int changeBalance(int bankId , double balance){
        try {
            pst = con.prepareStatement("update bank set balance = ? where bank_id = ?");
            pst.setDouble(1, balance);
            pst.setInt(2, bankId);
            return pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(PurchaseCreditDebitDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    
    @Override
    public int changeBalanceForDelete(int bankId , double balance){
        try {
            pst = con.prepareStatement("update bank set balance = balance+? where bank_id = ?");
            pst.setDouble(1, balance);
            pst.setInt(2, bankId);
            return pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(PurchaseCreditDebitDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    
    public static void main(String [] args){
        try {
            PurchaseCreditDebitDaoImpl creditDebitDaoImpl = new PurchaseCreditDebitDaoImpl();
            ResultSet rs = creditDebitDaoImpl.getSummaryPurchaseCredit(1);
            while(rs.next()){
                System.out.println(rs.getDouble(1));
                System.out.println(rs.getDouble(2));
                System.out.println(rs.getDouble(3));
            }
        } catch (SQLException ex) {
            Logger.getLogger(PurchaseCreditDebitDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
