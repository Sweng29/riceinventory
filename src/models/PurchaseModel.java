
package models;

import java.sql.Timestamp;



public class PurchaseModel  extends UtilityModel{

private int purchaseId;
private Timestamp purchaseDate;
private String builtyNo;

    public int getPurchaseId() {
        return purchaseId;
    }

    public void setPurchaseId(int purchaseId) {
        this.purchaseId = purchaseId;
    }

    public Timestamp getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Timestamp purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public String getBuiltyNo() {
        return builtyNo;
    }

    public void setBuiltyNo(String builtyNo) {
        this.builtyNo = builtyNo;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Timestamp getPaymentConditionDate() {
        return paymentConditionDate;
    }

    public void setPaymentConditionDate(Timestamp paymentConditionDate) {
        this.paymentConditionDate = paymentConditionDate;
    }

    public String getPaymentCondition() {
        return paymentCondition;
    }

    public void setPaymentCondition(String paymentCondition) {
        this.paymentCondition = paymentCondition;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public CompanyModel getCompanyModel() {
        return companyModel;
    }

    public void setCompanyModel(CompanyModel companyModel) {
        this.companyModel = companyModel;
    }

    public BrokerModel getBrokerModel() {
        return brokerModel;
    }

    public void setBrokerModel(BrokerModel brokerModel) {
        this.brokerModel = brokerModel;
    }
private Double totalAmount;
private Timestamp paymentConditionDate;
private String paymentCondition;
private String note;
private CompanyModel companyModel;
private BrokerModel brokerModel;


    
}
