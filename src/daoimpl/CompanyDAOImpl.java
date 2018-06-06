/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daoimpl;

import business.LoginFrame;
import connection.DBConnection;
import dao.CompanyDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import models.CompanyModel;
import models.EmployeesModel;

/**
 *
 * @author Lenovo
 */
public class CompanyDAOImpl implements CompanyDAO{

    boolean condition;
    ResultSet resultSet;
    PreparedStatement preparedStatement;
    
    String query ="";
    
    @Override
    public boolean addCompany(CompanyModel companyModel) {
        condition = false;
        try{
            Connection conn = DBConnection.getInstance();
            query = "INSERT INTO company(`name`,`contact_person`,`city`,`address`,`created_by`,`created_date`,`modified_by`,`modified_date`)\n" +
                    " VALUES (?,?,?,?,?,?,?,?);";
            preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, companyModel.getCompanyName());
            preparedStatement.setString(2, companyModel.getContactPerson());
            preparedStatement.setString(3, companyModel.getCity());
            preparedStatement.setString(4, companyModel.getAddress());
            preparedStatement.setInt(5, LoginFrame.employeesModel.getEmployeeId());
            preparedStatement.setTimestamp(6, companyModel.getCreatedDate());
            preparedStatement.setInt(7, LoginFrame.employeesModel.getEmployeeId());
            preparedStatement.setTimestamp(8, companyModel.getModifiedDate());
            condition = preparedStatement.execute();
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        return condition;
    }

    @Override
    public boolean updateCompany(CompanyModel companyModel) {
        condition = false;
        try{
            Connection conn = DBConnection.getInstance();
            query = "update company set `name`=?,`contact_person`=?,`city`=?,`address`=?,`modified_by`=?,`modified_date`=? where `company_id`=? AND `active`=1";
            preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, companyModel.getCompanyName());
            preparedStatement.setString(2, companyModel.getContactPerson());
            preparedStatement.setString(3, companyModel.getCity());
            preparedStatement.setString(4, companyModel.getAddress());
            preparedStatement.setInt(5, LoginFrame.employeesModel.getEmployeeId());
            preparedStatement.setTimestamp(6, companyModel.getModifiedDate());
            preparedStatement.setInt(7, companyModel.getCompanyId());
            condition = preparedStatement.execute();
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        return condition;    }

    @Override
    public boolean deleteCompanyById(CompanyModel companyModel) {
       condition = false; 
       try{
           Connection conn = DBConnection.getInstance();
           query = "update company set `active`=0,modified_by=?,modified_date=? where `company_id`=? and `active`=1";
           preparedStatement = conn.prepareStatement(query);
           preparedStatement.setInt(1, LoginFrame.employeesModel.getEmployeeId());
           preparedStatement.setTimestamp(2,companyModel.getModifiedDate());
           preparedStatement.setInt(3, companyModel.getCompanyId());
           condition = preparedStatement.execute();
       }catch(Exception e)
       {
           e.printStackTrace();
       }
       return condition;
    }

