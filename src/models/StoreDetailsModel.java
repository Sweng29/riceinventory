/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author Asif Ali
 */

public class StoreDetailsModel extends UtilityModel {

private int storeDetailId;
private ProductModel productModel;
private StoreModel storeModel;
private int availableQuantity;

    public int getStoreDetailId() {
        return storeDetailId;
    }

    public void setStoreDetailId(int storeDetailId) {
        this.storeDetailId = storeDetailId;
    }

    public ProductModel getProductModel() {
        return productModel;
    }

    public void setProductModel(ProductModel productModel) {
        this.productModel = productModel;
    }

    public StoreModel getStoreModel() {
        return storeModel;
    }

    public void setStoreModel(StoreModel storeModel) {
        this.storeModel = storeModel;
    }

    public int getAvailableQuantity() {
        return availableQuantity;
    }

    public void setAvailableQuantity(int availableQuantity) {
        this.availableQuantity = availableQuantity;
    }

}
