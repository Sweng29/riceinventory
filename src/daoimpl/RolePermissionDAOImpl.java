/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daoimpl;

import business.LoginFrame;
import connection.DBConnection;
import dao.RolePermissionDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import models.PermissionModel;
import models.RoleModel;
import models.RolePermissionModel;

/**
 *
 * @author Lenovo
 */
public class RolePermissionDAOImpl implements RolePermissionDAO{

    ResultSet resultSet;
    PreparedStatement preparedStatement;
    Connection conn = DBConnection.getInstance();
    String query="";
    
    @Override
    public ResultSet getRolePermissions() {
        ResultSet resultSet = null;
        try {
            String query = "SELECT rp.`role_permission_id` AS 'Role Permission ID',r.`role` AS 'Role',p.`permission` AS 'Permission' \n" +
                            "FROM role_permission rp INNER JOIN role r ON rp.`role_id` = r.`role_id`\n" +
                            "INNER JOIN permission p ON rp.`permission_id` = p.`permission_id`\n" +
                            "WHERE rp.`active` = 1 AND r.`active` = 1;";
            preparedStatement = conn.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultSet;    
    }

    @Override
    public Integer addRolePermission(RolePermissionModel rolePermissionModel) {
        Integer result = 0;
        try {
            String query = "Insert into role_permission (role_id,permission_id,created_by,created_date,modified_by,modified_date) values(?,?,?,?,?,?);";
            preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, rolePermissionModel.getRoleModel().getRole_id());
            preparedStatement.setInt(2, rolePermissionModel.getPermissionModel().getPermissionId());
            preparedStatement.setInt(3, LoginFrame.employeesModel.getEmployeeId());
            preparedStatement.setTimestamp(4, rolePermissionModel.getCreatedDate());
            preparedStatement.setInt(5, LoginFrame.employeesModel.getEmployeeId());
            preparedStatement.setTimestamp(6, rolePermissionModel.getModifiedDate());
            result = preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Integer updateRolePermission(RolePermissionModel rolePermissionModel) {
        Integer result = 0;
        try {
            String query = "update role_permission set role_id =? ,permission_id =? , modified_by =?,modified_date=? where role_permission_id=? and active=1;";
            preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, rolePermissionModel.getRoleModel().getRole_id());
            preparedStatement.setInt(2, rolePermissionModel.getPermissionModel().getPermissionId());
            preparedStatement.setInt(3, LoginFrame.employeesModel.getEmployeeId());
            preparedStatement.setTimestamp(4, rolePermissionModel.getModifiedDate());
            preparedStatement.setInt(5, rolePermissionModel.getRolePermissionId());
            result = preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Integer deleteRolePermissionById(RolePermissionModel rolePermissionModel) {
        Integer result = 0;
        try {
            String query = "delete from role_permission WHERE permission_id=? AND role_id=? AND active=1;";
            preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, rolePermissionModel.getPermissionModel().getPermissionId());
            preparedStatement.setInt(2, rolePermissionModel.getRoleModel().getRole_id());
            result = preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public RolePermissionModel getRolePermissionById(Integer rolePermissionId) {
        RolePermissionModel rolePermissionModel = null;
        try {
            String query = "SELECT rp.`role_permission_id` AS 'Role Permission ID',r.`role_id` AS 'Role ID',\n" +
                            "r.`role` AS 'Role',p.`permission_id` AS 'Permission ID',p.`permission` AS 'Permission' \n" +
                            "FROM role_permission rp INNER JOIN role r ON rp.`role_id` = r.`role_id`\n" +
                            "INNER JOIN permission p ON rp.`permission_id` = p.`permission_id`\n" +
                            "WHERE rp.`role_permission_id` = ? AND rp.`active` = 1 AND r.`active` = 1 AND p.`active`=1;";
            preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, rolePermissionId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                rolePermissionModel.setRolePermissionId(resultSet.getInt("Role Permission ID"));
                RoleModel roleModel = new RoleModel();
                roleModel.setRole(resultSet.getString("Role"));
                roleModel.setRole_id(resultSet.getInt("Role ID"));
                rolePermissionModel.setRoleModel(roleModel);
                PermissionModel permissionModel = new PermissionModel();
                permissionModel.setPermissionId(resultSet.getInt("Permission ID"));
                permissionModel.setPermission(resultSet.getString("Permission"));
                rolePermissionModel.setPermissionModel(permissionModel);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rolePermissionModel;
    }

    @Override
    public RolePermissionModel getRolePermissionByName(String rolePermissionName) {
       RolePermissionModel rolePermissionModel = new RolePermissionModel();
        try {
            String query = "SELECT rp.`role_permission_id` AS 'Role Permission ID',r.`role_id` AS 'Role ID',\n" +
                            "r.`role` AS 'Role',p.`permission_id` AS 'Permission ID',p.`permission` AS 'Permission' \n" +
                            "FROM role_permission rp INNER JOIN role r ON rp.`role_id` = r.`role_id`\n" +
                            "INNER JOIN permission p ON rp.`permission_id` = p.`permission_id`\n" +
                            "WHERE p.`permission` = ? AND rp.`active` = 1 AND r.`active` = 1 AND p.`active` =1;";
            preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, rolePermissionName);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                rolePermissionModel.setRolePermissionId(resultSet.getInt("Role Permission ID"));
                RoleModel roleModel = new RoleModel();
                roleModel.setRole(resultSet.getString("Role"));
                roleModel.setRole_id(resultSet.getInt("Role ID"));
                rolePermissionModel.setRoleModel(roleModel);
                PermissionModel permissionModel = new PermissionModel();
                permissionModel.setPermissionId(resultSet.getInt("Permission ID"));
                permissionModel.setPermission(resultSet.getString("Permission"));
                rolePermissionModel.setPermissionModel(permissionModel);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rolePermissionModel; 
    }

    @Override
    public RolePermissionModel getRolePermissionRecord(Integer rolePermissionId) {
        RolePermissionModel rolePermissionModel = new RolePermissionModel();
        try {
            String query = "SELECT * from role_permission where role_permission_id = ? and active =1";
            preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, rolePermissionId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                rolePermissionModel.setRolePermissionId(resultSet.getInt("role_permission_id"));
                RoleModel roleModel = new RoleModel();
                roleModel.setRole_id(resultSet.getInt("role_id"));
                rolePermissionModel.setRoleModel(roleModel);
                PermissionModel permissionModel = new PermissionModel();
                permissionModel.setPermissionId(resultSet.getInt("permission_id"));
                rolePermissionModel.setPermissionModel(permissionModel);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rolePermissionModel;        
    }

    @Override
    public ResultSet getAssignedPermissions(String roleType) {
        ResultSet resultSet = null;
        try {
            String query = "SELECT rp.`role_permission_id` AS 'Role Permission ID',r.`role_id` AS 'Role ID',\n" +
                            "r.`role` AS 'Role',p.`permission_id` AS 'Permission ID',p.`permission` AS 'Permission' \n" +
                            "FROM role_permission rp INNER JOIN role r ON rp.`role_id` = r.`role_id`\n" +
                            "INNER JOIN permission p ON rp.`permission_id` = p.`permission_id`\n" +
                            "WHERE r.`role`=? AND rp.`active` = 1 AND r.`active` = 1 AND p.`active` =1;";
            preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, roleType);
            resultSet = preparedStatement.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    @Override
    public ResultSet getUnAssignedPermissions(String roleType) {
        ResultSet resultSet = null;
        try {
            String query = "SELECT `permission` FROM permission WHERE permission_id NOT IN\n" +
                            "(SELECT rp.`permission_id` FROM role_permission rp\n" +
                            "INNER JOIN permission p ON p.`permission_id` = rp.`permission_id`\n" +
                            "INNER JOIN role r ON r.`role_id` = rp.`role_id`\n" +
                            "WHERE r.`role` = ? AND r.`active` = 1 AND p.`active` = 1 AND rp.`active` = 1) ";
            preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, roleType);
            resultSet = preparedStatement.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultSet;
    }
}
