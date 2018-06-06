
package models;


public class SellDetailModel  extends UtilityModel{
    private Integer sellDetailId;
    private SellModel sellModel;
    private StoreModel storeModel;
    private ProductModel productModel;
    private Integer quaintity;
    private Double price;

    public Integer getSellDetailId() {
        return sellDetailId;
    }

    public void setSellDetailId(Integer sellDetailId) {
        this.sellDetailId = sellDetailId;
    }

    public SellModel getSellModel() {
        return sellModel;
    }

    public void setSellModel(SellModel sellModel) {
        this.sellModel = sellModel;
    }

    public StoreModel getStoreModel() {
        return storeModel;
    }

    public void setStoreModel(StoreModel storeModel) {
        this.storeModel = storeModel;
    }

    public ProductModel getProductModel() {
        return productModel;
    }

    public void setProductModel(ProductModel productModel) {
        this.productModel = productModel;
    }

    public Integer getQuaintity() {
        return quaintity;
    }

    public void setQuaintity(Integer quaintity) {
        this.quaintity = quaintity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }    
}