    @Override
    public CompanyModel getCompanyById(Integer companyId) {
        CompanyModel companyModel = null;
        try{
            Connection conn = DBConnection.getInstance();
            query = "SELECT c.`company_id` AS 'Company ID',c.`name` AS 'Company Name',c.`contact_person` AS 'Contact Person',c.`city` AS 'City',\n" +
                    " c.`address` AS 'Address',e.`name` AS 'Created By',e.`emp_id` AS 'Created By ID',c.`created_date` AS 'Created Date',\n" +
                    " em.`name` AS 'Modified By',em.`emp_id` AS 'Modified By ID',c.`modified_date` AS 'Modified Date'\n" +
                    " FROM company c INNER JOIN employees e ON e.`emp_id` = c.`created_by` \n" +
                    " INNER JOIN employees em ON em.`emp_id` = c.`modified_by`\n" +
                    " WHERE c.`company_id`=? AND c.`active` = 1 AND e.`active`=1 AND em.`active`=1";
            preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, companyId);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next())
            {
                companyModel = new CompanyModel();
                EmployeesModel createdByEmployeeModel = new EmployeesModel();
                EmployeesModel modifiedByEmployeeModel = new EmployeesModel();
                companyModel.setCompanyId(resultSet.getInt("Company ID"));
                companyModel.setCompanyName(resultSet.getString("Company name"));
                companyModel.setContactPerson(resultSet.getString("Contact Person"));
                companyModel.setCity(resultSet.getString("City"));
                companyModel.setAddress(resultSet.getString("Address"));
                createdByEmployeeModel.setEmployeeId(resultSet.getInt("Created By ID"));
                createdByEmployeeModel.setName(resultSet.getString("Created By"));
                companyModel.setCreatedBy(createdByEmployeeModel);
                companyModel.setCreatedDate(resultSet.getTimestamp("Created Date"));
                modifiedByEmployeeModel.setEmployeeId(resultSet.getInt("Modified By ID"));
                modifiedByEmployeeModel.setName(resultSet.getString("Modified By"));
                companyModel.setModifiedBy(modifiedByEmployeeModel);
                companyModel.setModifiedDate(resultSet.getTimestamp("Modified Date"));
            }
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        return companyModel;
    }

    @Override
    public ResultSet viewAllCompanies() {
        try{
            Connection conn = DBConnection.getInstance();
            query = "SELECT c.`company_id` AS 'Company ID',c.`name` AS 'Company Name',c.`contact_person` AS 'Contact Person',c.`city` AS 'City',\n" +
                    "c.`address` AS 'Address' FROM company c WHERE c.`active` = 1;";
            preparedStatement = conn.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        return resultSet;
    }

    public CompanyModel getCompanyByName(String companyName) {
        CompanyModel companyModel = null;
        try{
            Connection conn = DBConnection.getInstance();
            query = "SELECT c.`company_id` AS 'Company ID',c.`name` AS 'Company Name',c.`contact_person` AS 'Contact Person',c.`city` AS 'City',\n" +
                    " c.`address` AS 'Address',e.`name` AS 'Created By',e.`emp_id` AS 'Created By ID',c.`created_date` AS 'Created Date',\n" +
                    " em.`name` AS 'Modified By',em.`emp_id` AS 'Modified By ID',c.`modified_date` AS 'Modified Date'\n" +
                    " FROM company c INNER JOIN employees e ON e.`emp_id` = c.`created_by` " +
                    " INNER JOIN employees em ON em.`emp_id` = c.`modified_by` " +
                    " WHERE c.`name`=? AND c.`active` = 1 AND e.`active`=1 AND em.`active`=1";
            preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, companyName);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next())
            {
                companyModel = new CompanyModel();
                EmployeesModel createdByEmployeeModel = new EmployeesModel();
                EmployeesModel modifiedByEmployeeModel = new EmployeesModel();
                companyModel.setCompanyId(resultSet.getInt("Company ID"));
                companyModel.setCompanyName(resultSet.getString("Company name"));
                companyModel.setContactPerson(resultSet.getString("Contact Person"));
                companyModel.setCity(resultSet.getString("City"));
                companyModel.setAddress(resultSet.getString("Address"));
                createdByEmployeeModel.setEmployeeId(resultSet.getInt("Created By ID"));
                createdByEmployeeModel.setName(resultSet.getString("Created By"));
                companyModel.setCreatedBy(createdByEmployeeModel);
                companyModel.setCreatedDate(resultSet.getTimestamp("Created Date"));
                modifiedByEmployeeModel.setEmployeeId(resultSet.getInt("Modified By ID"));
                modifiedByEmployeeModel.setName(resultSet.getString("Modified By"));
                companyModel.setModifiedBy(modifiedByEmployeeModel);
                companyModel.setModifiedDate(resultSet.getTimestamp("Modified Date"));
            }
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        return companyModel;
    }
}
