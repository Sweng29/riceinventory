/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.ResultSet;
import models.PurchaseExpenseModel;
import models.PurchaseModel;

/**
 *
 * @author Rehan Ali Azeemi
 */
public interface PurchaseExpenseDao {
    public int insertPurchaseExpense(PurchaseExpenseModel purchaseExpenseModel);
    public int insertPurchaseExpenseWithoutBank(PurchaseExpenseModel purchaseExpenseModel);
    public ResultSet getAllPurchaseExpense(PurchaseModel purchaseModel);
    public int deletePurchaseExpense(PurchaseExpenseModel purchaseExpenseModel);
}
