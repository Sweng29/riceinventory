/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daoimpl;

import connection.DBConnection;
import dao.EmployeesDao;
import models.EmployeesModel;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import business.MessageForm;

/**
 *
 * @author Rehan Ali Azeemi
 */
public class EmployeesDaoImpl implements EmployeesDao {

    @Override
    public ResultSet getAllEmployees() {
        try {
            PreparedStatement ps = DBConnection.getInstance().prepareStatement("SELECT e.emp_id AS \"EmployeeID\",e.`name` AS \"Name\",e.contact AS \"Contact\",e.address AS \"Address\",e.username AS \"Username\",r.role AS \"Role\" FROM employees e INNER JOIN role r ON e.`role_id` = r.`role_id` WHERE e.`active` = 1");
            ResultSet rs = ps.executeQuery();
            return rs;
        } catch (Exception e) {
            e.printStackTrace();
            new MessageForm("Error", e.toString(), "error.png").setVisible(true);
        }

        return null;
    }

    @Override
    public EmployeesModel checkLogin(EmployeesModel empModel) {
        EmployeesModel employeesModel = null;
        try {
            PreparedStatement ps = DBConnection.getInstance().prepareStatement("select * from employees where username = ? AND password = ? AND active = ?");
            ps.setString(1, empModel.getUsername());
            ps.setString(2, empModel.getPassword());
            ps.setInt(3, 1);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                employeesModel = new EmployeesModel();
                employeesModel.setEmployeeId(rs.getInt(1));
                employeesModel.setName(rs.getString(2));
                employeesModel.setUsername(rs.getString(5));
                employeesModel.setPassword(rs.getString(6));
                employeesModel.setRoleModel(new RoleDaoImpl().getRoleWithRoleId(rs.getInt(7)));
            }

        } catch (Exception e) {
            e.printStackTrace();
            new MessageForm("Error", e.toString(), "error.png").setVisible(true);
        }
        return employeesModel;
    }

    @Override
    public Integer addEmployee(EmployeesModel employeeModel) {
        try {
            PreparedStatement ps = DBConnection.getInstance().prepareStatement("Insert into employees(name,contact,address,username,password,role_id,created_by,created_date,modified_by,modified_date) Select ?,?,?,?,?,role_id,?,?,?,? from role where role = ?");
            ps.setString(1, employeeModel.getName());
            ps.setString(2, employeeModel.getContact());
            ps.setString(3, employeeModel.getAddress());
            ps.setString(4, employeeModel.getUsername());
            ps.setString(5, employeeModel.getPassword());
            ps.setInt(6, employeeModel.getCreatedBy().getEmployeeId());
            ps.setTimestamp(7, employeeModel.getCreatedDate());
            ps.setInt(8, employeeModel.getModifiedBy().getEmployeeId());
            ps.setTimestamp(9, employeeModel.getModifiedDate());
            ps.setString(10, employeeModel.getRoleModel().getRole());
            return ps.executeUpdate();
            
        } 
        catch (Exception e) {
            e.printStackTrace();
            new MessageForm("Error", e.toString(), "error.png").setVisible(true);
        }
        return 0;
    }

    @Override
    public Integer updateEmployee(EmployeesModel employeeModel) {
        try {
            PreparedStatement ps = DBConnection.getInstance().prepareStatement("Update employees set name = ?,contact = ?,address = ?,username = ?,role_id = (Select role_id from role where role = ?),modified_by = ?,modified_date = ? where emp_id = ?");
            ps.setString(1, employeeModel.getName());
            ps.setString(2, employeeModel.getContact());
            ps.setString(3, employeeModel.getAddress());
            ps.setString(4, employeeModel.getUsername());
            ps.setString(5, employeeModel.getRoleModel().getRole());
            ps.setInt(6, employeeModel.getModifiedBy().getEmployeeId());
            ps.setTimestamp(7, employeeModel.getModifiedDate());
            ps.setInt(8, employeeModel.getEmployeeId());
            
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            new MessageForm("Error", e.toString(), "error.png").setVisible(true);
        }
        return 0;
    }

    @Override
    public Integer deleteEmployee(Integer id) {
      try{
          PreparedStatement ps = DBConnection.getInstance().prepareStatement("Update employees set active = 0 where emp_id = ?");
            ps.setInt(1, id);
            return ps.executeUpdate();
      }catch(Exception e){
          e.printStackTrace();
          new MessageForm("Error", e.toString(), "error.png").setVisible(true);
      }
      return 0;
    }

    @Override
    public Integer deleteEmployee(Integer id, String dependency) {
          try{
          PreparedStatement ps = DBConnection.getInstance().prepareStatement("Update employees set active = 0 where role_id = ?");
            ps.setInt(1, id);
            return ps.executeUpdate();
      }catch(Exception e){
          e.printStackTrace();
          new MessageForm("Error", e.toString(), "error.png").setVisible(true);
      }
      return 0;
    }

    @Override
    public Boolean checkUsernameAvailbility(String username) {
         
        try {
            PreparedStatement ps = DBConnection.getInstance().prepareStatement("select * from employees where username = ? AND active = ?");
            ps.setString(1, username);
            ps.setInt(2, 1);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return true;
            }

        } catch (Exception e) {
            e.printStackTrace();
            new MessageForm("Error", e.toString(), "error.png").setVisible(true);
        }
        return false;
    }
    
}
