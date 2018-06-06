/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.sql.Timestamp;

/**
 *
 * @author Asif Ali
 */
public class SellCreditDebitModel  extends UtilityModel{
    private int sellCreditDebitId;
    private BankModel bankModel;
    private SellModel sellModel;
    private Double amountPaid;
    private String paymentType;
    private Timestamp date;
    private String comments;

    public int getSellCreditDebitId() {
        return sellCreditDebitId;
    }

    public void setSellCreditDebitId(int sellCreditDebitId) {
        this.sellCreditDebitId = sellCreditDebitId;
    }

    public BankModel getBankModel() {
        return bankModel;
    }

    public void setBankModel(BankModel bankModel) {
        this.bankModel = bankModel;
    }

    public SellModel getSellModel() {
        return sellModel;
    }

    public void setSellModel(SellModel sellModel) {
        this.sellModel = sellModel;
    }

    public Double getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(Double amountPaid) {
        this.amountPaid = amountPaid;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }
    
}
