/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daoimpl;

import connection.DBConnection;
import dao.BankDao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.BankModel;

/**
 *
 * @author Intel
 */
public class BankDaoImpl implements BankDao{
    private Connection con = DBConnection.getInstance();
    private PreparedStatement pst;
    private ResultSet rs;

    
    @Override
    public ResultSet getAllAccounts() {
        
        try {
            pst = con.prepareStatement("select bank_id , acc_title as 'Account Title' , "
                    + "acc_no as 'Account No' , bank_name as 'Bank Name' , "
                    + "branch_name as 'Branch Name' , balance as 'Balance Available' "
                    + "from bank where active = 1;");
            rs = pst.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(BankDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
        
    }

    @Override
    public int insertNewAccount(BankModel bankModel) {
        try {
            pst = con.prepareStatement("insert into bank(bank_name , branch_name , "
                    + "acc_title , acc_no , balance , created_by , created_date) "
                    + "select ? , ? , ? , ? , ? , emp_id , now() from employees "
                    + "where name = ?");
            pst.setString(1, bankModel.getBankName());
            pst.setString(2 , bankModel.getBranchName());
            pst.setString(3 , bankModel.getAccountTitle());
            pst.setString(4 , bankModel.getAccountNo());
            pst.setDouble(5, bankModel.getBalance());
            pst.setString(6 , bankModel.getCreatedBy().getName());
            
            return pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(BankDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    @Override
    public int updateAccount(BankModel bankModel) {
        try {
            pst = con.prepareStatement("update Bank b join employees e set "
                    + "b.bank_name = ? , b.branch_name = ? , b.acc_title = ?"
                    + " , b.acc_no = ? , b.balance = ? , b.modified_by = e.emp_id ,"
                    + "b.modified_date = now() where e.name = ? and b.bank_id = ?");
            
            pst.setString(1, bankModel.getBankName());
            pst.setString(2 , bankModel.getBranchName());
            pst.setString(3 , bankModel.getAccountTitle());
            pst.setString(4 , bankModel.getAccountNo());
            pst.setDouble(5, bankModel.getBalance());
            pst.setString(6 , bankModel.getModifiedBy().getName());
            pst.setInt(7 , bankModel.getBankId());
            return pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(BankDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    @Override
    public int deleteAccount(int bankId) {
        try {
            pst = con.prepareStatement("update bank set active = 0 where bank_id = ?;");
            pst.setInt(1 , bankId);
            return pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(BankDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    @Override
    public boolean checkAccountNo(String accNo) {
        try {
            pst =  con.prepareStatement("select * from bank where acc_no = ? "
                    + "and active = 1;");
            pst.setString(1 ,  accNo);
            rs = pst.executeQuery();
            if(rs.next()){
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(BankDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public BankModel getBankDetailsById(Integer bankId) {
        BankModel bankModel = null;
        try {
            pst = con.prepareStatement("select bank_id as 'Bank ID', acc_title as 'Account Title' , "
                    + "acc_no as 'Account No' , bank_name as 'Bank Name' , "
                    + "branch_name as 'Branch Name' , balance as 'Balance Available' "
                    + "from bank where bank_id=? AND active = 1;");
            pst.setInt(1, bankId);
            rs = pst.executeQuery();
            while(rs.next())
            {
                bankModel = new BankModel();
                bankModel.setBankId(rs.getInt("Bank ID"));
                bankModel.setBankName(rs.getString("Bank Name"));
                bankModel.setBranchName(rs.getString("Branch Name"));
                bankModel.setAccountNo(rs.getString("Account No"));
                bankModel.setAccountTitle(rs.getString("Account Title"));
                bankModel.setBalance(rs.getDouble("Balance Available"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(BankDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bankModel;
    }

    public ResultSet getAllAccountTitleAndAccNo() {
          try {
            pst = con.prepareStatement("select bank_id , acc_title as 'Account Title' , "
                    + "acc_no as 'Account No' from bank where active = 1;");
            rs = pst.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(BankDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }
    
}
