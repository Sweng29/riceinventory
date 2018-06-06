/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author Asif Ali
 */
public class SellExpenseModel extends UtilityModel {
    private int sellExpenseId;
    private SellModel sellModel;
    private Double transportExpense;
    private Double labourExpense;
    private Double other;
    private Double brokerComission;
    private String comments;

    public int getSellExpenseId() {
        return sellExpenseId;
    }

    public void setSellExpenseId(int sellExpenseId) {
        this.sellExpenseId = sellExpenseId;
    }

    public SellModel getSellModel() {
        return sellModel;
    }

    public void setSellModel(SellModel sellModel) {
        this.sellModel = sellModel;
    }

    public Double getTransportExpense() {
        return transportExpense;
    }

    public void setTransportExpense(Double transportExpense) {
        this.transportExpense = transportExpense;
    }

    public Double getLabourExpense() {
        return labourExpense;
    }

    public void setLabourExpense(Double labourExpense) {
        this.labourExpense = labourExpense;
    }

    public Double getOther() {
        return other;
    }

    public void setOther(Double other) {
        this.other = other;
    }

    public Double getBrokerComission() {
        return brokerComission;
    }

    public void setBrokerComission(Double brokerComission) {
        this.brokerComission = brokerComission;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
    
}
