/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daoimpl;

import business.MessageForm;
import connection.DBConnection;
import dao.RoleDao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import models.RoleModel;

/**
 *
 * @author Rehan Ali Azeemi
 */
public class RoleDaoImpl implements RoleDao {

    @Override
    public RoleModel getRoleWithRoleId(Integer roleId) {
        RoleModel roleModel = null;
        try {
            PreparedStatement ps = DBConnection.getInstance().prepareStatement("Select * from role where role_id = ? AND active = 1");
            ps.setInt(1, roleId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                roleModel = new RoleModel();
                roleModel.setRole_id(rs.getInt(1));
                roleModel.setRole(rs.getString(2));
            }
        } catch (Exception e) {
            e.printStackTrace();
            new MessageForm("Error", e.toString(), "error.png").setVisible(true);
        }

        return roleModel;
    }

    @Override
    public ResultSet getAllRoles() {
        try {
            PreparedStatement ps = DBConnection.getInstance().prepareStatement("SELECT role_id AS \"RoleId\",`role` AS \"Role\" FROM role WHERE active = 1");
            ResultSet rs = ps.executeQuery();
            return rs;
        } catch (Exception e) {
            e.printStackTrace();
            new MessageForm("Error", e.toString(), "error.png").setVisible(true);
        }

        return null;
    }

    @Override
    public Integer addRole(RoleModel roleModel) {
        try {
            PreparedStatement ps = DBConnection.getInstance().prepareStatement("Insert into role(role,created_by,created_date,modified_by,modified_date) values(?,?,?,?,?)");
            ps.setString(1, roleModel.getRole());
            ps.setInt(2, roleModel.getCreatedBy().getEmployeeId());
            ps.setTimestamp(3, roleModel.getCreatedDate());
            ps.setInt(4, roleModel.getModifiedBy().getEmployeeId());
            ps.setTimestamp(5, roleModel.getModifiedDate());
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            new MessageForm("Error", e.toString(), "error.png").setVisible(true);
        }

        return 0;
    }

    @Override
    public Integer updateRole(RoleModel roleModel) {
        try {
            PreparedStatement ps = DBConnection.getInstance().prepareStatement("update role set role = ?,modified_by = ?,modified_date = ? where role_id = ?");
            ps.setString(1, roleModel.getRole());
            ps.setInt(2, roleModel.getModifiedBy().getEmployeeId());
            ps.setTimestamp(3, roleModel.getModifiedDate());
            ps.setInt(4, roleModel.getRole_id());
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            new MessageForm("Error", e.toString(), "error.png").setVisible(true);
        }

        return 0;
    }

    @Override
    public Integer deleteRole(Integer id) {
         try {
            PreparedStatement ps = DBConnection.getInstance().prepareStatement("update role set active = 0 where role_id = ?");
            ps.setInt(1, id);
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            new MessageForm("Error", e.toString(), "error.png").setVisible(true);
        }

        return 0;
    }

    @Override
    public RoleModel getRoleWithRoleName(String roleName) {
      RoleModel roleModel = null;
        try {
            PreparedStatement ps = DBConnection.getInstance().prepareStatement("Select * from role where role = ? AND active = 1");
            ps.setString(1, roleName);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                roleModel = new RoleModel();
                roleModel.setRole_id(rs.getInt(1));
                roleModel.setRole(rs.getString(2));
            }
        } catch (Exception e) {
            e.printStackTrace();
            new MessageForm("Error", e.toString(), "error.png").setVisible(true);
        }

        return roleModel;
    }
}
