/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daoimpl;

import connection.DBConnection;
import dao.ProductDao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import models.ProductModel;
/**
 *
 * @author Intel
 */
public class ProductDaoImpl implements ProductDao{
     
   PreparedStatement pst;
    ResultSet rs;
    @Override
    public ResultSet getAllProducts() {
        try {
            Connection con = DBConnection.getInstance();
            pst = con.prepareStatement("select product_id , name as 'Product Name' from"
                    + " product where active = 1");
            rs = pst.executeQuery();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return rs;
    }
    
    @Override
    public int insertNewProduct(ProductModel pm){
        try{
            Connection con = DBConnection.getInstance();
            pst = con.prepareStatement("insert into product( name , "
                    + "created_by , created_date) select ? , emp_id , now() "
                    + "from employees where name = ?;");
            pst.setString(1, pm.getProductName());
            pst.setString(2, pm.getCreatedBy().getUsername());
            return pst.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }
        return 0;
    }
    @Override
    public ProductModel getProductByProductId(int productId){
        ProductModel productModel = null;
        try{
            Connection con = DBConnection.getInstance();
            pst = con.prepareStatement("Select * from product where product_id = ? "
                    + "and active = 1;");
            pst.setInt(1 , productId);
            rs = pst.executeQuery();
            if(rs.next()){
                productModel = new ProductModel();
                productModel.setProductId(rs.getInt("product_id"));
                productModel.setProductName(rs.getString("name"));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return productModel;
    }
    @Override
    public boolean checkProductBeforeInsertion(String productName){
        try{
            Connection con = DBConnection.getInstance();
            pst = con.prepareStatement("Select * from product where name = ? "
                    + "and active = 1;");
            pst.setString(1 , productName);
            rs = pst.executeQuery();
            if(rs.next()){
                return true;
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }
    
    @Override
    public int updateProduct(ProductModel productModel){
        try{
            Connection con = DBConnection.getInstance();
            pst = con.prepareStatement("update product p join employees e \n" +
"                    set p.name = ? ,p.modified_by = e.emp_id  , "
                    + "p.modified_date = now() where p.product_id = ? and "
                    + "e.name= ?;");
            pst.setString(1 , productModel.getProductName());
            pst.setInt(2, productModel.getProductId());
            pst.setString(3 , productModel.getModifiedBy().getUsername());
            return pst.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }
        return 0;
    }
    @Override
    public int deleteProduct(int productId){
        try{
            Connection con = DBConnection.getInstance();
            pst = con.prepareStatement("update product set active = 0 where product_id = ?");
            pst.setInt(1, productId);
            
            return pst.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public ProductModel getProductByProductName(String productName) {
        ProductModel productModel = null;
        try{
            Connection con = DBConnection.getInstance();
            pst = con.prepareStatement("Select * from product where name = ? "
                    + "and active = 1;");
            pst.setString(1 , productName);
            rs = pst.executeQuery();
            if(rs.next()){
                productModel = new ProductModel();
                productModel.setProductId(rs.getInt("product_id"));
                productModel.setProductName(rs.getString("name"));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return productModel;
    }
}
