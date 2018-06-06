/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.sql.Timestamp;

/**
 *
 * @author Rehan Ali Azeemi
 */
public class UtilityModel {
    private EmployeesModel createdBy;
    private EmployeesModel modifiedBy;
    private Timestamp createdDate;
    private Timestamp modifiedDate;
    private int active;

    public EmployeesModel getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(EmployeesModel createdBy) {
        this.createdBy = createdBy;
    }

    public EmployeesModel getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(EmployeesModel modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    public Timestamp getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Timestamp modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }
}
