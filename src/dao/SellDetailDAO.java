/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.ResultSet;
import models.SellDetailModel;

/**
 *
 * @author Lenovo
 */
public interface SellDetailDAO {
    public Integer addSellDetail(SellDetailModel sellDetailModel);
    public Integer updateSellDetail(SellDetailModel sellDetailModel);
    public Integer deleteSellDetail(SellDetailModel sellDetailModel);
    public SellDetailModel getSellDetailById(Integer sellDetailId);
    public ResultSet viewSellDetails();
    public ResultSet getSellDetailBySellId(Integer sellId);
    public Double getTotalAmountBySellId(Integer sellId);
    public SellDetailModel checkQuantityAvailabiltyBySellDetailId(SellDetailModel sellDetailModel);
}
