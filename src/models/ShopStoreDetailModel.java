/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.sql.Timestamp;

/**
 *
 * @author Asif Ali
 */
public class ShopStoreDetailModel extends UtilityModel {

    public ProductModel getProductModel() {
        return productModel;
    }

    public void setProductModel(ProductModel productModel) {
        this.productModel = productModel;
    }

    public StoreModel getFromStore() {
        return fromStore;
    }

    public void setFromStore(StoreModel fromStore) {
        this.fromStore = fromStore;
    }

    public StoreModel getToStore() {
        return toStore;
    }

    public void setToStore(StoreModel toStore) {
        this.toStore = toStore;
    }
    private int shopStoreDetailId;
    private StoreDetailsModel storeDetailsModel;
    private Timestamp date;
    private int quantity;
    private String comments;
    private ProductModel productModel;
    private StoreModel fromStore;
    private StoreModel toStore;
    public int getShopStoreDetailId() {
        return shopStoreDetailId;
    }

    public void setShopStoreDetailId(int shopStoreDetailId) {
        this.shopStoreDetailId = shopStoreDetailId;
    }

    public StoreDetailsModel getStoreDetailsModel() {
        return storeDetailsModel;
    }

    public void setStoreDetailsModel(StoreDetailsModel storeDetailsModel) {
        this.storeDetailsModel = storeDetailsModel;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
    
}
