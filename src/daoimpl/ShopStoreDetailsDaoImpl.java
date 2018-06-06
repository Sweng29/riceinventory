package daoimpl;

import business.LoginFrame;
import connection.DBConnection;
import dao.ShopStoreDetailsDao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.ShopStoreDetailModel;

/**
 *
 * @author Asif Ali
 */
public class ShopStoreDetailsDaoImpl implements ShopStoreDetailsDao {

    @Override
    public int addShopStoreDetails(ShopStoreDetailModel shopStoreDetailModel) {

        Connection conn = DBConnection.getInstance();
        String query = "INSERT INTO shop_store_detail (product_id,from_store,to_store,quantity,DATE,comments,created_by,created_date,modified_by,modified_date,active) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
        int row = 0;
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, shopStoreDetailModel.getProductModel().getProductId());
            preparedStatement.setInt(2, shopStoreDetailModel.getFromStore().getStoreId());
            preparedStatement.setInt(3, shopStoreDetailModel.getToStore().getStoreId());
            preparedStatement.setInt(4, shopStoreDetailModel.getQuantity());
            preparedStatement.setTimestamp(5, shopStoreDetailModel.getDate());
            preparedStatement.setString(6, shopStoreDetailModel.getComments());
            preparedStatement.setInt(7, LoginFrame.employeesModel.getEmployeeId());
            preparedStatement.setTimestamp(8, shopStoreDetailModel.getCreatedDate());
            preparedStatement.setInt(9, LoginFrame.employeesModel.getEmployeeId());
            preparedStatement.setTimestamp(10, shopStoreDetailModel.getModifiedDate());
            preparedStatement.setInt(11, 1);
            row = preparedStatement.executeUpdate();
        } catch (Exception ex) {
            System.out.println(ex);
        }

        return row;
    }

}
