/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daoimpl;

import connection.DBConnection;
import dao.PurchaseDao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.PurchaseModel;

/**
 *
 * @author Intel
 */
public class PurchaseDaoImpl implements PurchaseDao{
    private Connection con = DBConnection.getInstance();
    private PreparedStatement pst;
    private ResultSet rs;
    @Override
    public List<String> getAllBrokerNamesForCombos() {
        List<String> brokers = new ArrayList();
        try {
            pst = con.prepareStatement("Select name from broker where active = 1;");
            rs = pst.executeQuery();
            while(rs.next()){
                brokers.add(rs.getString("name"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(PurchaseDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return brokers;
    }

    @Override
    public ResultSet getAllPurchases() {
        try {
            pst = con.prepareStatement("select purchase_id , builty_no as 'Builty No' , "
                    + "`date` as 'Date', total_amount as 'Total Amount' , \n" +
                "c.name as 'Company' , b.name as 'Broker' , "
                    + "p.payment_condition_date as 'Payment Date' , p.note \n" +
                "as 'Note' from purchase p "
                    + "inner join company c using(company_id) inner join broker b \n" +
                "using(broker_id) where p.active = 1 order by purchase_id desc");
            rs = pst.executeQuery();
            return rs;
        } catch (SQLException ex) {
            Logger.getLogger(PurchaseDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }

    @Override
    public int insertNewPurchase(PurchaseModel purchaseModel) {
        try {
            pst = con.prepareStatement("insert into purchase(builty_no , date , "
                    + "payment_condition_date , note , \n" +
                    "    company_id , broker_id , created_by , created_date) "
                    + "select ? , ? ,\n" +
                    "    ? , ?, c.company_id , b.broker_id , e.emp_id , now() \n" +
                    "    from company c join broker b join employees e\n" +
                    "    where b.name = ? and c.name = ? and e.name = ?;");
            pst.setString(1, purchaseModel.getBuiltyNo());
            pst.setTimestamp(2, purchaseModel.getPurchaseDate());
            pst.setTimestamp(3,purchaseModel.getPaymentConditionDate());
            pst.setString(4,purchaseModel.getPaymentCondition());
            pst.setString(5, purchaseModel.getBrokerModel().getBrokerName());
            pst.setString(6,purchaseModel.getCompanyModel().getCompanyName());
            pst.setString(7,purchaseModel.getCreatedBy().getName());
            
            return pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(PurchaseDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    @Override
    public int updatePurcahse(PurchaseModel purchaseModel) {
        try{
            pst = con.prepareStatement("update purchase p "
                    + "join company c join broker b join employees e \n" +
                    "    set p.builty_no = ?, p.`date` =? , \n" +
                    "    payment_condition_date = ?, note=? , \n" +
                    "    p.company_id = c.company_id, p.broker_id = b.broker_id, \n" +
                    "    p.modified_by = e.emp_id , p.modified_date = now()\n" +
                    "    where b.name = ? and c.name = ? and e.name = ? \n" +
                    "    and p.purchase_id = ?;");
            pst.setString(1, purchaseModel.getBuiltyNo());
            pst.setTimestamp(2, purchaseModel.getPurchaseDate());
            pst.setTimestamp(3,purchaseModel.getPaymentConditionDate());
            pst.setString(4,purchaseModel.getPaymentCondition());
            pst.setString(5, purchaseModel.getBrokerModel().getBrokerName());
            pst.setString(6,purchaseModel.getCompanyModel().getCompanyName());
            pst.setString(7,purchaseModel.getCreatedBy().getName());
            pst.setInt(8, purchaseModel.getPurchaseId());
            
            return pst.executeUpdate();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        
        return 0;
    }

    @Override
    public int updateTotalAmount(int purchaseId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int deletePurchaseRecord(int purchaseId) {
        try {
            pst =con.prepareStatement("update purchase set active = 0 where purchase_id = ?");
            pst.setInt(1,purchaseId);
            return pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(PurchaseDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    @Override
    public List<String> getAllCompaniesForCombos() {
        List<String> companies = new ArrayList();
        try {
            pst = con.prepareStatement("select name from company where active = 1");
            rs = pst.executeQuery();
            while(rs.next()){
                companies.add(rs.getString("name"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(PurchaseDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return companies;
    }
    
}
