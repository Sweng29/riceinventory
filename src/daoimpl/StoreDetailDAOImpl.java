/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daoimpl;

import business.LoginFrame;
import connection.DBConnection;
import dao.StoreDetailDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import models.EmployeesModel;
import models.ProductModel;
import models.StoreDetailsModel;
import models.StoreModel;

/**
 *
 * @author Lenovo
 */
public class StoreDetailDAOImpl implements StoreDetailDAO{

    boolean condition;
    ResultSet resultSet;
    PreparedStatement preparedStatement;
    Connection conn = DBConnection.getInstance();
    String query ="";
    
    @Override
    public boolean addStoreDetail(StoreDetailsModel storeDetailsModel) {
        condition = false;
        try{
            query = "INSERT INTO store_detail(`product_id`,`store_id`,`avail_qty`,`created_by`,`created_date`,`modified_by`,`modified_date`) " +
                    " VALUES (?,?,?,?,?,?,?);";
            preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, storeDetailsModel.getProductModel().getProductId());
            preparedStatement.setInt(2, storeDetailsModel.getStoreModel().getStoreId());
            preparedStatement.setInt(3, storeDetailsModel.getAvailableQuantity());
            preparedStatement.setInt(4, LoginFrame.employeesModel.getEmployeeId());
            preparedStatement.setTimestamp(5, storeDetailsModel.getCreatedDate());
            preparedStatement.setInt(6, LoginFrame.employeesModel.getEmployeeId());
            preparedStatement.setTimestamp(7, storeDetailsModel.getModifiedDate());
            condition = preparedStatement.execute();
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        return condition;
    }

    @Override
    public boolean updateStoreDetail(StoreDetailsModel storeDetailsModel) {
        condition = false;
        try{
            query = "update store_detail set `product_id` = ?,`store_id`=?,`avail_qty`=?,`modified_by`=?,`modified_date`=? where `store_detail_id`=? AND `store_detail_id`=? and active=1";
            preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, storeDetailsModel.getProductModel().getProductId());
            preparedStatement.setInt(2, storeDetailsModel.getStoreModel().getStoreId());
            preparedStatement.setInt(3, storeDetailsModel.getAvailableQuantity());
            preparedStatement.setInt(4, LoginFrame.employeesModel.getEmployeeId());
            preparedStatement.setTimestamp(5, storeDetailsModel.getModifiedDate());
            preparedStatement.setInt(6, storeDetailsModel.getStoreDetailId());
            condition = preparedStatement.execute();
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        return condition;
    }

    @Override
    public boolean deleteStoreDetailById(StoreDetailsModel storeDetailsModel) {
       condition = false; 
       try{
           query = "update store_detail set `active`=0,modified_by=?,modified_date=? where `store_detail_id`=?";
           preparedStatement = conn.prepareStatement(query);
           preparedStatement.setInt(1, LoginFrame.employeesModel.getEmployeeId());
           preparedStatement.setTimestamp(2, storeDetailsModel.getModifiedDate());
           preparedStatement.setInt(3, storeDetailsModel.getStoreDetailId());
           condition = preparedStatement.execute();
       }catch(Exception e)
       {
           e.printStackTrace();
       }
       return condition;
    }

