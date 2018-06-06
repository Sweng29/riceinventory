
package models;


public class BrokerModel extends UtilityModel {
private Integer brokerId;
private String brokerName;
private String brokerContact;
private String brokerType;

    public Integer getBrokerId() {
        return brokerId;
    }

    public void setBrokerId(Integer brokerId) {
        this.brokerId = brokerId;
    }

    public String getBrokerName() {
        return brokerName;
    }

    public void setBrokerName(String brokerName) {
        this.brokerName = brokerName;
    }

    public String getBrokerContact() {
        return brokerContact;
    }

    public void setBrokerContact(String brokerContact) {
        this.brokerContact = brokerContact;
    }

    public String getBrokerType() {
        return brokerType;
    }

    public void setBrokerType(String brokerType) {
        this.brokerType = brokerType;
    }


    
}
