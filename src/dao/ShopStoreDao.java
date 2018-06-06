
package dao;

import java.sql.ResultSet;
import java.util.List;
import models.ProductModel;
import models.StoreDetailsModel;


public interface ShopStoreDao {

public ResultSet getAllProducts();
public ResultSet getAllStores(Integer productId);
public ResultSet getAllShopeDetails();
public boolean checkProductAvailablity(Integer productId);
public int insertProductsInToShops(StoreDetailsModel storeDetailsModels);
public int updateExistingProductQuantity(StoreDetailsModel storeDetailsModel);
public int deleteShopDetail(int productId);
public int updateShopQuantity(StoreDetailsModel storeDetailsModel);
public ResultSet getAllStoreData();
public boolean checkProductInStoreAvailablity(Integer productId , Integer storeId);
}
