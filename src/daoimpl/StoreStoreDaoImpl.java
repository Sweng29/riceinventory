/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daoimpl;

import connection.DBConnection;
import dao.StoreStoreDao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Asif Ali
 */
public class StoreStoreDaoImpl implements StoreStoreDao{

    @Override
    public ResultSet getStores() {
    ResultSet resultSet = null;
        Connection conn = DBConnection.getInstance();
    
    String query = "SELECT store_id , NAME as Name, address as Address FROM store WHERE store_id != 1 and active = 1";
    try{
    PreparedStatement preparedStatement = conn.prepareStatement(query);
     resultSet = preparedStatement.executeQuery();
    }catch(Exception e){
    e.printStackTrace();
    }
    return resultSet; 
    }

    @Override
    public ResultSet getProductsOfStore(int storeId) {
     
    ResultSet resultSet = null;
    Connection conn = DBConnection.getInstance();
    String query = "SELECT sd.`store_detail_id` AS id ,sd.store_id AS StoreId,sd.product_id  AS Pid  , p.name AS ProductName , s.name AS StoreName, sd.avail_qty AS Quantity FROM store_detail sd JOIN product p ON(sd.`product_id` = p.`product_id`) JOIN store s ON(s.`store_id` = sd.`store_id`)  WHERE  s.`store_id` = ?";
    try{
    PreparedStatement preparedStatement = conn.prepareStatement(query);
    preparedStatement.setInt(1, storeId);
    resultSet = preparedStatement.executeQuery();
    }catch(Exception e){
    e.printStackTrace();
    }
    return resultSet;
    }
    public int getStoreQuantity( int storeId , int productId){
    int quantity = 0;
    ResultSet resultSet = null;
    Connection conn = DBConnection.getInstance();
    String query = "SELECT avail_qty FROM store_detail WHERE store_id = ? AND product_id = ?";
    try{
    PreparedStatement preparedStatement = conn.prepareStatement(query);
    preparedStatement.setInt(1, storeId);
    preparedStatement.setInt(2, productId);
    resultSet = preparedStatement.executeQuery();
    while(resultSet.next()){
    quantity = resultSet.getInt("avail_qty");
    }
    }catch(Exception e){
    e.printStackTrace();
    }      
    return quantity;
    }
    
}
