
package models;

import java.sql.Timestamp;




public class SellModel extends UtilityModel {

private Integer sellId;
private BrokerModel brokerModel;
private CompanyModel companyModel;
private String builtyNo;
private Double totalAmount;
private Timestamp date ;
private String note;

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }


    public Integer getSellId() {
        return sellId;
    }

    public void setSellId(Integer sellId) {
        this.sellId = sellId;
    }

    public BrokerModel getBrokerModel() {
        return brokerModel;
    }

    public void setBrokerModel(BrokerModel brokerModel) {
        this.brokerModel = brokerModel;
    }

    public CompanyModel getCompanyModel() {
        return companyModel;
    }

    public void setCompanyModel(CompanyModel companyModel) {
        this.companyModel = companyModel;
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

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }
    
}
