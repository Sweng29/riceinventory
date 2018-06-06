/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.ResultSet;
import models.BrokerModel;

/**
 *
 * @author Lenovo
 */
public interface BrokerDAO {
    
    public boolean addBroker(BrokerModel brokerModel);
    public boolean updateBroker(BrokerModel brokerModel);
    public boolean deleteBrokerById(BrokerModel brokerModel);
    public BrokerModel getBrokerById(Integer brokerId);
    public BrokerModel getBrokerByName(String brokerName);
    public ResultSet viewAllBrokers();
}
