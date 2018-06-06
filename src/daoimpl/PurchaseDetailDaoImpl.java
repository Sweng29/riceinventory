/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daoimpl;
import connection.DBConnection;
import dao.PurchaseDetailDao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.PurchaseDetailsModel;
/**
 *
 * @author Intel
 */
public class PurchaseDetailDaoImpl implements PurchaseDetailDao{
    private Connection con = DBConnection.getInstance();
    private PreparedStatement pst;
    private ResultSet rs;
    
    @Override
    public ResultSet getAllPurchaseDetailByPurchaseId(int id) {
        try {
            pst = con.prepareStatement("select purchase_detail_id , p.name as 'Product' , \n" +
"s.name as 'Store/Shop' , quantity as 'Quantity' , \n" +
"round(price,2) as 'Unit Price' , round((quantity*price),2) as 'Total Price'\n" +
",payment_cond_note as 'Note'  from purchase_detail pd inner join product p using(product_id) \n" +
" inner join store s using(store_id) where pd.purchase_id = ? and pd.active = 1;");
            pst.setInt(1 , id);
            rs  = pst.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(PurchaseDetailDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return rs;
    }

    @Override
    public int addDetail(PurchaseDetailsModel purchaseDetailModel) {
        try {
            pst = con.prepareStatement("insert into purchase_detail "
                    + "(purchase_id , product_id , store_id , quantity , "
                    + "price , created_by , created_date) \n" +
" select ? , p.product_id , s.store_id , ? , ? , e.emp_id , now() "
                    + "from product p join employees e join store s \n" +
" where p.name = ? and s.name = ? and e.name = ?; ");
            
            pst.setInt(1, purchaseDetailModel.getPurchaseModel().getPurchaseId());
            pst.setInt(2, purchaseDetailModel.getQuantity());
            pst.setDouble(3, purchaseDetailModel.getPrice());
            pst.setString(4, purchaseDetailModel.getProductModel().getProductName());
            pst.setString(5 , purchaseDetailModel.getStoreModel().getStoreName());
            pst.setString(6, purchaseDetailModel.getCreatedBy().getName());
            
            return pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(PurchaseDetailDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return 0;
    }

    @Override
    public int updateDetail(PurchaseDetailsModel purchaseDetailModel) {
        try {
            pst = con.prepareStatement(" update purchase_detail pd "
                    + "join product p join employees e join store s set \n" +
"  pd.product_id = p.product_id, pd.store_id = s.store_id, pd.quantity=? , \n" +
"  pd.price =?, pd.modified_by = e.emp_id , pd.modified_date = now() \n" +
"  where p.name = ? and s.name = ? and e.name = ? and pd.purchase_detail_id = ?;");
            
            pst.setInt(1, purchaseDetailModel.getQuantity());
            pst.setDouble(2, purchaseDetailModel.getPrice());
            pst.setString(3, purchaseDetailModel.getProductModel().getProductName());
            pst.setString(4 , purchaseDetailModel.getStoreModel().getStoreName());
            pst.setString(5, purchaseDetailModel.getModifiedBy().getName());            
            pst.setInt(6, purchaseDetailModel.getPurchaseId());
            return pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(PurchaseDetailDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return 0;
    }

    @Override
    public int deleteDetail(int id) {
        try {
            pst = con.prepareStatement("update purchase_detail set active = 0 "
                    + "where purchase_detail_id = ?");
            pst.setInt(1, id);
            return pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(PurchaseDetailDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    @Override
    public List<String> getProductsForCombo() {
        List<String> products = new ArrayList();
        try {
            pst = con.prepareStatement("select name from product where active = 1");
            rs =  pst.executeQuery();
            while(rs.next()){
                products.add(rs.getString("name"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(PurchaseDetailDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return products;
    }

    @Override
    public List<String> getStoresForCombos() {
        List<String> stores = new ArrayList();
        try {
            pst = con.prepareStatement("select name from store where active = 1");
            rs =  pst.executeQuery();
            while(rs.next()){
                stores.add(rs.getString("name"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(PurchaseDetailDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return stores;
    }
    public int updateTotalAmountOfPurchase(int purchaseId){
        try {
            pst = con.prepareStatement("    update purchase "
                    + "set total_amount = (Select sum(price*quantity) "
                    + "from purchase_detail where active = 1 and purchase_id = ?) "
                    + "where purchase_id = ?;");
            pst.setInt(1 , purchaseId);
            pst.setInt(2 , purchaseId);
             return pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(PurchaseDetailDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
}
