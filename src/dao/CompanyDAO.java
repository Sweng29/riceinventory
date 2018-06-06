/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.ResultSet;
import models.CompanyModel;

/**
 *
 * @author Lenovo
 */
public interface CompanyDAO {
       
    public boolean addCompany(CompanyModel companyModel);
    public boolean updateCompany(CompanyModel companyModel);
    public boolean deleteCompanyById(CompanyModel companyModel);
    public CompanyModel getCompanyById(Integer companyId);
    public CompanyModel getCompanyByName(String companyName);
    public ResultSet viewAllCompanies(); 
}
