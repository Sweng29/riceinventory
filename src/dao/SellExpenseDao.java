/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.ResultSet;
import models.PurchaseExpenseModel;
import models.PurchaseModel;
import models.SellModel;

/**
 *
 * @author Rehan Ali Azeemi
 */
public interface SellExpenseDao {
    public int insertPurchaseExpense(PurchaseExpenseModel purchaseExpenseModel);
    public int insertPurchaseExpenseWithoutBank(PurchaseExpenseModel purchaseExpenseModel);
    public ResultSet getAllPurchaseExpense(SellModel sellModel);
    public int deletePurchaseExpense(PurchaseExpenseModel purchaseExpenseModel);
}
