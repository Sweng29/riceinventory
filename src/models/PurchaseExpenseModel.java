
package models;


public class PurchaseExpenseModel  extends UtilityModel{
private int expenseId;
private PurchaseModel purchaseModel;
private SellModel sellModel;
private BankModel bank;
private String paymentType;
private Integer transportExpense;
private Integer labourExpense;
private Integer other;
private Integer brokerPaidAmount;
private Integer brokerComission;
private String comment;

    public BankModel getBank() {
        return bank;
    }

    public void setBank(BankModel bank) {
        this.bank = bank;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getExpenseId() {
        return expenseId;
    }

    public void setExpenseId(int expenseId) {
        this.expenseId = expenseId;
    }

    public PurchaseModel getPurchaseModel() {
        return purchaseModel;
    }

    public void setPurchaseModel(PurchaseModel purchaseModel) {
        this.purchaseModel = purchaseModel;
    }

    public SellModel getSellModel() {
        return sellModel;
    }

    public void setSellModel(SellModel sellModel) {
        this.sellModel = sellModel;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public Integer getTransportExpense() {
        return transportExpense;
    }

    public void setTransportExpense(Integer transportExpense) {
        this.transportExpense = transportExpense;
    }

    public Integer getLabourExpense() {
        return labourExpense;
    }

    public void setLabourExpense(Integer labourExpense) {
        this.labourExpense = labourExpense;
    }

    public Integer getOther() {
        return other;
    }

    public void setOther(Integer other) {
        this.other = other;
    }

    public Integer getBrokerPaidAmount() {
        return brokerPaidAmount;
    }

    public void setBrokerPaidAmount(Integer brokerPaidAmount) {
        this.brokerPaidAmount = brokerPaidAmount;
    }

    public Integer getBrokerComission() {
        return brokerComission;
    }

    public void setBrokerComission(Integer brokerComission) {
        this.brokerComission = brokerComission;
    }
}