    @Override
    public StoreDetailsModel getStoreDetailById(Integer storeDetailId) {
        StoreDetailsModel storeDetailsModel = null;
        try{
            query = "SELECT sd.`store_detail_id` AS 'Store Detail ID',p.`name` AS 'Product Name',s.`name` AS 'Store Name',sd.`avail_qty` AS 'Available Quaintity' " +
                    "FROM store_detail sd INNER JOIN product p ON sd.`product_id` = p.`product_id` " +
                    "INNER JOIN store s ON sd.`store_id` = s.`store_id` " +
                    "WHERE sd.`store_detail_id` = ? AND sd.`active` = 1 AND s.`active` = 1 AND p.`active` = 1;";
            preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, storeDetailId);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next())
            {
                storeDetailsModel = new StoreDetailsModel();
                StoreModel storeModel = new StoreModel();
                ProductModel productModel = new ProductModel();
                storeDetailsModel.setStoreDetailId(resultSet.getInt("Store Detail ID"));
                productModel.setProductName(resultSet.getString("Product Name"));
                storeModel.setStoreName(resultSet.getString("Store Name"));
                storeDetailsModel.setProductModel(productModel);
                storeDetailsModel.setStoreModel(storeModel);
                storeDetailsModel.setAvailableQuantity(resultSet.getInt("Available Quaintity"));               
            }
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        return storeDetailsModel;
    }

    @Override
    public ResultSet viewAllStoreDetails() {
    try{
            query = "SELECT sd.`store_detail_id` AS 'Store Detail ID',p.`name` AS 'Product Name',s.`name` AS 'Store Name',sd.`avail_qty` AS 'Available Quaintity' " +
                    "FROM store_detail sd INNER JOIN product p ON sd.`product_id` = p.`product_id` " +
                    "INNER JOIN store s ON sd.`store_id` = s.`store_id` " +
                    "WHERE  sd.`active` = 1 AND s.`active` = 1 AND p.`active` = 1;";
            
            preparedStatement = conn.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        return resultSet;
    }

    @Override
    public StoreDetailsModel checkProductStoreAvailabilty(StoreDetailsModel storeDetailsModelOne) {
        StoreDetailsModel storeDetailsModel = null;
        try{
            query = "SELECT sd.`store_detail_id` AS 'Store Detail ID',p.`name` AS 'Product Name',s.`name` AS 'Store Name',sd.`avail_qty` AS 'Available Quaintity' " +
                    "FROM store_detail " +
                    "sd INNER JOIN product p ON sd.`product_id` = p.`product_id` " +
                    "INNER JOIN store s ON sd.`store_id` = s.`store_id` " +
                    "WHERE sd.`product_id` = ? AND sd.`store_id` = ? AND sd.`active` = 1 AND s.`active` = 1 AND p.`active` = 1;";
            preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, storeDetailsModelOne.getProductModel().getProductId());
            preparedStatement.setInt(2, storeDetailsModelOne.getStoreModel().getStoreId());
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next())
            {
                storeDetailsModel = new StoreDetailsModel();
                StoreModel storeModel = new StoreModel();
                ProductModel productModel = new ProductModel();
                storeDetailsModel.setStoreDetailId(resultSet.getInt("Store Detail ID"));
                productModel.setProductName(resultSet.getString("Product Name"));
                storeModel.setStoreName(resultSet.getString("Store Name"));
                storeDetailsModel.setProductModel(productModel);
                storeDetailsModel.setStoreModel(storeModel);
                storeDetailsModel.setAvailableQuantity(resultSet.getInt("Available Quaintity"));               
            }
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        return storeDetailsModel;
    }

    @Override
    public boolean updateAvailableQuainty(StoreDetailsModel storeDetailsModel) {
        condition = false;
        try{
            query = "update store_detail set `avail_qty`=?,`modified_by`=?,`modified_date`=? where `product_id` = ? AND`store_id`=? AND active=1";
            preparedStatement = conn.prepareStatement(query);
            System.out.println("Adding this much qty to store detail : "+storeDetailsModel.getAvailableQuantity()+" P:ID "+storeDetailsModel.getProductModel().getProductId()+" S:ID "+storeDetailsModel.getStoreModel().getStoreId());
            preparedStatement.setInt(1, storeDetailsModel.getAvailableQuantity());
            preparedStatement.setInt(2, LoginFrame.employeesModel.getEmployeeId());
            preparedStatement.setTimestamp(3, storeDetailsModel.getModifiedDate());
            preparedStatement.setInt(4, storeDetailsModel.getProductModel().getProductId());
            preparedStatement.setInt(5, storeDetailsModel.getStoreModel().getStoreId());
            condition = preparedStatement.execute();
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        return condition;
    }

    @Override
    public ResultSet viewStoreDetailById(Integer storeId) {
    try{
            query = "SELECT sd.`store_detail_id` AS 'Store Detail ID',p.`name` AS 'Product Name',s.`name` AS 'Store Name',sd.`avail_qty` AS 'Available Quaintity' " +
                    "FROM store_detail sd INNER JOIN product p ON sd.`product_id` = p.`product_id` " +
                    "INNER JOIN store s ON sd.`store_id` = s.`store_id` " +
                    "WHERE sd.`store_id`=? AND sd.`active` = 1 AND s.`active` = 1 AND p.`active` = 1;";
            
            preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, storeId);
            resultSet = preparedStatement.executeQuery();
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        return resultSet;
    }
    
    
    public ResultSet viewStoreDetailByProductId(Integer productId) {
    try{
            query = "SELECT sd.`store_detail_id` as 'Store Detail ID',p.`name` as 'Product Name',s.`name` as 'Store Name' ,sd.`avail_qty`as 'Available Quantity' \n " +
                    "FROM store_detail sd INNER JOIN product p ON sd.`product_id` = p.`product_id` \n " +
                    "INNER JOIN store s ON sd.`store_id` = s.`store_id` WHERE p.product_id = ? AND s.`store_id`!=1;";            
            preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, productId);
            resultSet = preparedStatement.executeQuery();
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        return resultSet;
    }
}
