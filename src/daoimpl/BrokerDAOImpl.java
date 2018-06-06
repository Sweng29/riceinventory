/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daoimpl;

import business.LoginFrame;
import connection.DBConnection;
import dao.BrokerDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import models.BrokerModel;
import models.EmployeesModel;

/**
 *
 * @author Lenovo
 */
public class BrokerDAOImpl implements BrokerDAO{

    boolean condition;
    ResultSet resultSet;
    PreparedStatement preparedStatement;
    Connection conn = DBConnection.getInstance();
    String query ="";
    
    @Override
    public boolean addBroker(BrokerModel brokerModel) {
        condition = false;
        try{
            query = "INSERT INTO broker(`name`,`contact`,`broker_type`,`created_by`,`created_date`,`modified_by`,`modified_date`)\n " +
                    " VALUES (?,?,?,?,?,?,?);";
            preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, brokerModel.getBrokerName());
            preparedStatement.setString(2, brokerModel.getBrokerContact());
            preparedStatement.setString(3, brokerModel.getBrokerType());
            preparedStatement.setInt(4, LoginFrame.employeesModel.getEmployeeId());
            preparedStatement.setTimestamp(5, brokerModel.getCreatedDate());
            preparedStatement.setInt(6, LoginFrame.employeesModel.getEmployeeId());
            preparedStatement.setTimestamp(7, brokerModel.getModifiedDate());
            condition = preparedStatement.execute();
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        return condition;
    }

    @Override
    public boolean updateBroker(BrokerModel brokerModel) {
        condition = false;
        try{
            query = "update broker set `name` = ?,`contact`=?,`broker_type`=?,`modified_by`=?,`modified_date`=? where `broker_id`=? and active=1";
            preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, brokerModel.getBrokerName());
            preparedStatement.setString(2, brokerModel.getBrokerContact());
            preparedStatement.setString(3, brokerModel.getBrokerType());
            preparedStatement.setInt(4, LoginFrame.employeesModel.getEmployeeId());
            preparedStatement.setTimestamp(5, brokerModel.getModifiedDate());
            preparedStatement.setInt(6, brokerModel.getBrokerId());
            condition = preparedStatement.execute();
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        return condition;
    }

    @Override
    public boolean deleteBrokerById(BrokerModel brokerModel) {
       condition = false; 
       try{
           query = "update broker set `active`=0,modified_by=?,modified_date=? where `broker_id`=?";
           preparedStatement = conn.prepareStatement(query);
           preparedStatement.setInt(1, LoginFrame.employeesModel.getEmployeeId());
           preparedStatement.setTimestamp(2, brokerModel.getModifiedDate());
           preparedStatement.setInt(3, brokerModel.getBrokerId());
           condition = preparedStatement.execute();
       }catch(Exception e)
       {
           e.printStackTrace();
       }
       return condition;
    }

    @Override
    public BrokerModel getBrokerById(Integer brokerId) {
        BrokerModel brokerModel = null;
        try{
            query = "SELECT b.`broker_id` AS 'Broker ID',b.`name` AS 'Broker Name',b.`contact` AS 'Contact',b.`broker_type` AS 'Broker Type',\n" +
                    " e.`name` AS 'Created By',e.`emp_id` AS 'Created By ID',b.`created_date` AS 'Created Date',em.`name` AS 'Modified By',em.`emp_id` AS 'Modified By ID',b.`modified_date` AS 'Modified Date'\n" +
                    " FROM broker b INNER JOIN employees e\n" +
                    " ON e.`emp_id` = b.`created_by` INNER JOIN employees em ON em.`emp_id` = b.`modified_by`\n" +
                    " WHERE b.`broker_id`=? AND b.`active` = 1 AND e.`active`=1 AND em.`active`=1;";
            preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, brokerId);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next())
            {
                brokerModel = new BrokerModel();
                EmployeesModel createdByEmployeeModel = new EmployeesModel();
                EmployeesModel modifiedByEmployeeModel = new EmployeesModel();
                brokerModel.setBrokerId(resultSet.getInt("Broker ID"));
                brokerModel.setBrokerName(resultSet.getString("Broker Name"));
                brokerModel.setBrokerContact(resultSet.getString("Contact"));
                brokerModel.setBrokerType(resultSet.getString("Broker Type"));
                createdByEmployeeModel.setName(resultSet.getString("Created By"));
                createdByEmployeeModel.setEmployeeId(resultSet.getInt("Created By ID"));
                modifiedByEmployeeModel.setEmployeeId(resultSet.getInt("Modified By ID"));
                modifiedByEmployeeModel.setName(resultSet.getString("Modified By"));
                brokerModel.setCreatedBy(createdByEmployeeModel);
                brokerModel.setCreatedDate(resultSet.getTimestamp("Created Date"));
                brokerModel.setModifiedBy(modifiedByEmployeeModel);
                brokerModel.setModifiedDate(resultSet.getTimestamp("Modified Date"));
            }
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        return brokerModel;
    }

    @Override
    public ResultSet viewAllBrokers() {
        try{
            query = "SELECT b.`broker_id` AS 'Broker ID',b.`name` AS 'Broker Name',b.`contact` AS 'Contact',b.`broker_type` AS 'Broker Type' FROM broker b where b.`active`=1;";
            preparedStatement = conn.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        return resultSet;
    }

    public BrokerModel getBrokerByName(String brokerName) {
        BrokerModel brokerModel = null;
        try{
            query = "SELECT b.`broker_id` AS 'Broker ID',b.`name` AS 'Broker Name',b.`contact` AS 'Contact',b.`broker_type` AS 'Broker Type',\n" +
                    " e.`name` AS 'Created By',e.`emp_id` AS 'Created By ID',b.`created_date` AS 'Created Date',em.`name` AS 'Modified By',em.`emp_id` AS 'Modified By ID',b.`modified_date` AS 'Modified Date'\n" +
                    " FROM broker b INNER JOIN employees e\n" +
                    " ON e.`emp_id` = b.`created_by` INNER JOIN employees em ON em.`emp_id` = b.`modified_by`\n" +
                    " WHERE b.`name`=? AND b.`active` = 1 AND e.`active`=1 AND em.`active`=1;";
            preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, brokerName);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next())
            {
                brokerModel = new BrokerModel();
                EmployeesModel createdByEmployeeModel = new EmployeesModel();
                EmployeesModel modifiedByEmployeeModel = new EmployeesModel();
                brokerModel.setBrokerId(resultSet.getInt("Broker ID"));
                brokerModel.setBrokerName(resultSet.getString("Broker Name"));
                brokerModel.setBrokerContact(resultSet.getString("Contact"));
                brokerModel.setBrokerType(resultSet.getString("Broker Type"));
                createdByEmployeeModel.setName(resultSet.getString("Created By"));
                createdByEmployeeModel.setEmployeeId(resultSet.getInt("Created By ID"));
                modifiedByEmployeeModel.setEmployeeId(resultSet.getInt("Modified By ID"));
                modifiedByEmployeeModel.setName(resultSet.getString("Modified By"));
                brokerModel.setCreatedBy(createdByEmployeeModel);
                brokerModel.setCreatedDate(resultSet.getTimestamp("Created Date"));
                brokerModel.setModifiedBy(modifiedByEmployeeModel);
                brokerModel.setModifiedDate(resultSet.getTimestamp("Modified Date"));
            }
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        return brokerModel;
    }
    
}
