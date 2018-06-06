/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daoimpl;

import business.LoginFrame;
import connection.DBConnection;
import dao.SellDetailDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import models.ProductModel;
import models.SellDetailModel;
import models.SellModel;
import models.StoreModel;

/**
 *
 * @author Lenovo
 */
public class SellDetailDAOImpl implements SellDetailDAO{

    ResultSet resultSet;
    PreparedStatement preparedStatement;
    Connection conn = DBConnection.getInstance();
    String query ="";
        
    @Override
    public Integer addSellDetail(SellDetailModel sellDetailModel) {
        Integer condition = 0;
        try{
            query = "INSERT INTO `sell_detail`(sell_id,product_id,`store_id`,`quantity`,`price`,`created_by`,`created_date`,`modified_by`,`modified_date`) " +
                    " VALUES(?,?,?,?,?,?,?,?,?);";
            preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, sellDetailModel.getSellModel().getSellId());
            preparedStatement.setInt(2, sellDetailModel.getProductModel().getProductId());
            preparedStatement.setInt(3, sellDetailModel.getStoreModel().getStoreId());
            preparedStatement.setInt(4, sellDetailModel.getQuaintity());
            preparedStatement.setDouble(5, sellDetailModel.getPrice());
            preparedStatement.setInt(6, LoginFrame.employeesModel.getEmployeeId());
            preparedStatement.setTimestamp(7, sellDetailModel.getCreatedDate());
            preparedStatement.setInt(8, LoginFrame.employeesModel.getEmployeeId());
            preparedStatement.setTimestamp(9, sellDetailModel.getModifiedDate());
            condition = preparedStatement.executeUpdate();
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        return condition;
    }

    @Override
    public Integer updateSellDetail(SellDetailModel sellDetailModel) {
        Integer condition = 0;
        try{
            query = "UPDATE `sell_detail` SET `sell_id`=?,`product_id`=?,`store_id`=?,`quantity`=?,`price`=?,`modified_by`=?,`modified_date`=? " +
                    " WHERE `sell_detail_id` =? ";
            preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, sellDetailModel.getSellModel().getSellId());
            preparedStatement.setInt(2, sellDetailModel.getProductModel().getProductId());
            preparedStatement.setInt(3, sellDetailModel.getStoreModel().getStoreId());
            preparedStatement.setInt(4, sellDetailModel.getQuaintity());
            preparedStatement.setDouble(5, sellDetailModel.getPrice());
            preparedStatement.setInt(6, LoginFrame.employeesModel.getEmployeeId());
            preparedStatement.setTimestamp(7, sellDetailModel.getModifiedDate());
            preparedStatement.setInt(8, sellDetailModel.getSellDetailId());
            condition = preparedStatement.executeUpdate();
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        return condition;
    }

    @Override
    public Integer deleteSellDetail(SellDetailModel sellDetailModel) {
        Integer condition = 0;
        try{
            query = "UPDATE `sell_detail` SET active=0,`modified_by`=?,`modified_date`=? " +
                    " WHERE `sell_detail_id` =? ";
            preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, LoginFrame.employeesModel.getEmployeeId());
            preparedStatement.setTimestamp(2, sellDetailModel.getModifiedDate());
            preparedStatement.setInt(3, sellDetailModel.getSellDetailId());
            condition = preparedStatement.executeUpdate();
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        return condition;
    }

