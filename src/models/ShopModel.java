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
public class ShopModel extends UtilityModel {
    private int shopId;
    private String name;
    private String shopQty;

    public int getShopId() {
        return shopId;
    }

    public void setShopId(int shopId) {
        this.shopId = shopId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShopQty() {
        return shopQty;
    }

    public void setShopQty(String shopQty) {
        this.shopQty = shopQty;
    }
    
}
