/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import models.EmployeesModel;
import java.sql.ResultSet;
/**
 *
 * @author Rehan Ali Azeemi
 */
public interface EmployeesDao {
    public EmployeesModel checkLogin(EmployeesModel empModel);
    public ResultSet getAllEmployees();
    public Integer addEmployee(EmployeesModel employeeModel);
    public Integer updateEmployee(EmployeesModel employeeModel);
    public Integer deleteEmployee(Integer id);
    public Integer deleteEmployee(Integer id,String dependency);
    public Boolean checkUsernameAvailbility(String username);
}
