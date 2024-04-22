package com.monsatorm.demo.service;

import com.monsatorm.demo.model.dbo.Client;
import com.monsatorm.demo.model.dbo.Order;

public interface ClientService {

    public void createClientRequest(Client client);
    public Order returnOrderDetails();


}
