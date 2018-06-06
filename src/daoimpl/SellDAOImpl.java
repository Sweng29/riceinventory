/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daoimpl;

import business.LoginFrame;
import connection.DBConnection;
import dao.SellDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import models.BrokerModel;
import models.CompanyModel;
import models.SellModel;
import models.StoreModel;

/**
 *
 * @author Lenovo
 */
public class SellDAOImpl implements SellDAO{

    ResultSet resultSet;
    PreparedStatement preparedStatement;
    Connection conn = DBConnection.getInstance();
    String query="";
    
    @Override
    public Integer addSellRecord(SellModel sellModel) {
        Integer condition = 0;
        try{
            query = "INSERT INTO sell(`broker_id`,`company_id`,`builty_no`,`date`,`note`,`created_by`,`created_date`,`modified_by`,`modified_date`)\n" +
                    " VALUES(?,?,?,?,?,?,?,?,?);";
            preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, sellModel.getBrokerModel().getBrokerId());
            preparedStatement.setInt(2, sellModel.getCompanyModel().getCompanyId());
            preparedStatement.setString(3, sellModel.getBuiltyNo());
            preparedStatement.setTimestamp(4, sellModel.getDate());
            preparedStatement.setString(5, sellModel.getNote());
            preparedStatement.setInt(6, LoginFrame.employeesModel.getEmployeeId());
            preparedStatement.setTimestamp(7, sellModel.getCreatedDate());
            preparedStatement.setInt(8, LoginFrame.employeesModel.getEmployeeId());
            preparedStatement.setTimestamp(9, sellModel.getModifiedDate());
            condition = preparedStatement.executeUpdate();
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        return condition;
    }

    @Override
    public Integer updateSellRecord(SellModel sellModel) {
        Integer condition = 0;
        try{
            query = "UPDATE sell SET `broker_id`=?,`company_id`=?,`builty_no`=?,`note`=?,`date`=?,`modified_by`=?,`modified_date`=? WHERE `sell_id`=?";
            preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, sellModel.getBrokerModel().getBrokerId());
            preparedStatement.setInt(2, sellModel.getCompanyModel().getCompanyId());
            preparedStatement.setString(3, sellModel.getBuiltyNo());
            preparedStatement.setString(4, sellModel.getNote());
            preparedStatement.setTimestamp(5, sellModel.getDate());
            preparedStatement.setInt(6, LoginFrame.employeesModel.getEmployeeId());
            preparedStatement.setTimestamp(7, sellModel.getModifiedDate());
            preparedStatement.setInt(8, sellModel.getSellId());
            condition = preparedStatement.executeUpdate();
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        return condition;
    }

    @Override
    public Integer deleteSellRecord(SellModel sellModel) {
        Integer condition = 0;
        try{
            query = "UPDATE sell SET active=0,modified_by=?,modified_date=? WHERE `sell_id`=?";
            preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, LoginFrame.employeesModel.getEmployeeId());
            preparedStatement.setTimestamp(2, sellModel.getModifiedDate());
            preparedStatement.setInt(3, sellModel.getSellId());
            condition = preparedStatement.executeUpdate();
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        return condition;
    }

    @Override
    public SellModel getSellRecordById(Integer sellId) {
        SellModel sellModel = null;
        try{
            query = "SELECT s.`sell_id` AS 'Sell ID',b.`name` AS 'Broker Name',c.`name` AS 'Company Name',s.`builty_no` as 'Builty Number', "
                    + "s.`total_amount` AS 'Total Amount',s.`date` AS 'Sell Date',s.`note` as 'Note' " +
                    "FROM sell s INNER JOIN broker b ON s.`broker_id` = b.`broker_id` " +
                    "INNER JOIN `company` c ON s.`company_id` = c.`company_id` " +
                    "WHERE s.`sell_id`=? AND s.`active`=1 AND c.`active`=1 AND b.`active`=1";
            preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, sellId);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next())
            {
                sellModel = new SellModel();
                BrokerModel brokerModel = new BrokerModel();
                CompanyModel companyModel = new CompanyModel();
                sellModel.setSellId(resultSet.getInt("Sell ID"));
                brokerModel.setBrokerName(resultSet.getString("Broker Name"));
                companyModel.setCompanyName(resultSet.getString("Company Name"));
                sellModel.setTotalAmount(resultSet.getDouble("Total Amount"));
                sellModel.setBuiltyNo(resultSet.getString("Builty Number"));
                sellModel.setDate(resultSet.getTimestamp("Sell Date"));
                sellModel.setNote(resultSet.getString("Note"));
                sellModel.setBrokerModel(brokerModel);
                sellModel.setCompanyModel(companyModel);
            }
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        return sellModel;
    }

    @Override
    public ResultSet viewAllSellRecords() {
        try{
            query = "SELECT s.`sell_id` AS 'Sell ID',b.`name` AS 'Broker Name',c.`name` AS 'Company Name', s.`builty_no` as 'Builty No', "
                    + "s.`total_amount` AS 'Total Amount',s.`date` AS 'Sell Date',s.`note` as 'Note' " +
                    "FROM sell s INNER JOIN broker b ON s.`broker_id` = b.`broker_id` " +
                    "INNER JOIN `company` c ON s.`company_id` = c.`company_id` " +
                    "WHERE s.`active`=1 AND c.`active`=1 AND b.`active`=1";
            preparedStatement = conn.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        return resultSet;
    }

    @Override
    public Integer updateSellRecordBySellId(SellModel sellModel) {
        Integer condition = 0;
        try{
            query = "UPDATE sell SET total_amount=?,`modified_by`=?,`modified_date`=? WHERE `sell_id`=?";
            preparedStatement = conn.prepareStatement(query);
            preparedStatement.setDouble(1, sellModel.getTotalAmount());
            preparedStatement.setInt(2, LoginFrame.employeesModel.getEmployeeId());
            preparedStatement.setTimestamp(3, sellModel.getModifiedDate());
            preparedStatement.setInt(4, sellModel.getSellId());
            condition = preparedStatement.executeUpdate();
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        return condition;
    }
}
