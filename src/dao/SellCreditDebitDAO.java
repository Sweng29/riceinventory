/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.ResultSet;
import models.SellCreditDebitModel;

/**
 *
 * @author Lenovo
 */
public interface SellCreditDebitDAO {
    public Integer addSellCreditDebit(SellCreditDebitModel sellCreditDebitModel);
    public Integer updateSellCreditDebit(SellCreditDebitModel sellCreditDebitModel);
    public Integer deleteSellCreditDebit(SellCreditDebitModel sellCreditDebitModel);
    public SellCreditDebitModel getSellCreditDebitById(Integer sellCreditDebitId);
    public ResultSet viewAllSellCreditDebit();
    public ResultSet viewAllSellCreditDebitBySellId(Integer sellId);
    public ResultSet viewAllTotalAmountBySellId(Integer sellId);
    public SellCreditDebitModel checkAvailabilty(SellCreditDebitModel sellCreditDebitModel);
}