    @Override
    public SellDetailModel getSellDetailById(Integer sellDetailId) {
        SellDetailModel sellDetailModelResult = null;
        try{
            query = "SELECT sd.`sell_detail_id` AS 'Sell Detail ID',sd.`sell_id` AS 'Sell ID',p.`name` AS 'Product Name', " +
                    "st.`name` AS 'Store Name',sd.`quantity` AS 'Quantity',sd.`price` AS 'Price' " +
                    "FROM `sell_detail` sd INNER JOIN sell s ON sd.`sell_id` = s.`sell_id` " +
                    "INNER JOIN `product` p ON sd.`product_id` = p.`product_id` " +
                    "INNER JOIN store st ON sd.`store_id` = st.`store_id` " +
                    "WHERE sd.`sell_detail_id`=? AND sd.`active`=1 AND s.`active`=1 AND p.`active`=1 AND st.`active`=1;";
            preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, sellDetailId);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next())
            {
                sellDetailModelResult = new SellDetailModel();
                ProductModel productModel = new ProductModel();
                SellModel sellModel = new SellModel();
                StoreModel storeModel = new StoreModel();
                productModel.setProductName(resultSet.getString("Product Name"));
                storeModel.setStoreName(resultSet.getString("Store Name"));
                sellDetailModelResult.setProductModel(productModel);
                sellDetailModelResult.setSellModel(sellModel);
                sellDetailModelResult.setStoreModel(storeModel);
                sellDetailModelResult.setQuaintity(resultSet.getInt("Quantity"));
                sellDetailModelResult.setSellDetailId(resultSet.getInt("Sell Detail ID"));
                sellDetailModelResult.setPrice(resultSet.getDouble("Price"));
            }
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        return sellDetailModelResult;        
    }

    @Override
    public ResultSet viewSellDetails() {
        try{
            query = "SELECT sd.`sell_detail_id` AS 'Sell Detail ID',sd.`sell_id` AS 'Sell ID',p.`name` AS 'Product Name', " +
                    "st.`name` AS 'Store Name',sd.`quantity` AS 'Quantity',sd.`price` AS 'Price' " +
                    "FROM `sell_detail` sd INNER JOIN sell s ON sd.`sell_id` = s.`sell_id` " +
                    "INNER JOIN `product` p ON sd.`product_id` = p.`product_id` " +
                    "INNER JOIN store st ON sd.`store_id` = st.`store_id` " +
                    "WHERE  sd.`active`=1 AND s.`active`=1 AND p.`active`=1 AND st.`active`=1;";
            preparedStatement = conn.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        return resultSet;
    }

    @Override
    public ResultSet getSellDetailBySellId(Integer sellId) {
        try{
            query = "SELECT sd.`sell_detail_id` AS 'Sell Detail ID',sd.`sell_id` AS 'Sell ID',p.`name` AS 'Product Name', " +
                    "st.`name` AS 'Store Name',SUM(sd.`quantity`) AS 'Quantity',sd.`price` AS 'Price' " +
                    "FROM `sell_detail` sd INNER JOIN sell s ON sd.`sell_id` = s.`sell_id` " +
                    "INNER JOIN `product` p ON sd.`product_id` = p.`product_id` " +
                    "INNER JOIN store st ON sd.`store_id` = st.`store_id` " +
                    "WHERE sd.`sell_id` =? AND sd.`quantity`!=0 AND  sd.`active`=1 AND s.`active`=1 AND p.`active`=1 AND st.`active`=1 group by sd.`store_id`,sd.`product_id`;";
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
    public Double getTotalAmountBySellId(Integer sellId) {
      Double totalAmount = 0.0;
      try{
          query = "SELECT SUM(quantity*price) as 'Total Amount' FROM sell_detail WHERE sell_id=? AND active=1 GROUP BY sell_id";
          preparedStatement = conn.prepareStatement(query);
          preparedStatement.setInt(1, sellId);
          resultSet = preparedStatement.executeQuery();
          while(resultSet.next())
          {
              totalAmount = resultSet.getDouble("Total Amount");
          }
      }catch(Exception e)
      {
          e.printStackTrace();
      }
      return totalAmount;
    }

    @Override
    public SellDetailModel checkQuantityAvailabiltyBySellDetailId(SellDetailModel sellDetailModel) {
        SellDetailModel sellDetailModelResult = null;
        try{
            query = "SELECT sd.`sell_detail_id` AS 'Sell Detail ID',sd.`sell_id` AS 'Sell ID',sd.`product_id` as 'Product ID',p.`name` AS 'Product Name', " +
                    " sd.`store_id` as 'Store ID',st.`name` AS 'Store Name',sd.`quantity` AS 'Quantity',sd.`price` AS 'Price' " +
                    "FROM `sell_detail` sd INNER JOIN sell s ON sd.`sell_id` = s.`sell_id` " +
                    "INNER JOIN `product` p ON sd.`product_id` = p.`product_id` " +
                    "INNER JOIN store st ON sd.`store_id` = st.`store_id` " +
                    "WHERE sd.`sell_id`=? AND sd.`product_id`=? AND sd.`store_id`=? AND sd.`active`=1 AND s.`active`=1 AND p.`active`=1 AND st.`active`=1;";
            preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, sellDetailModel.getSellModel().getSellId());
            preparedStatement.setInt(2, sellDetailModel.getProductModel().getProductId());
            preparedStatement.setInt(3, sellDetailModel.getStoreModel().getStoreId());
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next())
            {
                sellDetailModelResult = new SellDetailModel();
                ProductModel productModel = new ProductModel();
                SellModel sellModel = new SellModel();
                sellModel.setSellId(resultSet.getInt("Sell ID"));
                productModel.setProductId(resultSet.getInt("Product ID"));
                StoreModel storeModel = new StoreModel();
                storeModel.setStoreId(resultSet.getInt("Store ID"));
                productModel.setProductName(resultSet.getString("Product Name"));
                storeModel.setStoreName(resultSet.getString("Store Name"));
                sellDetailModelResult.setProductModel(productModel);
                sellDetailModelResult.setSellModel(sellModel);
                sellDetailModelResult.setStoreModel(storeModel);
                sellDetailModelResult.setQuaintity(resultSet.getInt("Quantity"));
                sellDetailModelResult.setSellDetailId(resultSet.getInt("Sell Detail ID"));
                sellDetailModelResult.setPrice(resultSet.getDouble("Price"));
            }
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        return sellDetailModelResult;        
    }
}
