/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.ResultSet;
import models.BankModel;

/**
 *
 * @author Intel
 */
public interface BankDao {
    public ResultSet getAllAccounts();
    public int insertNewAccount(BankModel bankModel);
    public int updateAccount(BankModel bankModel);
    public int deleteAccount(int bankId);
    public boolean checkAccountNo(String accNo);
    public BankModel getBankDetailsById(Integer bankId);
}
