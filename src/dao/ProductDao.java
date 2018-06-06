/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;
import java.util.List;
import models.ProductModel;
import java.sql.ResultSet;
/**
 *
 * @author Intel
 */
public interface ProductDao {
    public  ResultSet getAllProducts();
    public int insertNewProduct(ProductModel pm);
    public ProductModel getProductByProductId(int productId);
    public boolean checkProductBeforeInsertion(String productName);
    public int updateProduct(ProductModel productModel);
    public int deleteProduct(int productId);
    public ProductModel getProductByProductName(String productName);
}
