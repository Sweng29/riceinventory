
package models;


public class PurchaseDetailsModel  extends UtilityModel{

private int purchaseId;
private PurchaseModel purchaseModel;
private ProductModel productModel;

    public int getPurchaseId() {
        return purchaseId;
    }

    public void setPurchaseId(int purchaseId) {
        this.purchaseId = purchaseId;
    }

    public PurchaseModel getPurchaseModel() {
        return purchaseModel;
    }

    public void setPurchaseModel(PurchaseModel purchaseModel) {
        this.purchaseModel = purchaseModel;
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

    public StoreModel getStoreModel() {
        return storeModel;
    }

    public void setStoreModel(StoreModel storeModel) {
        this.storeModel = storeModel;
    }
private int quantity;
private Double price;
private StoreModel storeModel;

    
}
