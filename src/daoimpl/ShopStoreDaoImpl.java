package daoimpl;

import business.LoginFrame;
import connection.DBConnection;
import dao.ShopStoreDao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.StoreDetailsModel;

public class ShopStoreDaoImpl implements ShopStoreDao {

    @Override
    public ResultSet getAllProducts() {
        ResultSet resultSet = null;
        Connection conn = DBConnection.getInstance();
        String query = "SELECT product_id AS Id , NAME AS ProductName FROM product WHERE active = 1";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return resultSet;
    }

    @Override
    public ResultSet getAllStores(Integer productId) {
        ResultSet resultSet = null;
        Connection conn = DBConnection.getInstance();
        String query = "SELECT sd.`store_detail_id` AS id ,sd.store_id as StoreId,sd.product_id  as Pid  , p.name AS ProductName , s.name AS StoreName, sd.avail_qty AS Quantity FROM store_detail sd JOIN product p ON(sd.`product_id` = p.`product_id`) JOIN store s ON(s.`store_id` = sd.`store_id`)  WHERE p.`product_id` = ?  AND s.`store_id` != 1";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, productId);
            resultSet = preparedStatement.executeQuery();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    @Override
    public ResultSet getAllShopeDetails() {
        ResultSet resultSet = null;
        Connection conn = DBConnection.getInstance();
        String query = "SELECT sd.`store_detail_id` AS id ,p.product_id , p.name AS ProductName , s.name AS ShopName, sd.avail_qty AS Quantity FROM store_detail sd JOIN product p ON(sd.`product_id` = p.`product_id`) JOIN store s ON(s.`store_id` = sd.`store_id`)  WHERE  s.`store_id` = 1";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultSet;

    }

    public int insertProductsInToShops(StoreDetailsModel storeDetailsModel) {

        int row = 0;
        Connection conn = DBConnection.getInstance();
        String query = "INSERT INTO store_detail(product_id,store_id,avail_qty,created_by,created_date,modified_by,modified_date,active) VALUES (?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, storeDetailsModel.getProductModel().getProductId());
            preparedStatement.setInt(2, 1);
            preparedStatement.setInt(3, storeDetailsModel.getAvailableQuantity());
            preparedStatement.setInt(4, LoginFrame.employeesModel.getEmployeeId());
            preparedStatement.setTimestamp(5, storeDetailsModel.getCreatedDate());
            preparedStatement.setInt(6, LoginFrame.employeesModel.getEmployeeId());
            preparedStatement.setTimestamp(7, storeDetailsModel.getModifiedDate());
            preparedStatement.setInt(8, 1);
            row = preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return row;
    }

    @Override
    public boolean checkProductAvailablity(Integer productId) {
    boolean check = false;
        Connection conn = DBConnection.getInstance();
        String query = "SELECT product_id FROM store_detail WHERE product_id = ? AND store_id = 1";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, productId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                check = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check;   
    }

    @Override
    public int updateExistingProductQuantity(StoreDetailsModel storeDetailsModel) {
        int row = 0;
        Connection conn = DBConnection.getInstance();
        String query = "UPDATE store_detail SET avail_qty = ? , created_by = ? , modified_by = ? , created_date = ? , modified_date = ? WHERE  store_id = ? and product_id = ?";
        try{
        PreparedStatement preparedStatement = conn.prepareStatement(query);
        preparedStatement.setInt(1, storeDetailsModel.getAvailableQuantity());
        preparedStatement.setInt(2, LoginFrame.employeesModel.getEmployeeId());
        preparedStatement.setInt(3, LoginFrame.employeesModel.getEmployeeId());
        preparedStatement.setTimestamp(4, storeDetailsModel.getCreatedDate());
        preparedStatement.setTimestamp(5, storeDetailsModel.getModifiedDate());
        preparedStatement.setInt(6, storeDetailsModel.getStoreModel().getStoreId());
        preparedStatement.setInt(7, storeDetailsModel.getProductModel().getProductId());
         row = preparedStatement.executeUpdate();
        }catch(Exception e){
        e.printStackTrace();
        }
        return row;
    }
public int deleteShopDetail(int productId){
  int row = 0;
  Connection conn = DBConnection.getInstance();
  String query = "DELETE FROM store_detail WHERE product_id = ? and store_id = 1";    
  try{
  
      PreparedStatement preparedStatement = conn.prepareStatement(query);
      preparedStatement.setInt(1, productId);
      row = preparedStatement.executeUpdate();
  }catch(Exception e){
  e.printStackTrace();
  }
  return row;
}
public int updateShopQuantity(StoreDetailsModel storeDetailsModel){
    int row = 0;
        Connection conn = DBConnection.getInstance();
        String query = "UPDATE store_detail SET avail_qty = ? , created_by = ? , modified_by = ? , created_date = ? , modified_date = ? WHERE  store_id = 1 and product_id = ?";
        try{
        PreparedStatement preparedStatement = conn.prepareStatement(query);
        preparedStatement.setInt(1, storeDetailsModel.getAvailableQuantity());
        preparedStatement.setInt(2, LoginFrame.employeesModel.getEmployeeId());
        preparedStatement.setInt(3, LoginFrame.employeesModel.getEmployeeId());
        preparedStatement.setTimestamp(4, storeDetailsModel.getCreatedDate());
        preparedStatement.setTimestamp(5, storeDetailsModel.getModifiedDate());
        preparedStatement.setInt(6, storeDetailsModel.getProductModel().getProductId());
         row = preparedStatement.executeUpdate();
        }catch(Exception e){
        e.printStackTrace();
        }
        return row;
}
  
public ResultSet getAllStoreData(){

    ResultSet resultSet = null;
        Connection conn = DBConnection.getInstance();
        String query = "SELECT sd.`store_detail_id` AS id ,sd.store_id as StoreId,sd.product_id  as Pid  , p.name AS ProductName , s.name AS StoreName, sd.avail_qty AS Quantity FROM store_detail sd JOIN product p ON(sd.`product_id` = p.`product_id`) JOIN store s ON(s.`store_id` = sd.`store_id`)  WHERE  s.`store_id` != 1";
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultSet;
    
   
   } 
public boolean checkProductInStoreAvailablity(Integer productId , Integer storeId){
    
    boolean check = false;
    Connection conn = DBConnection.getInstance();
    String query = "SELECT product_id , store_id FROM store_detail WHERE store_id = ? AND product_id = ?";
    try{
           PreparedStatement preparedStatement = conn.prepareStatement(query);
           preparedStatement.setInt(1, storeId);
           preparedStatement.setInt(2, productId);
           ResultSet resultSet = preparedStatement.executeQuery();
          if(resultSet.next()){
          check = true;
          }
      
    }catch(Exception e){
    e.printStackTrace();
    }
    return check;
}
  
    
}
