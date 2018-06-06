
package models;


public class SellDetailsModel  extends UtilityModel{
 private int sellDetailId;
 private SellModel sellModel;

    public int getSellDetailId() {
        return sellDetailId;
    }

    public void setSellDetailId(int sellDetailId) {
        this.sellDetailId = sellDetailId;
    }

    public SellModel getSellModel() {
        return sellModel;
    }

    public void setSellModel(SellModel sellModel) {
        this.sellModel = sellModel;
    }

    public ProductModel getProductModel() {
        return productModel;
    }

    public void setProductModel(ProductModel productModel) {
        this.productModel = productModel;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
 private ProductModel productModel;
 private int quantity;
 private Double price;

    
}
