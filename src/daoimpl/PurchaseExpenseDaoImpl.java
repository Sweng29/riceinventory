/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daoimpl;

import business.MessageForm;
import connection.DBConnection;
import dao.PurchaseExpenseDao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import models.PurchaseExpenseModel;
import models.PurchaseModel;

/**
 *
 * @author Rehan Ali Azeemi
 */
public class PurchaseExpenseDaoImpl implements PurchaseExpenseDao{

    public int insertPurchaseExpense(PurchaseExpenseModel purchaseExpenseModel) {
        try{
            PreparedStatement ps = DBConnection.getInstance().prepareStatement("insert into purchase_sell_expense(purchase_id,bank_id,payment_type,transport_expense,labour_expense,other,broker_comission,broker_paid_amount,comments,created_by,created_date)  values(?,?,?,?,?,?,?,?,?,?,now())");
            ps.setInt(1, purchaseExpenseModel.getPurchaseModel().getPurchaseId());
            ps.setInt(2, purchaseExpenseModel.getBank().getBankId());
            ps.setString(3, purchaseExpenseModel.getPaymentType());
            ps.setInt(4, purchaseExpenseModel.getTransportExpense());
            ps.setInt(5, purchaseExpenseModel.getLabourExpense());
            ps.setInt(6, purchaseExpenseModel.getOther());
            ps.setInt(7, purchaseExpenseModel.getBrokerComission());
            ps.setInt(8, purchaseExpenseModel.getBrokerPaidAmount());
            ps.setString(9, purchaseExpenseModel.getComment());
            ps.setInt(10, purchaseExpenseModel.getCreatedBy().getEmployeeId());
           
            int status = ps.executeUpdate();
            
            ps = DBConnection.getInstance().prepareStatement("update bank set balance = balance - ? where bank_id = ? and active = 1");
            ps.setInt(1, purchaseExpenseModel.getTransportExpense()+purchaseExpenseModel.getLabourExpense()+purchaseExpenseModel.getOther()+purchaseExpenseModel.getBrokerPaidAmount());
            ps.setInt(2, purchaseExpenseModel.getBank().getBankId());
            
            int status1 = ps.executeUpdate();
            
            if(status == 1 && status1 == 1){
                return 1;
            }
        }
        catch(Exception e){
            e.printStackTrace();
            new MessageForm("Error", e.toString(), "error.png").setVisible(true);
        }
        
        return 0;
    }

    @Override
    public ResultSet getAllPurchaseExpense(PurchaseModel purchaseModel) {
        try{
            PreparedStatement ps = DBConnection.getInstance().prepareStatement("SELECT p.expense_id, p.purchase_id AS 'Purchase No',IFNULL(b.acc_no,'-') AS 'Acc No',p.payment_type AS 'Payment Type',p.`transport_expense` AS 'Transport',p.`labour_expense` AS 'Labour',p.`other` AS 'Other',p.`broker_comission` AS 'Broker Comm',p.`broker_paid_amount` AS 'Broker Paid',p.`comments` AS 'Comment' FROM `purchase_sell_expense` p LEFT JOIN bank b ON p.`bank_id` = b.`bank_id` WHERE p.`purchase_id` = ? AND p.active = 1");
            ps.setInt(1, purchaseModel.getPurchaseId());
            return ps.executeQuery();
        }
        catch(Exception e){
            e.printStackTrace();
            new MessageForm("Error", e.toString(), "error.png").setVisible(true);
        }
        
        return null;
    }

    @Override
    public int insertPurchaseExpenseWithoutBank(PurchaseExpenseModel purchaseExpenseModel) {
     try{
            PreparedStatement ps = DBConnection.getInstance().prepareStatement("insert into purchase_sell_expense(purchase_id,payment_type,transport_expense,labour_expense,other,broker_comission,broker_paid_amount,comments,created_by,created_date) values(?,?,?,?,?,?,?,?,?,now())");
            ps.setInt(1, purchaseExpenseModel.getPurchaseModel().getPurchaseId());
            ps.setString(2, purchaseExpenseModel.getPaymentType());
            ps.setInt(3, purchaseExpenseModel.getTransportExpense());
            ps.setInt(4, purchaseExpenseModel.getLabourExpense());
            ps.setInt(5, purchaseExpenseModel.getOther());
            ps.setInt(6, purchaseExpenseModel.getBrokerComission());
            ps.setInt(7, purchaseExpenseModel.getBrokerPaidAmount());
            ps.setString(8, purchaseExpenseModel.getComment());
            ps.setInt(9, purchaseExpenseModel.getCreatedBy().getEmployeeId());
            
            return ps.executeUpdate();
            
        }
        catch(Exception e){
            e.printStackTrace();
            new MessageForm("Error", e.toString(), "error.png").setVisible(true);
        }
        
        return 0;
        
    }

    @Override
    public int deletePurchaseExpense(PurchaseExpenseModel purchaseExpenseModel) {
        try{
            PreparedStatement ps = DBConnection.getInstance().prepareStatement("update purchase_sell_expense set active = 0 where expense_id = ?");
            ps.setInt(1, purchaseExpenseModel.getExpenseId());
            
            int status = ps.executeUpdate();
            
            ps = DBConnection.getInstance().prepareStatement("update bank set balance = balance + ? where acc_no = ? and active = 1");
            ps.setInt(1, purchaseExpenseModel.getTransportExpense()+purchaseExpenseModel.getLabourExpense()+purchaseExpenseModel.getOther()+purchaseExpenseModel.getBrokerPaidAmount());
            ps.setString(2, purchaseExpenseModel.getBank().getAccountNo());
            
            int status1 = ps.executeUpdate();
            
            if(status == 1 && status1 == 1){
                return 1;
            }
            
        }
        catch(Exception e){
            e.printStackTrace();
            new MessageForm("Error", e.toString(), "error.png").setVisible(true);
        }
        
        return 0;
    }
    
        public int deletePurchaseExpenseWithoutBank(PurchaseExpenseModel purchaseExpenseModel) {
         try{
            PreparedStatement ps = DBConnection.getInstance().prepareStatement("update purchase_sell_expense set active = 0 where expense_id = ?");
            ps.setInt(1, purchaseExpenseModel.getExpenseId());
            System.out.println(purchaseExpenseModel.getExpenseId());
            return ps.executeUpdate();  
        }
        catch(Exception e){
            e.printStackTrace();
            new MessageForm("Error", e.toString(), "error.png").setVisible(true);
        }
        
        return 0;
    }
    
}
