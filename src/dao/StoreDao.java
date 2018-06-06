
package dao;

import java.sql.ResultSet;
import models.StoreModel;

public interface StoreDao {

public ResultSet getAllStores();
public int insertStoreDetail(StoreModel storeModel);
public int updateStoreDetail(StoreModel storeModel);
public int deleteStoreDetail(StoreModel storeModel);
public StoreModel getStoreByName(String storeName);
public StoreModel getStoreById(Integer storeId); 
}
