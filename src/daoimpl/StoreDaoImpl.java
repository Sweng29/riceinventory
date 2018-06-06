/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daoimpl;

import connection.DBConnection;
import dao.StoreDao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.StoreModel;

/**
 *
 * @author Asif Ali
 */
public class StoreDaoImpl implements StoreDao{
    
    @Override
    public ResultSet getAllStores() {
     ResultSet resultSet = null;
         try {
           
             Connection conn = DBConnection.getInstance();
            String query = "SELECT store_id AS 'Store ID' , name AS 'Store Name' , address AS 'Address' FROM store  WHERE store_id!=1 AND active = 1";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
             resultSet = preparedStatement.executeQuery();
         } catch (Exception ex) {
           System.out.println(ex);
         } 
         
     return resultSet;
    }

    @Override
    public int insertStoreDetail(StoreModel storeModel) {
     int row = 0 ;
        try{
        Connection conn = DBConnection.getInstance();
        String query = "insert into store(name,address,created_by,created_date,modified_by,modified_date) values (?,?,?,?,?,?)";
     PreparedStatement preparedStatement = conn.prepareStatement(query);
     preparedStatement.setString(1, storeModel.getStoreName());
     preparedStatement.setString(2, storeModel.getStoreAddress());
     preparedStatement.setInt(3, storeModel.getCreatedBy().getEmployeeId());
     preparedStatement.setTimestamp(4, storeModel.getCreatedDate());
     preparedStatement.setInt(5, storeModel.getModifiedBy().getEmployeeId());
     preparedStatement.setTimestamp(6, storeModel.getModifiedDate());
    
     row = preparedStatement.executeUpdate();
     }catch(Exception e){
     e.printStackTrace();
     }  
    return row ;
    }

    @Override
    public int updateStoreDetail(StoreModel storeModel) {
     int row = 0 ;
        try{
        Connection conn = DBConnection.getInstance();
        String query = "update store set name = ? , address = ? , modified_by = ? , modified_date = ? where store_id = ?";
     PreparedStatement preparedStatement = conn.prepareStatement(query);
     preparedStatement.setString(1, storeModel.getStoreName());
     preparedStatement.setString(2, storeModel.getStoreAddress());
 
     preparedStatement.setInt(3, storeModel.getModifiedBy().getEmployeeId());
     preparedStatement.setTimestamp(4, storeModel.getModifiedDate());
     preparedStatement.setInt(5, storeModel.getStoreId());
     row = preparedStatement.executeUpdate();
     }catch(Exception e){
     e.printStackTrace();
     }  
    return row ;
   
    }

    @Override
    public int deleteStoreDetail(StoreModel storeModel) {
    int row = 0;    
    try{
      Connection conn = DBConnection.getInstance();
        String query = "update store set active = 0 where store_id = ?";
        PreparedStatement preparedStatement = conn.prepareStatement(query);
        preparedStatement.setInt(1, storeModel.getStoreId());
        row = preparedStatement.executeUpdate();
    }catch(Exception e){
    e.printStackTrace();
    }
    return row;
    }

    @Override
    public StoreModel getStoreByName(String storeName) {
    StoreModel storeModel = null;
    try {
           
            Connection conn = DBConnection.getInstance();
            String query = "SELECT store_id AS 'Store ID' , name AS `Store Name` , address AS Address FROM store  WHERE name=? AND active = 1";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, storeName);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next())
            {
                storeModel = new StoreModel();
                storeModel.setStoreId(resultSet.getInt("Store ID"));
                storeModel.setStoreName(resultSet.getString("Store Name"));
            }
         } catch (Exception ex) {
           ex.printStackTrace();
         } 
     return storeModel;
    }

    @Override
    public StoreModel getStoreById(Integer storeId) {
        StoreModel storeModel = null;
        try {

                Connection conn = DBConnection.getInstance();
                String query = "SELECT store_id AS 'Store ID' , name AS `Store Name` , address AS Address FROM store  WHERE store_id=? AND active = 1";
                PreparedStatement preparedStatement = conn.prepareStatement(query);
                preparedStatement.setInt(1, storeId);
                ResultSet resultSet = preparedStatement.executeQuery();
                while(resultSet.next())
                {
                    storeModel = new StoreModel();
                    storeModel.setStoreId(resultSet.getInt("Store ID"));
                    storeModel.setStoreName(resultSet.getString("Store Name"));
                }
             } catch (Exception ex) {
               ex.printStackTrace();
             } 
         return storeModel;
    }
}
