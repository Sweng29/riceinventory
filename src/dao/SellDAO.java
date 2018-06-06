/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.ResultSet;
import models.SellModel;

/**
 *
 * @author Lenovo
 */
public interface SellDAO {

   public Integer addSellRecord(SellModel sellModel);
   public Integer updateSellRecord(SellModel sellModel);
   public Integer deleteSellRecord(SellModel sellModel);
   public SellModel getSellRecordById(Integer sellId);
   public ResultSet viewAllSellRecords();
   public Integer updateSellRecordBySellId(SellModel sellModel);
}
