/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daoimpl;

import connection.DBConnection;
import dao.ShopDao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.EmployeesModel;
import models.ShopModel;

/**
 *
 * @author Asif Ali
 */
public class ShopDaoImpl implements ShopDao {

    @Override
    public ResultSet getAllShops() {
        ResultSet resultSet = null;
        try {
            Connection conn = DBConnection.getInstance();
            String query = "SELECT shop_id AS Id , NAME AS NAME , shop_qty AS ShopQuantity FROM shop  WHERE active = 1";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
             resultSet = preparedStatement.executeQuery();
        
        } catch(Exception e){
        e.printStackTrace();
        }
         return resultSet;
          
        
    }

    @Override
    public int insertShopData(ShopModel shopModel) {
    int row = 0;
    try{
        Connection conn = DBConnection.getInstance();
        String query = "INSERT INTO shop ( NAME,shop_qty,created_by,created_date,modified_by,modified_date,active) VALUES (?,?,?,?,?,?,?)";
        PreparedStatement preparedStatement = conn.prepareStatement(query);
        preparedStatement.setString(1, shopModel.getName());
        preparedStatement.setString(2, shopModel.getShopQty());
        preparedStatement.setInt(3,shopModel.getCreatedBy().getEmployeeId());
        preparedStatement.setTimestamp(4, shopModel.getCreatedDate());
        preparedStatement.setInt(5, shopModel.getModifiedBy().getEmployeeId());
        preparedStatement.setTimestamp(6, shopModel.getModifiedDate());
        preparedStatement.setInt(7, 1);
        
         row = preparedStatement.executeUpdate();
    }catch(Exception e){
    e.printStackTrace();
    }
    return row;
    }

    @Override
    public int updateShopData(ShopModel shopModel) {
    int row = 0;    
    try{
        Connection conn = DBConnection.getInstance();
        
        String query = "UPDATE shop SET NAME = ? , shop_qty = ? , modified_by = ? , modified_date = ? WHERE shop_id = ?";
        
        PreparedStatement preparedStatement = conn.prepareStatement(query);
        preparedStatement.setString(1, shopModel.getName());
        preparedStatement.setString(2, shopModel.getShopQty());
        preparedStatement.setInt(3, shopModel.getModifiedBy().getEmployeeId());
        preparedStatement.setTimestamp(4, shopModel.getModifiedDate());
        preparedStatement.setInt(5, shopModel.getShopId());
        
        row = preparedStatement.executeUpdate();
    }catch(Exception e){
    e.printStackTrace();
    }
    return row;
    }

    @Override
    public int deleteShopData(ShopModel shopModel) {
    int row = 0;
    try{
        Connection conn = DBConnection.getInstance();
        String query = "update shop set active = 0 where shop_id = ? ";
        PreparedStatement preparedStatement = conn.prepareStatement(query);
        preparedStatement.setInt(1, shopModel.getShopId());
        row = preparedStatement.executeUpdate();
    }catch(Exception e){
    e.printStackTrace();
    }
    return row;
    }
    

    @Override
    public ShopModel getShopModeWithId(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
 
    
    
    
    
}
