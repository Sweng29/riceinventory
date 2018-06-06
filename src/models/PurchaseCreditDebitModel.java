
package models;

import java.sql.Timestamp;


public class PurchaseCreditDebitModel  extends UtilityModel{

private int purchaseCreditDebitId;
private PurchaseModel purchaseModel;
private BankModel bankModel;
private Double amountPaid;
private Timestamp date;
private String comments;
private String paymentType;

    public int getPurchaseCreditDebitId() {
        return purchaseCreditDebitId;
    }

    public void setPurchaseCreditDebitId(int purchaseCreditDebitId) {
        this.purchaseCreditDebitId = purchaseCreditDebitId;
    }

    public PurchaseModel getPurchaseModel() {
        return purchaseModel;
    }

    public void setPurchaseModel(PurchaseModel purchaseModel) {
        this.purchaseModel = purchaseModel;
    }

    public BankModel getBankModel() {
        return bankModel;
    }

    public void setBankModel(BankModel bankModel) {
        this.bankModel = bankModel;
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
