/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daoimpl;

import business.LoginFrame;
import connection.DBConnection;
import dao.PermissionDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import models.PermissionModel;

/**
 *
 * @author Lenovo
 */
public class PermissionDAOImpl implements PermissionDAO{
    
    ResultSet resultSet;
    PreparedStatement preparedStatement;
    Connection conn = DBConnection.getInstance();
    String query="";
    
    @Override
    public ResultSet getAllPermissions() {
        ResultSet resultSet = null;
        try{
            query = "SELECT permission_id AS 'Permission ID',permission AS 'Permissions' FROM permission WHERE active=1;";
            Statement statement = conn.createStatement();
            resultSet = statement.executeQuery(query);
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        return resultSet;    
    }

    @Override
    public Integer addPermission(PermissionModel permissionModel) {
        Integer result = 0;
        try{
            query = "INSERT into permission (`permission`,created_by,created_date,modified_by,modified_date) VALUES (?,?,?,?,?);";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, permissionModel.getPermission());
            preparedStatement.setInt(2, LoginFrame.employeesModel.getEmployeeId());
            preparedStatement.setTimestamp(3, permissionModel.getCreatedDate());
            preparedStatement.setInt(4, LoginFrame.employeesModel.getEmployeeId());
            preparedStatement.setTimestamp(5, permissionModel.getModifiedDate());
            result = preparedStatement.executeUpdate();
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Integer deletePermissionById(PermissionModel permissionModel) {
    Integer result = 0;
        try{
            String query = "update permission set active = 0 ,modified_by = ?,modified_date=?  where permission_id = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1,LoginFrame.employeesModel.getEmployeeId());
            preparedStatement.setTimestamp(2, permissionModel.getModifiedDate());
            preparedStatement.setInt(3,permissionModel.getPermissionId());
            result = preparedStatement.executeUpdate();
                    
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Integer updatePermission(PermissionModel permissionModel) {
        Integer result = 0;
        try{
            String query = "update permission set `permission` = ?,modified_by=?,modified_date=? where permission_id = ? AND active = 1";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, permissionModel.getPermission());
            preparedStatement.setInt(2, LoginFrame.employeesModel.getEmployeeId());
            preparedStatement.setTimestamp(3, permissionModel.getModifiedDate());
            preparedStatement.setInt(4, permissionModel.getPermissionId());
            result = preparedStatement.executeUpdate();
                    
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public PermissionModel getPermissionById(Integer permissionId) {
       PermissionModel permissionModel = null;
        try{
            String query = "SELECT p.`permission_id` AS 'Permission ID'," +
            "p.`permission` AS 'Permission' FROM permission p \n" +
            "WHERE p.`permission_id` = ? AND p.`active` = 1;";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, permissionId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next())
            {
               permissionModel = new PermissionModel();
               permissionModel.setPermissionId(resultSet.getInt("Permission ID"));
               permissionModel.setPermission(resultSet.getString("Permission"));
            }
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        return permissionModel; 
    }

    @Override
    public PermissionModel getPermissionByName(String permissionName) {
       PermissionModel permissionModel = null;
        try{
            String query = "SELECT p.`permission_id` AS 'Permission ID',\n" +
            "p.`permission` AS 'Permission' FROM permission p \n" +
            "WHERE p.`permission` = ? AND p.`active` = 1;";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, permissionName);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next())
            {
               permissionModel = new PermissionModel();
               permissionModel.setPermissionId(resultSet.getInt("Permission ID"));
               permissionModel.setPermission(resultSet.getString("Permission"));
            }
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        return permissionModel; 
    }
}
