/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.ResultSet;
import java.util.List;
import models.PurchaseModel;

/**
 *
 * @author Intel
 */
public interface PurchaseDao {
    public List<String> getAllCompaniesForCombos();
    public List<String> getAllBrokerNamesForCombos();
    public ResultSet getAllPurchases();
    public int insertNewPurchase(PurchaseModel purchaseModel);
    public int updatePurcahse(PurchaseModel purchaseModel);
    public int updateTotalAmount(int purchaseId);
    public int deletePurchaseRecord(int purchaseId);
}
