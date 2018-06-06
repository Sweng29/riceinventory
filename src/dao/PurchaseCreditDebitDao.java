/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.ResultSet;
import java.util.List;
import models.PurchaseCreditDebitModel;
/**
 *
 * @author M.UMAIR
 */
public interface PurchaseCreditDebitDao {
    public ResultSet getPurchaseCreditDebitBYPurchaseId(int purchaseId);
    public int addPurchaseCreditDebit(PurchaseCreditDebitModel creditDebitModel);
    public int updatePurchaseCreditDebit(PurchaseCreditDebitModel creditDebitModel);
    public int deletePurchaseCreditDebit(int pcdId , int empId);
    public ResultSet getSummaryPurchaseCredit(int purchaseId);
    public int changeBalance(int bankId , double balance);
    public int changeBalanceForDelete(int bankId , double balance);
}
