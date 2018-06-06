/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.ResultSet;
import models.CompanyModel;
import models.StoreDetailsModel;

/**
 *
 * @author Lenovo
 */
public interface StoreDetailDAO {
    
    public boolean addStoreDetail(StoreDetailsModel storeDetailsModel);
    public boolean updateStoreDetail(StoreDetailsModel storeDetailsModel);
    public boolean deleteStoreDetailById(StoreDetailsModel storeDetailsModel);
    public StoreDetailsModel getStoreDetailById(Integer storeDetailId);
    public ResultSet viewStoreDetailById(Integer storeId);
    public ResultSet viewAllStoreDetails(); 
    public StoreDetailsModel checkProductStoreAvailabilty(StoreDetailsModel storeDetailsModel);
    public boolean updateAvailableQuainty(StoreDetailsModel storeDetailsModel);
    public ResultSet viewStoreDetailByProductId(Integer productId);
}
