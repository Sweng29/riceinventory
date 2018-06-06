/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.ResultSet;
import java.util.List;
import models.PurchaseDetailsModel;

/**
 *
 * @author Intel
 */
public interface PurchaseDetailDao {
    public ResultSet getAllPurchaseDetailByPurchaseId(int id);
    public int addDetail(PurchaseDetailsModel purchaseDetailModel);
    public int updateDetail(PurchaseDetailsModel purchaseDetailModel);
    public int deleteDetail(int id);
    public List<String> getProductsForCombo();
    public List<String> getStoresForCombos();
    
}
