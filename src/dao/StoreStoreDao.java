/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.ResultSet;

/**
 *
 * @author Asif Ali
 */
public interface StoreStoreDao {
    
    public ResultSet getStores();
    public ResultSet getProductsOfStore(int storeId);
    public int getStoreQuantity( int storeId , int productId);
}
