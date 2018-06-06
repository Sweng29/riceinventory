/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.ResultSet;
import java.sql.Timestamp;

/**
 *
 * @author Intel
 */
public interface ProfitLossDao {
    public ResultSet getPurchase(Timestamp startDate , Timestamp endDate);
    public ResultSet getSell(Timestamp startDate , Timestamp endDate);
    public ResultSet getPurchaseSumary(Timestamp startDate , Timestamp endDate);
    public ResultSet getSellSumary(Timestamp startDate , Timestamp endDate);
    public ResultSet getPurchaseExpense(Timestamp startDate , Timestamp endDate);
    public ResultSet getSellExpense(Timestamp startDate , Timestamp endDate);
    
}
